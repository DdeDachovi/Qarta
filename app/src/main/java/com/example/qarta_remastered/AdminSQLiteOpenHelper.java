package com.example.qarta_remastered;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        String Area = "CREATE TABLE Area (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, area varchar(255) NOT NULL UNIQUE);";
        String Cargo = "CREATE TABLE Cargo (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, cargo varchar(255) NOT NULL UNIQUE);";
        String Imagen_menu = "CREATE TABLE Imagen_menu (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, imagen varchar(255) NOT NULL UNIQUE);";
        String Local = "CREATE TABLE Local (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre varchar(255) NOT NULL UNIQUE, direccion varchar(255) NOT NULL UNIQUE, ciudad varchar(255) NOT NULL, horario varchar(255) NOT NULL, contacto integer(10) NOT NULL, logo varchar(255) NOT NULL, instagram varchar(255), Usuarioid integer(10) NOT NULL);";
        String Local_Usuario = "CREATE TABLE Local_Usuario (Localid integer(10) NOT NULL, Usuarioid integer(10) NOT NULL, Rol_usuarioid integer(10) NOT NULL, PRIMARY KEY (Localid, Usuarioid, Rol_usuarioid));";
        String Menu  = "CREATE TABLE Menu (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre varchar(255) NOT NULL, precio integer(10) NOT NULL, numero_ventas integer(10) NOT NULL, tiempo_estimado_entrega date, Imagen_menuid integer(10), Ofertaid integer(10), Localid integer(10) NOT NULL);";
        String Mesas = "CREATE TABLE Mesas (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, numero_mesa integer(10) NOT NULL, Localid integer(10) NOT NULL, estado integer(10) NOT NULL);";
        String Oferta = "CREATE TABLE Oferta (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, descuento integer(10) NOT NULL, fecha_inicio date NOT NULL, fecha_termino date NOT NULL);";
        String Usuario = "CREATE TABLE Usuario (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rut integer(10) NOT NULL UNIQUE, nombres varchar(255) NOT NULL, apellidos varchar(255) NOT NULL, telefono integer(10) NOT NULL UNIQUE, email varchar(255) NOT NULL UNIQUE, password varchar(255) NOT NULL);";
        String Usuario_Ventas = "CREATE TABLE Usuario_Ventas (Usuarioid integer(10) NOT NULL, Ventasid integer(10) NOT NULL, PRIMARY KEY (Usuarioid, Ventasid));";
        String Ventas = "CREATE TABLE Ventas (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, numero_boleta integer(10) NOT NULL, total integer(10) NOT NULL, iva integer(10) NOT NULL, descuento integer(10) NOT NULL, estado integer(10) NOT NULL, da_propina boolean NOT NULL, propina integer(10), Usuarioid integer(10) NOT NULL, Localid integer(10) NOT NULL, Mesasid integer(10) NOT NULL);";
        String Ventas_Menu = "CREATE TABLE Ventas_Menu (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Ventasid integer(10) NOT NULL, Menuid integer(10) NOT NULL, cantidad integer(10) DEFAULT 0 NOT NULL, fecha_pedido date NOT NULL, fecha_entrega date, fecha_alargue date, estado integer(10) NOT NULL);";
        String Rol_usuario = "CREATE TABLE Rol_usuario (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rol varchar(255) NOT NULL UNIQUE);";

        BaseDeDatos.execSQL(Area);
        BaseDeDatos.execSQL(Cargo);
        BaseDeDatos.execSQL(Imagen_menu);
        BaseDeDatos.execSQL(Local);
        BaseDeDatos.execSQL(Local_Usuario);
        BaseDeDatos.execSQL(Menu);
        BaseDeDatos.execSQL(Mesas);
        BaseDeDatos.execSQL(Oferta);
        BaseDeDatos.execSQL(Usuario);
        BaseDeDatos.execSQL(Usuario_Ventas);
        BaseDeDatos.execSQL(Ventas);
        BaseDeDatos.execSQL(Ventas_Menu);
        BaseDeDatos.execSQL(Rol_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
