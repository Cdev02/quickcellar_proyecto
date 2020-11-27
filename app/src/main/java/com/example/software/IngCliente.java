package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IngCliente extends AppCompatActivity {
    EditText txtNomUsuario,txtContraUsuario,txtIdCliente,txtCorreoUs;
    Button btnIgresar;
    FloatingActionButton btn_salir;
    myClass myClass;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ing_cliente);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        btnIgresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(validar_registro()) {
                        db.execSQL("INSERT INTO cliente VALUES('" +
                                txtIdCliente.getText().toString() + "','" +
                                txtNomUsuario.getText().toString() + "','" +
                                txtCorreoUs.getText().toString() + "','" +
                                txtContraUsuario.getText().toString() + "', 0)");
                        Toast.makeText(getApplicationContext(), "Registro completado", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception er)
                {
                    Toast.makeText(IngCliente.this,er.getMessage().toString(),Toast.LENGTH_LONG);
                }
                txtIdCliente.setText("");
                txtNomUsuario.setText("");
                txtCorreoUs.setText("");
                txtContraUsuario.setText("");
            }
        });
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_usuario.class);
                startActivity(i);
            }
        });
    }

    public boolean validar_registro(){
        String nombre= txtNomUsuario.getText().toString();
        String nick=txtIdCliente.getText().toString();
        String correo= txtCorreoUs.getText().toString();
        String contra=txtContraUsuario.getText().toString();
        if(nombre.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nick.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(correo.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(contra.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nombre.length()!=0 && nick.length()!=0 && correo.length()!=0 && contra.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void conectar() {
        btn_salir=findViewById(R.id.btnSalirAlPerfilRegistro);
        txtContraUsuario=findViewById(R.id.txtContraUsuario);
        txtNomUsuario=findViewById(R.id.txtNomUsuario);
        btnIgresar=findViewById(R.id.btnIngresar);
        txtIdCliente=findViewById(R.id.txtIdCliente);
        txtCorreoUs=findViewById(R.id.txtCorreoUs);
    }
    @Override
    public void onBackPressed() {

    }
}