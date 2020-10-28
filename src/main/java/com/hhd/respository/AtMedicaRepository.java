package com.hhd.respository;

import com.hhd.entities.AtencionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtMedicaRepository extends JpaRepository<AtencionMedica, Long> {

    public abstract AtencionMedica findAtMedicaByIdFicha(Long idFicha);
}
