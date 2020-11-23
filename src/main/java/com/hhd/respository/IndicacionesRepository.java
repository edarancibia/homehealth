package com.hhd.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhd.entities.IndicacionesAlta;

@Repository
public interface IndicacionesRepository extends JpaRepository<IndicacionesAlta, Long> {

	public abstract IndicacionesAlta findIndicacionesAltaByIdFicha(int idficha);
}
