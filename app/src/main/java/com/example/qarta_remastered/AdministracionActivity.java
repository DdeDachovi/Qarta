package com.example.qarta_remastered;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdministracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
    }

    public void ViewLocal(View view){
        Intent intent = new Intent(this, LocalIndexActivity.class);
        startActivity(intent);
    }

    public void ViewProducto(View view){
        Intent intent = new Intent(this, ProductoIndexActivity.class);
        startActivity(intent);
    }

    public void ViewUsuario(View view){
        Intent intent = new Intent(this, UsuarioIndexActivity.class);
        startActivity(intent);
    }

    public void ViewMesa(View view){
        Intent intent = new Intent(this, MesaIndexActivity.class);
        startActivity(intent);
    }
}
