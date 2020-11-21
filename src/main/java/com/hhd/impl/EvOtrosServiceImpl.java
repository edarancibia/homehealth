package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.EvOtros;
import com.hhd.respository.EvOtrosRepository;
import com.hhd.service.EvOtrosService;

@Service
public class EvOtrosServiceImpl implements EvOtrosService {

	@Autowired
	public EvOtrosRepository repository;
	
	@Override
	public EvOtros addEvOtros(EvOtros evolucion) {
		return repository.save(evolucion);
	}

	@Override
	public List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha) {
		return repository.findEvolucionByIdFicha(idFicha);
	}

}
