package com.hhd.impl;

import com.hhd.entities.EducacionEnf;
import com.hhd.respository.EdEnfRepository;
import com.hhd.service.EduEnfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduEnfServiceImpl implements EduEnfService {

    @Autowired
    public EdEnfRepository repository;

    @Override
    public EducacionEnf addEducacion(EducacionEnf educacion) {
        return repository.save(educacion);
    }

    @Override
    public EducacionEnf findEducacionEnfByIdFicha(Long idFicha) {
        return repository.findEducacionEnfByIdFicha(idFicha);
    }
}
