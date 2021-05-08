package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Ventas implements Serializable {

    private String id;
    private String numero_boleta;
    private String iva;
    private String descuento;
    private String propina;
    private String Localid;
    private String Mesaid;
    private String Usuarioid;
    private String estado;
    private String total;
    private String da_propina;

    public Ventas(String id, String numero_boleta, String iva, String descuento, String da_propina, String propina, String localid, String mesaid, String usuarioid, String estado, String total) {
        this.id = id;
        this.numero_boleta = numero_boleta;
        this.iva = iva;
        this.descuento = descuento;
        this.da_propina = da_propina;
        this.propina = propina;
        this.estado = estado;
        this.total = total;
        Localid = localid;
        Mesaid = mesaid;
        Usuarioid = usuarioid;
    }

    public String getId() {
        return id;
    }

    public String getNumero_boleta() {
        return numero_boleta;
    }

    public String getIva() {
        return iva;
    }

    public String getDescuento() {
        return descuento;
    }

    public String getPropina() {
        return propina;
    }

    public String getLocalid() {
        return Localid;
    }

    public String getMesaid() {
        return Mesaid;
    }

    public String getUsuarioid() {
        return Usuarioid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumero_boleta(String numero_boleta) {
        this.numero_boleta = numero_boleta;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public void setPropina(String propina) {
        this.propina = propina;
    }

    public void setLocalid(String localid) {
        Localid = localid;
    }

    public void setMesaid(String mesaid) {
        Mesaid = mesaid;
    }

    public void setUsuarioid(String usuarioid) {
        Usuarioid = usuarioid;
    }

    public String getEstado() {
        return estado;
    }

    public String getTotal() {
        return total;
    }

    public String getDa_propina() {
        return da_propina;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setDa_propina(String da_propina) {
        this.da_propina = da_propina;
    }
}
