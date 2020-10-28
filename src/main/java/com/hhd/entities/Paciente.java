package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "idpaciente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "a_pat")
    private String aPat;

    @Column(name = "a_mat")
    private String aMat;

    @Column(name = "rut_num")
    private int rutNum;

    @Column(name = "rut_dig")
    private int rutDig;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "fecha_nac")
    private Date fechaNac;

    @Column(name = "edad")
    private int edad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "id_prevision")
    private Long idPrevision;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getaPat() {
        return aPat;
    }

    public String getaMat() {
        return aMat;
    }

    public int getRutNum() {
        return rutNum;
    }

    public int getRutDig() {
        return rutDig;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdPrevision() {
        return idPrevision;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setaPat(String aPat) {
        this.aPat = aPat;
    }

    public void setaMat(String aMat) {
        this.aMat = aMat;
    }

    public void setRutNum(int rutNum) {
        this.rutNum = rutNum;
    }

    public void setRutDig(int rutDig) {
        this.rutDig = rutDig;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdPrevision(Long idPrevision) {
        this.idPrevision = idPrevision;
    }

    public Paciente() {

    }

    public Paciente(Long idPaciente, String nombre, String aPat, String aMat, int rutNum, int rutDig, String direccion, Date fechaNac, int edad, String telefono, String email, Long idPrevision) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.aPat = aPat;
        this.aMat = aMat;
        this.rutNum = rutNum;
        this.rutDig = rutDig;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.idPrevision = idPrevision;
    }
}
