package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Rol_usuario implements Serializable {

    private String id;
    private String rol;

    public Rol_usuario(String id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
