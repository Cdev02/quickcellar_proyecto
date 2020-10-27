package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Actualizar extends AppCompatActivity {
    EditText txtANomUsuario,txtAContraUsuario,txtANickname,txtAcorreoUs;
    Button btnActuali;
    myClass myClass;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        btnActuali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("update cliente set nombre='" + txtANomUsuario.getText().toString()
                            + "',correo='"+txtAcorreoUs.getText().toString()+
                            "',clave_ingreso='"+ txtAContraUsuario.getText().toString()+
                           "'where nickname='" + txtANickname.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(),"Actualización completada",Toast.LENGTH_LONG).show();

                }catch (Exception er)
                {
                    Toast.makeText(Actualizar.this,er.getMessage().toString(),Toast.LENGTH_LONG);
                }
                txtANickname.setText("");
                txtANomUsuario.setText("");
                txtAcorreoUs.setText("");
                txtAContraUsuario.setText("");


                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

    }

    private void conectar() {
        txtAContraUsuario=findViewById(R.id.txtAContraUsuario);
        txtANomUsuario=findViewById(R.id.txtANomUsuario);
        btnActuali=findViewById(R.id.btnActualizar);
        txtANickname=findViewById(R.id.txtANickname);
        txtAcorreoUs=findViewById(R.id.txtAcorreoUS);
    }
}