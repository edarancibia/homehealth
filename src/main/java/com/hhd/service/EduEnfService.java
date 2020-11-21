package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EducacionEnf;

public interface EduEnfService {

    public abstract EducacionEnf addEducacionEnf(EducacionEnf educacion);
    public abstract EducacionEnf findEducacionEnfByIdFicha(Long idFicha);
    List<Map<String, Object>> findEducacionByIdFicha(Long idFicha);
}
