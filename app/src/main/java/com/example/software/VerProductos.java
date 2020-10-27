package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class VerProductos extends AppCompatActivity {

    ListView llProductos;
    Button btnVolverr;
    myClass myClass;
    SQLiteDatabase db;
    ArrayList<String> listado=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
        conectar();
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        desplegar();
        btnVolverr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void conectar() {
        llProductos = findViewById(R.id.llProductos);
        btnVolverr= findViewById(R.id.btnVolverr);
    }
    private ArrayList<String> ListaUnidades(){
        Cursor myCursor;
        ArrayList<String>datos= new ArrayList<>();
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT * FROM producto",null);
        if(myCursor.moveToFirst()) {
            do {
                String line =myCursor.getString(0)+"   "+
                        myCursor.getString(1)+"   "+
                        myCursor.getString(2)+"   "+
                        myCursor.getString(3)+"   "+
                        myCursor.getString(4)+"   "+
                        myCursor.getInt(5);
                datos.add(line);
            }while (myCursor.moveToNext());
        }
        db.close();
        return  datos;
    }
    private void desplegar()
    {
        listado=ListaUnidades();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listado);
        llProductos.setAdapter(adapter);
    }
}