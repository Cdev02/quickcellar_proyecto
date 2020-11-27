package com.example.software;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> nombre_pr, referencia_pr,marca_pr,descr_pr;
    private ArrayList<Integer> id_producto, id_seccionPr,precio_pr;
    private int idCarrito;
    CustomAdapter2(Activity activity, Context context, ArrayList nombre_pr,ArrayList referencia_pr,ArrayList marca_pr,
                  ArrayList descr_pr, ArrayList id_producto,ArrayList id_seccion,ArrayList precio, int idCarrito) {
        this.activity = activity;
        this.context = context;
        this.nombre_pr = nombre_pr;
        this.id_producto=id_producto;
        this.id_seccionPr=id_seccion;
        this.referencia_pr=referencia_pr;
        this.marca_pr=marca_pr;
        this.descr_pr=descr_pr;
        this.precio_pr=precio;
        this.idCarrito=idCarrito;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_personalizado, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvId_prod.setText(String.valueOf(id_producto.get(position)));
        holder.tvNombre_pro.setText(String.valueOf(nombre_pr.get(position)));
        holder.tvPrecio_pro.setText(String.valueOf(precio_pr.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_producto.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId_prod, tvNombre_pro,tvPrecio_pro;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId_prod = itemView.findViewById(R.id.tvIdProducto);
            tvNombre_pro = itemView.findViewById(R.id.tvNombreProducto);
            tvPrecio_pro=itemView.findViewById(R.id.tvPrecioProducto);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

