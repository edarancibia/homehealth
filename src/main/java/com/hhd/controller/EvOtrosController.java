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

import com.hhd.entities.EvKine;
import com.hhd.entities.EvOtros;
import com.hhd.impl.EvOtrosServiceImpl;

import ch.qos.logback.classic.spi.LoggingEventVO;

@RestController
@RequestMapping("evolucion-o")
public class EvOtrosController {
	
	@Autowired
	public EvOtrosServiceImpl evService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model) {
		ModelAndView mv = new ModelAndView("ev-otros");
		model.addAttribute("idficha", idficha);
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEvolucion(@RequestBody EvOtros evolucion){
		EvOtros evolucionDb = evService.addEvOtros(evolucion);
		return new ResponseEntity<EvOtros>(evolucionDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listEvolucion(@PathVariable Long idficha){
		List<Map<String, Object>> evoluciones = evService.findEvolucionByIdFicha(idficha);
		return evoluciones;
	}

}
