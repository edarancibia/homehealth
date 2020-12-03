package com.hhd.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hhd.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
