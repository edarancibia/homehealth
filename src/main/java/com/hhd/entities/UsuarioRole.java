package com.hhd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_role")
public class UsuarioRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuariorole")
	private Long idUsuarioRole;
	
	@Column(name = "idusuario")
	private int idUsuario;
	
	@Column(name = "idrole")
	private int idRole;

	public Long getIdUsuarioRole() {
		return idUsuarioRole;
	}

	public void setIdUsuarioRole(Long idUsuarioRole) {
		this.idUsuarioRole = idUsuarioRole;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public UsuarioRole(Long idUsuarioRole, int idUsuario, int idRole) {
		this.idUsuarioRole = idUsuarioRole;
		this.idUsuario = idUsuario;
		this.idRole = idRole;
	}

	public UsuarioRole() {
		
	}
	
}
