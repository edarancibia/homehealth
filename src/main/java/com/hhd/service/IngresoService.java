package com.hhd.service;

import com.hhd.entities.Ingreso;

public interface IngresoService {

    public abstract Ingreso addIngreso(Ingreso ingreso);
    public abstract Ingreso findIngresoByIdIngreso(Long idIngreso);
    public abstract Ingreso findIngresoByIdFicha(int idficha);
}
