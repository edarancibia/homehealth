package com.hhd.respository;

import com.hhd.entities.EvKine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvKineRepository extends JpaRepository<EvKine, Long> {

    public abstract EvKine findEvKineByIdFicha(Long idficha);
}
