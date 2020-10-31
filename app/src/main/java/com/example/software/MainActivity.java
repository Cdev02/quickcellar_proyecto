package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnIngCliente,btnVerClientes,btnAggSeccion,btnAggProduct,btnVerProductos,btnAggEmple;
    ImageButton btnImgSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_gerente);
        conectar();
        btnIngCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),IngCliente.class);
                startActivity(i);
            }
        });
        btnVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Consultclient.class);
                startActivity(i);
            }
        });
        btnImgSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LogInGerente.class);
                startActivity(i);
            }
        });
        btnVerProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),VerProductos.class);
                startActivity(i);
            }
        });
        btnAggProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AgregarProductos.class);
                startActivity(i);
            }
        });
        btnAggEmple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AggEmpleado.class);
                startActivity(i);
            }
        });
        btnAggSeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),secciones_activity.class);
                startActivity(i);
            }
        });
    }
    private void conectar() {
        btnIngCliente=findViewById(R.id.btnIngCliente);
        btnVerClientes=findViewById(R.id.btnVerClientes);
        btnAggSeccion=findViewById(R.id.btnAggSeccion);
        btnAggProduct=findViewById(R.id.btnAggProduct);
        btnVerProductos=findViewById(R.id.btnVerProductos);
        btnAggEmple=findViewById(R.id.btnAggEmple);
        btnImgSalir = findViewById(R.id.btnImgSalir);
    }
}