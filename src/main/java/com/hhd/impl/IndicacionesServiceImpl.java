package com.hhd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.IndicacionesAlta;
import com.hhd.respository.IndicacionesRepository;
import com.hhd.service.IndicacionesService;

@Service
public class IndicacionesServiceImpl implements IndicacionesService {
	
	@Autowired
	public IndicacionesRepository repository;

	@Override
	public IndicacionesAlta addIndicaciones(IndicacionesAlta indicaciones) {
		return repository.save(indicaciones);
	}

	@Override
	public IndicacionesAlta findIndicacionesAltaByIdFicha(int idficha) {
		return repository.findIndicacionesAltaByIdFicha(idficha);
	}

}
