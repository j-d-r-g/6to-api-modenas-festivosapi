package com.festivos.festivosapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "calendario")
public class CalendarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private String tipo;

    public CalendarioEntity() {}

    public CalendarioEntity(String fecha, String tipo) {
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}