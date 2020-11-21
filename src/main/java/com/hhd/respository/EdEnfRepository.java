package com.hhd.respository;

import com.hhd.entities.EducacionEnf;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EdEnfRepository extends JpaRepository<EducacionEnf, Long> {

    public abstract EducacionEnf findEducacionEnfByIdFicha(Long idFicha);
    
    @Query(value = "select e.id_ficha, DATE_FORMAT(e.fecha,'%d-%m-%Y %H:%i') as fecha,e.descripcion,e.rut_usu \r\n"
     		+ "from educacion_enf e \r\n"
     		+ "where e.id_ficha = :idFicha order by e.fecha  desc", nativeQuery = true)
     List<Map<String, Object>> findEducacionByIdFicha(Long idFicha);

}
