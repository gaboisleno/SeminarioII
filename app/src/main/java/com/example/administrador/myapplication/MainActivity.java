package com.example.administrador.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson       = new Gson();
        FileHelper fh   = new FileHelper();

        String myExercisesJson = "[" +
                "{\"descripcion\":\"Test\",\"nombre\":\"Zancadas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Sentadillas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales\",\"tipo\":1},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Elevacion de piernas\",\"tipo\":1},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Plancha\",\"tipo\":1} " +
                "]";

        TextView myText = (TextView)findViewById(R.id.welcomeUser);

        if( fh.writeStringAsFile(myExercisesJson, "exercises.json")){
            Log.d("File", "Exercise file created");
        }else{
            Log.d("File", "Exercise file not created !");
        }

        //*Read json files
        String userInfo = fh.readFileAsString("user.json");
        Log.d("ReadFile", userInfo);

        String exercisesInfo = fh.readFileAsString("exercises.json");
        Log.d("ReadFile", exercisesInfo);

        Usuario appUser = gson.fromJson(userInfo, Usuario.class);
        myText.setText("Bienvenido " + appUser.getNombre());

    }

    public void onClick (View view){
        Intent miIntent = new Intent(MainActivity.this, JustDoIt.class);
        startActivity(miIntent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }




}
