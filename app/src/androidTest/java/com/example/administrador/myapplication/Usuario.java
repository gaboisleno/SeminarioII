package com.example.administrador.myapplication;

public class Usuario {
    public String nombre;
    public int experiencia;
    public int nivel;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public int getNivel() {
        return this.nivel;
    }

    public String getNombre() {
        return this.nombre;
    }
}
