package com.hhd.respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hhd.entities.EvEnfermeria;

@Repository
public interface EvEnfermeriaRepository extends JpaRepository<EvEnfermeria, Long> {

    @Query(value = "select e.id_ficha, DATE_FORMAT(e.fecha,'%d-%m-%Y %H:%i') as fecha,e.descripcion \r\n"
     		+ "from ev_enfermeria e \r\n"
     		+ "where e.id_ficha = :idFicha order by e.fecha  desc", nativeQuery = true)
     List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
