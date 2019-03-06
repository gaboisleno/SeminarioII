package com.example.administrador.myapplication;
import android.app.Application;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileHelper extends  Application{

    Gson gson = new Gson();


    public String testLoad(){
        String s = "";

        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.exercises);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
           s = s + new String(b);
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return s;
    }

    public String loadExercises() {
        String tContents = "[" +
                "{\"descripcion\":\"Test\",\"nombre\":\"Zancadas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Jalones\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Extension de cadera\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Sentadillas\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Escalar montaña\",\"tipo\":1}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones en banco\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Flexiones espartanas\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Triceps en banco\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Saltos burpees\",\"tipo\":2}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales\",\"tipo\":3},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales cruzados\",\"tipo\":3},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Elevacion de piernas\",\"tipo\":3},  " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Plancha\",\"tipo\":3}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Lumbares\",\"tipo\":3}, " +
                "{\"descripcion\":\"Test\",\"nombre\":\"Abdominales bicileta\",\"tipo\":3} " +
                "]";
        return tContents;
    }

    public List<Ejercicio> getExerciseList(){
        //Recuperar ejercicios
        String exercisesInfo = loadExercises();

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

    public List<UserLog> getLogList(){
        //Recuperar logs
        String logInfo = "["+readFileAsString("log.json")+"]";
        Log.d("LOGS", ""+logInfo);

        List<UserLog> logList = new ArrayList<>();

        UserLog[] logJson = gson.fromJson(logInfo, UserLog[].class);

        Log.d("LOGS LENGHT", ""+logJson.length);

        //Cargar la lista
        for (int i = 0; i < logJson.length; i++) {
            logList.add(logJson[i]);
        }

        return logList;
    }

    public UserLog lastLog(){
        BufferedReader br = null;
        UserLog log = null;

        if (!(fileExists("log.json"))){
            log = new UserLog(3);
            return log;
        }

        try {
            String sCurrentLine;
            String lastLine = "";
            br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/log.json"));

            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
                lastLine = sCurrentLine;
            }
            br.close();

            if (lastLine.equals("")){
                return new UserLog(3);
            }

            log = gson.fromJson(lastLine, UserLog.class);
            return log;
        } catch (IOException e) {
            e.printStackTrace();
            return log;
        }
    }

    public boolean saveLog(UserLog log){
        String jsonLog = "{" +

                "\""+"day"+"\"" + ":" + "\""+log.getDay()+"\"" + "," +

                "\""+"exerciseType" + "\"" + ":" + "\"" + log.getExerciseType() + "\"" + "," +

                "\""+"complete" + "\"" + ":" + "\"" +log.getComplete() + "\"" +

                "}";

        if (!(fileExists("log.json"))){
             writeStringAsFile("", "log.json");
        } else if (!readFileAsString("log.json").equals("")) {
            jsonLog = ",\n"+jsonLog;
        }

        /*if (lastLog()!=null && (log.getDay().equals(lastLog().getDay()))){
            Log.d("Date","Same Date last log!!!");
            return false;
        }*/

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/log.json", true));
            bw.write(jsonLog);
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
        File file = new File(Environment.getExternalStorageDirectory()+"/"+fileName);
        if(file.exists()){
            return true;
        } else {
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
