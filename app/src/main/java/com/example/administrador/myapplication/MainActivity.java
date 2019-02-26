package com.example.administrador.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Gson gson       = new Gson();
    FileHelper fh   = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String myExercisesJson = fh.loadData();
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
    }

    public void onClick (View view){
        Log.d("Onclick","GotoJustDoit");
        Intent miIntent = new Intent(MainActivity.this, JustDoIt.class);
        fh.saveLog(fh.getDate());
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
