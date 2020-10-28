package com.hhd.respository;

import com.hhd.entities.SignosVitales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long> {
}
