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

        Gson gson = new Gson();
        //Json's
        String myUserJson = "{\"nombre\":\"Gabriel\",\"nivel\":\"1\",\"experiencia\":0}";
        Usuario appUser = gson.fromJson(myUserJson, Usuario.class);

        TextView myText = (TextView)findViewById(R.id.welcomeUser);
        myText.setText("Bienvenido " + appUser.getNombre());

        FileHelper fh = new FileHelper();

        if( fh.writeStringAsFile(myUserJson, "user.json")){
           Log.d("File", "File created");
        }else{
           Log.d("File", "File not created !");
        }

        String texto = fh.readFileAsString("user.json");
        Log.d("ReadFile", texto);


    }

    public void onClick (View view){
        Intent miIntent = new Intent(MainActivity.this, JustDoIt.class);
        startActivity(miIntent);
    }






}
