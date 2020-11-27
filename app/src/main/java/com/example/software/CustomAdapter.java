package com.example.software;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> nombre_pr, referencia_pr,marca_pr,descr_pr;
    private ArrayList<Integer> id_producto, id_seccionPr,precio_pr;
    private int idCarrito,comprasCl;
    private String idCliente, nombreCl, correoCl, claveCl;
    CustomAdapter(Activity activity, Context context, ArrayList nombre_pr,ArrayList referencia_pr,ArrayList marca_pr,
                  ArrayList descr_pr, ArrayList id_producto,ArrayList id_seccion,ArrayList precio, int idCarrito, String idCl,
                  String nombCl,String emailCl,String claveCl,int comprasCl) {
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
        this.comprasCl=comprasCl;this.idCliente=idCl;this.nombreCl=nombCl;this.correoCl=emailCl;this.claveCl=claveCl;
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
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,detalleProducto.class);
                intent.putExtra("idPr",id_producto.get(position));
                intent.putExtra("nombrePr",String.valueOf(nombre_pr.get(position)));
                intent.putExtra("seccionPr",String.valueOf(id_seccionPr.get(position)));
                intent.putExtra("marcaPr",String.valueOf(marca_pr.get(position)));
                intent.putExtra("refPr",String.valueOf(referencia_pr.get(position)));
                intent.putExtra("descrPr",String.valueOf(descr_pr.get(position)));
                intent.putExtra("idCarrito",idCarrito);
                intent.putExtra("idCliente",idCliente);
                intent.putExtra("nombreCliente",nombreCl);
                intent.putExtra("emailCliente",correoCl);
                intent.putExtra("comprasCliente",comprasCl);
                intent.putExtra("claveAcceso",claveCl);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id_producto.size();
    }

    public void filter(String strSearch){
        if(strSearch.length()==0){

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btnAgregarProductoAlCarrito;
        TextView tvId_prod, tvNombre_pro,tvPrecio_pro;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnAgregarProductoAlCarrito=itemView.findViewById(R.id.btnAgregarAlCarrito);
            tvId_prod = itemView.findViewById(R.id.tvIdProducto);
            tvNombre_pro = itemView.findViewById(R.id.tvNombreProducto);
            tvPrecio_pro=itemView.findViewById(R.id.tvPrecioProducto);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
