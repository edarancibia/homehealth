package com.hhd.impl;

import com.hhd.entities.Ficha;
import com.hhd.respository.FichaRepository;
import com.hhd.service.FichaService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichaServiceImpl implements FichaService {

    @Autowired
    public FichaRepository repository;

    @Override
    public Ficha addFicha(Ficha ficha) {
        return repository.save(ficha);
    }

    @Override
    public Ficha findFichaByIdficha(Long idFicha) {
        return repository.findFichaByIdFicha(idFicha);
    }

	@Override
	public List<Ficha> findFichaByRutPac(int rutnum) {
		return repository.findFichaByRutPac(rutnum);
	}

	@Override
	public List<Map<String, Object>> findFichasByRut(int rutnum) {
		return repository.findFichasByRut(rutnum);
	}

}
