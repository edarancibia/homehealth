package com.hhd.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.EvEnfermeria;
import com.hhd.entities.EvMedica;
import com.hhd.impl.EvEnfermeriaServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/evolucion-e")
public class EvEnfermeriaController {
	
	@Autowired
	public EvEnfermeriaServiceImpl evEnfService;

	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("ev-enfermeria");
		model.addAttribute("idficha", idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEvolucion(@RequestBody EvEnfermeria evolucion){
		EvEnfermeria evolucionDb = evEnfService.addEvEnfermeria(evolucion) ;
		return new ResponseEntity<EvEnfermeria>(evolucionDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listEvolucion(@PathVariable Long idficha){
		List<Map<String, Object>> evoluciones = evEnfService.findEvolucionByIdFicha(idficha);
		return evoluciones;
	}
}
