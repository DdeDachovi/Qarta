package com.example.qarta_remastered.Adaptadores;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qarta_remastered.AdminSQLiteOpenHelper;
import com.example.qarta_remastered.CarroCompraListaActivity;
import com.example.qarta_remastered.Models.Menu;
import com.example.qarta_remastered.Models.Ventas;
import com.example.qarta_remastered.Models.Ventas_menu;
import com.example.qarta_remastered.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarroCompra extends RecyclerView.Adapter<CarroCompra.ProductosViewHolder> {

    Context context;
    List<Menu> carrito;
    TextView tvTotal;
    Button pagar;
    double total = 0;

    public CarroCompra(Context context, List<Menu> carrito, TextView tvTotal, Button pagar) {
        this.context = context;
        this.carrito = carrito;
        this.tvTotal = tvTotal;
        this.pagar = pagar;


        for(int i = 0; i < carrito.size(); i++){
            total = total + Double.parseDouble("" + carrito.get(i).getPrecio());
        }

        tvTotal.setText("" + total);

    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carro_compra_adapter, null, false);
        return new ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder holder, final int position) {
        holder.tvNomProducto.setText(carrito.get(position).getNombre());
        holder.tvPrecio.setText(carrito.get(position).getPrecio());

        holder.quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrito.remove(carrito.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return carrito.size();
    }

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomProducto, tvPrecio;
        Button quitar;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            quitar = itemView.findViewById(R.id.removeCarrito);
        }
    }
}
