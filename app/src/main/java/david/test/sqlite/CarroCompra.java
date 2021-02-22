package david.test.sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarroCompra extends AppCompatActivity {

    List<Menu> carrito;
    Button pagar;
    AdaptadorCarroCompra adaptador;
    RecyclerView rvListaCarro;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carro_compra);

        carrito = (ArrayList<Menu>) getIntent().getSerializableExtra("CarroCompras");
        pagar = (Button)findViewById(R.id.Pagar);
        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarroCompra.this,1));
        tvTotal = findViewById(R.id.tvTotal);

        adaptador = new AdaptadorCarroCompra(CarroCompra.this, carrito,tvTotal,pagar);
        rvListaCarro.setAdapter(adaptador);
    }
}
