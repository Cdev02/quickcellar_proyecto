package com.example.software;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Carrito extends AppCompatActivity {
    Button btnCarrito;
    EditText txtCarrito;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        conectar();

    }

    private void conectar() {
        btnCarrito.findViewById(R.id.btnCarrito);
        txtCarrito.findViewById(R.id.txtCarrito);
        spinner.findViewById(R.id.spinner);
    }
}