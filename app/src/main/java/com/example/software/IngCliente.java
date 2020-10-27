package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngCliente extends AppCompatActivity {
    EditText txtNomUsuario,txtContraUsuario,txtNickname,txtCorreoUs;
    Button btnIgresar;
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
                            db.execSQL("insert into cliente values(" +
                            txtNickname.getText().toString() + ",'" +
                            txtNomUsuario.getText().toString() + "','" +
                            txtCorreoUs.getText().toString() + "','" +
                            txtContraUsuario.getText().toString() + "','" + "')");
                    Toast.makeText(getApplicationContext(),"Registro compretado",Toast.LENGTH_LONG).show();

                }catch (Exception er)
                {
                    Toast.makeText(IngCliente.this,er.getMessage().toString(),Toast.LENGTH_LONG);
                }
                txtNickname.setText("");
                txtNomUsuario.setText("");
                txtCorreoUs.setText("");
                txtContraUsuario.setText("");

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void conectar() {
        txtContraUsuario=findViewById(R.id.txtContraUsuario);
        txtNomUsuario=findViewById(R.id.txtNomUsuario);
        btnIgresar=findViewById(R.id.btnIngresar);
        txtNickname=findViewById(R.id.txtNickname);
        txtCorreoUs=findViewById(R.id.txtCorreoUs);
    }
}