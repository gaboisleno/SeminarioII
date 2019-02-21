package com.example.administrador.myapplication;

import static java.lang.Math.sqrt;

public class LevelManager {

    public static int getMaxExc(int level){
        //Segun el nivel, retorna el maximo de ejercicios
        int max;
        switch (level)
        {
            case 1: case 2: case 3:
                max = 3;
                break;
            case 4: case 5: case 6:
                max = 4;
                break;
            default:
                max = 6;
                break;
        }
        return max;
    }




}
