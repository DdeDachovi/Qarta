package com.example.qarta_remastered.Models;

import java.io.Serializable;

public class Local implements Serializable {

    private String id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String horario;
    private String contaco;
    private String logo;
    private String instagram;
    private String Usuarioid;

    public Local (String id, String nombre, String direccion, String ciudad, String horario, String contacto, String logo, String instagram, String Usuarioid){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.horario = horario;
        this.contaco = contacto;
        this.logo = logo;
        this.instagram = instagram;
        this.Usuarioid = Usuarioid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setContaco(String contaco) {
        this.contaco = contaco;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setUsuarioid(String usuarioid) {
        Usuarioid = usuarioid;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getHorario() {
        return horario;
    }

    public String getContaco() {
        return contaco;
    }

    public String getLogo() {
        return logo;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getUsuarioid() {
        return Usuarioid;
    }
}
