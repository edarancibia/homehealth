package com.hhd.respository;

import com.hhd.entities.AtencionEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtEnfRepository extends JpaRepository<AtencionEnfermeria, Long> {

    public abstract AtencionEnfermeria findAtencionEnfermeriaByIdFicha(Long idFicha);
}
