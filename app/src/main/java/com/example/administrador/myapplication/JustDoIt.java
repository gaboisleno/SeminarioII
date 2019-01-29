package com.example.administrador.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JustDoIt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_do_it);

        Gson gson       = new Gson();
        FileHelper fh   = new FileHelper();

        //Json's
        String userInfo = fh.readFileAsString("user.json");
        Log.d("ReadFile", userInfo);
        String exercisesInfo = fh.readFileAsString("exercises.json");
        Log.d("ReadFile", exercisesInfo);

        Usuario appUser = gson.fromJson(userInfo, Usuario.class);

        //Recuperar ejercicios
        List<Ejercicio> rutina = new ArrayList<>();
        Ejercicio[] appRuotine = gson.fromJson(exercisesInfo, Ejercicio[].class);

        //Llenar la lista con ejercicios
        for (int i = 0; i < appRuotine.length; i++) {
            rutina.add(appRuotine[i]);
        }

        //Limpiar screen
        TextView myText = (TextView)findViewById(R.id.txtView);
        myText.setText("");

        //Mostrar los ejercicios en el texview
        int maxExc = getMaxExc(appUser.getNivel());
        int rand = 0;

        String texto = myText.getText() + "\n" +
                "Nivel: "+ appUser.getNivel() +
                " Experiencia: "+ appUser.getExperiencia() +
                "\n";

        myText.setText(texto);

        for (int i=0; i < maxExc; i++){
            rand = new Random().nextInt(rutina.size());

            texto = myText.getText() + "\n" + rutina.get(rand).getNombre();

            myText.setText(texto);

            rutina.remove(rand);
        }




        final Button tired = findViewById(R.id.tired);
        tired.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickTired","Cansado...");
                //Restar experiencia aqui...
            }
        });

        final Button finish = findViewById(R.id.finished);
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("OnClickFinish","Completado!");
                //Sumar experiencia aqui...
            }
        });

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
