package com.hhd.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.hhd.entities.AtencionMedica;
import com.hhd.entities.Ficha;
import com.hhd.impl.AtMedicaServiceImpl;
import com.hhd.util.PdfGenaratorUtil;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/atencion-m")
public class AteMedicaController {

	@Autowired
	public AtMedicaServiceImpl atMedService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	private static final Log LOG = LogFactory.getLog(AteMedicaController.class);
	
	@GetMapping("/{idficha}")
    public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session){
        ModelAndView mv = new ModelAndView("atencion-medica");
        model.addAttribute("idficha",idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
    }
	
	@PostMapping("/save")
	public ResponseEntity<?> addAtencionMedica(@RequestBody AtencionMedica atencion){
		AtencionMedica newAtencion = atMedService.AddAtencion(atencion);
		return new ResponseEntity<AtencionMedica>(newAtencion,HttpStatus.OK);
	}
	
	@GetMapping("/get-atencion/{idficha}")
	public ResponseEntity<?> getAtencionByFicha(@PathVariable int idficha){
		AtencionMedica atencion = atMedService.findAtencionMedicaByIdFicha(idficha);
		if(atencion == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<AtencionMedica>(atencion,HttpStatus.OK);
		}
	}
	
	@GetMapping("/datos")
	public @ResponseBody Map<String, Object> datosPaciente(){
		Map<String, Object> datos = atMedService.getDatosPacientePdf(18);
		LOG.info("datos: " + datos.get("nombre"));
		return datos;
	}
	
	@GetMapping("/export/{idficha}")
	public void export(@PathVariable int idficha) throws Exception {
		AtencionMedica atencion = atMedService.findAtencionMedicaByIdFicha(idficha);
		Map<String, Object> cab = atMedService.getDatosPacientePdf(idficha);
		Map<String,String> data = new HashMap<String,String>();
		Map<String, Object> med = atMedService.getMedicoPdf(idficha);
		Map<String, Object> ante = atMedService.getAntededentesPdf(idficha);
		
		data.put("nombre",cab.get("nombre").toString());
		data.put("rut", cab.get("rut").toString());
		data.put("edad", cab.get("edad").toString());
		data.put("prevision", cab.get("prevision").toString());
		
		data.put("hta", ante.get("hta").toString());
		data.put("dld", ante.get("dld").toString());
		data.put("tbc", ante.get("tbc").toString());
		data.put("epoc", ante.get("epoc").toString());
		data.put("lcfa", ante.get("lcfa").toString());
		data.put("acxfa", ante.get("acxfa").toString());
		data.put("acv", ante.get("acv").toString());
		data.put("depre", ante.get("depre").toString());
		data.put("ob", ante.get("ob").toString());
		data.put("dm", ante.get("dm").toString());
		data.put("ca", ante.get("ca").toString());
		data.put("cardio", ante.get("cardio").toString());
		
		
	    data.put("fecha", atencion.getFecha().toString().substring(0,10));
	    data.put("examen", atencion.getExamenFisico());
	    data.put("anam", atencion.getAnamnesis());
	    data.put("diag", atencion.getDiagPresuntivo());
	    data.put("indi", atencion.getIndDomicilio());
	    data.put("examenes", atencion.getExmanes());
	    data.put("medico", med.get("medico").toString());
	    data.put("rutnum", med.get("rutnum").toString());
	    
	    
	    pdfGenaratorUtil.createPdf("pdf/ate-medica",data); 
	}
}
