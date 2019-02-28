package com.example.administrador.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Gson gson       = new Gson();
    FileHelper fh   = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button metrics = findViewById(R.id.metrics);
        final Button options = findViewById(R.id.options);
        String myExercisesJson = fh.loadExercises();
        Usuario appUser = fh.loadUser();

        TextView myText = (TextView)findViewById(R.id.welcomeUser);

        //Todo: preguntar como hacer esto-.
        if( fh.writeStringAsFile(myExercisesJson, "exercises.json")){
            Log.d("File", "Exercise file created");
        }else{
            Log.d("File", "Exercise file not created !");
        }

        String exercisesInfo = fh.readFileAsString("exercises.json");
        Log.d("ReadFile", exercisesInfo);

        myText.setText(
                "Bienvenido " + appUser.getNombre()+" nivel "+appUser.getNivel() + " exp " + appUser.getExp()
        );

        metrics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this, Metrics.class);
                startActivity(miIntent);
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this, Options.class);
                startActivity(miIntent);
            }
        });
    }

    public void onClick (View view){
        Log.d("Onclick","GotoJustDoit");
        // todo Comprobar si el dia de hoy hizo la rutina//
        Intent miIntent = new Intent(MainActivity.this, JustDoIt.class);
        startActivity(miIntent);
    }





    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        Usuario appUser = fh.loadUser();
        TextView myText = (TextView)findViewById(R.id.welcomeUser);
        myText.setText(
                "Bienvenido " + appUser.getNombre()+" nivel "+appUser.getNivel() + " exp " + appUser.getExp()
        );
    }


}
