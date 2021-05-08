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

public class ProductosCocinaActivity extends AppCompatActivity {

    ListView lista_productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_cocina);

        try{

            String venta = this.getIntent().getExtras().getString("Venta");
            String[] boleta =  venta.split(",");
            lista_productos = (ListView)findViewById(R.id.lista_productos);
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Ventas_menu vm, Menu m WHERE vm.Ventasid == ? AND vm.Menuid == m.id", new String[]{boleta[0]});
            List<String> items = new ArrayList<String>();

            if(filas == null){

                String sql = "UPDATE Ventas SET estado = ? WHERE id == ?";
                BaseDeDatos.execSQL(sql, new String[]{"2", boleta[1]});
                Toast.makeText(this,"Se ha completado la orden",Toast.LENGTH_LONG).show();

            }else{

                try {
                    while (filas.moveToNext()) {
                        String estado = "";
                        switch (filas.getString(7)){
                            case "0":
                                estado = "Orden tomada";
                                break;
                            case "1":
                                estado = "En preparacion";
                                break;
                            case "2":
                                estado = "Orden lista";
                                break;
                        }
                        String local = "Producto:" + filas.getString(9) + "" +
                                "- Estado:" + estado;
                        items.add(local);
                    }
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            filas.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            lista_productos.setAdapter(adapter);


            lista_productos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String menu = (String)adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(ProductosCocinaActivity.this, ProductoPreparacionActivity.class);
                    intent.putExtra("Menu", menu);
                    startActivity(intent);
                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
