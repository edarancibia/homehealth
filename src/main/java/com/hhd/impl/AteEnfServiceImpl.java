package com.hhd.impl;

import com.hhd.entities.AtencionEnfermeria;
import com.hhd.respository.AtEnfRepository;
import com.hhd.service.AteEnfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AteEnfServiceImpl implements AteEnfService {

    @Autowired
    public AtEnfRepository repository;

    @Override
    public AtencionEnfermeria addAtencion(AtencionEnfermeria ateEnf) {
        return repository.save(ateEnf);
    }

    @Override
    public AtencionEnfermeria findAtencionEnfermeriaByIdFicha(Long idFicha) {
        return repository.findAtencionEnfermeriaByIdFicha(idFicha);
    }
}
