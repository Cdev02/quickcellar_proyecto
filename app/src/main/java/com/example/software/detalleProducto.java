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

public class detalleProducto extends AppCompatActivity {
    TextView tvNomPr,tvSecPr,tvMarcaPr,tvRefPr,tvDescPr;
    EditText txtCantiPr;
    Button btnAggPr;
    String nomPr, secPr, marcaPr, refPr, DesPr;
    int idCar, idPr,cantPr;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        Bundle b=getIntent().getExtras();
        if(b!=null){
            idPr=b.getInt("idPr");
            nomPr= b.getString("nombrePr");
            secPr=b.getString("seccionPr");
            marcaPr=b.getString("marcaPr");
            refPr=b.getString("refPr");
            DesPr=b.getString("descrPr");
            idCar=b.getInt("idCarrito");
            tvNomPr.setText(nomPr);
            tvMarcaPr.setText(marcaPr);
            tvRefPr.setText(refPr);
            tvDescPr.setText(DesPr);
        }
        btnAggPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantPr=Integer.parseInt(txtCantiPr.getText().toString());
                try {
                    db=myClass.getWritableDatabase();
                    if(validar_registro()){
                        db.execSQL("INSERT INTO seleccion_producto VALUES(null,"+idPr+"," + idCar + "," + cantPr +")");
                        Toast.makeText(getApplicationContext(),"Se agregó al carrito "+ idCar,Toast.LENGTH_SHORT).show();
                        db.close();
                        Intent i=new Intent(getApplicationContext(),comprar_activity.class);
                        i.putExtra("idCar",idCar);
                        startActivity(i);
                    }
                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(),er.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void conectar(){
        tvNomPr=findViewById(R.id.tvNomProducto);
        tvSecPr=findViewById(R.id.tvSeccionproducto);
        tvMarcaPr=findViewById(R.id.tvMarcaproducto);
        tvRefPr=findViewById(R.id.tvRefProducto);
        tvDescPr=findViewById(R.id.tvDescrProducto);
        txtCantiPr=findViewById(R.id.txtCantidadALlevar);
        btnAggPr=findViewById(R.id.btnAgregarAlCarrito);
    }

    private boolean validar_registro(){
        String cantidad=txtCantiPr.getText().toString();
        if(cantidad.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa la cantidad que desaas llevar",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cantidad.length()!=0){
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"ingresa los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}