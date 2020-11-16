package com.hhd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhd.entities.Paciente;
import com.hhd.impl.PacienteServiceImpl;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	public PacienteServiceImpl pacienteService;
	
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
}
