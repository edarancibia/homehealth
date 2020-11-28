package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.EvTens;

public interface EvTensService {

	public abstract EvTens addEvTens(EvTens evolucion);
	List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
