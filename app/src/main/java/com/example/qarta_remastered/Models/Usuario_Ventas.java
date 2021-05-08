package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Usuario_Ventas implements Serializable {

    private String Usuarioid;
    private String Ventasid;

    public Usuario_Ventas(String usuarioid, String ventasid) {
        Usuarioid = usuarioid;
        Ventasid = ventasid;
    }

    public String getUsuarioid() {
        return Usuarioid;
    }

    public String getVentasid() {
        return Ventasid;
    }
}
