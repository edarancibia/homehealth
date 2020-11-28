package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.EvTens;
import com.hhd.respository.EvTensRepository;
import com.hhd.service.EvTensService;

@Service
public class EvTensServiceImpl implements EvTensService {

	@Autowired
	public EvTensRepository repository;
	
	@Override
	public EvTens addEvTens(EvTens evolucion) {
		return repository.save(evolucion);
	}

	@Override
	public List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha) {
		return repository.findEvolucionByIdFicha(idFicha);
	}

}
