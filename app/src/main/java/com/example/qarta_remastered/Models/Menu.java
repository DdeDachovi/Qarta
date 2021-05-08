package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Menu implements Serializable {

    private String id;
    private String Nombre;
    private String precio;
    private String Numero_ventas;
    private String tiempo_estimado_entrega;
    private String Imagen_menuid;
    private String Ofetaid;
    private String Localid;

    public Menu(String id, String nombre, String precio, String numero_ventas,String tiempo_estimado_entrega, String imagen_menuid, String ofetaid, String localid) {
        this.id = id;
        this.Nombre = nombre;
        this.precio = precio;
        this.Numero_ventas = numero_ventas;
        this.tiempo_estimado_entrega = tiempo_estimado_entrega;
        this.Imagen_menuid = imagen_menuid;
        this.Ofetaid = ofetaid;
        this.Localid = localid;
    }

    public void setTiempo_estimado_entrega(String tiempo_estimado_entrega) {
        this.tiempo_estimado_entrega = tiempo_estimado_entrega;
    }

    public String getTiempo_estimado_entrega() {
        return tiempo_estimado_entrega;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNumero_ventas() {
        return Numero_ventas;
    }

    public void setNumero_ventas(String numero_ventas) {
        Numero_ventas = numero_ventas;
    }

    public String getImagen_menuid() {
        return Imagen_menuid;
    }

    public void setImagen_menuid(String imagen_menuid) {
        Imagen_menuid = imagen_menuid;
    }

    public String getOfetaid() {
        return Ofetaid;
    }

    public void setOfetaid(String ofetaid) {
        Ofetaid = ofetaid;
    }

    public String getLocalid() {
        return Localid;
    }

    public void setLocalid(String localid) {
        Localid = localid;
    }
}