package david.test.sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ListView locales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        //Cargar Locales a la lista
        locales = (ListView) findViewById(R.id.Locales);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Local", null);
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
        locales.setAdapter(adapter);


        locales.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String asd = (String)adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MenuActivity.this, LocalActivity.class);
                intent.putExtra("local", asd);
                startActivity(intent);
            }
        });
    }

}
