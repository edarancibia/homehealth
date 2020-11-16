package com.hhd.service;

import java.util.List;
import java.util.Map;

import com.hhd.entities.Paciente;

public interface PacienteService {

    public abstract Paciente addPaciente(Paciente paciente);
    public abstract Paciente findPacienteByRutNum(int rutNum);
    List<Map<String, Object>> getListaPacienteActuales();
    List<Map<String, Object>> getPacienteDatos(int idficha);

}
