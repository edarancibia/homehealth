package com.hhd.service;

import com.hhd.entities.SignosVitales;

public interface SignosVitalesService {

    public abstract SignosVitales addSignosVitales(SignosVitales signosVitales);
    public abstract SignosVitales findSignosVitalesByIdFicha(Long idFicha);
}
