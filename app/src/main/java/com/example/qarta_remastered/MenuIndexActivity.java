package com.example.qarta_remastered;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qarta_remastered.Adaptadores.Productos;
import com.example.qarta_remastered.Models.Menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuIndexActivity extends AppCompatActivity implements Serializable {

    TextView tvCantProductos, BienvenidoLocal, MesaLocal;
    Button btnVerCarro;
    RecyclerView rvListaProductos;
    Productos adaptador;

    List<Menu> listaProductos = new ArrayList<>();
    List<Menu> carrito = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_index);

        try {

            tvCantProductos = (TextView) findViewById(R.id.tvCantProductos);
            btnVerCarro = (Button) findViewById(R.id.btnVerCarro);
            rvListaProductos = (RecyclerView) findViewById(R.id.rvListaProductos);
            rvListaProductos.setLayoutManager(new GridLayoutManager(MenuIndexActivity.this, 1));
            BienvenidoLocal = (TextView) findViewById(R.id.BienvenidoLocal);
            MesaLocal = (TextView) findViewById(R.id.MesaLocal);

            //Rescatar productos del local escaneado
            String idLocal = getIntent().getExtras().getString("idLocal");
            String idMesa = getIntent().getExtras().getString("idMesa");
            String nombreLocal = getIntent().getExtras().getString("Nombre");

            MesaLocal.setText("Mesa n°" + idMesa);

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


            BienvenidoLocal.setText("Bienvenido/a a " + nombreLocal);

            Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Menu WHERE Localid == ?", new String[]{idLocal});

            List<String> items = new ArrayList<String>();
            try{
                while (filas.moveToNext()){
                    //Se ingresan los productos a la lista
                    listaProductos.add(new Menu(filas.getString(0),filas.getString(1),filas.getString(2),filas.getString(3),filas.getString(4),filas.getString(5),filas.getString(6),filas.getString(7)));
                }
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            adaptador = new Productos(MenuIndexActivity.this, tvCantProductos,btnVerCarro,listaProductos,carrito);
            rvListaProductos.setAdapter(adaptador);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
