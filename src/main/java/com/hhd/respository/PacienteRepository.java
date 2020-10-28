package com.hhd.respository;

import com.hhd.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public abstract Paciente findPacienteByRutNum(int rutNUm);
}
