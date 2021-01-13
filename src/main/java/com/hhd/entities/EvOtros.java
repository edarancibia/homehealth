package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ev_otros_prof")
public class EvOtros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idevotros")
	private Long idEvolucion;
	
	@Column(name = "id_ficha")
	private int idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "rut_usu")
	private String rutUsu;

	public Long getIdEvolucion() {
		return idEvolucion;
	}

	public void setIdEvolucion(Long idEvolucion) {
		this.idEvolucion = idEvolucion;
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

	public String getRutUsu() {
		return rutUsu;
	}

	public void setRutUsu(String rutUsu) {
		this.rutUsu = rutUsu;
	}

	public EvOtros(Long idEvolucion, int idFicha, Date fecha, String descripcion, String rutUsu) {
		this.idEvolucion = idEvolucion;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.rutUsu = rutUsu;
	}

	public EvOtros() {
		
	}
	
}
