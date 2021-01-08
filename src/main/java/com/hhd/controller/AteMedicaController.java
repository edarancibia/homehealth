package com.hhd.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.hhd.entities.AtencionMedica;
import com.hhd.entities.Ficha;
import com.hhd.impl.AtMedicaServiceImpl;
import com.hhd.util.PdfGenaratorUtil;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

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
	HttpEntity<byte[]> createPdf(
            @PathVariable("idficha") String fileName, HttpServletResponse response) throws IOException {


		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/pdf/pdf-ate-medica.vm", "UTF-8");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		
		int idficha = Integer.parseInt(fileName);
		AtencionMedica atencion = atMedService.findAtencionMedicaByIdFicha(idficha);
		Map<String, Object> cab = atMedService.getDatosPacientePdf(idficha);
		Map<String, Object> med = atMedService.getMedicoPdf(idficha);
		Map<String, Object> ante = atMedService.getAntededentesPdf(idficha);
		
		context.put("nombre",cab.get("nombre").toString());
		context.put("rut", cab.get("rut").toString());
		context.put("edad", cab.get("edad").toString());
		context.put("prevision", cab.get("prevision").toString());
		
		context.put("hta", ante.get("hta").toString());
		context.put("dld", ante.get("dld").toString());
		context.put("tbc", ante.get("tbc").toString());
		context.put("epoc", ante.get("epoc").toString());
		context.put("lcfa", ante.get("lcfa").toString());
		context.put("acxfa", ante.get("acxfa").toString());
		context.put("acv", ante.get("acv").toString());
		context.put("depre", ante.get("depre").toString());
		context.put("ob", ante.get("ob").toString());
		context.put("dm", ante.get("dm").toString());
		context.put("ca", ante.get("ca").toString());
		context.put("cardio", ante.get("cardio").toString());
		
		
		context.put("fecha", atencion.getFecha().toString().substring(0,10));
		context.put("examen", atencion.getExamenFisico());
		context.put("anam", atencion.getAnamnesis());
		context.put("diag", atencion.getDiagPresuntivo());
		context.put("indi", atencion.getIndDomicilio());
		context.put("examenes", atencion.getExmanes());
		context.put("medico", med.get("medico").toString());
		context.put("rutnum", med.get("rutnum").toString());
		
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
