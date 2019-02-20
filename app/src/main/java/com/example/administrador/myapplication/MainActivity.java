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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson       = new Gson();
        FileHelper fh   = new FileHelper();

        String myExercisesJson = fh.LoadData();
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
                "Bienvenido " + appUser.getNombre()+" estás en nivel "+appUser.getNivel()
        );
    }

    public void onClick (View view){
        Log.d("Onclick","GotoJustDoit");

        Intent miIntent = new Intent(MainActivity.this, JustDoIt.class);
        startActivity(miIntent);
    }

    //TODO agregar un archivo log, con informacion sobre que dias se conectó
    public String getDate(){
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        String todayString = formatter.format(todayDate);
        Log.d("DATE", todayString);
        return todayString;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }




}
