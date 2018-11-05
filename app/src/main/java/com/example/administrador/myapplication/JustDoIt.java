package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


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
        String myJson;
        myJson = "[" +
        "{\"descripcion\":\"Test\",\"nombre\":\"Lunges\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"High Knees\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Turning Kicks\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Climbers\",\"tipo\":1},  " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Plank Jumpins\",\"tipo\":1},  " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Lunges Step-ups\",\"tipo\":2} " +
        "]";

        List<Ejercicio> rutina = new ArrayList<>();
        Ejercicio[] rutin = gson.fromJson(myJson, Ejercicio[].class);

        //Escribir en el textbox
        TextView myText = (TextView)findViewById(R.id.txtView);
        myText.setText("");

        //Mostrar todos los ejercicios en el texview
        int maxExc = 3;
        int rnd = 0;

        for (int i=0; i < maxExc && i < rutin.length; i++){
            rnd = new Random().nextInt(rutin.length);
            myText.setText(myText.getText() + "\n" + rutin[rnd].getNombre());
        }
    }


    public void onClick (View view){
        finish();
    }
}
