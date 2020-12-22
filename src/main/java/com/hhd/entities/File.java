package com.hhd.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfiles")
	private Long idFile;
	
	@Column(name = "idficha")
	private int idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "rut_usu")
	private int rutUsu;
	
	@Column(name = "rut_pac")
	private int rutPac;

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRutUsu() {
		return rutUsu;
	}

	public void setRutUsu(int rutUsu) {
		this.rutUsu = rutUsu;
	}

	public int getRutPac() {
		return rutPac;
	}

	public void setRutPac(int rutPac) {
		this.rutPac = rutPac;
	}
	
	public File(Long idFile, int idFicha, Date fecha, String descripcion, String url, int rutUsu, int rutPac) {
		this.idFile = idFile;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.url = url;
		this.rutUsu = rutUsu;
		this.rutPac = rutPac;
	}

	public File() {
		
	}

	
}
