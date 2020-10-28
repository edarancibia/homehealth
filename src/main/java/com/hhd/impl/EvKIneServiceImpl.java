package com.hhd.impl;

import com.hhd.entities.EvKine;
import com.hhd.respository.EvKineRepository;
import com.hhd.service.EvKineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvKIneServiceImpl implements EvKineService {

    @Autowired
    public EvKineRepository repository;

    @Override
    public EvKine addEvKine(EvKine evKine) {
        return repository.save(evKine);
    }

    @Override
    public EvKine findEvKineByIdFicha(Long idficha) {
        return repository.findEvKineByIdFicha(idficha);
    }
}
