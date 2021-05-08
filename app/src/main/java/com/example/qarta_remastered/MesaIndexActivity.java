package com.example.qarta_remastered;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MesaIndexActivity extends AppCompatActivity {

    TextView numeroMesa;
    Spinner idLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_index);
        numeroMesa = (TextView)findViewById(R.id.numeroMesa);
        idLocal = (Spinner)findViewById(R.id.idLocal);


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Local", null);
        List<String> items = new ArrayList<String>();
        try{
            while (filas.moveToNext()){
                String local = filas.getString(0);
                items.add(local);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        filas.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, items ) ;
        idLocal.setAdapter(adapter);
    }


    public void AddMesa(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String local = idLocal.getSelectedItem().toString();;
        String mesa = numeroMesa.getText().toString();

        if(!local.isEmpty() && !mesa.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("numero_mesa", mesa);
            registro.put("Localid", local);
            registro.put("estado", 0);

            BaseDeDatos.insert("Mesas", null, registro);
            BaseDeDatos.close();

            //set text
            numeroMesa.setText("");

            Toast.makeText(this, "Mesa agregada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
