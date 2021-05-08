package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Local_Usuario implements Serializable {

    private String Localid;
    private String Usuarioid;
    private String Rol_usuarioid;

    public Local_Usuario(String localid, String usuarioid, String rol_usuarioid) {
        Localid = localid;
        Usuarioid = usuarioid;
        Rol_usuarioid = rol_usuarioid;
    }

    public String getLocalid() {
        return Localid;
    }

    public String getUsuarioid() {
        return Usuarioid;
    }

    public String getRol_usuarioid() {
        return Rol_usuarioid;
    }
}
