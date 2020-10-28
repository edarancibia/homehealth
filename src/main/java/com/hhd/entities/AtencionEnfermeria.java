package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "atencion_enfermeria")
public class AtencionEnfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idatencionenf")
    private Long idAtencionEnf;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "estado")
    private int estado;

    public Long getIdAtencionEnf() {
        return idAtencionEnf;
    }

    public Long getIdFicha() {
        return idFicha;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setIdAtencionEnf(Long idAtencionEnf) {
        this.idAtencionEnf = idAtencionEnf;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public AtencionEnfermeria(Long idAtencionEnf, Long idFicha, Date fecha, int estado) {
        this.idAtencionEnf = idAtencionEnf;
        this.idFicha = idFicha;
        this.fecha = fecha;
        this.estado = estado;
    }

    public AtencionEnfermeria() {

    }
}
