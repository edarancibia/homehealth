package com.hhd.controller;

import com.hhd.entities.Ficha;
import com.hhd.entities.Ingreso;
import com.hhd.entities.Paciente;
import com.hhd.impl.FichaServiceImpl;
import com.hhd.impl.IngresoServiceImpl;
import com.hhd.impl.PacienteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ingreso")
public class IngresoController {
    
    @Autowired
    public PacienteServiceImpl pacienteService;
    
    @Autowired
    public FichaServiceImpl fichaService;
    
    @Autowired
    public IngresoServiceImpl ingresoService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("ingreso");
        return mv;
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

}
