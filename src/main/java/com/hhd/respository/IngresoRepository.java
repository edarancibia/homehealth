package com.hhd.respository;

import com.hhd.entities.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    public abstract Ingreso findIngresoByIdIngreso(Long idIngreso);
}
