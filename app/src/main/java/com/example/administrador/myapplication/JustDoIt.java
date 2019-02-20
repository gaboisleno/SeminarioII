package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JustDoIt extends AppCompatActivity {

    int maxExc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);

        //Setear componentes
        final Button tired = findViewById(R.id.tired);
        final Button finish = findViewById(R.id.finished);
        final Button next = findViewById(R.id.next);
        final TextView myText = (TextView)findViewById(R.id.txtView);
        final ImageView gifView = (ImageView)findViewById(R.id.gifView);

        String texto = "";
        myText.setText(texto);

        finish.setVisibility(View.INVISIBLE);
        tired.setVisibility(View.INVISIBLE);

        Gson gson       = new Gson();
        FileHelper fh   = new FileHelper();

        //Json's
        String userInfo = fh.readFileAsString("user.json");
        Log.d("ReadFile", userInfo);
        String exercisesInfo = fh.readFileAsString("exercises.json");
        Log.d("ReadFile", exercisesInfo);

        Usuario appUser = gson.fromJson(userInfo, Usuario.class);

        //Recuperar ejercicios
        final List<Ejercicio> rutina = new ArrayList<>();
        final Ejercicio[] appRuotine = gson.fromJson(exercisesInfo, Ejercicio[].class);

        //Cargar la lista con ejercicios
        for (int i = 0; i < appRuotine.length; i++) {
            rutina.add(appRuotine[i]);
        }

        //Primer ejercicio
        texto = getExcersice(rutina);
        myText.setText(texto);
        maxExc = getMaxExc(appUser.getNivel());
        setImage(texto, gifView);
        //

        //Eventos click
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickNext","Siguiente ejercicio...");

                String texto = "";
                myText.setText(texto);
                texto = getExcersice(rutina);
                maxExc--;

                //Cambiar gif del ejercicio
                setImage(texto, gifView);

                //Rutina completa?
                if (texto.equals("") || maxExc < 1 ){
                    myText.setText("Completado!");
                    gifView.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    finish.setVisibility(View.VISIBLE);
                    tired.setVisibility(View.VISIBLE);
                }else{
                    myText.setText(texto);
                }
            }
        });

        tired.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickTired","Cansado...");
                //Restar experiencia aqui...
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickFinish","Completado!");
                //Sumar experiencia aqui...
                finish();
            }
        });

    }

    public String getExcersice(List<Ejercicio> rutina){
        int size = rutina.size();
        String retorno = "";

        if (size > 0){
            int rand = new Random().nextInt(size);
            retorno = rutina.get(rand).getNombre();
            rutina.remove(rand);
            return retorno;
        }else{
            return retorno;
        }

    }


    public int getMaxExc(int level){
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

    public void setImage(String id, ImageView img){
        switch (id){
            case "Zancadas":
                img.setImageResource(R.drawable.zancadas);
                break;
            case "Sentadillas":
                img.setImageResource(R.drawable.sentadillas);
                break;
            case "Flexiones":
                img.setImageResource(R.drawable.flexiones);
                break;
            case "Abdominales":
                img.setImageResource(R.drawable.abdominales);
                break;
            case "Elevacion de piernas":
                img.setImageResource(R.drawable.elevacion_piernas);
                break;
            case "Plancha":
                img.setImageResource(R.drawable.plancha);
                break;
            case "Abdominales bicileta":
                img.setImageResource(R.drawable.abdominales_bicicleta);
                break;

        }
    }
}
