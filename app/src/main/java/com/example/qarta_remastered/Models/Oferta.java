package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Oferta implements Serializable {

    private String id;
    private String descuento;
    private String fecha_inicio;
    private String fecha_termino;

    public Oferta(String id, String descuento, String fecha_inicio, String fecha_termino) {
        this.id = id;
        this.descuento = descuento;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
    }

    public String getId() {
        return id;
    }

    public String getDescuento() {
        return descuento;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }
}
