package com.example.qarta_remastered;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoConfirmadoActivity extends AppCompatActivity {

    TextView PedidoConfirmado;
    Button Volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_confirmado);

        PedidoConfirmado = (TextView)findViewById(R.id.PedidoConfirmado);
        Volver = (Button)findViewById(R.id.Volver);
    }

    public void Volver(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
