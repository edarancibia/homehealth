package com.hhd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.SignosVitales;
import com.hhd.impl.SignosVitalesServiceImpl;

@RestController
@RequestMapping("/signos")
public class SignosController {

	@Autowired
	public SignosVitalesServiceImpl signosService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(Model model,@PathVariable int idficha){
		ModelAndView mv = new ModelAndView("signos");
		model.addAttribute("idficha", idficha);
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addSignos(@RequestBody SignosVitales signos){
		SignosVitales signosDb = signosService.addSignosVitales(signos);
		return new ResponseEntity<SignosVitales>(signosDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listSignos(@PathVariable Long idficha){
		List<Map<String, Object>> signos = signosService.findSignosVitalesByIdFicha(idficha);
		return signos;
	}
}
