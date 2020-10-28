package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ev_kine")
public class EvKine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idevkine")
    private Long idEvolucionKine;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "rut_usu")
    private int rutUsu;

    public Long getIdEvolucionKine() {
        return idEvolucionKine;
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

    public void setIdEvolucionKine(Long idEvolucionKine) {
        this.idEvolucionKine = idEvolucionKine;
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

    public Long getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public EvKine(Long idEvolucionKine, Long idFicha, Date fecha, String descripcion, int rutUsu) {
        this.idEvolucionKine = idEvolucionKine;
        this.idFicha = idFicha;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.rutUsu = rutUsu;
    }

    public EvKine() {

    }
}
