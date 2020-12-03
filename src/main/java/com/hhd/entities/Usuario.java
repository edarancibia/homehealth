package com.hhd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "ape_pat")
	private String aPat;
	
	@Column(name = "ape_mat")
	private String aMat;
	
	@Column(name = "vigente")
	private String vigente;
	
	@Column(name = "rutnum")
	private String rutNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getaPat() {
		return aPat;
	}
	public void setaPat(String aPat) {
		this.aPat = aPat;
	}
	public String getaMat() {
		return aMat;
	}
	public void setaMat(String aMat) {
		this.aMat = aMat;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	
	public String getRutNum() {
		return rutNum;
	}
	public void setRutNum(String rutNum) {
		this.rutNum = rutNum;
	}
	
	public Usuario(int id, String nombre, String email, String clave, String role, String aPat, String aMat,
			String vigente, String rutNum) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.role = role;
		this.aPat = aPat;
		this.aMat = aMat;
		this.vigente = vigente;
		this.rutNum = rutNum;
	}
	
	public Usuario() {
		
	}
	
}
