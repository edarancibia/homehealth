package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ev_enfermeria")
public class EvEnfermeria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idevenfermeria")
	private Long idEvolucionEnf;
	
	@Column(name = "id_ficha")
	private int idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "rut_usu")
	private int rutUsu;

	public Long getIdEvolucionEnf() {
		return idEvolucionEnf;
	}

	public void setIdEvolucionEnf(Long idEvolucionEnf) {
		this.idEvolucionEnf = idEvolucionEnf;
	}

	public int getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getRutUsu() {
		return rutUsu;
	}

	public void setRutUsu(int rutUsu) {
		this.rutUsu = rutUsu;
	}

	public EvEnfermeria(Long idEvolucionEnf, int idFicha, Date fecha, String descripcion, int rutUsu) {
		this.idEvolucionEnf = idEvolucionEnf;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.rutUsu = rutUsu;
	}

	public EvEnfermeria() {

	}

}
