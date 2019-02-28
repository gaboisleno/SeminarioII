package com.example.administrador.myapplication;

public class UserLog {

    private String day;
    private int exerciseType;
    private boolean complete;

    public UserLog(){}
    public UserLog(int type){
        this.exerciseType = type;
    }

    public UserLog(String day, int exerciseType, boolean complete){
        this.day = day;
        this.exerciseType = exerciseType;
        this.complete = complete;
    }

    public void setDay(String day){this.day = day;}
    public void setExerciseType(int type){this.exerciseType = type;}
    public void setComplete(boolean complete){this.complete = complete;}

    public String getDay() {
        return day;
    }
    public int getExerciseType() {
        return this.exerciseType;
    }
    public boolean getComplete(){ return this.complete; }

    public String completesino(){
        if (this.complete){
            return "completada ✔";
        } else return "no completa ✖";
    }

    public String getTypeString(){
        String retorno="";
        switch (exerciseType) {
            case 1:
                retorno = "piernas";
                break;
            case 2:
                retorno = "brazos";
                break;
            case 3:
                retorno = "torzo";
                break;
        }
        return retorno;
    }
}
