package com.hhd.service;

import com.hhd.entities.EvKine;

public interface EvKineService {

    public abstract EvKine addEvKine(EvKine evKine);
    public abstract EvKine findEvKineByIdFicha(Long idficha);
}
