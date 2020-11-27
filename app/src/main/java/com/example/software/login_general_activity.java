package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_general_activity extends AppCompatActivity {
    CardView cvGerente,cvCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_general_activity);
        conectar();
        cvGerente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LogInGerente.class);
                startActivity(i);
            }
        });
        cvCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),login_usuario.class);
                startActivity(i);

            }
        });
    }

    private void conectar() {
        cvCliente=findViewById(R.id.cvIngresoComoCliente);
        cvGerente=findViewById(R.id.cvIngresoComoGerente);
    }
    @Override
    public void onBackPressed() {

    }
}