package com.hhd.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhd.entities.Usuario;
import com.hhd.respository.UsuarioRepository;
import com.hhd.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	public UsuarioRepository repository;
	
	@Override
	public Usuario addUsuario(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public Usuario findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	public Usuario findByEmailAndClaveAndVigente(String email, String clave, String vigente) {
		return repository.findByEmailAndClaveAndVigente(email, clave, vigente);
	}

	@Override
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
