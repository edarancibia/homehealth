package com.hhd.impl;

import com.hhd.entities.SignosVitales;
import com.hhd.respository.SignosVitalesRepository;
import com.hhd.service.SignosVitalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignosVitalesServiceImpl implements SignosVitalesService {

    @Autowired
    public SignosVitalesRepository repository;

    @Override
    public SignosVitales addSignosVitales(SignosVitales signosVitales) {
        return repository.save(signosVitales);
    }

    @Override
    public SignosVitales findSignosVitalesByIdFicha(Long idFicha) {
        return repository.findSignosVitalesByIdFicha(idFicha);
    }
}
