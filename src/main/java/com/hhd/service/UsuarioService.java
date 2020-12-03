package com.hhd.service;

import com.hhd.entities.Usuario;

public interface UsuarioService {

	public abstract Usuario addUsuario(Usuario usuario);
	public abstract Usuario findByNombre(String nombre);
	Usuario findByEmail(String email);
	Usuario findByEmailAndClaveAndVigente(String email, String clave, String vigente);
}
