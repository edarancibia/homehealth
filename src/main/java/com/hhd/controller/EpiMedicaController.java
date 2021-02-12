package com.hhd.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.AtencionMedica;
import com.hhd.entities.EpicrisisMedica;
import com.hhd.entities.EvEnfermeria;
import com.hhd.entities.EvMedica;
import com.hhd.entities.Ficha;
import com.hhd.entities.IndicacionesAlta;
import com.hhd.entities.Ingreso;
import com.hhd.impl.AtMedicaServiceImpl;
import com.hhd.impl.EpiMedicaServiceImpl;
import com.hhd.impl.FichaServiceImpl;
import com.hhd.impl.IndicacionesServiceImpl;
import com.hhd.impl.IngresoServiceImpl;
import com.hhd.util.PdfGenaratorUtil;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("epicrisis-med")
public class EpiMedicaController {
	
	@Autowired
	public EpiMedicaServiceImpl epiMedService;
	
	@Autowired
	public IndicacionesServiceImpl indicacionesService;
	
	@Autowired
	public IngresoServiceImpl ingresoService;
	
	@Autowired
	public FichaServiceImpl fichaService;
	
	@Autowired
	public AtMedicaServiceImpl atMedService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	private static final Log LOG = LogFactory.getLog(EpiMedicaController.class);
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("epicrisis-med");
		model.addAttribute("idficha", idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEpicrisis(@RequestBody EpicrisisMedica epicrisis){
		EpicrisisMedica epicrisisDb = epiMedService.addEpicrisisMedica(epicrisis);
		return new ResponseEntity<EpicrisisMedica>(epicrisisDb,HttpStatus.OK);
	}
	
	@GetMapping("/get-epicrisis/{idficha}")
	public ResponseEntity<?> getEpecrisisByFicha(@PathVariable int idficha){
		EpicrisisMedica epicrisis = epiMedService.findEpicrisisMedicaByIdFicha(idficha);
		if(epicrisis == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<EpicrisisMedica>(epicrisis,HttpStatus.OK);
		}
	}
	
	@PostMapping("/add-indicaciones")
	public ResponseEntity<?> addEpicrisisIndicaciones(@RequestBody IndicacionesAlta indicaciones){
		IndicacionesAlta indicacionesDb = indicacionesService.addIndicaciones(indicaciones);
		return new ResponseEntity<IndicacionesAlta>(indicacionesDb,HttpStatus.OK);
	}
	
	@GetMapping("/get-epicrisis-indicaciones/{idficha}")
	public ResponseEntity<?> getEpecrisisIndicacionesByFicha(@PathVariable int idficha){
		IndicacionesAlta indicaciones = indicacionesService.findIndicacionesAltaByIdFicha(idficha);
		if(indicaciones == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<IndicacionesAlta>(indicaciones,HttpStatus.OK);
		}
	}
	
	@GetMapping("/get-epicrisis-diag/{idficha}")
	public ResponseEntity<?> getEpecrisisDiagByFicha(@PathVariable int idficha){
		Ingreso diags = ingresoService.findIngresoByIdFicha(idficha);
		if(diags == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Ingreso>(diags,HttpStatus.OK);
		}
	}
	
	//CIERRA FICHA
	@PutMapping("/alta/{idficha}")
	public ResponseEntity<?> altaFicha(@PathVariable int idficha){
		Ficha fichaDb = fichaService.findFichaByIdficha((long) idficha);
		
		if(fichaDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		fichaDb.setEstado(0);
		fichaService.addFicha(fichaDb);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/export/{idficha}")
	HttpEntity<byte[]> createPdf(
            @PathVariable("idficha") String fileName, HttpServletResponse response,
            HttpSession session) throws IOException {


		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/pdf/epi-med.vm", "UTF-8");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		
		int idficha = Integer.parseInt(fileName);
		EpicrisisMedica epi = epiMedService.findEpicrisisMedicaByIdFicha(idficha);
		Map<String, Object> cab = atMedService.getDatosPacientePdf(idficha);
		
		context.put("nombre",cab.get("nombre").toString());
		context.put("rut", cab.get("rut").toString());
		context.put("edad", cab.get("edad").toString());
		context.put("prevision", cab.get("prevision").toString());
		
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		context.put("fecha", date);
		
		context.put("resumen", epi.getResumen().toString());
		context.put("indicaciones", epi.getIndicaciones().toString());
		context.put("diag", epi.getDiagnostico().toString());
		
		context.put("medico", session.getAttribute("username"));
		context.put("rutnum", session.getAttribute("rutusu").toString());
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos = pdfGenaratorUtil.generatePdf(writer.toString());

		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileName.replace(" ", "_"));
	    header.setContentLength(baos.toByteArray().length);

	    return new HttpEntity<byte[]>(baos.toByteArray(), header);

	}


}
