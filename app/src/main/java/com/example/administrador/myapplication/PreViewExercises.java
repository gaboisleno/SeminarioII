package com.example.administrador.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class PreViewExercises extends AppCompatActivity {

    FileHelper fh = new FileHelper();
    int type;
    int maxExc;
    List<Ejercicio> listTodo = new ArrayList<Ejercicio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view_exercises);
        final Button ready = findViewById(R.id.btnReady);
        setTitle("Ésta será tu rutina");

        Usuario appUser = fh.loadUser();
        UserLog lastUserLog = fh.lastLog();

        ListView lista = findViewById(R.id.listView);
        ArrayAdapter<String> adaptador;

        //Filtro de rutina
        Bundle extras = getIntent().getExtras();

        type = lastUserLog.getExerciseType();
        if (type==1) {
            type=2;
        } else if (type==2) {
            type=3;
        } else if (type==3) {
            type=1;
        }

        if (extras != null) {
            type = extras.getInt("type");
        }

        Log.e("TYPe", ""+type);
        maxExc = LevelManager.getExercises(appUser.getNivel());
        final List<Ejercicio> ejercicios = fh.filteredRutine(type);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        String text;


        for (int i=0; i < maxExc; i++){
            Ejercicio exc = getExcersice(ejercicios);
            listTodo.add(exc);
            text = exc.getNombre() +" "+LevelManager.getSeries(appUser.getNivel())+"x"+ LevelManager.repetitions(appUser.getNivel());
            adaptador.add(text);
        }
        text = "- Tiempo estimado "+ LevelManager.getSeries(appUser.getNivel()) * 5 +" min -";
        adaptador.add(text);

        lista.setAdapter(adaptador);

        ready.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(PreViewExercises.this, JustDoIt.class);

                Gson gson = new Gson();
                String jsonList = gson.toJson(listTodo);
                miIntent.putExtra("myList", jsonList);
                startActivity(miIntent);
            }
        });
    }

    public Ejercicio getExcersice(List<Ejercicio> rutina){
        int size = rutina.size();
        Ejercicio retorno;

        if (size > 0){
            int rand = new Random().nextInt(size);
            retorno = rutina.get(rand);
            rutina.remove(rand);
            return retorno;
        } else {
            return new Ejercicio(0,"","");
        }
    }
}
