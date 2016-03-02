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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        textElement.setText("I love you");

        URL url = null;
        try {
            url = new URL("http://www.magic.theredbard.com/public/json/?action=carddata&cardname=Jace, the Mind Sculptor&setname=Worldwake");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            InputStream in = con.getInputStream();
            //pick back up here

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //   url = new URL("http://www.magic.theredbard.com/public/json/?action=carddata&cardname=Jace, the Mind Sculptor&setname=Worldwake");

    }
}
