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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileHelper extends  Application{

    Gson gson = new Gson();

    public String LoadData() {
        String tContents = "[" +
                "{\"descripcion\":\"Test\",\"nombre\":\"Zancadas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Sentadillas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales\",\"tipo\":1},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Elevacion de piernas\",\"tipo\":1},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Plancha\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales bicileta\",\"tipo\":1} " +
                "]";
        return tContents;
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
