package com.hhd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.impl.AtMedicaServiceImpl;

@RestController
@RequestMapping("/atencion-m")
public class AteMedicaController {

	@Autowired
	public AtMedicaServiceImpl atMedService;
	
	@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("atencion-medica");
        return mv;
    }
}
