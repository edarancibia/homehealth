package com.hhd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pauta-kinesica")
public class PautaKIneController {
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("pauta-k");
		return mv;
	}

}