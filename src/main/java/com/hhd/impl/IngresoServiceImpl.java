package com.hhd.impl;

import com.hhd.entities.Ingreso;
import com.hhd.respository.IngresoRepository;
import com.hhd.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoServiceImpl implements IngresoService {

    @Autowired
    public IngresoRepository respository;

    @Override
    public Ingreso addIngreso(Ingreso ingreso) {
        return respository.save(ingreso);
    }

    @Override
    public Ingreso findIngresoByIdIngreso(Long idIngreso) {
        return respository.findIngresoByIdIngreso(idIngreso);
    }
}
