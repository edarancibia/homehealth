package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ingreso")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idingreso")
    private Long idIngreso;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "id_cesfam")
    private Long idCesfam;

    @Column(name = "ficha_clinica")
    private int fichaClinica;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @Column(name = "telefono_cuidador")
    private String telefonoCuidador;

    @Column(name = "derivado_resp")
    private String derivadoCresp;

    @Column(name = "rut_med_deriva")
    private int rutMedDeriva;

    @Column(name = "diag1")
    private String diag1;

    @Column(name = "diag2")
    private String diag2;

    @Column(name = "diag3")
    private String diag3;

    @Column(name = "diag4")
    private String diag4;

    @Column(name = "plan_tto1")
    private String planTto1;

    @Column(name = "plan_tto2")
    private String planTto2;

    @Column(name = "plan_tto3")
    private String planTto3;

    @Column(name = "plan_tto4")
    private String planTto4;

    public Long getIdIngreso() {
        return idIngreso;
    }

    public Long getIdFicha() {
        return idFicha;
    }

    public Date getFecha() {
        return fecha;
    }

    public Long getIdCesfam() {
        return idCesfam;
    }

    public int getFichaClinica() {
        return fichaClinica;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public String getTelefonoCuidador() {
        return telefonoCuidador;
    }

    public String getDerivadoCresp() {
        return derivadoCresp;
    }

    public int getRutMedDeriva() {
        return rutMedDeriva;
    }

    public String getDiag1() {
        return diag1;
    }

    public String getDiag2() {
        return diag2;
    }

    public String getDiag3() {
        return diag3;
    }

    public String getDiag4() {
        return diag4;
    }

    public String getPlanTto1() {
        return planTto1;
    }

    public String getPlanTto2() {
        return planTto2;
    }

    public String getPlanTto3() {
        return planTto3;
    }

    public String getPlanTto4() {
        return planTto4;
    }

    public void setIdIngreso(Long idIngreso) {
        this.idIngreso = idIngreso;
    }

    public void setIdFicha(Long idFicha) {
        this.idFicha = idFicha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdCesfam(Long idCesfam) {
        this.idCesfam = idCesfam;
    }

    public void setFichaClinica(int fichaClinica) {
        this.fichaClinica = fichaClinica;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public void setTelefonoCuidador(String telefonoCuidador) {
        this.telefonoCuidador = telefonoCuidador;
    }

    public void setDerivadoCresp(String derivadoCresp) {
        this.derivadoCresp = derivadoCresp;
    }

    public void setRutMedDeriva(int rutMedDeriva) {
        this.rutMedDeriva = rutMedDeriva;
    }

    public void setDiag1(String diag1) {
        this.diag1 = diag1;
    }

    public void setDiag2(String diag2) {
        this.diag2 = diag2;
    }

    public void setDiag3(String diag3) {
        this.diag3 = diag3;
    }

    public void setDiag4(String diag4) {
        this.diag4 = diag4;
    }

    public void setPlanTto1(String planTto1) {
        this.planTto1 = planTto1;
    }

    public void setPlanTto2(String planTto2) {
        this.planTto2 = planTto2;
    }

    public void setPlanTto3(String planTto3) {
        this.planTto3 = planTto3;
    }

    public void setPlanTto4(String planTto4) {
        this.planTto4 = planTto4;
    }

    public Ingreso(Long idIngreso, Long idFicha, Date fecha, Long idCesfam, int fichaClinica, String alergias, String telefonoUsuario, String telefonoCuidador, String derivadoCresp, int rutMedDeriva, String diag1, String diag2, String diag3, String diag4, String planTto1, String planTto2, String planTto3, String planTto4) {
        this.idIngreso = idIngreso;
        this.idFicha = idFicha;
        this.fecha = fecha;
        this.idCesfam = idCesfam;
        this.fichaClinica = fichaClinica;
        this.alergias = alergias;
        this.telefonoUsuario = telefonoUsuario;
        this.telefonoCuidador = telefonoCuidador;
        this.derivadoCresp = derivadoCresp;
        this.rutMedDeriva = rutMedDeriva;
        this.diag1 = diag1;
        this.diag2 = diag2;
        this.diag3 = diag3;
        this.diag4 = diag4;
        this.planTto1 = planTto1;
        this.planTto2 = planTto2;
        this.planTto3 = planTto3;
        this.planTto4 = planTto4;
    }

    public Ingreso() {

    }
}
