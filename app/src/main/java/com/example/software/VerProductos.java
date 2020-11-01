package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    RecyclerView recProductos;
    myClass myClass;
    SQLiteDatabase db;
    ArrayList<Producto>productoArrayList;
    private ProductoAdapter productoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
        myClass=new myClass(this);
        myClass.startWork();
        db=myClass.getWritableDatabase();
        recProductos=findViewById(R.id.recProductos);
        productoArrayList=new ArrayList<>();
        productoAdapter=new ProductoAdapter(this,productoArrayList);
        RecyclerView recyclerView=findViewById(R.id.recProductos);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(productoAdapter);
        mostrarEnRecycler();
    }

    private ArrayList<String> ListaUnidades(){
        Cursor myCursor;
        ArrayList<String>datos= new ArrayList<>();
        SQLiteDatabase db =myClass.getWritableDatabase();
        myCursor=db.rawQuery("SELECT * FROM producto",null);
        if(myCursor.moveToFirst()) {
            do {
                String line =myCursor.getInt(0)+"\n"+
                        myCursor.getInt(1)+"\n"+
                        myCursor.getString(2)+"\n"+
                        myCursor.getString(3)+"\n"+
                        myCursor.getString(4)+"\n"+
                        myCursor.getString(5)+"\n"+
                        myCursor.getInt(6);
                datos.add(line);
            }while (myCursor.moveToNext());
        }
        db.close();
        return  datos;
    }
    public void mostrarEnRecycler(){
        SQLiteDatabase db=myClass.getReadableDatabase();
        Producto producto=null;

        Cursor cursor=db.rawQuery("SELECT * FROM producto",null);
        while (cursor.moveToFirst()){
            producto=new Producto();
            producto.setID(cursor.getInt(0));
            producto.setNombre(cursor.getString(2));
            producto.setReferencia(cursor.getString(3));
            producto.setMarca(cursor.getString(4));
            producto.setDesscripcion(cursor.getString(5));
            producto.setPrecio(cursor.getInt(6));
            productoAdapter.agregarProducto(producto);

        }
    }
}