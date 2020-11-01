package com.example.software;

public class Producto {
    private  int ID;
    private String nombre;
    private String referencia;
    private String marca;
    private String desscripcion;
    private  int precio;

    public Producto() {
    }

    public Producto(int ID, String nombre, String referencia, String marca, String desscripcion, int precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.referencia = referencia;
        this.marca = marca;
        this.desscripcion = desscripcion;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDesscripcion() {
        return desscripcion;
    }

    public void setDesscripcion(String desscripcion) {
        this.desscripcion = desscripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
