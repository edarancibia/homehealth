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
    private int idFicha;

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
    
    @Column(name = "hta")
    private int hta;
    
    @Column(name = "dld")
    private int dld;
    
    @Column(name = "tbc")
    private int tbc;
    
    @Column(name = "epoc")
    private int epoc;
    
    @Column(name = "lcfa")
    private int lcfa;
    
    @Column(name = "acxfa")
    private int acxfa;
    
    @Column(name = "acv")
    private int acv;
    
    @Column(name = "depre")
    private int depre;
    
    @Column(name = "ob")
    private int ob;
    
    @Column(name = "dm")
    private int dm;
    
    @Column(name = "ca")
    private int ca;
    
    @Column(name = "cardio")
    private int cardio;
    
    @Column(name = "examenes")
    private String exmanes;

    public Long getIdAtencionMedica() {
        return idAtencionMedica;
    }

    public int getIdFicha() {
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

    public void setIdFicha(int idFicha) {
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

    public int getHta() {
		return hta;
	}

	public void setHta(int hta) {
		this.hta = hta;
	}

	public int getDld() {
		return dld;
	}

	public void setDld(int dld) {
		this.dld = dld;
	}

	public int getTbc() {
		return tbc;
	}

	public void setTbc(int tbc) {
		this.tbc = tbc;
	}

	public int getEpoc() {
		return epoc;
	}

	public void setEpoc(int epoc) {
		this.epoc = epoc;
	}

	public int getLcfa() {
		return lcfa;
	}

	public void setLcfa(int lcfa) {
		this.lcfa = lcfa;
	}

	public int getAcxfa() {
		return acxfa;
	}

	public void setAcxfa(int acxfa) {
		this.acxfa = acxfa;
	}

	public int getAcv() {
		return acv;
	}

	public void setAcv(int acv) {
		this.acv = acv;
	}

	public int getDepre() {
		return depre;
	}

	public void setDepre(int depre) {
		this.depre = depre;
	}

	public int getOb() {
		return ob;
	}

	public void setOb(int ob) {
		this.ob = ob;
	}

	public int getDm() {
		return dm;
	}

	public void setDm(int dm) {
		this.dm = dm;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getCardio() {
		return cardio;
	}

	public void setCardio(int cardio) {
		this.cardio = cardio;
	}
	
	public String getExmanes() {
		return exmanes;
	}

	public void setExmanes(String exmanes) {
		this.exmanes = exmanes;
	}
	
	public AtencionMedica(Long idAtencionMedica, int idFicha, Date fecha, String examenFisico, String anamnesis,
			String diagPresuntivo, String indDomicilio, int rutUsu, int estado, int hta, int dld, int tbc, int epoc,
			int lcfa, int acxfa, int acv, int depre, int ob, int dm, int ca, int cardio, String exmanes) {
		this.idAtencionMedica = idAtencionMedica;
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.examenFisico = examenFisico;
		this.anamnesis = anamnesis;
		this.diagPresuntivo = diagPresuntivo;
		this.indDomicilio = indDomicilio;
		this.rutUsu = rutUsu;
		this.estado = estado;
		this.hta = hta;
		this.dld = dld;
		this.tbc = tbc;
		this.epoc = epoc;
		this.lcfa = lcfa;
		this.acxfa = acxfa;
		this.acv = acv;
		this.depre = depre;
		this.ob = ob;
		this.dm = dm;
		this.ca = ca;
		this.cardio = cardio;
		this.exmanes = exmanes;
	}

	public AtencionMedica() {

    }
}
