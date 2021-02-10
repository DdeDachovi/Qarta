package david.test.sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, direccion,ciudad,userid;
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.Name);
        direccion = (EditText)findViewById(R.id.Direccion);
        ciudad = (EditText)findViewById(R.id.Ciudad);
        userid = (EditText)findViewById(R.id.User_id);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();
        String dir = direccion.getText().toString();
        String city = ciudad.getText().toString();
        String user = userid.getText().toString();

        if(!name.isEmpty() && !dir.isEmpty() && !city.isEmpty() && !user.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("nombre", name);
            registro.put("direccion", dir);
            registro.put("ciudad", city);
            registro.put("Usuarioid", user);

            BaseDeDatos.insert("Local", null, registro);
            BaseDeDatos.close();

            nombre.setText("");
            direccion.setText("");
            ciudad.setText("");
            userid.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //consultar
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();

        if (!name.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery("SELECT direccion,ciudad,Usuarioid FROM Local WHERE nombre = ?", new String[]{name});

            if(fila.moveToFirst()) {
                direccion.setText(fila.getString(0));
                ciudad.setText(fila.getString(1));
                userid.setText(fila.getString(2));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el local", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debe introducir el nombre", Toast.LENGTH_SHORT).show();
        }
    }

    //DELETE

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();

        if (!name.isEmpty()) {

            int cantidad = BaseDeDatos.delete("Local", "nombre=?", new String[]{name});
            BaseDeDatos.close();

            nombre.setText("");
            direccion.setText("");

            if(cantidad == 1) {
                Toast.makeText(this, "Local eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El local no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe introducir el nombre", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar (View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombre.getText().toString();
        String dir = direccion.getText().toString();

        if(!name.isEmpty() && !dir.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("nombre", name);
            registro.put("direccion", dir);

            int cantidad = BaseDeDatos.update("Local", registro, "nombre =?", new String[]{name});
            BaseDeDatos.close();

            if (cantidad == 1) {
                Toast.makeText(this, "El local ha sido modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El local no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debe introducir todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}