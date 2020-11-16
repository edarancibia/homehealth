package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.SignosVitales;

public interface SignosVitalesService {

    public abstract SignosVitales addSignosVitales(SignosVitales signosVitales);
    List<Map<String, Object>> findSignosVitalesByIdFicha(Long idFicha);
}
