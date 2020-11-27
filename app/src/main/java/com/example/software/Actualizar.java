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

public class Actualizar extends AppCompatActivity {
    EditText txtANomUsuario,txtAContraUsuario,txtANickname,txtAcorreoUs;
    Button btnActuali;
    FloatingActionButton regresarAlPerfil;
    myClass myClass;
    SQLiteDatabase db;
    String IdCl, nombreCl, correoCl, claveCl;
    int comprasCl,contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        contador=0;
        Bundle b=getIntent().getExtras();
        if(b!=null){
            IdCl= b.getString("idCliente");
            nombreCl= b.getString("nombreCliente");
            correoCl= b.getString("emailCliente");
            comprasCl= b.getInt("comprasCliente");
            claveCl=b.getString("claveAcceso");
            txtANickname.setText(IdCl);
            txtANomUsuario.setText(nombreCl);
            txtAcorreoUs.setText(correoCl);
            txtAContraUsuario.setText(claveCl);
        }
        btnActuali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                try {
                    db.execSQL("update cliente set nombre='" + txtANomUsuario.getText().toString()
                            + "',correo='"+txtAcorreoUs.getText().toString()+
                            "',clave_ingreso='"+ txtAContraUsuario.getText().toString()+
                           "'where Id_cliente='" + txtANickname.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(),"Actualización completada",Toast.LENGTH_LONG).show();
                }catch (Exception er)
                {
                    Toast.makeText(Actualizar.this,er.getMessage().toString(),Toast.LENGTH_LONG);
                }
            }
        });
        regresarAlPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),perfilClienteActivity.class);
                if(contador>=1){
                    IdCl= txtANickname.getText().toString();
                    nombreCl= txtANomUsuario.getText().toString();
                    correoCl= txtAcorreoUs.getText().toString();
                    claveCl=txtAContraUsuario.getText().toString();
                }
                i.putExtra("idCliente",IdCl);
                i.putExtra("nombreCliente",nombreCl);
                i.putExtra("emailCliente",correoCl);
                i.putExtra("comprasCliente",comprasCl);
                i.putExtra("claveAcceso",claveCl);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }

    private void conectar() {
        txtAContraUsuario=findViewById(R.id.txtAContraUsuario);
        txtANomUsuario=findViewById(R.id.txtANomUsuario);
        btnActuali=findViewById(R.id.btnActualizar);
        txtANickname=findViewById(R.id.txtANickname);
        txtAcorreoUs=findViewById(R.id.txtAcorreoUS);
        regresarAlPerfil=findViewById(R.id.btnSalirAlPerfilActualizar);
    }
}