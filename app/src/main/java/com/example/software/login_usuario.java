package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class login_usuario extends AppCompatActivity {
    EditText txtContraUsu,txtIdentUsu;
    Button btnLoginUsu;
    myClass myClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        btnLoginUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar_registro()){
                    ingresar(txtIdentUsu.getText().toString(),txtContraUsu.getText().toString());
                }
            }
        });
    }

    private void conectar() {
        txtContraUsu=findViewById(R.id.txtContraUsu);
        txtIdentUsu=findViewById(R.id.txtIdentUsu);
        btnLoginUsu=findViewById(R.id.btnLoginUsu);
    }
    public void ingresar(String idCl,String claveCl){
        Cursor myCursor;
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT * FROM cliente WHERE Id_cliente='"+idCl+"' AND clave_ingreso='"+claveCl+"'",null);
        if (myCursor.moveToFirst()==true) {


            String idClienteExtraido = myCursor.getString(0);
            String Nombre = myCursor.getString(1);
            String Correo = myCursor.getString(2);
            String claveClienteExtraido = myCursor.getString(3);
            int totalCompras= myCursor.getInt(4);




            if (idCl.equals(idClienteExtraido) && claveCl.equals(claveClienteExtraido)) {

                Intent intent1 = new Intent(getApplicationContext(), perfilClienteActivity.class);
                intent1.putExtra("idCliente",idClienteExtraido);
                intent1.putExtra("Nombre",Nombre);
                intent1.putExtra("Correo",Correo);
                intent1.putExtra("TotalCompras",totalCompras);

                startActivity(intent1);

            }else{
                Toast.makeText(getApplicationContext(),"Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validar_registro(){
        String idCl=txtIdentUsu.getText().toString();
        String clave_cl=txtContraUsu.getText().toString();
        if(idCl.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(clave_cl.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(idCl.length()!=0 && clave_cl.length()!=0){
            return true;
        }else{
            return false;
        }
    }
}