package com.hhd.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhd.entities.EpicrisisMedica;

@Repository
public interface EpiMedicaRepository extends JpaRepository<EpicrisisMedica, Long> {

	public abstract EpicrisisMedica findEpicrisisMedicaByIdFicha(int idficha);
}
