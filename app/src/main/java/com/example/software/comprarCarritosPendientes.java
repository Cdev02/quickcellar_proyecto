    package com.example.software;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

    public class comprarCarritosPendientes extends AppCompatActivity {
    ListView lvCarritos;
    FloatingActionButton btnSalir;
        myClass myClass;
        SQLiteDatabase db;
        ArrayList<Integer> listado=new ArrayList<>();
        String IdCl, nombreCl, correoCl, claveCl;
        int comprasCl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_carritos_pendientes);
        lvCarritos=findViewById(R.id.lvCarritosPendientes);
        btnSalir=findViewById(R.id.btnSalirDeCarritosPendientes);
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();

        Bundle b=getIntent().getExtras();
        if(b!=null){
            IdCl= b.getString("idCliente");
            nombreCl= b.getString("nombreCliente");
            correoCl= b.getString("emailCliente");
            comprasCl= b.getInt("comprasCliente");
            claveCl=b.getString("claveAcceso");
        }
        desplegar();
        lvCarritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ArrayList<String> nombreProductos=new ArrayList<>();
                    ArrayList<Integer> precioProductos=new ArrayList<>();
                    String idCarrito;
                    int idCarritoEntero;
                    Cursor myCursor;
                    idCarrito=lvCarritos.getItemAtPosition(position).toString();
                    idCarritoEntero=Integer.parseInt(idCarrito);
                        db=myClass.getWritableDatabase();
                        myCursor=db.rawQuery("select nombre_producto, seleccion_producto.cantidad, precio_unitario, (precio_unitario * seleccion_producto.cantidad) from " +
                                "producto inner join seleccion_producto on producto.Id_producto=seleccion_producto.Id_producto " +
                                "inner join carrito on carrito.Id_carrito=seleccion_producto.Id_carrito " +
                                "where carrito.Id_carrito="+idCarritoEntero+"",null);
                        if(myCursor.moveToFirst()) {
                            do {
                                String line=(myCursor.getString(0))+"\n"+"precio: "+myCursor.getInt(2)+"\n"+"cantidad seleccionada: "+myCursor.getInt(1);
                                nombreProductos.add(line);
                                int precio=(myCursor.getInt(3));
                                precioProductos.add(precio);
                            }while (myCursor.moveToNext());
                        }
                        db.close();
                        Intent i=new Intent(getApplicationContext(),listadoProductosPendientes.class);
                        i.putExtra("idCarrito",idCarritoEntero);
                        i.putExtra("nombreProductos",nombreProductos);
                        i.putExtra("precioProductos",precioProductos);
                        i.putExtra("idCliente",IdCl);
                        i.putExtra("nombreCliente",nombreCl);
                        i.putExtra("emailCliente",correoCl);
                        i.putExtra("comprasCliente",comprasCl);
                        i.putExtra("claveAcceso",claveCl);
                        startActivity(i);
                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(),er.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),perfilClienteActivity.class);
                i.putExtra("idCliente",IdCl);
                i.putExtra("nombreCliente",nombreCl);
                i.putExtra("emailCliente",correoCl);
                i.putExtra("comprasCliente",comprasCl);
                i.putExtra("claveAcceso",claveCl);
                startActivity(i);
            }
        });
    }
        private ArrayList<Integer> ListaUnidades(){
            Cursor myCursor;
            ArrayList<Integer>datos= new ArrayList<>();
            SQLiteDatabase db =myClass.getWritableDatabase();
            myCursor=db.rawQuery("SELECT Id_carrito FROM carrito WHERE Id_cliente='"+IdCl+"' and carrito.estado_compra=0",null);
            if(myCursor.moveToFirst()) {
                do {
                    int line=(myCursor.getInt(0));
                    datos.add(line);
                }while (myCursor.moveToNext());
            }
            db.close();
            return  datos;
        }
        private void desplegar()
        {
            listado=ListaUnidades();
            ArrayAdapter<Integer> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listado);
            lvCarritos.setAdapter(adapter);

        }
        @Override
        public void onBackPressed() {

        }
}