package com.example.qarta_remastered;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LocalCocinaActivity extends AppCompatActivity {

    ListView Locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_cocina);

        Locales = (ListView) findViewById(R.id.lista_locales);

        try{

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Local ", null);
            List<String> items = new ArrayList<String>();

            try {
                while (filas.moveToNext()) {
                    String local = filas.getString(1) ;
                    items.add(local);
                }
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            filas.close();
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            Locales.setAdapter(adapter);

            Locales.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String local = (String)adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(LocalCocinaActivity.this, CocinaIndexActivity.class);
                    intent.putExtra("Local", local);
                    startActivity(intent);
                }
            });

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
