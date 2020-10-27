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
    Button btnIngCliente,btnVerClientes,btnActuCliente,btnAggProduct,btnVerProductos,btnAggEmple;
    ImageButton btnImgSalir;
    EditText txt1;
    myClass myClass;
    SQLiteDatabase db;

    ListView lvResultados;
    ArrayList<String>listado=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_gerente);
        conectar();


        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();


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
        btnActuCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Actualizar.class);
                startActivity(i);
            }
        });
    }

    private void conectar() {
        btnIngCliente=findViewById(R.id.btnIngCliente);
        btnVerClientes=findViewById(R.id.btnVerClientes);
        btnActuCliente=findViewById(R.id.btnActuCliente);
        btnAggProduct=findViewById(R.id.btnAggProduct);
        btnVerProductos=findViewById(R.id.btnVerProductos);
        btnAggEmple=findViewById(R.id.btnAggEmple);
        btnImgSalir = findViewById(R.id.btnImgSalir);


    }

}