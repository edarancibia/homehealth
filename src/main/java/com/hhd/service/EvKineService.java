package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EvKine;

public interface EvKineService {

    public abstract EvKine addEvKine(EvKine evKine);
    public abstract EvKine findEvKineByIdFicha(Long idficha);
    List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
