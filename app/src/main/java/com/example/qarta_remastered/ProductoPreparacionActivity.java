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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductoPreparacionActivity extends AppCompatActivity {

    TextView Nom_producto,Estado_producto;
    Button Cocinar_producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_preparacion);

        try{

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            Nom_producto = (TextView)findViewById(R.id.Nom_producto);
            Estado_producto = (TextView)findViewById(R.id.Estado_producto);
            Cocinar_producto = (Button)findViewById(R.id.Cocinar_producto);

            String menu = this.getIntent().getExtras().getString("Menu");
            String[] nom_estado = menu.split("-");
            String nombre = nom_estado[0].split(":")[1];

            String sql = "SELECT * FROM Menu Where nombre == ?";

            Cursor producto = BaseDeDatos.rawQuery(sql,new String[]{nombre});
            producto.moveToNext();

            Nom_producto.setText(producto.getString(1));


        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void CocinarProducto(View view){

        try{

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            String nombre = Nom_producto.getText().toString();
            String sql = "SELECT * FROM Menu m, Ventas_menu vm WHERE m.id == vm.Menuid AND m.nombre == ?";
            Cursor producto = BaseDeDatos.rawQuery(sql,new String[]{nombre});
            producto.moveToNext();

            //UPDATE ESTADO
            sql = "UPDATE Ventas_menu SET estado = ? WHERE Menuid == ?";
            BaseDeDatos.execSQL(sql,new String[]{"2",producto.getString(0)});

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
