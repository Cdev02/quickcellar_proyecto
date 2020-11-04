package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_usuario extends AppCompatActivity {
    EditText txtContraUsu,txtIdentUsu;
    Button btnLoginUsu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        conectar();
        btnLoginUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(validar_registro()) {
                        Intent i=new Intent(getApplicationContext(),Carrito.class);
                        startActivity(i);
                    }

                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(), "intente de nuevo", Toast.LENGTH_LONG).show();
                }
                txtContraUsu.setText("");
                txtIdentUsu.setText("");

            }
        });
    }

    private void conectar() {
        txtContraUsu.findViewById(R.id.txtContraUsu);
        txtIdentUsu.findViewById(R.id.txtIdentUsu);
        btnLoginUsu.findViewById(R.id.btnLoginUsu);

    }
    public boolean validar_registro(){
        String contra=txtContraUsu.getText().toString();
        String id=txtIdentUsu.getText().toString();

        if(contra.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;

        }
        if(id.length()!=0 && contra.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}