package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LogInGerente extends AppCompatActivity {
    ArrayList<String> listado=new ArrayList<>();
    String id= "17100";
    String contra= "17100";
    Button btnLogin;
    FloatingActionButton btnSalir;
    EditText txtIdenti,txtContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_gerente);
        conectar();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtIdenti.getText().toString().equals(id) && txtContra.getText().toString().equals(contra))
                {
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(getApplicationContext(),"la datos son  inválidos",Toast.LENGTH_LONG).show();
                    txtContra.setText("");
                    txtIdenti.setText("");
                }

            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_general_activity.class);
                startActivity(i);
            }
        });


    }

    private void conectar() {
        btnSalir=findViewById(R.id.btnSalirAlLoginGeneral);
        btnLogin=findViewById(R.id.btnLogin);
        txtContra=findViewById(R.id.txtContra);
        txtIdenti=findViewById(R.id.txtIdent);
    }
    @Override
    public void onBackPressed() {

    }
}