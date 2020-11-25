package com.hhd.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/")
public class UserController {

	@GetMapping("")
	public ModelAndView indexLogin() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("user-register");
		return mv;
	}
}
