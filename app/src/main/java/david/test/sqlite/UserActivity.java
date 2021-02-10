package david.test.sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity{

    EditText nombres,apellidos,rut,telefono,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);


        nombres = (EditText)findViewById(R.id.Nom);
        apellidos = (EditText)findViewById(R.id.Apellido);
        rut = (EditText)findViewById(R.id.getrut);
        telefono = (EditText)findViewById(R.id.phone);
        correo = (EditText)findViewById(R.id.email);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String name = nombres.getText().toString();
        String apellido = apellidos.getText().toString();
        String r_ut = rut.getText().toString();
        String phone = telefono.getText().toString();
        String email = correo.getText().toString();

        if(!name.isEmpty() && !apellido.isEmpty() && !email.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("rut",r_ut);
            registro.put("nombres", name);
            registro.put("apellidos", apellido);
            registro.put("telefono",phone);
            registro.put("email",email);

            BaseDeDatos.insert("Usuario", null, registro);
            BaseDeDatos.close();

            nombres.setText("");
            apellidos.setText("");
            rut.setText("");
            telefono.setText("");
            correo.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //consultar
    public void BuscarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String run = rut.getText().toString();

        if (!run.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("SELECT * FROM Usuario WHERE rut = ?", new String[]{run});

            if (fila.moveToFirst()) {
                rut.setText(fila.getString(1));
                nombres.setText(fila.getString(2));
                apellidos.setText(fila.getString(3));
                telefono.setText(fila.getString(4));
                correo.setText(fila.getString(5));
                Toast.makeText(this, fila.getString(0), Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el Usuario", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
            fila.close();
        } else {
            Toast.makeText(this, "Debe introducir el rut", Toast.LENGTH_SHORT).show();
        }
    }


    //DELETE

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "test", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String run = rut.getText().toString();

        if (!run.isEmpty()){
            int cantidad = BaseDeDatos.delete("Usuario", "rut = ?", new String[]{run});
            BaseDeDatos.close();

            nombres.setText("");
            apellidos.setText("");
            rut.setText("");
            telefono.setText("");
            correo.setText("");

            if(cantidad == 1) {
                Toast.makeText(this, "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe introducir el rut", Toast.LENGTH_SHORT).show();
        }
    }
}
