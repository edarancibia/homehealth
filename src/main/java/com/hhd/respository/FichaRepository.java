package com.hhd.respository;

import com.hhd.entities.Ficha;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    public  abstract Ficha findFichaByIdFicha(Long idFicha);
    
    public abstract List<Ficha> findFichaByRutPac(int rutnum);
    
    @Query(value = "select f.idficha,DATE_FORMAT(f.fecha,'%d-%m-%Y') as fecha, f.rut_pac "
    		+ "from ficha f "
    		+ "where f.rut_pac = :rutnum", nativeQuery = true)
    List<Map<String, Object>> findFichasByRut(int rutnum);
}
