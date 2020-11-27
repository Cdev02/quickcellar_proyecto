package com.example.software;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class listadoProductosPendientes extends AppCompatActivity {
    FloatingActionButton btnSalir,btnComprar;
    RecyclerView recProductos;
    ArrayList<String>productosRecibidos=new ArrayList<>();
    ArrayList<Integer> preciosRecibidos=new ArrayList<>();
    CustomAdapter3 customAdapter;
    myClass myClass;
    SQLiteDatabase db;
    String idCliente,nombreCl,claveCl,correoCl;
    int idCarrito,comprasCl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos_pendientes);
        btnComprar=findViewById(R.id.btnComprarProductosPendientes);
        btnSalir=findViewById(R.id.btnSalirDeProductosPendientes);
        recProductos=findViewById(R.id.recProductosPendientes);
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            idCarrito=b.getInt("idCarrito");
            idCliente=b.getString("idCliente");
            nombreCl= b.getString("nombreCliente");
            correoCl= b.getString("emailCliente");
            comprasCl= b.getInt("comprasCliente");
            claveCl=b.getString("claveAcceso");
            idCarrito=b.getInt("idCarrito");
            productosRecibidos=b.getStringArrayList("nombreProductos");
            preciosRecibidos=b.getIntegerArrayList("precioProductos");
        }
        customAdapter = new CustomAdapter3(listadoProductosPendientes.this,this, productosRecibidos,preciosRecibidos,idCarrito);
        recProductos.setAdapter(customAdapter);
        recProductos.setLayoutManager(new LinearLayoutManager(listadoProductosPendientes.this));

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),comprarCarritosPendientes.class);
                i.putExtra("idCar",idCarrito);
                i.putExtra("idCliente",idCliente);
                i.putExtra("nombreCliente",nombreCl);
                i.putExtra("emailCliente",correoCl);
                i.putExtra("comprasCliente",comprasCl);
                i.putExtra("claveAcceso",claveCl);
                Toast.makeText(getApplicationContext(),String.valueOf(idCarrito),Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialog();
            }
        });
    }

    private void mostrarDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(listadoProductosPendientes.this);
        builder.setMessage("¿Confirmar este listado de productos como comprados?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    db=myClass.getWritableDatabase();
                    db.execSQL("update carrito set estado_compra = 1 where Id_carrito ="+idCarrito+"");
                    Toast.makeText(getApplicationContext(),"Productos comprados exitosamente",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),comprarCarritosPendientes.class);
                    i.putExtra("idCliente",idCliente);
                    i.putExtra("nombreCliente",nombreCl);
                    i.putExtra("emailCliente",correoCl);
                    i.putExtra("comprasCliente",comprasCl);
                    i.putExtra("claveAcceso",claveCl);
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).show();
    }



    @Override
    public void onBackPressed() {

    }
}