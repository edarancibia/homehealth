package com.hhd.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hhd.entities.UsuarioRole;

@Repository
public interface UsuarioRoleRepository extends CrudRepository<UsuarioRole, Long> {

}
