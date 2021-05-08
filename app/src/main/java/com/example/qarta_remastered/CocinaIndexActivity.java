package com.example.qarta_remastered;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.qarta_remastered.Adaptadores.CarroCompra;
import com.example.qarta_remastered.Models.Menu;

import java.util.ArrayList;
import java.util.List;

public class CocinaIndexActivity extends AppCompatActivity{

    ListView lista_ventas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocina_index);

        try{
            lista_ventas = (ListView)findViewById(R.id.lista_ventas);
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            String Local = getIntent().getExtras().getString("Local");
            Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Ventas v, Local l WHERE v.Localid == l.id AND l.nombre == ?", new String[]{Local});
            List<String> items = new ArrayList<String>();

            try {
                while (filas.moveToNext()) {
                    String local = filas.getString(0) + "," + filas.getString(10) + "," + filas.getString(1);
                    items.add(local);
                }
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            filas.close();
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            lista_ventas.setAdapter(adapter);

            lista_ventas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String venta = (String)adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(CocinaIndexActivity.this, ProductosCocinaActivity.class);
                    intent.putExtra("Venta", venta);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
