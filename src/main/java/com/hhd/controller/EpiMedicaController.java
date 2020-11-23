package com.hhd.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hhd.entities.AtencionMedica;
import com.hhd.entities.EpicrisisMedica;
import com.hhd.entities.Ficha;
import com.hhd.entities.IndicacionesAlta;
import com.hhd.entities.Ingreso;
import com.hhd.impl.EpiMedicaServiceImpl;
import com.hhd.impl.FichaServiceImpl;
import com.hhd.impl.IndicacionesServiceImpl;
import com.hhd.impl.IngresoServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("epicrisis-med")
public class EpiMedicaController {
	
	@Autowired
	public EpiMedicaServiceImpl epiMedService;
	
	@Autowired
	public IndicacionesServiceImpl indicacionesService;
	
	@Autowired
	public IngresoServiceImpl ingresoService;
	
	@Autowired
	public FichaServiceImpl fichaService;
	
	private static final Log LOG = LogFactory.getLog(EpiMedicaController.class);
	
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
	
	@GetMapping("/get-epicrisis/{idficha}")
	public ResponseEntity<?> getEpecrisisByFicha(@PathVariable int idficha){
		EpicrisisMedica epicrisis = epiMedService.findEpicrisisMedicaByIdFicha(idficha);
		if(epicrisis == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<EpicrisisMedica>(epicrisis,HttpStatus.OK);
		}
	}
	
	@PostMapping("/add-indicaciones")
	public ResponseEntity<?> addEpicrisisIndicaciones(@RequestBody IndicacionesAlta indicaciones){
		IndicacionesAlta indicacionesDb = indicacionesService.addIndicaciones(indicaciones);
		return new ResponseEntity<IndicacionesAlta>(indicacionesDb,HttpStatus.OK);
	}
	
	@GetMapping("/get-epicrisis-indicaciones/{idficha}")
	public ResponseEntity<?> getEpecrisisIndicacionesByFicha(@PathVariable int idficha){
		IndicacionesAlta indicaciones = indicacionesService.findIndicacionesAltaByIdFicha(idficha);
		if(indicaciones == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<IndicacionesAlta>(indicaciones,HttpStatus.OK);
		}
	}
	
	@GetMapping("/get-epicrisis-diag/{idficha}")
	public ResponseEntity<?> getEpecrisisDiagByFicha(@PathVariable int idficha){
		Ingreso diags = ingresoService.findIngresoByIdFicha(idficha);
		if(diags == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Ingreso>(diags,HttpStatus.OK);
		}
	}
	
	//CIERRA FICHA
	@PutMapping("/alta/{idficha}")
	public ResponseEntity<?> altaFicha(@PathVariable int idficha){
		Ficha fichaDb = fichaService.findFichaByIdficha((long) idficha);
		
		if(fichaDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		fichaDb.setEstado(0);
		fichaService.addFicha(fichaDb);
		return ResponseEntity.ok().build();
	}

}
