package david.test.sqlite;

import java.io.Serializable;

public class Menu implements Serializable {

    private String id;
    private String Nombre;
    private String precio;
    private String Numero_ventas;
    private String Imagen_menuid;
    private String Ofetaid;
    private String Localid;

    public Menu(String id, String nombre, String precio, String numero_ventas, String imagen_menuid, String ofetaid, String localid) {
        this.id = id;
        this.Nombre = nombre;
        this.precio = precio;
        this.Numero_ventas = numero_ventas;
        this.Imagen_menuid = imagen_menuid;
        this.Ofetaid = ofetaid;
        this.Localid = localid;
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