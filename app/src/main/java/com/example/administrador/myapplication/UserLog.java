package com.example.administrador.myapplication;

public class UserLog {

    private String day;
    private int exerciseType;
    private boolean complete;
    private int levelActivity = 0;

    public UserLog(){}
    public UserLog(int type){
        this.exerciseType = type;
    }
    public UserLog(String day, int exerciseType, boolean complete, int levelActivity){
        this.day = day;
        this.exerciseType = exerciseType;
        this.complete = complete;
        this.levelActivity = levelActivity;
    }

    public void setDay(String day){this.day = day;}
    public void setExerciseType(int type){this.exerciseType = type;}
    public void setComplete(boolean complete){this.complete = complete;}
    public void setLevelUp(){this.levelActivity = 1;}
    public void setLevelDown(){this.levelActivity = -1;}
    public void setLevelNull(){this.levelActivity = 0;}

    public int getLevelActivity (){return this.levelActivity;}
    public String getDay() {
        return day;
    }
    public int getExerciseType() {
        return this.exerciseType;
    }
    public boolean getComplete(){ return this.complete; }

    public String completesino(){
        if (this.complete){
            return "completa ✔";
        } else return "incompleta ✖";
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
