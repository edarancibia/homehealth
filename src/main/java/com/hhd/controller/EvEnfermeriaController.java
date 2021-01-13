package com.hhd.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.EpicrisisMedica;
import com.hhd.entities.EvEnfermeria;
import com.hhd.entities.EvMedica;
import com.hhd.impl.AtMedicaServiceImpl;
import com.hhd.impl.EvEnfermeriaServiceImpl;
import com.hhd.util.PdfGenaratorUtil;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/evolucion-e")
public class EvEnfermeriaController {
	
	@Autowired
	public EvEnfermeriaServiceImpl evEnfService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@Autowired
	public AtMedicaServiceImpl atMedService;

	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("ev-enfermeria");
		model.addAttribute("idficha", idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEvolucion(@RequestBody EvEnfermeria evolucion){
		EvEnfermeria evolucionDb = evEnfService.addEvEnfermeria(evolucion) ;
		return new ResponseEntity<EvEnfermeria>(evolucionDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listEvolucion(@PathVariable Long idficha){
		List<Map<String, Object>> evoluciones = evEnfService.findEvolucionByIdFicha(idficha);
		return evoluciones;
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
		Template t = ve.getTemplate("templates/pdf/ev-enfe.vm", "UTF-8");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		
		Long idficha = Long.parseLong(fileName);
		int idficha2 = Integer.parseInt(fileName);
		Map<String, Object> cab = atMedService.getDatosPacientePdf(idficha2);
		List<Map<String, Object>> evoluciones = evEnfService.findEvolucionByIdFicha(idficha);
		
		context.put("nombre",cab.get("nombre").toString());
		context.put("rut", cab.get("rut").toString());
		context.put("edad", cab.get("edad").toString());
		context.put("prevision", cab.get("prevision").toString());
		
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		context.put("fecha", date);
		
		context.put("evoluciones", evoluciones);
		
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
