package com.cit360team.cit360android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONUtilities;
import org.quickconnectfamily.json.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void httpUrlSandbox(View view){

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "http://www.magic.theredbard.com/public/json/?action=carddata&cardname=Jace,%20the%20Mind%20Sculptor&setname=Worldwake";
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void notJsonUrlNasty(View view){

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "http://www.byui.edu";
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void badUrlNasty(View view){
        //FileNotFoundException

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "http://www.magic.theredbard.com/thisdoesnotexist/nothere";
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void nullUrlNasty(View view){

        //MalformedURLException

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = null;
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void badMethodNasty(View view){

        //ProtocolException

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "http://www.magic.theredbard.com/public/json/?action=carddata&cardname=Jace,%20the%20Mind%20Sculptor&setname=Worldwake";
        String result = null;

        //NastyHttpThread is trying to use "Dog Crap" as a method
        NastyHttpThread httpThread = new NastyHttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void malformedUrlNasty(View view){

        //MalformedURLException

        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "ftp.theredbard.com";
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        Thread thread = new Thread(httpThread);
        thread.start();
        try {
            thread.join();
            result = httpThread.getReturnString();
            textElement.setText(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void noThreadNasty(View view){

        //This throws a NetworkOnMainThreadException
        //because trying to do network connection on main thread
        TextView textElement = (TextView) findViewById(R.id.jsonOutputSpot);
        String urlString = "http://www.magic.theredbard.com/public/json/?action=carddata&cardname=Jace,%20the%20Mind%20Sculptor&setname=Worldwake";
        String result = null;

        HttpThread httpThread = new HttpThread(urlString);

        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;


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
            System.out.println(result);
            textElement.setText(result);


    } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
