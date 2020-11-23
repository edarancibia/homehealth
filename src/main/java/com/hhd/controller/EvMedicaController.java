package com.hhd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.EvMedica;
import com.hhd.entities.SignosVitales;
import com.hhd.impl.EvMedicaServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("evolucion-m")
public class EvMedicaController {

	@Autowired
	public EvMedicaServiceImpl evMedicaService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha,Model model) {
		ModelAndView mv = new ModelAndView("ev-medica");
		model.addAttribute("idficha", idficha);
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEvolucion(@RequestBody EvMedica evolucion){
		EvMedica evolucionDb = evMedicaService.addEvMedica(evolucion);
		return new ResponseEntity<EvMedica>(evolucionDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listEvolucion(@PathVariable Long idficha){
		List<Map<String, Object>> evoluciones = evMedicaService.findEvolucionByIdFicha(idficha);
		return evoluciones;
	}
	
}
