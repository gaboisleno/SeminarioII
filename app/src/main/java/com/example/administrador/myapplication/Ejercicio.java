package com.example.administrador.myapplication;

import com.google.gson.annotations.SerializedName;

class Ejercicio {

    private int tipo;


    private String nombre;


    private String descripcion;

    public Ejercicio(int tipo, String nombre, String descripcion){
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
