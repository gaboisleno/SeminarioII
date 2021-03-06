package com.example.administrador.myapplication;

public class Usuario {
    private String nombre;
    private int nivel;
    private int experiencia;

    public Usuario(String nombre, int nivel, int exp){
        this.nombre = nombre;
        this.nivel = nivel;
        this.experiencia = exp;
    }

    public boolean winExp(){
        boolean ret = false;

        if ((this.experiencia) >= (this.nivel * 500)){
            this.experiencia = 0;
            this.nivel += 1;
            ret = true;
        } else {
            this.experiencia += 500;
        }

        return ret;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setExp(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void levelUp (){
        this.nivel++;
    }

    public void levelDown(){
        if (this.nivel>1 )
            this.nivel--;
            this.experiencia=0;
    }

    public int getExp() {
        return this.experiencia;
    }

    public int getNivel() {
        return this.nivel;
    }

    public String getNombre() {
        return this.nombre;
    }

    private void manageExp(){

    }

}
