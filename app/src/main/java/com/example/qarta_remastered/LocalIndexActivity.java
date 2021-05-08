package com.example.qarta_remastered;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LocalIndexActivity extends AppCompatActivity {
    EditText nombre, direccion,ciudad,userid,horario,contacto,logo,instagram;
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_index);

        nombre = (EditText)findViewById(R.id.Name);
        direccion = (EditText)findViewById(R.id.Direccion);
        ciudad = (EditText)findViewById(R.id.Ciudad);
        userid = (EditText)findViewById(R.id.User_id);
        horario = (EditText)findViewById(R.id.horario);
        contacto = (EditText)findViewById(R.id.contacto);
        logo = (EditText)findViewById(R.id.logo);
        instagram = (EditText)findViewById(R.id.instagram);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();
        String dir = direccion.getText().toString();
        String city = ciudad.getText().toString();
        String user = userid.getText().toString();
        String horariost = horario.getText().toString();
        String contactost = contacto.getText().toString();
        String logost = logo.getText().toString();
        String insta = instagram.getText().toString();

        if(!name.isEmpty() && !dir.isEmpty() && !city.isEmpty() && !user.isEmpty() && !horariost.isEmpty() && !contactost.isEmpty() && !logost.isEmpty() && !insta.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("nombre", name);
            registro.put("direccion", dir);
            registro.put("ciudad", city);
            registro.put("horario", horariost);
            registro.put("contacto", contactost);
            registro.put("logo", logost);
            registro.put("instagram", insta);
            registro.put("Usuarioid", user);

            BaseDeDatos.insert("Local", null, registro);
            BaseDeDatos.close();

            nombre.setText("");
            direccion.setText("");
            ciudad.setText("");
            userid.setText("");
            horario.setText("");
            contacto.setText("");
            logo.setText("");
            instagram.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //consultar
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();

        if (!name.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("SELECT * FROM Local WHERE nombre = ?", new String[]{name});

            if(fila.moveToFirst()) {
                //Revisar indices
                direccion.setText(fila.getString(2));
                ciudad.setText(fila.getString(3));
                horario.setText(fila.getString(4));
                contacto.setText(fila.getString(5));
                logo.setText(fila.getString(6));
                instagram.setText(fila.getString(7));
                userid.setText(fila.getString(8));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el local", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debe introducir el nombre", Toast.LENGTH_SHORT).show();
        }
    }

    //DELETE

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();

        if (!name.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Local", "nombre=?", new String[]{name});
            BaseDeDatos.close();

            nombre.setText("");
            direccion.setText("");

            if(cantidad == 1) {
                Toast.makeText(this, "Local eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El local no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe introducir el nombre", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();
        String dir = direccion.getText().toString();

        if(!name.isEmpty() && !dir.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre", name);
            registro.put("direccion", dir);

            int cantidad = BaseDeDatos.update("Local", registro, "nombre =?", new String[]{name});
            BaseDeDatos.close();

            if (cantidad == 1) {
                Toast.makeText(this, "El local ha sido modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El local no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe introducir todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
