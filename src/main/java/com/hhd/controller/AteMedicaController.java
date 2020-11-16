package com.hhd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.AtencionMedica;
import com.hhd.entities.Ficha;
import com.hhd.impl.AtMedicaServiceImpl;

@RestController
@RequestMapping("/atencion-m")
public class AteMedicaController {

	@Autowired
	public AtMedicaServiceImpl atMedService;
	
	@GetMapping("/{rutnum}")
    public ModelAndView index(@PathVariable int rutnum, Model model){
        ModelAndView mv = new ModelAndView("atencion-medica");
        model.addAttribute("rutnum",rutnum);
        return mv;
    }
	
	@PostMapping("/save")
	public ResponseEntity<?> addAtencionMedica(@RequestBody AtencionMedica atencion){
		AtencionMedica newAtencion = atMedService.AddAtencion(atencion);
		return new ResponseEntity<AtencionMedica>(newAtencion,HttpStatus.OK);
	}
}
