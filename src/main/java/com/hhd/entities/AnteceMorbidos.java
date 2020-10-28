package com.hhd.entities;

import javax.persistence.*;

@Entity
@Table(name = "antece_morbidos")
public class AnteceMorbidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idantecemorbidos")
    private Long idAnteceMorbidos;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getIdAnteceMorbidos() {
        return idAnteceMorbidos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdAnteceMorbidos(Long idAnteceMorbidos) {
        this.idAnteceMorbidos = idAnteceMorbidos;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AnteceMorbidos(Long idAnteceMorbidos, String descripcion) {
        this.idAnteceMorbidos = idAnteceMorbidos;
        this.descripcion = descripcion;
    }

    public AnteceMorbidos() {

    }
}
