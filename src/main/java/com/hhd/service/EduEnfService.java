package com.hhd.service;

import com.hhd.entities.EducacionEnf;

public interface EduEnfService {

    public abstract EducacionEnf addEducacion(EducacionEnf educacion);
    public abstract EducacionEnf findEducacionEnfByIdFicha(Long idFicha);
}
