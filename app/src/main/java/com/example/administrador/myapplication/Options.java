package com.example.administrador.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Options extends AppCompatActivity {

    FileHelper fh = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        setTitle("Opciones");

        final Button rename = findViewById(R.id.rename);
        final Button clean = findViewById(R.id.clear_data);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final Usuario user = fh.loadUser();


        rename.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String newName = editText.getText().toString();
                if (newName.length()>1){
                    user.setNombre(newName);
                    fh.saveUser(user);
                    Toast.makeText(Options.this, "Cambio realizado con exito",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    editText.setError("Campo requerido!");
                }
            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new AlertDialog.Builder(Options.this)
                        .setTitle("Confirmacion")
                        .setMessage("Realmente desea borrar los datos de usuario?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                               fh.writeStringAsFile("","log.json");
                               Usuario newUser = new Usuario("Invitado", 1, 0);
                               fh.saveUser(newUser);

                                Toast.makeText(Options.this, "Datos eliminados",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}
