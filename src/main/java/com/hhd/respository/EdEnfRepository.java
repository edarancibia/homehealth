package com.hhd.respository;

import com.hhd.entities.EducacionEnf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdEnfRepository extends JpaRepository<EducacionEnf, Long> {

}
