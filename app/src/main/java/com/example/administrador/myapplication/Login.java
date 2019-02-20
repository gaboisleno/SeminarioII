package com.example.administrador.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        Button btn = (Button)findViewById(R.id.buttonContinue);

        //Si el usuario existe goto MainActivity
        if (fh.fileExists("user.json")){
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                TextView textName = (TextView)findViewById(R.id.editName);

                if (textName.getText().length() > 1){
                    String name = textName.getText()+"";
                    Usuario newUser = new Usuario(name,1,0);

                    if (fh.saveUser(newUser)){
                        startActivity(new Intent(Login.this, MainActivity.class));
                    } else { Log.d("Error","Error on saveUser(), Login activity"); }

                } else {
                    textName.setError("Campo requerido!");
                }
            }
        });
    }


    public void requestPermission(){

        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;

        if (ContextCompat.checkSelfPermission(Login.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // this thread waiting for the user's response! After the user
            } else {
                ActivityCompat.requestPermissions(Login.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
    }
}
