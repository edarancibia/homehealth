package com.hhd.impl;

import com.hhd.entities.Paciente;
import com.hhd.respository.PacienteRepository;
import com.hhd.service.PacienteService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    public PacienteRepository repository;

    @Override
    public Paciente addPaciente(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public Paciente findPacienteByRutNum(int rutNum) {
        return repository.findPacienteByRutNum(rutNum);
    }

	@Override
	public List<Map<String, Object>> getListaPacienteActuales() {
		return repository.getListaPacienteActuales();
	}

	@Override
	public List<Map<String, Object>> getPacienteDatos(int idficha) {
		return repository.getPacienteDatos(idficha);
	}
}
