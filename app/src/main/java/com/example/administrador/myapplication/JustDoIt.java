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

        //Json's
        String myUserJson = "{\"nombre\":\"Gabriel\",\"nivel\":\"1\",\"experiencia\":0}";

        String myExercisesJson = "[" +
        "{\"descripcion\":\"Test\",\"nombre\":\"Zancadas\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Sentadillas\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones\",\"tipo\":1}, " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales\",\"tipo\":1},  " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Plank Jumpins\",\"tipo\":1},  " +
        "{\"descripcion\":\"Test\",\"nombre\":\"Lunges Step-ups\",\"tipo\":2} " +
        "]";

        Usuario appUser = gson.fromJson(myUserJson, Usuario.class);

        //Recuperar ejercicios
        List<Ejercicio> rutina = new ArrayList<>();
        Ejercicio[] appRuotine = gson.fromJson(myExercisesJson, Ejercicio[].class);

        //llenar la lista de ejercicios
        for (int i = 0; i < appRuotine.length; i++) {
            rutina.add(appRuotine[i]);
        }

        //Limpiar screen
        TextView myText = (TextView)findViewById(R.id.txtView);
        myText.setText("");

        //Mostrar todos los ejercicios en el texview
        int maxExc = getMaxExc(appUser.getNivel());
        int rnd = 0;

        String texto = myText.getText() + "\n" +
                " Nombre: "+ appUser.getNombre() +
                " Experiencia: "+ appUser.getExperiencia() +
                " Nivel: "+ appUser.getNivel() +
                "\n";

        myText.setText(texto);

        for (int i=0; i < maxExc; i++){
            rnd = new Random().nextInt(rutina.size());

            texto = myText.getText() + "\n" + rutina.get(rnd).getNombre();

            myText.setText(texto);

            rutina.remove(rnd);
        }

    }


    public void onClick (View view){
        finish();
    }

    public int getMaxExc(int level){
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
