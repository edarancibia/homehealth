package com.hhd.service;

import com.hhd.entities.AtencionEnfermeria;

public interface AteEnfService {

    public abstract AtencionEnfermeria addAtencion(AtencionEnfermeria ateEnf);
    public abstract AtencionEnfermeria findAtencionEnfermeriaByIdFicha(Long idFicha);
}
