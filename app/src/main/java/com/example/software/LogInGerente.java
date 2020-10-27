package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LogInGerente extends AppCompatActivity {
    ArrayList<String> listado=new ArrayList<>();
    String id= "17100";
    String contra= "17100";
    Button btnLogin;
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


    }

    private void conectar() {
        btnLogin=findViewById(R.id.btnLogin);
        txtContra=findViewById(R.id.txtContra);
        txtIdenti=findViewById(R.id.txtIdent);
    }
}