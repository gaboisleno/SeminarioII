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
import java.util.logging.Level;

public class JustDoIt extends AppCompatActivity {

    private int maxExc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);

        //Setear variables
        Gson gson       = new Gson();
        FileHelper fh   = new FileHelper();
        Usuario appUser = fh.loadUser();
        final List<Ejercicio> rutina = fh.getExerciseList();

        maxExc = LevelManager.getMaxExc(appUser.getNivel());

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

        //Primer ejercicio
        Ejercicio exc = getExcersice(rutina);
        myText.setText(exc.getNombre());
        setImage(exc.getNombre(), gifView);

        //Evento click
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickNext","Siguiente ejercicio...");

                String texto = "";
                myText.setText(texto);

                Ejercicio exc = getExcersice(rutina);
                maxExc--;

                //Cambiar gif del ejercicio
                setImage(exc.getNombre(), gifView);

                //Rutina completa?
                if (exc.getNombre().equals("") || maxExc < 1 ){
                    myText.setText("Completado!");
                    gifView.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.INVISIBLE);
                    finish.setVisibility(View.VISIBLE);
                    tired.setVisibility(View.VISIBLE);
                }else{
                    myText.setText(exc.getNombre());
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
                FileHelper fh = new FileHelper();
                Usuario appUser = fh.loadUser();

                Log.d("Load user",appUser.toString());

                appUser.setExp(appUser.getExp() + 100);
                if (fh.saveUser(appUser)) finish();

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
        }else{
            return new Ejercicio(0,"","");
        }
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
