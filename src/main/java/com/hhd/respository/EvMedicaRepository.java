package com.hhd.respository;

import com.hhd.entities.EvMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;

@Repository
public interface EvMedicaRepository extends JpaRepository<EvMedica, Long> {

}
