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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AggEmpleado extends AppCompatActivity {
    EditText txtNombre, txtFechaing,txtIdem;
    Button btnAgregarEmpl;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agg_empleado);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        btnAgregarEmpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    int comision_defecto=0;
                    if(validar_registro()){
                        db.execSQL("insert into empleado values(" +
                        Integer.parseInt(txtIdem.getText().toString()) + ",'" +
                        txtNombre.getText().toString() + "','" +
                        txtFechaing.getText().toString() + "','" + comision_defecto + "')");
                        Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception er)
                {
                    Toast.makeText(getApplicationContext(),er.getMessage(),Toast.LENGTH_LONG).show();
                }
                txtIdem.setText("");
                txtNombre.setText("");
                txtFechaing.setText("");
            }
        });

        txtFechaing.setOnClickListener(new View.OnClickListener() {
            Calendar calendar=Calendar.getInstance();
            final int anio=calendar.get(Calendar.YEAR);
            final int mes=calendar.get(Calendar.MONTH);
            final int dia=calendar.get(Calendar.DAY_OF_MONTH);
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AggEmpleado.this, new DatePickerDialog.OnDateSetListener() {
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

                        txtFechaing.setText(fecha);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });
    }

    public boolean validar_registro(){
        String nombre=txtNombre.getText().toString();
        String Id=txtIdem.getText().toString();
        String fecha=txtFechaing.getText().toString();
        if(nombre.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Id.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(fecha.length()==0){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nombre.length()!=0 && Id.length()!=0 && fecha.length()!=0){
            Toast.makeText(getApplicationContext(),"Registro hecho exitosamente",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos correctamente",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void conectar() {
        txtFechaing = findViewById(R.id.txtFechaing);
        txtNombre = findViewById(R.id.txtNombre);
        txtIdem=findViewById(R.id.txtIdEm);
        btnAgregarEmpl = findViewById(R.id.btnAgregarEmpl);
    }
}