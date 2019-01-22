package com.example.administrador.myapplication;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper extends  Application{

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
