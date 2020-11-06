package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class agregar_alCarrito extends AppCompatActivity {

    Button btnCrearCar;
    Spinner spSelecEmp;
    EditText txtFechaCreaCar,txtEmpleadoCargo;
    myClass myClass=new myClass(this);
    ArrayList<String>idEmp=new ArrayList<>();
    ArrayList<String> listado=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_al_carrito);
        myClass.startWork();
        conectar();
        desplegar();
        Calendar calendar=Calendar.getInstance();
        String fechaHoraActual= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        txtFechaCreaCar.setText(fechaHoraActual);



        spSelecEmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtEmpleadoCargo.setText(spSelecEmp.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCrearCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idEmpleadoExtraido=idEmp.get(spSelecEmp.getSelectedItemPosition()).trim();
                Intent intent = new Intent(getApplicationContext(), comprar_activity.class);
                intent.putExtra("idEmpleado", idEmpleadoExtraido);
                intent.putExtra("fechaCreacion", txtFechaCreaCar.getText().toString());
                startActivity(intent);
            }
        });
    }




    private ArrayList<String> extraerEmpleados(){
        Cursor myCursor;
        ArrayList<String>datos= new ArrayList<>();
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT * FROM empleado",null);
        if(myCursor.moveToFirst()) {
            do {
                String nombre = myCursor.getString(0)+ " "+myCursor.getString(1);
                idEmp.add(myCursor.getString(0));
                datos.add(nombre);
            }while (myCursor.moveToNext());
        }
        db.close();
        return datos;
    }
    private void desplegar()
    {
        listado=extraerEmpleados();
        ArrayAdapter adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item_personalizado,listado);
        spSelecEmp.setAdapter(adapter);
    }

    private void conectar(){
        btnCrearCar=findViewById(R.id.btnCrearCarrito);
        spSelecEmp=findViewById(R.id.spEmpleados);
        txtFechaCreaCar=findViewById(R.id.txtFechaCreacionCarrito);
        txtEmpleadoCargo=findViewById(R.id.txtEmpleadoACargoCarrito);
    }
}