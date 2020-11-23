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

import com.hhd.entities.ProcEnfermeria;
import com.hhd.impl.ProcEnfServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/proc-enf")
public class ProcEnfController {

	@Autowired
	public ProcEnfServiceImpl procEnfService;
	
	@GetMapping("/{idficha}")
	public ModelAndView index(@PathVariable int idficha, Model model) {
		ModelAndView mv = new ModelAndView("proc-enfermeria");
		model.addAttribute("idficha", idficha);
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addProcedimiento(@RequestBody ProcEnfermeria proc){
		ProcEnfermeria procDb = procEnfService.addProcEnfermeria(proc);
		return new ResponseEntity<ProcEnfermeria>(procDb,HttpStatus.OK);
	}
	
	@GetMapping("/list/{idficha}")
	public @ResponseBody List<Map<String, Object>> listProcedimientos(@PathVariable Long idficha){
		List<Map<String, Object>> procedimientos = procEnfService.findProcedimientoByIdFicha(idficha);
		return procedimientos;
	}
}
