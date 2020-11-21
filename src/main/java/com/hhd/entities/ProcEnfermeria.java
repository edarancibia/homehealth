package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "procedimientos_enf")
public class ProcEnfermeria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprocedimientosenf")
	private Long idProcedimiento;
	
	@Column(name = "id_ficha")
	private int idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "rut_usu")
	private int rutUsu;

	public Long getIdProcedimiento() {
		return idProcedimiento;
	}

	public void setIdProcedimiento(Long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
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

	public ProcEnfermeria(Long idProcedimiento, int idFicha, Date fecha, String descripcion, int rutUsu) {
		this.idProcedimiento = idProcedimiento;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.rutUsu = rutUsu;
	}

	public ProcEnfermeria() {
	
	}
	

}
