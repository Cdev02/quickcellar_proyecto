package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarProductos extends AppCompatActivity {
    EditText txtNomProducto, txtCantidad,txtidProducto,txtDescrip,txtPrecio,txtMarca;
    Button btnAgregar;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(validar_registro()){
                        db.execSQL("insert into producto values(" +
                        Integer.parseInt(txtidProducto.getText().toString()) + ",'" +
                        txtNomProducto.getText().toString() + "','" +
                        txtCantidad.getText().toString() + "','" +
                        txtMarca.getText().toString() + "','" +
                        txtDescrip.getText().toString() + "','" +
                        Integer.parseInt(txtPrecio.getText().toString()) + "')");
                        Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception er)
                {
                    Toast.makeText(AgregarProductos.this,er.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
                txtNomProducto.setText("");
                txtCantidad.setText("");
                txtidProducto.setText("");
                txtDescrip.setText("");
                txtPrecio.setText("");
                txtMarca.setText("");
            }
        });
    }
    public boolean validar_registro(){
        String nombreProducto=txtNomProducto.getText().toString();
        String IdProducto=txtidProducto.getText().toString();
        String cantidadProducto=txtCantidad.getText().toString();
        String descripcion=txtDescrip.getText().toString();
        String precioProducto=txtPrecio.getText().toString();
        String marcaProducto=txtMarca.getText().toString();
        if(nombreProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(IdProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cantidadProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(descripcion.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(precioProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(marcaProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nombreProducto.length()!=0 && IdProducto.length()!=0 && cantidadProducto.length()!=0
        &&descripcion.length()!=0 &&precioProducto.length()!=0 &&marcaProducto.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void conectar() {
        txtCantidad = findViewById(R.id.txtCantidad);
        txtNomProducto = findViewById(R.id.txtNomProducto);
        txtidProducto=findViewById(R.id.txtidProducto);
        txtDescrip=findViewById(R.id.txtDescrip);
        txtPrecio=findViewById(R.id.txtPrecio);
        btnAgregar = findViewById(R.id.btnAgregar);
        txtMarca=findViewById(R.id.txtMarca);
    }
}