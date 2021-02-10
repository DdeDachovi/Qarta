package david.test.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String Local = "CREATE TABLE Local (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre varchar(255) NOT NULL UNIQUE, direccion varchar(255) NOT NULL UNIQUE, ciudad varchar(255) NOT NULL, Usuarioid integer(10) NOT NULL, FOREIGN KEY(Usuarioid) REFERENCES Usuario(id));";
        String Local_usuario = "CREATE TABLE Local_Usuario (Localid integer(10) NOT NULL, Usuarioid integer(10) NOT NULL, Cargoid integer(10) NOT NULL, Areaid integer(10) NOT NULL, PRIMARY KEY (Localid, Usuarioid), FOREIGN KEY(Areaid) REFERENCES Area(id), FOREIGN KEY(Cargoid) REFERENCES Cargo(id), FOREIGN KEY(Localid) REFERENCES Local(id), FOREIGN KEY(Usuarioid) REFERENCES Usuario(id));";
        String Menu  = "CREATE TABLE Menu (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre varchar(255) NOT NULL, precio integer(10) NOT NULL, numero_ventas integer(10) NOT NULL, Imagen_menuid integer(10), Ofertaid integer(10), Localid integer(10) NOT NULL, FOREIGN KEY(Localid) REFERENCES Local(id), FOREIGN KEY(Ofertaid) REFERENCES Oferta(id), FOREIGN KEY(Imagen_menuid) REFERENCES Imagen_menu(id));";
        String Mesa = "CREATE TABLE Mesa (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, numero_mesa integer(10) NOT NULL, Localid integer(10) NOT NULL, FOREIGN KEY(Localid) REFERENCES Local(id));";
        String Oferta = "CREATE TABLE Oferta (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, descuento integer(10) NOT NULL);";
        String Usuario = "CREATE TABLE Usuario (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rut integer(10) NOT NULL UNIQUE, nombres varchar(255) NOT NULL, apellidos varchar(255) NOT NULL, telefono integer(10) NOT NULL UNIQUE, email varchar(255) NOT NULL UNIQUE);";
        String Usuario_ventas = "CREATE TABLE Usuario_Ventas (Usuarioid integer(10) NOT NULL, Ventasid integer(10) NOT NULL, PRIMARY KEY (Usuarioid, Ventasid), FOREIGN KEY(Ventasid) REFERENCES Ventas(id), FOREIGN KEY(Usuarioid) REFERENCES Usuario(id));";
        String Venta = "CREATE TABLE Venta (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, numero_boleta integer(10) NOT NULL UNIQUE, Localid integer(10) NOT NULL, Mesasid integer(10) NOT NULL, FOREIGN KEY(Mesasid) REFERENCES Mesas(id), FOREIGN KEY(Localid) REFERENCES Local(id));";
        String Ventas_menu = "CREATE TABLE Ventas_Menu (Ventasid integer(10) NOT NULL, Menuid integer(10) NOT NULL, cantidad integer(10) DEFAULT 0 NOT NULL, PRIMARY KEY (Ventasid, Menuid), FOREIGN KEY(Menuid) REFERENCES Menu(id), FOREIGN KEY(Ventasid) REFERENCES Ventas(id));";

        BaseDeDatos.execSQL(Area);
        BaseDeDatos.execSQL(Cargo);
        BaseDeDatos.execSQL(Imagen_menu);
        BaseDeDatos.execSQL(Local);
        BaseDeDatos.execSQL(Local_usuario);
        BaseDeDatos.execSQL(Menu);
        BaseDeDatos.execSQL(Mesa);
        BaseDeDatos.execSQL(Oferta);
        BaseDeDatos.execSQL(Usuario);
        BaseDeDatos.execSQL(Usuario_ventas);
        BaseDeDatos.execSQL(Venta);
        BaseDeDatos.execSQL(Ventas_menu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
