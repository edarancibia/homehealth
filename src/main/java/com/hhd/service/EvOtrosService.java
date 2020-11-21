package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EvOtros;

public interface EvOtrosService {

	public abstract EvOtros addEvOtros(EvOtros evolucion);
	List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
