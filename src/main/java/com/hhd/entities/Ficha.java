package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ficha")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idficha")
    private Long idFicha;

    @Column(name = "rut_pac")
    private int rutPac;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private int estado;

    public Long getIdFicha() {
        return idFicha;
    }

    public int getRutPac() {
        return rutPac;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public void setRutPac(int rutPac) {
        this.rutPac = rutPac;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Ficha(Long idFicha, int rutPac, Date fecha, int estado) {
        this.idFicha = idFicha;
        this.rutPac = rutPac;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Ficha() {

    }
}
