package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class secciones_activity extends AppCompatActivity {
    EditText txtNombreSec,txtDescripcionSec,txtFechaSec;
    Button btnAggSec;
    FloatingActionButton btnSalir;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones_activity);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        txtFechaSec.setOnClickListener(new View.OnClickListener() {
            Calendar calendar=Calendar.getInstance();
            final int anio=calendar.get(Calendar.YEAR);
            final int mes=calendar.get(Calendar.MONTH);
            final int dia=calendar.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(secciones_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month+=1;
                        String fecha,diaT,mesT;
                        diaT=String.valueOf(dayOfMonth);
                        mesT=String.valueOf(month);
                        if(dayOfMonth<10){
                            diaT="0"+dayOfMonth;
                        }
                        if(month<10){
                            mesT="0"+month;
                        }
                        fecha=diaT+"/"+mesT+"/"+year;
                        txtFechaSec.setText(fecha);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });
        btnAggSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(validar_registro()){
                        db.execSQL("INSERT INTO seccion VALUES(null ,'"+
                                txtNombreSec.getText().toString() + "','" +
                                txtDescripcionSec.getText().toString() + "','" +
                                txtFechaSec.getText().toString() + "')");
                        Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(),er.getMessage(),Toast.LENGTH_LONG).show();
                }
                txtNombreSec.setText("");
                txtDescripcionSec.setText("");
                txtFechaSec.setText("");
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
    private void conectar(){
        btnSalir=findViewById(R.id.btnSalirAggSeccion);
        txtNombreSec=findViewById(R.id.txtNombreSeccion);
        txtDescripcionSec=findViewById(R.id.txtDescripcionSeccion);
        txtFechaSec=findViewById(R.id.txtFechaCrSeccion);
        btnAggSec=findViewById(R.id.btnAgregarSecciones);
    }
    @Override
    public void onBackPressed() {

    }
    public boolean validar_registro(){
        String nombre=txtNombreSec.getText().toString();
        String desc=txtDescripcionSec.getText().toString();
        String fecha=txtFechaSec.getText().toString();
        if(nombre.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(desc.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fecha.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nombre.length()!=0 && desc.length()!=0 && fecha.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}