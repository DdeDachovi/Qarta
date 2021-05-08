package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Imagen_menu implements Serializable {

    private String id;
    private String imagen;

    public Imagen_menu(String id, String imagen) {
        this.id = id;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
