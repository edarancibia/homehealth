package com.hhd.respository;

import com.hhd.entities.Paciente;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public abstract Paciente findPacienteByRutNum(int rutNum);
    
    @Query(value = "select f2.idficha, UPPER(CONCAT(p.a_pat,' ',p.a_mat,' ',p.nombre)) as nombre,f2.edad AS edad,\n"
    		+ "	p.rut_num ,f2.fecha \n"
    		+ "from paciente p, ficha f2 \n"
    		+ "where p.rut_num = f2.rut_pac and f2.estado =1", nativeQuery = true)
    List<Map<String, Object>> getListaPacienteActuales();
    
    @Query(value = "select f2.idficha ,p.rut_num, UPPER(CONCAT(p.a_pat,' ',p.a_mat,' ',p.nombre)) as nombre,f2.edad AS edad \n"
    		+ "    		from paciente p, ficha f2 \n"
    		+ "    		where p.rut_num = f2.rut_pac and f2.idficha = :idficha", nativeQuery = true)
    List<Map<String, Object>> getPacienteDatos(int idficha);
}
