package com.hhd.controller;

import com.hhd.entities.Ficha;
import com.hhd.entities.Ingreso;
import com.hhd.entities.Paciente;
import com.hhd.impl.FichaServiceImpl;
import com.hhd.impl.IngresoServiceImpl;
import com.hhd.impl.PacienteServiceImpl;
import com.hhd.util.PdfService;
import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/ingreso")
public class IngresoController {
    
    @Autowired
    public PacienteServiceImpl pacienteService;
    
    @Autowired
    public FichaServiceImpl fichaService;
    
    @Autowired
    public IngresoServiceImpl ingresoService;
    
    @Autowired
    public PdfService pdfService;

    @GetMapping("/")
    public ModelAndView index(HttpSession session){
        ModelAndView mv = new ModelAndView("ingreso");
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
    }
    
    @PostMapping("/add-ficha")
    public ResponseEntity<?> addFicha(@RequestBody Ficha ficha){
    	Ficha newFicha = fichaService.addFicha(ficha);
    	return new ResponseEntity<Ficha>(newFicha,HttpStatus.OK);
    }
    
    @PostMapping("/add-ingreso")
    public ResponseEntity<?> addIngreso(@RequestBody Ingreso ingreso){
    	Ingreso newIngreso = ingresoService.addIngreso(ingreso);
    	return new ResponseEntity<Ingreso>(newIngreso,HttpStatus.OK);
    }
    
    @GetMapping("/export-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
