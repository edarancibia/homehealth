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

import com.hhd.entities.EducacionEnf;
import com.hhd.impl.EduEnfServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu"})
@RequestMapping("/educacion-enf")
public class EdEnfermeria {
	@Autowired
	public EduEnfServiceImpl educacionService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("educacion-enf");
		model.addAttribute("idficha", idficha);
        if(session.getAttribute("idusuario") == null) {
        	return new ModelAndView("login");
        }else {
        	return mv;
        }
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addEducacion(@RequestBody EducacionEnf educacion){
		EducacionEnf educacionDb = educacionService.addEducacionEnf(educacion);
		return new ResponseEntity<EducacionEnf>(educacionDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listEducacion(@PathVariable Long idficha){
		List<Map<String, Object>> educaciones = educacionService.findEducacionByIdFicha(idficha);
		return educaciones;
	}

}
