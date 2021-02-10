package david.test.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity{

    EditText price,name;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_activity);

        price = (EditText)findViewById(R.id.Price);
        name = (EditText)findViewById(R.id.Name);
        spinner = (Spinner)findViewById(R.id.spinner);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
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
        spinner.setAdapter(adapter);
    }

    public void AddProducto(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = name.getText().toString();
        String precio = price.getText().toString();
        String spin = spinner.getSelectedItem().toString();

        if(!nombre.isEmpty() && !precio.isEmpty() && !spin.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("precio", precio);
            registro.put("numero_ventas",0);
            registro.put("Localid",spin);

            BaseDeDatos.insert("Menu", null, registro);
            BaseDeDatos.close();

            //set text
            name.setText("");
            price.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //consultar
    public void BuscarProducto(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = name.getText().toString();
        String spin = spinner.getSelectedItem().toString();

        if (!nombre.isEmpty() && !spin.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("SELECT precio FROM Menu WHERE nombre = ? AND Localid = ?", new String[]{nombre,spin});

            if(fila.moveToFirst()) {
                price.setText(fila.getString(0));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el producto en el local especificado", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debe introducir el nombre y el id del local", Toast.LENGTH_SHORT).show();
        }
    }

}
