package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "indicaciones_alta")
public class IndicacionesAlta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idindicaciones")
	private Long idIndicaciones;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "id_ficha")
	private int idFicha;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "rut_usu")
	private int rutUsu;

	public Long getIdIndicaciones() {
		return idIndicaciones;
	}

	public void setIdIndicaciones(Long idIndicaciones) {
		this.idIndicaciones = idIndicaciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
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

	public IndicacionesAlta(Long idIndicaciones, Date fecha, int idFicha, String descripcion, int rutUsu) {
		this.idIndicaciones = idIndicaciones;
		this.fecha = fecha;
		this.idFicha = idFicha;
		this.descripcion = descripcion;
		this.rutUsu = rutUsu;
	}
	
}
