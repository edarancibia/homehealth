package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EvEnfermeria;

public interface EvEnfermeriaService {

	public abstract EvEnfermeria addEvEnfermeria(EvEnfermeria evolucion);
	List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
