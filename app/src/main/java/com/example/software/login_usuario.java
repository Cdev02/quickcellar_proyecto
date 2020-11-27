package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class login_usuario extends AppCompatActivity {
    EditText txtContraUsu,txtIdentUsu;
    TextView tvCrearCuenta;
    Button btnLoginUsu;
    FloatingActionButton btn_salir;
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
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_general_activity.class);
                startActivity(i);
            }
        });
        tvCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),IngCliente.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
    private void conectar() {
        tvCrearCuenta=findViewById(R.id.tvCrearCuentaUsuario);
        txtContraUsu=findViewById(R.id.txtContraUsu);
        txtIdentUsu=findViewById(R.id.txtIdentUsu);
        btnLoginUsu=findViewById(R.id.btnLoginUsu);
        btn_salir=findViewById(R.id.btnSalirAlLogin);
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

                Intent i=new Intent(getApplicationContext(),perfilClienteActivity.class);
                i.putExtra("idCliente",idClienteExtraido);
                i.putExtra("nombreCliente",Nombre);
                i.putExtra("emailCliente",Correo);
                i.putExtra("comprasCliente",totalCompras);
                i.putExtra("claveAcceso",claveClienteExtraido);
                Toast.makeText(getApplicationContext(),"BIENVENIDO",Toast.LENGTH_LONG).show();
                db.close();
                startActivity(i);
                txtContraUsu.setText("");
                txtIdentUsu.setText("");

            }else{
                Toast.makeText(getApplicationContext(),"Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Inténtelo de nuevo",Toast.LENGTH_SHORT).show();
        }
        db.close();
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