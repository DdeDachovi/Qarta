package david.test.sqlite;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LocalActivity extends AppCompatActivity implements Serializable{

    TextView tvCantProductos;
    Button btnVerCarro;
    RecyclerView rvListaProductos;
    AdaptadorProductos adaptador;

    List<Menu> listaProductos = new ArrayList<>();
    List<Menu> carrito = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_activity);

        btnVerCarro = (Button)findViewById(R.id.btnVerCarro);
        tvCantProductos = (TextView)findViewById(R.id.tvCantProductos);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(LocalActivity.this, 1));


        //Cargar productos a la lista
        String nombre_local = getIntent().getExtras().getString("local");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //Se rescatan los productos
        Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Menu,Local WHERE Menu.Localid = Local.id AND Local.nombre = ?", new String[]{nombre_local});

        try{
            while (filas.moveToNext()){
                //Se ingresan los productos a la lista
                listaProductos.add(new Menu(filas.getString(0),filas.getString(1),filas.getString(2),filas.getString(3),filas.getString(4),filas.getString(5),filas.getString(6)));
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        adaptador = new AdaptadorProductos(LocalActivity.this, tvCantProductos,btnVerCarro,listaProductos,carrito);
        rvListaProductos.setAdapter(adaptador);

    }
}
