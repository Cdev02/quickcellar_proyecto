package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AgregarProductos extends AppCompatActivity {
    EditText txtNombrePr, txtMarcaPr,txtDescripPr,txtPrecioPr,txtRefPr;
    Button btnAgregar;
    FloatingActionButton btnSalir;
    Spinner spSecciones;
    ArrayList<String> listado=new ArrayList<>();
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
        desplegar();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_sec=obtenerIdSeccion();
                try {
                    int precioProducto=Integer.parseInt(txtPrecioPr.getText().toString());
                    if(!db.isOpen()){
                        db=myClass.getWritableDatabase();
                        if(validar_registro()){
                            db.execSQL("INSERT INTO producto VALUES(null,"+id_sec+",'" +
                                    txtNombrePr.getText().toString() + "','" +
                                    txtRefPr.getText().toString() + "','" +
                                    txtMarcaPr.getText().toString() + "','" +
                                    txtDescripPr.getText().toString() +"',"+precioProducto+")");
                            Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_LONG).show();
                            db.close();
                        }
                    }
                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(),er.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
                txtNombrePr.setText("");
                txtRefPr.setText("");
                spSecciones.setSelected(false);
                txtDescripPr.setText("");
                txtPrecioPr.setText("");
                txtMarcaPr.setText("");
            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private int obtenerIdSeccion(){
        Cursor myCursor;
        String nombreSec=spSecciones.getSelectedItem().toString();
        int id_sec=0;
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT Id_seccion FROM seccion WHERE nombre_seccion='"+nombreSec+"'",null);
        if(myCursor.moveToFirst()) {
            do {
                id_sec=myCursor.getInt(0);
            }while (myCursor.moveToNext());
        }
        db.close();
        return id_sec;
    }
    private ArrayList<String> ListaUnidades(){
        Cursor myCursor;
        ArrayList<String>datos= new ArrayList<>();
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT * FROM seccion",null);
        if(myCursor.moveToFirst()) {
            do {
                String line = myCursor.getString(1);
                datos.add(line);
            }while (myCursor.moveToNext());
        }
        db.close();
        return  datos;
    }
    private void desplegar()
    {
        listado=ListaUnidades();
        ArrayAdapter adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item_personalizado,listado);
        spSecciones.setAdapter(adapter);
        spSecciones.setSelection(-1);
    }



    public boolean validar_registro(){
        String nombreProducto=txtNombrePr.getText().toString();
        String refProducto=txtRefPr.getText().toString();
        String descripcionPr=txtDescripPr.getText().toString();
        String precioProducto=txtPrecioPr.getText().toString();
        String marcaProducto=txtMarcaPr.getText().toString();
        if(nombreProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(refProducto.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(descripcionPr.length()==0){
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
        if(nombreProducto.length()!=0 && refProducto.length()!=0
        &&descripcionPr.length()!=0 &&precioProducto.length()!=0 &&marcaProducto.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    @Override
    public void onBackPressed() {

    }
    private void conectar() {
        btnSalir=findViewById(R.id.btnSalirAggProductos);
        txtNombrePr = findViewById(R.id.txtNomProducto);
        txtRefPr = findViewById(R.id.txtReferenciaPr);
        spSecciones=findViewById(R.id.spSecciones);
        txtMarcaPr=findViewById(R.id.txtMarcaPr);
        txtDescripPr=findViewById(R.id.txtDescripcionPr);
        btnAgregar = findViewById(R.id.btnAgregar);
        txtPrecioPr=findViewById(R.id.txtPrecioPr);
    }
}