package com.example.leerhtml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String url = "https://eventos.justiciasanluis.gov.ar/inscripciones/eventia/acreditar.php?evid=13&dni=1&clv=reflejar2019";
    private String atributo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(getApplicationContext(),"Procesando",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document mBlogDocument = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements mElementDataSize = mBlogDocument.select("img");
                // Locate the content attribute
                int mElementSize = mElementDataSize.size();
                int posi=mElementDataSize.indexOf("nook.png");
                atributo=mElementDataSize.attr("src");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView

            if(atributo.equals("nook.png")){
                Toast.makeText(getApplicationContext(),"no aceptado",Toast.LENGTH_LONG).show();
            }else if(atributo.equals("pone el otro png")){
                Toast.makeText(getApplicationContext(),"no aceptado",Toast.LENGTH_LONG).show();
            }



        }
    }
}
