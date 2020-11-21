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

import com.hhd.entities.EpicrisisMedica;
import com.hhd.impl.EpiMedicaServiceImpl;

@RestController
@RequestMapping("epicrisis-med")
public class EpiMedicaController {
	
	@Autowired
	public EpiMedicaServiceImpl epiMedService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model) {
		ModelAndView mv = new ModelAndView("epicrisis-med");
		model.addAttribute("idficha", idficha);
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEpicrisis(@RequestBody EpicrisisMedica epicrisis){
		EpicrisisMedica epicrisisDb = epiMedService.addEpicrisisMedica(epicrisis);
		return new ResponseEntity<EpicrisisMedica>(epicrisisDb,HttpStatus.OK);
	}

}
