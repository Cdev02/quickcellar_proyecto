package com.example.software;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class Product_recy extends AppCompatActivity {

    RecyclerView idrecyclerview;
    ArrayList<Producto> productoArrayList;
    myClass myClass;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recy);
        setContentView(R.layout.activity_product_recy);
        idrecyclerview = findViewById(R.id.idrecyclerview);
        productoArrayList = new ArrayList<>();
        sqlLite = new SqlLite(this, "producto", null, 1);
        productoAdapter = new ProductoAdapter(this, productoArrayList);

        RecyclerView recyclerView = findViewById(R.id.idrecyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(ProductoAdapter);
        mostrarDatos();
    }
    public void mostrarDatos() {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Producto producto = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM persona", null);
        while (cursor.moveToNext()) {
            producto = new Producto();
            producto.setID(cursor.getInt(0));
            producto.setNombre(cursor.getString(1));
            producto.setReferencia(cursor.getString(2));
            producto.setMarca(cursor.getString(3));
            producto.setDesscripcion(cursor.getString(4));
            producto.setPrecio(cursor.getInt(5));

        }
    }
}