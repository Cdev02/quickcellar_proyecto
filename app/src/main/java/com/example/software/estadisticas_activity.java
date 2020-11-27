package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class estadisticas_activity extends AppCompatActivity {
    TextView tvVentas,tvEmpleados, tvCantidadProductos, tvClientes;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_activity);
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        conectar();
    }

    private void consultarVentas(){
        Cursor myCursor;
        myCursor=db.rawQuery("select sum(precio_unitario * seleccion_producto.cantidad) from " +
                "producto inner join seleccion_producto on producto.Id_producto=seleccion_producto.Id_producto " +
                "inner join carrito on carrito.Id_carrito=seleccion_producto.Id_carrito " +
                "inner join cliente on carrito.Id_cliente=cliente.Id_cliente where estado_compra = 1 group by cliente.Id_cliente",null);
        if(myCursor.moveToFirst()) {
            do {
                int line=(myCursor.getInt(0));
            }while (myCursor.moveToNext());
        }
        db.close();

    }

    private void conectar(){
        tvVentas=findViewById(R.id.tvVentasTotales);
        tvEmpleados=findViewById(R.id.tvEmpleadosConMasVentas);
        tvCantidadProductos=findViewById(R.id.tvCantidadProductos);
        tvClientes=findViewById(R.id.tvCantidadClientes);
    }
}