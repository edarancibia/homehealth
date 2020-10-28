package com.hhd.service;

import com.hhd.entities.Ficha;

public interface FichaService {

    public abstract Ficha addFicha(Ficha ficha);
    public abstract Ficha findFichaByIdficha(Long idFicha);
}
