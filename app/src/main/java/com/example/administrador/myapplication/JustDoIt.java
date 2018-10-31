package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class JustDoIt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);


        Gson gson = new Gson();

        //De Obj A Json
        /*
        Ejercicio ej = new Ejercicio(1, "Flexiones", "hola");
        String myJson = gson.toJson(ej);
        */

        //De Json a Obj
        /*
        String myJson = "{\"descripcion\":\"hola\",\"nombre\":\"Flexiones\",\"tipo\":1}";
        Ejercicio ej = gson.fromJson(myJson, Ejercicio.class);
        */

        //De un Json a una Lista
        String myJson = "[{\"descripcion\":\"hola\",\"nombre\":\"Flexiones\",\"tipo\":1}, {\"descripcion\":\"hola\",\"nombre\":\"Sentadillas\",\"tipo\":2} ]";

        List<Ejercicio> rutina = new ArrayList<>();
        Ejercicio[] rutin = gson.fromJson(myJson, Ejercicio[].class);

    }

    public void onClick (View view){
        finish();
    }
}
