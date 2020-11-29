package com.hhd.respository;

import com.hhd.entities.SignosVitales;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long> {
     
     @Query(value = "select DATE_FORMAT(s.fecha,'%d-%m-%Y %H:%i') as fecha,fc,sat,pa,tax,hgt,resp, glasgow \r\n"
     		+ "from signos_vitales s \r\n"
     		+ "where s.id_ficha = :idFicha order by s.fecha  desc", nativeQuery = true)
     List<Map<String, Object>> findSignosVitalesByIdFicha(Long idFicha);
}
