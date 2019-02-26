package com.example.administrador.myapplication;
import android.app.Application;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FileHelper extends  Application{

    Gson gson = new Gson();

    public String loadData() {
        String tContents = "[" +
                "{\"descripcion\":\"Test\",\"nombre\":\"Zancadas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Sentadillas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Escalar montaña\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales\",\"tipo\":3},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales cruzados\",\"tipo\":3},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Elevacion de piernas\",\"tipo\":2},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Plancha\",\"tipo\":3}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales bicileta\",\"tipo\":3} " +
                "]";
        return tContents;
    }

    public List<Ejercicio> getExerciseList(){
        //Recuperar ejercicios
        String exercisesInfo = loadData();

        final List<Ejercicio> rutina = new ArrayList<>();
        final Ejercicio[] appRuotine = gson.fromJson(exercisesInfo, Ejercicio[].class);

        //Cargar la lista con ejercicios
        for (int i = 0; i < appRuotine.length; i++) {
            rutina.add(appRuotine[i]);
        }
        return rutina;
    }

    public List<Ejercicio> filteredRutine(int tipo){
        //1:Piernas
        //2:Brazos
        //3:Torzo

        List<Ejercicio> all = getExerciseList();
        List<Ejercicio> filtered = new ArrayList<>();

        for (Ejercicio e : all) {
            Log.d("Filter",e.getNombre());
            if (e.getTipo() == tipo){
                filtered.add(e);
            }
        }
        return filtered;
    }

    public String lastLog(){
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String lastLine = "";
            br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/log.txt"));

            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
                lastLine = sCurrentLine;
            }
            br.close();
            return lastLine;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean saveLog(String data){
        if (data.equals(lastLog())){
            Log.d("Date","Same Date last log!!!");
            return false;
        }

        BufferedWriter bw = null;
        try {
            Log.d("Appending", "Appending mensaje "+data);
            bw = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/log.txt", true));
            bw.write(data);
            bw.newLine();
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e) {
            Log.d("Appending", "Appending Error");
            return false;
        }
    }

    //TODO agregar un archivo log, con informacion sobre que dias se conectó
    public String getDate(){
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todayString = formatter.format(todayDate);
        Log.d("DATE", todayString);
        return todayString;
    }

    public Usuario loadUser(){
        String userInfo = readFileAsString("user.json");
        Log.d("ReadFile", userInfo);
        Usuario appUser = gson.fromJson(userInfo, Usuario.class);
        return appUser;
    }

    public boolean saveUser(Usuario user){
        String myUserJson = "{\"nombre\":\""+user.getNombre()+"\",\"nivel\":\""+user.getNivel()+"\",\"experiencia\":"+user.getExp()+"}";
        //*Create json file
        if (writeStringAsFile(myUserJson, "user.json")){
            Log.d("File", "User file created");
               return true;
        }else{
            Log.d("File", "User file not created !");
            return false;
        }
    }

    //start TODO implementar encode-decode, la funcion está lista
    public String encode(String text){
        byte[] data = text.getBytes();
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }

    public String decode(String base64){
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        String text = new String(data);
        return text;
    }

    //end TODO ------------------------------------------------

    public boolean writeStringAsFile(String fileContents, String fileName) {
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory(), fileName));
            out.write(fileContents);
            out.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean fileExists(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public String readFileAsString(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (IOException e) {

        }

        return stringBuilder.toString();
    }


}
