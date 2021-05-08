package com.example.qarta_remastered;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scanner;
    private Button Administracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Administracion = (Button)findViewById(R.id.administracion);

    }

    public void ViewAdministracion(View view){
        Intent intent = new Intent(this, AdministracionActivity.class);
        startActivity(intent);
    }

    public void ViewCocina(View view){
        Intent intent = new Intent(this, LocalCocinaActivity.class);
        startActivity(intent);
    }


    public void Scanner(View v){
        scanner = new ZXingScannerView(this);
        setContentView(scanner);
        scanner.setResultHandler(this);
        scanner.startCamera();
    }


    @Override
    public void handleResult(com.google.zxing.Result result) {
        try{
            //manipular texto de entrada FORMATO DE PRUEBA: IDLOCAL-IDMESA-NOMBRELOCAL
            Log.v("HandleResult",result.getText());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Restaurant escaneado");
            builder.setMessage(result.getText());
            AlertDialog alert = builder.create();
            alert.show();

            scanner.resumeCameraPreview((ZXingScannerView.ResultHandler) this);
            scanner.stopCamera();

            String idLocal = result.getText().split(",")[0];
            String idMesa = result.getText().split(",")[1];
            String nombreLocal = result.getText().split(",")[2];
            Intent intent = new Intent(this, MenuIndexActivity.class);
            intent.putExtra("idLocal", idLocal);
            intent.putExtra("idMesa",idMesa);
            intent.putExtra("Nombre",nombreLocal);
            startActivity(intent);

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }



}