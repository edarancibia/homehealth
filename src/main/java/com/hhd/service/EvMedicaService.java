package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EvMedica;

public interface EvMedicaService {

	public abstract EvMedica addEvMedica(EvMedica evolucion);
    List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
    public abstract EvMedica findByIdEvMedica(Long id);
}
