package com.example.administrador.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class Login extends AppCompatActivity {

    FileHelper fh = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Si el usuario existe goto MainActivity
        if (fh.fileExists("user.json")){
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        Button btn = (Button)findViewById(R.id.buttonContinue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textName = (TextView)findViewById(R.id.editName);

                if (textName.getText().length() > 1){
                    Gson gson = new Gson();

                    //Create user
                    String myUserJson = "{\"nombre\":\""+textName.getText()+"\",\"nivel\":\"1\",\"experiencia\":0}";
                    //Usuario appUser = gson.fromJson(myUserJson, Usuario.class);

                    //*Create json file
                    if( fh.writeStringAsFile(myUserJson, "user.json")){
                        //Go to MainActivity
                        Log.d("File", "User file created");
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else{
                        Log.d("File", "User file not created !");
                    }
                }else{
                    textName.setError("Campo requerido!");
                }


            }
        });
    }
}
