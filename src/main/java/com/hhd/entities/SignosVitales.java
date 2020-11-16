package com.hhd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "signos_vitales")
public class SignosVitales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsignos")
    private Long idSignos;

    @Column(name = "id_atencion_enf")
    private Long idAtencionEnf;

    @Column(name = "id_ficha")
    private Long idFicha;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "fc")
    private String fc;

    @Column(name = "sat")
    private String sat;

    @Column(name = "pa")
    private String pa;

    @Column(name = "tax")
    private String tax;

    @Column(name = "hgt")
    private String hgt;

    @Column(name = "rut_usu")
    private int rutUsu; //usuario responsable
    
    @Column(name = "resp")
    private String resp;

    public Long getIdSignos() {
        return idSignos;
    }

    public Long getIdAtencionEnf() {
        return idAtencionEnf;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getFc() {
        return fc;
    }

    public String getSat() {
        return sat;
    }

    public String getPa() {
        return pa;
    }

    public String getTax() {
        return tax;
    }

    public String getHgt() {
        return hgt;
    }

    public int getRutUsu() {
        return rutUsu;
    }

    public void setIdSignos(Long idSignos) {
        this.idSignos = idSignos;
    }

    public void setIdAtencionEnf(Long idAtencionEnf) {
        this.idAtencionEnf = idAtencionEnf;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
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

    public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

    public SignosVitales(Long idSignos, Long idAtencionEnf, Long idFicha, Date fecha, String fc, String sat, String pa,
			String tax, String hgt, int rutUsu, String resp) {
		this.idSignos = idSignos;
		this.idAtencionEnf = idAtencionEnf;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.fc = fc;
		this.sat = sat;
		this.pa = pa;
		this.tax = tax;
		this.hgt = hgt;
		this.rutUsu = rutUsu;
		this.resp = resp;
	}

	public SignosVitales() {

    }
}
