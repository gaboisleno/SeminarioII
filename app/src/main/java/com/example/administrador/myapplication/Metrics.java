package com.example.administrador.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

public class Metrics extends AppCompatActivity {

    FileHelper fh = new FileHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        setTitle("Registro de rutinas");

        ListView lista = findViewById(R.id.listView);
        ArrayAdapter<String> adaptador;
        List<UserLog> logs = fh.getLogList();
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        String text;

        for (int i= logs.size()-1; i >= 0; i--){
            text =  logs.get(i).getDay() + " - Rutina de "+logs.get(i).getTypeString() + " " + logs.get(i).completesino();
            adaptador.add(text);
        }

        lista.setAdapter(adaptador);

    }

}
