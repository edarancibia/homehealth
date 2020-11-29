package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.Ficha;

public interface FichaService {

    public abstract Ficha addFicha(Ficha ficha);
    public abstract Ficha findFichaByIdficha(Long idFicha);
    public abstract List<Ficha> findFichaByRutPac(int rutnum);
    List<Map<String, Object>> findFichasByRut(int rutnum);
}
