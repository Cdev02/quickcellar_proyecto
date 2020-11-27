package com.example.software;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class perfilClienteActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView navigation;
    TextView tvNombre,tvId,tvEmail,tvCompras;
    String idClienteExtraido,nombre,correo,clave;
    myClass myClass;
    SQLiteDatabase db;
    int totalCompras;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        Bundle b=getIntent().getExtras();
        if(b!=null){
            idClienteExtraido= b.getString("idCliente");
            nombre= b.getString("nombreCliente");
            correo= b.getString("emailCliente");
            totalCompras= b.getInt("comprasCliente");
            clave=b.getString("claveAcceso");
            tvId.setText(idClienteExtraido);
            tvNombre.setText(nombre);
            tvEmail.setText(correo);
            tvCompras.setText(totalCompras+"");
        }
        obtenerTotalCompras();
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_usuario.class);
                startActivity(i);
            }
        });
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.actualizarOpcion:
                        Intent i=new Intent(getApplicationContext(),Actualizar.class);
                        i.putExtra("idCliente",idClienteExtraido);
                        i.putExtra("nombreCliente",nombre);
                        i.putExtra("emailCliente",correo);
                        i.putExtra("comprasCliente",totalCompras);
                        i.putExtra("claveAcceso",clave);
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.comprarOpcion:
                        Intent i2=new Intent(getApplicationContext(),agregar_alCarrito.class);
                        i2.putExtra("idCliente",idClienteExtraido);
                        i2.putExtra("nombreCliente",nombre);
                        i2.putExtra("emailCliente",correo);
                        i2.putExtra("comprasCliente",totalCompras);
                        i2.putExtra("claveAcceso",clave);
                        startActivity(i2);
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.verCatalogoOpcion:
                        Intent i3=new Intent(getApplicationContext(),catalogoClientesActivity.class);
                        i3.putExtra("idCliente",idClienteExtraido);
                        i3.putExtra("nombreCliente",nombre);
                        i3.putExtra("emailCliente",correo);
                        i3.putExtra("comprasCliente",totalCompras);
                        i3.putExtra("claveAcceso",clave);
                        startActivity(i3);
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.comprarCarritoOpcion:
                        Intent i4=new Intent(getApplicationContext(),comprarCarritosPendientes.class);
                        i4.putExtra("idCliente",idClienteExtraido);
                        i4.putExtra("nombreCliente",nombre);
                        i4.putExtra("emailCliente",correo);
                        i4.putExtra("comprasCliente",totalCompras);
                        i4.putExtra("claveAcceso",clave);
                        startActivity(i4);
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });
    }

    private void conectar() {
        navigation=findViewById(R.id.bnvNavigationBottom);
        tvNombre=findViewById(R.id.tvNombreClientePerfil);
        tvId=findViewById(R.id.tvIdClientePerfilPerfil);
        tvEmail=findViewById(R.id.tvEmailClientePerfil);
        tvCompras=findViewById(R.id.tvTotalComprasClientePerfil);
        frameLayout=findViewById(R.id.frameLayoutSalir);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nombre",nombre);
        outState.putString("id",idClienteExtraido);
        outState.putString("email",correo);
        outState.putInt("totalCompras",totalCompras);
    }
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nombre=savedInstanceState.getString("nombre");
        idClienteExtraido=savedInstanceState.getString("id");
        correo=savedInstanceState.getString("email");
        totalCompras=savedInstanceState.getInt("totalCompras");
        tvNombre.setText(nombre);
        tvId.setText(idClienteExtraido);
        tvEmail.setText(correo);
        tvCompras.setText(totalCompras+"");
    }

    private void obtenerTotalCompras(){
        Cursor myCursor;
        db=myClass.getWritableDatabase();
        myCursor=db.rawQuery("select sum(precio_unitario * seleccion_producto.cantidad) from " +
                "producto inner join seleccion_producto on producto.Id_producto=seleccion_producto.Id_producto " +
                "inner join carrito on carrito.Id_carrito=seleccion_producto.Id_carrito " +
                "inner join cliente on carrito.Id_cliente=cliente.Id_cliente where cliente.id_cliente='"+idClienteExtraido+"'" +
                "and estado_compra = 1 group by cliente.Id_cliente",null);
        if(myCursor.moveToFirst()) {
            do {
                tvCompras.setText(String.valueOf(myCursor.getInt(0)));
            }while (myCursor.moveToNext());
        }
        db.close();
    }
}