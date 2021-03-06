package com.hhd.respository;

import com.hhd.entities.EvKine;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvKineRepository extends JpaRepository<EvKine, Long> {

    public abstract EvKine findEvKineByIdFicha(Long idficha);
    
    @Query(value = "select e.id_ficha, DATE_FORMAT(e.fecha,'%d-%m-%Y %H:%i') as fecha,e.descripcion \r\n"
     		+ "from ev_kine e \r\n"
     		+ "where e.id_ficha = :idFicha order by e.fecha  desc", nativeQuery = true)
     List<Map<String, Object>> findEvolucionByIdFicha(Long idFicha);
}
