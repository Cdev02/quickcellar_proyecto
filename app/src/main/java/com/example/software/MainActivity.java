package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
    CardView cvClientes, cvProductos, cvAgEmpleados, cvAgSeccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_gerente);
        conectar();
        cvClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Consultclient.class);
                startActivity(i);
            }
        });
        cvProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AgregarProductos.class);
                startActivity(i);
            }
        });
        cvAgEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),AggEmpleado.class);
                startActivity(i);
            }
        });
        cvAgSeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),secciones_activity.class);
                startActivity(i);
            }
        });
    }
    private void conectar() {
        cvClientes=findViewById(R.id.cvClientes);
        cvProductos=findViewById(R.id.cvAgProductos);
        cvAgEmpleados=findViewById(R.id.cvAgEmpleados);
        cvAgSeccion=findViewById(R.id.cvAgSeccion);
    }
}