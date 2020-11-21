package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.ProcEnfermeria;

public interface ProcEnfermeriaService {

	public abstract ProcEnfermeria addProcEnfermeria(ProcEnfermeria proc);
	public abstract ProcEnfermeria findProcEnfermeriaByIdFicha(ProcEnfermeria proc);
	List<Map<String, Object>> findProcedimientoByIdFicha(Long idFicha);
}
