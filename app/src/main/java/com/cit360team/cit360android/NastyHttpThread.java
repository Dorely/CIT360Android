package com.cit360team.cit360android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jonth on 3/4/2016.
 */
public class NastyHttpThread implements Runnable {

    String urlString;
    String returnString;

    NastyHttpThread(String urlString){
        this.urlString = urlString;
    }

    public String getReturnString(){
        return this.returnString;
    }

    @Override
    public void run() {


        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        String result = null;
        ArrayList fromJson = new ArrayList();

        try {
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("Dog Crap");

            con.setReadTimeout(15 * 1000);

            con.connect();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            result = stringBuilder.toString();
            returnString = result;
            System.out.println(returnString);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
