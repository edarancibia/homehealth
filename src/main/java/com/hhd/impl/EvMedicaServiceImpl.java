package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.EvMedica;
import com.hhd.respository.EvMedicaRepository;
import com.hhd.service.EvMedicaService;

@Service
public class EvMedicaServiceImpl implements EvMedicaService {

	@Autowired
	public EvMedicaRepository repository;

	@Override
	public EvMedica addEvMedica(EvMedica evolucion) {
		return repository.save(evolucion);
	}

	@Override
	public List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha) {
		return repository.findEvolucionByIdFicha(idFicha);
	}
	
	
}
