package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Ventas_menu implements Serializable {

    private String Ventasid;
    private String Menuid;
    private String cantidad;
    private String fecha_pedido;
    private String fecha_entrega;
    private String fecha_alargue;
    private String estado;

    public Ventas_menu(String ventasid, String menuid, String cantidad, String fecha_pedido, String fecha_entrega, String fecha_alargue, String estado) {
        Ventasid = ventasid;
        Menuid = menuid;
        this.cantidad = cantidad;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.fecha_alargue = fecha_alargue;
        this.estado = estado;
    }

    public String getVentasid() {
        return Ventasid;
    }

    public String getMenuid() {
        return Menuid;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public String getFecha_alargue() {
        return fecha_alargue;
    }

    public void setVentasid(String ventasid) {
        Ventasid = ventasid;
    }

    public void setMenuid(String menuid) {
        Menuid = menuid;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public void setFecha_alargue(String fecha_alargue) {
        this.fecha_alargue = fecha_alargue;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
