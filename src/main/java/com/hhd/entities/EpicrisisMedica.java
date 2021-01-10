package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "epicrisis_medica")
public class EpicrisisMedica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idepicrisismedica")
	private Long idEpicrisis;
	
	@Column(name = "id_ficha")
	private int idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "resumen")
	private String resumen;
	
	@Column(name = "indicaciones")
	private String indicaciones;
	
	@Column(name = "diagnostico")
	private String diagnostico;
	
	@Column(name = "rut_usu")
	private String rutUsu;

	public Long getIdEpicrisis() {
		return idEpicrisis;
	}

	public void setIdEpicrisis(Long idEpicrisis) {
		this.idEpicrisis = idEpicrisis;
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
	
	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getIndicaciones() {
		return indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getRutUsu() {
		return rutUsu;
	}

	public void setRutUsu(String rutUsu) {
		this.rutUsu = rutUsu;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public EpicrisisMedica(Long idEpicrisis, int idFicha, Date fecha, String resumen, String indicaciones,
			String diagnostico, String rutUsu) {
		this.idEpicrisis = idEpicrisis;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.resumen = resumen;
		this.indicaciones = indicaciones;
		this.diagnostico = diagnostico;
		this.rutUsu = rutUsu;
	}

	public EpicrisisMedica() {
		super();
	}

}
