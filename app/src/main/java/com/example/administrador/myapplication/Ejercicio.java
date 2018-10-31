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
}
