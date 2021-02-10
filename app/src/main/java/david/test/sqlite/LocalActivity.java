package david.test.sqlite;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LocalActivity extends AppCompatActivity implements Serializable{

    ListView productos;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_activity);

        //Cargar Locales a la lista
        String nombre_local = getIntent().getExtras().getString("local");
        productos = (ListView)findViewById(R.id.Productos);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Menu,Local WHERE Menu.Localid = Local.id AND Local.nombre = ?", new String[]{nombre_local});
        List<String> items = new ArrayList<String>();
        try{
            while (filas.moveToNext()){
                String local = filas.getString(1) + " Precio: $" + filas.getString(2);
                items.add(local);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        filas.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, items );
        productos.setAdapter(adapter);
    }
}
