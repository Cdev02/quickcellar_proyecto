package com.example.software;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class comprar_activity extends AppCompatActivity {
    RecyclerView recProductos;
    myClass myClass;
    SQLiteDatabase db;
    ArrayList<String> nombre_pr, referencia_pr,marca_pr,descr_pr;
    ArrayList<Integer> id_producto, id_seccionPr,precio_pr;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_activity);
        recProductos=findViewById(R.id.recProductos);
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        inicializarArrays();
        guardarDatosEnArray();
        customAdapter = new CustomAdapter(comprar_activity.this,this, nombre_pr, referencia_pr, marca_pr,
                descr_pr,id_producto, id_seccionPr,precio_pr);
        recProductos.setAdapter(customAdapter);
        recProductos.setLayoutManager(new LinearLayoutManager(comprar_activity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void guardarDatosEnArray(){
        Cursor myCursor;
        myCursor=db.rawQuery("SELECT * FROM producto",null);
        if(myCursor.moveToFirst()) {
            do {
                id_producto.add(myCursor.getInt(0));
                id_seccionPr.add(myCursor.getInt(1));
                nombre_pr.add(myCursor.getString(2));
                referencia_pr.add(myCursor.getString(3));
                marca_pr.add(myCursor.getString(4));
                descr_pr.add(myCursor.getString(5));
                precio_pr.add(myCursor.getInt(6));

            }while (myCursor.moveToNext());
        }
        db.close();
    }

    private void inicializarArrays(){
        id_producto=new ArrayList<>();
        id_seccionPr=new ArrayList<>();
        nombre_pr=new ArrayList<>();
        referencia_pr=new ArrayList<>();
        marca_pr=new ArrayList<>();
        descr_pr=new ArrayList<>();
        precio_pr=new ArrayList<>();
    }
}