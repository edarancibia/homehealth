package com.hhd.impl;

import com.hhd.entities.EducacionEnf;
import com.hhd.respository.EdEnfRepository;
import com.hhd.service.EduEnfService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduEnfServiceImpl implements EduEnfService {

    @Autowired
    public EdEnfRepository repository;

    @Override
    public EducacionEnf findEducacionEnfByIdFicha(Long idFicha) {
        return repository.findEducacionEnfByIdFicha(idFicha);
    }

	@Override
	public List<Map<String, Object>> findEducacionByIdFicha(Long idFicha) {
		return repository.findEducacionByIdFicha(idFicha);
	}

	@Override
	public EducacionEnf addEducacionEnf(EducacionEnf educacion) {
		return repository.save(educacion);
	}
}
