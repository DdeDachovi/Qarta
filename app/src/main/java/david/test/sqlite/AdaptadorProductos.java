package david.test.sqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Menu> listaProductos;
    List<Menu> carrito;

    public AdaptadorProductos(Context context, TextView tvCantProductos, Button btnVerCarro, List<Menu> listaProductos, List<Menu> carrito) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carrito = carrito;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, null, false);
        return new ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder holder, final int position) {
        holder.tvNomProducto.setText(listaProductos.get(position).getNombre());
        holder.tvPrecio.setText(listaProductos.get(position).getPrecio());

        holder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.cbCarro.isChecked()){
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carrito.add(listaProductos.get(position));
                }else if(!holder.cbCarro.isChecked()) {
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carrito.remove(listaProductos.get(position));
                }
            }
        });

        holder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cantidadProducto.setText("" + (Integer.parseInt(holder.cantidadProducto.getText().toString().trim()) + 1));
            }
        });

        holder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.cantidadProducto.getText().toString().trim()) > 0){
                    holder.cantidadProducto.setText("" + (Integer.parseInt(holder.cantidadProducto.getText().toString().trim()) - 1));
                }
            }
        });

        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CarroCompra.class);
                intent.putExtra("CarroCompras", (Serializable) carrito);
                context.startActivity(intent);
            }
        });

        holder.addCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.cantidadProducto.getText().toString().trim()) > 0){

                    int cantidad = Integer.parseInt(holder.cantidadProducto.getText().toString().trim());
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + cantidad));
                    holder.cantidadProducto.setText("" + 0);

                    for(int i = 0; i < cantidad; i++){
                        carrito.add(listaProductos.get(position));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomProducto, tvPrecio, tvCantProductos, cantidadProducto;
        Button mas, menos, addCarrito;
        CheckBox cbCarro;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCantProductos = itemView.findViewById(R.id.tvCantProductos);
            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            cantidadProducto = itemView.findViewById(R.id.cantidadProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            cbCarro = itemView.findViewById(R.id.cbCarro);
            mas = itemView.findViewById(R.id.addProducto);
            menos = itemView.findViewById(R.id.removeProducto);
            addCarrito = itemView.findViewById(R.id.addCarrito);
        }
    }
}
