package com.hhd.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhd.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByNombre(String nombre);
	Usuario findByEmail(String email);
	Usuario findByEmailAndClaveAndVigente(String email, String clave, String vigente);
}
