package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Mesas implements Serializable {

    private String id;
    private String numero_mesa;
    private String Localid;
    private String estado;

    public Mesas(String id, String numero_mesa, String localid, String estado) {
        this.id = id;
        this.numero_mesa = numero_mesa;
        this.estado = estado;
        Localid = localid;
    }

    public String getId() {
        return id;
    }

    public String getNumero_mesa() {
        return numero_mesa;
    }

    public String getLocalid() {
        return Localid;
    }

    public void setNumero_mesa(String numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public void setLocalid(String localid) {
        Localid = localid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
