package com.hhd.respository;

import com.hhd.entities.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    public  abstract Ficha findFichaByIdFicha(Long idFicha);
}
