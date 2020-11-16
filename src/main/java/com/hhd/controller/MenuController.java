package com.hhd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.Paciente;
import com.hhd.impl.PacienteServiceImpl;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@GetMapping("/{idficha}")
	public ModelAndView index(Model model,@PathVariable int idficha) {
		ModelAndView mv = new ModelAndView("menu-opciones");
		model.addAttribute("idficha", idficha);
		return mv;
	}
}
