package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

public class JustDoIt extends AppCompatActivity {

    public int maxExc;
    FileHelper fh   = new FileHelper();
    Gson gson       = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);

        //Setear variables
        Usuario appUser = fh.loadUser();
        //final List<Ejercicio> rutina = fh.getExerciseList();
        final List<Ejercicio> rutina = fh.filteredRutine(3); //Filtro de rutina

        //Setear componentes
        final Button tired = findViewById(R.id.tired);
        final Button finish = findViewById(R.id.finished);
        final Button next = findViewById(R.id.next);
        final TextView myText = (TextView)findViewById(R.id.txtView);
        final TextView repetitionsText = (TextView)findViewById(R.id.txtRepetitions);
        final TextView descriptionText = (TextView)findViewById(R.id.txtDescription);
        final ImageView gifView = (ImageView)findViewById(R.id.gifView);

        finish.setVisibility(View.INVISIBLE);
        tired.setVisibility(View.INVISIBLE);

        String texto = "";
        String repetitionInfo =
                LevelManager.getSeries(appUser.getNivel())+ " series de " +
                        LevelManager.repetitions(appUser.getNivel()) + " repeticiones";
        myText.setText(texto);
        repetitionsText.setText(repetitionInfo);

        maxExc = LevelManager.getExercises(appUser.getNivel());
        setTitle( (maxExc-1) + " ejercicios restantes");

        //Primer ejercicio
        Ejercicio exc = getExcersice(rutina);
        myText.setText(exc.getNombre());
        setImage(exc.getNombre(), gifView);

        //Evento click
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickNext","Siguiente ejercicio...");
                setTitle( (maxExc-2) + " ejercicios restantes");

                String texto = "";
                myText.setText(texto);

                Ejercicio exc = getExcersice(rutina);
                maxExc--;

                //Cambiar gif del ejercicio
                setImage(exc.getNombre(), gifView);

                //Rutina completa?
                if (exc.getNombre().equals("") || maxExc < 1 ){
                    myText.setText("Completado!");
                    setTitle( "Rutina completa");

                    gifView.setVisibility(View.INVISIBLE);
                    repetitionsText.setVisibility(View.INVISIBLE);
                    descriptionText.setVisibility(View.INVISIBLE);
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
                Usuario appUser = fh.loadUser();
                appUser.levelDown();//todo: quitar exp en lugar de lvl
                end(appUser);

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickFinish","Completado!");
                Usuario appUser = fh.loadUser();
                appUser.levelUp(); //todo: dar exp en lugar de lvl
                end(appUser);
            }
        });

    }

    public void end(Usuario appUser){
        if (fh.saveUser(appUser)) finish();
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
            case "Abdominales cruzados":
                img.setImageResource(R.drawable.abdominales_cruzados);
                break;
            case "Escalar montaña":
                img.setImageResource(R.drawable.escalar_montana);
                break;

        }
    }
}
