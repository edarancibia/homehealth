package com.hhd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.hhd.entities.Ficha;
import com.hhd.entities.Paciente;
import com.hhd.impl.FichaServiceImpl;
import com.hhd.impl.PacienteServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	public PacienteServiceImpl pacienteService;
	
	@Autowired
	public FichaServiceImpl fichaService;
	
    @GetMapping("/verifica-paciente/{rutnum}")
    public ResponseEntity<?> verificaPaciente(@PathVariable("rutnum") int rutnum){
    	Paciente pacienteBd = pacienteService.findPacienteByRutNum(rutnum);
    	if(null != pacienteBd) {
    		return ResponseEntity.ok(pacienteBd); // si paciente ya existe
    	}else {
			return ResponseEntity.notFound().build();
		}	
    }
    
    @GetMapping("/obtiene-paciente/{idficha}")
    public @ResponseBody List<Map<String, Object>> getPaciente(@PathVariable("idficha") int idficha){
    	List<Map<String, Object>> pacienteBd = pacienteService.getPacienteDatos(idficha);
    	if(null != pacienteBd) {
    		return pacienteBd; // si paciente ya existe
    	}else {
			return (List<Map<String, Object>>) ResponseEntity.notFound().build();
		}	
    }
    
    @PostMapping("/add-paciente")
    public ResponseEntity<?> addPaciente(@RequestBody Paciente paciente){
    	Paciente newPac = pacienteService.addPaciente(paciente);
    	return new ResponseEntity<Paciente>(newPac,HttpStatus.OK);
    }
	
	@GetMapping("/list")
	public @ResponseBody List<Map<String, Object>> getPacientesList(){
		 List<Map<String, Object>> pacientes = pacienteService.getListaPacienteActuales();
		 return pacientes;
	}
	
	@GetMapping("/search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("paciente-busca");
		return mv;
	}
	
	//LISTA DE FICHAS X PACIENTE
	@GetMapping("/fichas/{rutnum}")
	public @ResponseBody List<Map<String, Object>> getFichas(@PathVariable int rutnum){
		List<Map<String, Object>> fichas = fichaService.findFichasByRut(rutnum);
		return fichas;
	}
}
