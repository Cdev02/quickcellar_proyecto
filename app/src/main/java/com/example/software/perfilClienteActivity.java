package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class perfilClienteActivity extends AppCompatActivity {
    Button btnComprarProductos,btnActualizarDatosCliente;
    EditText txtTotalcomp,txtEmailPer,txtNomPer,txtIdPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);
        conectar();

        Bundle b=getIntent().getExtras();
        if(b!=null){
            String idClienteExtraido= b.getString("idCliente");
            String Nombre= b.getString("Nombre");
            String Correo= b.getString("Correo");
            int TotalCompras= b.getInt("TotalCompras");


            txtIdPer.setText(idClienteExtraido);
            txtNomPer.setText(Nombre);
            txtEmailPer.setText(Correo);
            txtTotalcomp.setText(TotalCompras+"");
        }

        btnActualizarDatosCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Actualizar.class);
                startActivity(i);
            }
        });
        btnComprarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),agregar_alCarrito.class);
                startActivity(i);
            }
        });
    }

    private void conectar() {
        btnComprarProductos=findViewById(R.id.btnComprarProductos);
        btnActualizarDatosCliente=findViewById(R.id.btnActualizarDatosCliente);
        txtTotalcomp=findViewById(R.id.txtTotalcomp);
        txtEmailPer=findViewById(R.id.txtEmailPer);
        txtNomPer=findViewById(R.id.txtNomPer);
        txtIdPer=findViewById(R.id.txtIdPer);

    }
}