package com.example.administrador.myapplication;

import static java.lang.Math.sqrt;

public class LevelManager {

    public static int repetitions(int level){
        return 8 + ((level-1) * 2);
    }

    public static int getSeries(int level){
        int retorno;

        switch (level){
            case 1: case 2: case 3:
                retorno = 2;
                break;
            case 4: case 5: case 6:
                retorno = 3;
                break;
            case 7: case 8: case 9:
                retorno = 4;
                break;
            default:
                retorno = 5;
                break;
        }

        return retorno;
    }

    public static int getExercises(int level){
        //Segun el nivel, retorna el maximo de ejercicios
        int max;
        switch (level)
        {
            case 1: case 2: case 3: case 4:
                max = 3;
                break;
            case 5: case 6: case 7: case 8:
                max = 4;
                break;
            case 9: case 10: case 11: case 12:
                max=5;
                break;
            default:
                max = 6;
                break;
        }
        return max;
    }




}
