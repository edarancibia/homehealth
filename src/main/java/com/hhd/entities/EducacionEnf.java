package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "educacion_enf")
public class EducacionEnf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideducacionef")
    private Long idEducacionEnf;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "rut_usu")
    private String rutUsu;

    public Long getIdEducacionEnf() {
        return idEducacionEnf;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdEducacionEnf(Long idEducacionEnf) {
        this.idEducacionEnf = idEducacionEnf;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    
    public String getRutUsu() {
		return rutUsu;
	}

	public void setRutUsu(String rutUsu) {
		this.rutUsu = rutUsu;
	}

	public EducacionEnf(Long idEducacionEnf, Long idFicha, Date fecha, String descripcion, String rutUsu) {
        this.idEducacionEnf = idEducacionEnf;
        this.idFicha = idFicha;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.rutUsu = rutUsu;
    }

    public EducacionEnf() {

    }
}
