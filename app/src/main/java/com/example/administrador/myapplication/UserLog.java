package com.example.administrador.myapplication;

public class UserLog {

    private String day;
    private int exerciseType;

    public UserLog(){}

    public UserLog(String day, int exerciseType){
        this.day = day;
        this.exerciseType = exerciseType;
    }

    public void setDay(String day){this.day = day;}
    public void setExerciseType(int type){this.exerciseType = type;}

    public String getDay() {
        return day;
    }
    public int getExerciseType() {
        return this.exerciseType;
    }
}
