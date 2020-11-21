package com.hhd.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.ProcEnfermeria;
import com.hhd.respository.ProcEnfermeriaRepository;
import com.hhd.service.ProcEnfermeriaService;

@Service
public class ProcEnfServiceImpl implements ProcEnfermeriaService {

	@Autowired
	public ProcEnfermeriaRepository repository;
	
	@Override
	public ProcEnfermeria addProcEnfermeria(ProcEnfermeria proc) {
		return repository.save(proc);
	}

	@Override
	public ProcEnfermeria findProcEnfermeriaByIdFicha(ProcEnfermeria proc) {
		return repository.findProcEnfermeriaByIdFicha(proc);
	}

	@Override
	public List<Map<String, Object>> findProcedimientoByIdFicha(Long idFicha) {
		return repository.findProcedimientoByIdFicha(idFicha);
	}

}
