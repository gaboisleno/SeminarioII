package com.example.administrador.myapplication;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class FileHelper extends  Application{

    //TODO implementar encode-decode, la funcion esta lista
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
