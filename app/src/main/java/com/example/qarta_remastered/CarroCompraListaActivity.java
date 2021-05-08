package com.example.qarta_remastered;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qarta_remastered.Adaptadores.CarroCompra;
import com.example.qarta_remastered.Models.Menu;
import com.example.qarta_remastered.Models.Ventas;
import com.example.qarta_remastered.Models.Ventas_menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarroCompraListaActivity extends AppCompatActivity implements Serializable {

    List<Menu> carrito;
    Button pagar;
    CarroCompra adaptador;
    RecyclerView rvListaCarro;
    TextView tvTotal;
    String mesa,local;

    private int boleta = 9879122;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compra);

        carrito = (ArrayList<Menu>) getIntent().getSerializableExtra("CarroCompras");
        pagar = (Button)findViewById(R.id.Pagar);
        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarroCompraListaActivity.this,1));
        tvTotal = findViewById(R.id.tvTotal);

        mesa = getIntent().getExtras().getString("Mesa");
        local = getIntent().getExtras().getString("Nombre");

        adaptador = new CarroCompra(CarroCompraListaActivity.this, carrito,tvTotal,pagar);
        rvListaCarro.setAdapter(adaptador);
    }

    public void ConfirmarPedido(View view){
        try{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "qarta12", null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
            //Crear venta

            String total = tvTotal.getText().toString();
            if(!total.isEmpty()){

                String[] cifras = total.split("\\.");
                double totalint = Double.parseDouble(cifras[0]);
                double iva = (double)((totalint*19)/119);
                double propina = (double)totalint/10;

                /*
                String nombre = local.split(" a ")[1];
                Cursor filas = BaseDeDatos.rawQuery("SELECT * FROM Local WHERE nombre == ?", new String[]{nombre});
                filas.moveToNext();
                */

                //String idMesa = mesa.split("n°")[1];
                Ventas venta = new Ventas("0","" + boleta,"" + iva,"0","false","" + propina,"2","1","1","0",total);

                String sql = "INSERT INTO Ventas (numero_boleta,total,iva,descuento,estado,da_propina,propina,Usuarioid,Localid,Mesasid) VALUES (?,?,?,?,?,?,?,?,?,?)";

                BaseDeDatos.execSQL(sql,new String[]{venta.getNumero_boleta(),venta.getTotal(),venta.getIva(),venta.getDescuento(),venta.getEstado(),venta.getDa_propina(),venta.getPropina(),venta.getUsuarioid(),venta.getLocalid(),venta.getMesaid()});


                //Crear array menu-venta

                for(Menu m:carrito){

                    Date fecha = new Date();
                    Cursor cursor = BaseDeDatos.rawQuery("SELECT * FROM Ventas WHERE numero_boleta == ?",new String[]{""+boleta});
                    cursor.moveToNext();
                    String query = "SELECT id FROM Ventas WHERE numero_boleta == ?";

                    Ventas_menu vm = new Ventas_menu(cursor.getString(0), m.getId(), "" + 1, "" + fecha, "" + 0, "" + 0, "" + 0);

                    String sql_producto = "INSERT INTO Ventas_menu (Ventasid,Menuid,cantidad,fecha_pedido,fecha_entrega,fecha_alargue,estado) VALUES (?,?,?,?,?,?,?)";
                    BaseDeDatos.execSQL(sql_producto, new String[]{vm.getVentasid(),vm.getMenuid(),vm.getCantidad(),vm.getFecha_pedido(),vm.getFecha_entrega(),vm.getFecha_alargue(),vm.getEstado()});

                }

                BaseDeDatos.close();
                boleta++;
                //Se ingresan los productos, por lo que ahora deberian poder verse en cocina
                Intent intent = new Intent(this, PedidoConfirmadoActivity.class);
                startActivity(intent);
            }

        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

}
