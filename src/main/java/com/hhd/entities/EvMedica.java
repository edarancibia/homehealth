package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ev_medica")
public class EvMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevmedica")
    private Long idEvMedica;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "rut_usu")
    private int rutUsu;
    
    @Column(name = "id_ficha")
    private int idFicha;

    public Long getIdEvMedica() {
        return idEvMedica;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getRutUsu() {
        return rutUsu;
    }

    public void setIdEvMedica(Long idEvMedica) {
        this.idEvMedica = idEvMedica;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRutUsu(int rutUsu) {
        this.rutUsu = rutUsu;
    }

    public int getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}

    public EvMedica(Long idEvMedica, Date fecha, String descripcion, int rutUsu, int idFicha) {
		super();
		this.idEvMedica = idEvMedica;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.rutUsu = rutUsu;
		this.idFicha = idFicha;
	}

	public EvMedica() {

    }
}
