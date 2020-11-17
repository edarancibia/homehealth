package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.EvEnfermeria;
import com.hhd.respository.EvEnfermeriaRepository;
import com.hhd.service.EvEnfermeriaService;

@Service
public class EvEnfermeriaServiceImpl implements EvEnfermeriaService {

	@Autowired
	public EvEnfermeriaRepository repository;
	
	@Override
	public EvEnfermeria addEvEnfermeria(EvEnfermeria evolucion) {
		return repository.save(evolucion);
	}

	@Override
	public List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha) {
		return repository.findEvolucionByIdFicha(idFicha);
	}

	
}
