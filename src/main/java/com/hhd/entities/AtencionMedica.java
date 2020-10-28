package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "atencion_medica")
public class AtencionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idatencionmedica")
    private Long idAtencionMedica;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "examen_fisico")
    private String examenFisico;

    @Column(name = "anamnesis")
    private String anamnesis;

    @Column(name = "diag_presuntivo")
    private String diagPresuntivo;

    @Column(name = "ind_domicilio")
    private String indDomicilio;

    @Column(name = "rut_usu")
    private int rutUsu;

    @Column(name = "estado")
    private int estado;

    public Long getIdAtencionMedica() {
        return idAtencionMedica;
    }

    public Long getIdFicha() {
        return idFicha;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public String getDiagPresuntivo() {
        return diagPresuntivo;
    }

    public String getIndDomicilio() {
        return indDomicilio;
    }

    public int getRutUsu() {
        return rutUsu;
    }

    public int getEstado() {
        return estado;
    }

    public void setIdAtencionMedica(Long idAtencionMedica) {
        this.idAtencionMedica = idAtencionMedica;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public void setDiagPresuntivo(String diagPresuntivo) {
        this.diagPresuntivo = diagPresuntivo;
    }

    public void setIndDomicilio(String indDomicilio) {
        this.indDomicilio = indDomicilio;
    }

    public void setRutUsu(int rutUsu) {
        this.rutUsu = rutUsu;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public AtencionMedica(Long idAtencionMedica, Long idFicha, Date fecha, String examenFisico, String anamnesis, String diagPresuntivo, String indDomicilio, int rutUsu, int estado) {
        this.idAtencionMedica = idAtencionMedica;
        this.idFicha = idFicha;
        this.fecha = fecha;
        this.examenFisico = examenFisico;
        this.anamnesis = anamnesis;
        this.diagPresuntivo = diagPresuntivo;
        this.indDomicilio = indDomicilio;
        this.rutUsu = rutUsu;
        this.estado = estado;
    }

    public AtencionMedica() {

    }
}
