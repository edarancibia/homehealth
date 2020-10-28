package com.hhd.service;

import com.hhd.entities.Paciente;

public interface PacienteService {

    public abstract Paciente addPaciente(Paciente paciente);
    public abstract Paciente findPacienteByRutNum(int rutNum);

}
