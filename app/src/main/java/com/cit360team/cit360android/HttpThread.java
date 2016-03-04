package com.cit360team.cit360android;

import android.view.View;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONUtilities;
import org.quickconnectfamily.json.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jonth on 3/4/2016.
 */
public class HttpThread implements Runnable {

    String urlString;
    String returnString;

    HttpThread(String urlString){
        this.urlString = urlString;
    }

    public String getReturnString(){
        return this.returnString;
    }

    @Override
    public void run() {

//        textElement.setText("I love you");

        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        String result = null;
        ArrayList fromJson = new ArrayList();

        try {
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

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
