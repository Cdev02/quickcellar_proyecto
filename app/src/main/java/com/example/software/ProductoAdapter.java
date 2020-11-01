package com.example.software;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.productoView> implements Filterable {

    private List<Producto> productoList = new ArrayList<>();
    private Context context;
    private ArrayList<Producto>productosArrayList;


    public ProductoAdapter(Context context,ArrayList<Producto>productoList) {
        this.productoList = productoList;
        this.context = context;
        this.productosArrayList=productoList;
    }

    @NonNull
    @Override
    public productoView onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mostrar, viewGroup, false);
        return new productoView(view);
    }

    @Override
    public void onBindViewHolder( productoView productoView, int i) {
        Producto producto = productoList.get(i);
        productoView.txtIDMostrarM.setText(String.valueOf(producto.getID()));
        productoView.txtNombreMostrarM.setText(String.valueOf(producto.getNombre()));
        productoView.txtReferenciaM.setText(String.valueOf(producto.getReferencia()));
        productoView.txtMarcaM.setText(String.valueOf(producto.getMarca()));
        productoView.txtDescripcionM.setText(String.valueOf(producto.getDesscripcion()));
        productoView. txtPrecionM.setText(String.valueOf(producto.getPrecio()));


    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public void agregarProducto(Producto producto){
        productoList.add(producto);
        this.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class productoView extends RecyclerView.ViewHolder{
        private TextView txtIDMostrarM,txtNombreMostrarM,txtReferenciaM,txtMarcaM,txtDescripcionM,txtPrecionM;

        public productoView(@NonNull View itemView) {
            super(itemView);
            txtIDMostrarM=itemView.findViewById(R.id.txtIDMostrarM);
            txtNombreMostrarM=itemView.findViewById(R.id.txtNombreMostrarM);
            txtReferenciaM=itemView.findViewById(R.id.txtReferenciaM);
            txtMarcaM=itemView.findViewById(R.id.txtMarcaM);
            txtDescripcionM=itemView.findViewById(R.id.txtDescripcionM);
            txtPrecionM=itemView.findViewById(R.id.txtPrecionM);

        }
    }
}
