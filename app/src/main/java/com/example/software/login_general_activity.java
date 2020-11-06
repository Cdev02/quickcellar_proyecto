package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_general_activity extends AppCompatActivity {
    Button btnGerente,btnCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_general_activity);
        conectar();
        btnGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LogInGerente.class);
                startActivity(i);
            }
        });
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),login_usuario.class);
                startActivity(i);

            }
        });
    }

    private void conectar() {
        btnCliente=findViewById(R.id.btnIngresoComoCliente);
        btnGerente=findViewById(R.id.btnIngresoComoGerente);
    }
}