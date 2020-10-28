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

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "rut_usu")
    private int rutUsu;

    public Long getIdEducacionEnf() {
        return idEducacionEnf;
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

    public void setIdEducacionEnf(Long idEducacionEnf) {
        this.idEducacionEnf = idEducacionEnf;
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

    public EducacionEnf(Long idEducacionEnf, Date fecha, String descripcion, int rutUsu) {
        this.idEducacionEnf = idEducacionEnf;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.rutUsu = rutUsu;
    }

    public EducacionEnf() {

    }
}
