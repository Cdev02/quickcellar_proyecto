package com.example.software;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> nombre_pr;
    private ArrayList<Integer> precio_pr;
    private int idCarrito;
    CustomAdapter3(Activity activity, Context context, ArrayList nombre_pr, ArrayList precio, int idCarrito) {
        this.activity = activity;
        this.context = context;
        this.nombre_pr = nombre_pr;
        this.precio_pr=precio;
        this.idCarrito=idCarrito;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_personalizado2, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvNombre_pro.setText(String.valueOf(nombre_pr.get(position)));
        holder.tvPrecio_pro.setText(String.valueOf(precio_pr.get(position)));
    }

    @Override
    public int getItemCount() {
        return nombre_pr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre_pro,tvPrecio_pro;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre_pro = itemView.findViewById(R.id.tvNombreProductoPendiente);
            tvPrecio_pro=itemView.findViewById(R.id.tvPrecioProductoPendiente);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
