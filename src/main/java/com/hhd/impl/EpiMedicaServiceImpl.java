package com.hhd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.EpicrisisMedica;
import com.hhd.respository.EpiMedicaRepository;
import com.hhd.service.EpiMedicaService;

@Service
public class EpiMedicaServiceImpl implements EpiMedicaService{
	
	@Autowired
	public EpiMedicaRepository repository;

	@Override
	public EpicrisisMedica addEpicrisisMedica(EpicrisisMedica epicrisis) {
		return repository.save(epicrisis);
	}

	@Override
	public EpicrisisMedica findEpicrisisMedicaByIdFicha(int idficha) {
		return repository.findEpicrisisMedicaByIdFicha(idficha);
	}

}
