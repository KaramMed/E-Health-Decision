package com.example.midok.projet_service_medicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class Final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        // recup des données
        Intent i = getIntent();
        /*TextView t1 = findViewById(R.id.t1);
        TextView t2 = findViewById(R.id.t2);
        TextView t3 = findViewById(R.id.t3);
        TextView t4 = findViewById(R.id.t4);
        TextView t5 = findViewById(R.id.t5);
        TextView t6 = findViewById(R.id.t6);
        TextView t7 = findViewById(R.id.t7);*/
        final TextView reponse = findViewById(R.id.reponse);

        // attribution des données
        String genre = i.getStringExtra("genre");
        String age = i.getStringExtra("age");
        String jours_hospi = i.getStringExtra("jours_hospi");
        String type = i.getStringExtra("type_medcin");
        String analyse = i.getStringExtra("analyse");
        String urgence = i.getStringExtra("urgence");
        String hosp = i.getStringExtra("hosp");



        // Attribution des parametres en request Http
        RequestParams parametre = new RequestParams();
        AsyncHttpClient client = new AsyncHttpClient();

        String url ="http://192.168.43.27:8080/E-Health_Decision/AcceuilHttp";

        // attribution en paramteres
        parametre.put("age",i.getStringExtra("age"));
        parametre.put("genre",i.getStringExtra("genre"));
        parametre.put("jours_hospi",i.getStringExtra("jours_hospi"));
        parametre.put("type_medcin",i.getStringExtra("type_medcin"));
        parametre.put("analyse",i.getStringExtra("analyse"));
        parametre.put("urgence",i.getStringExtra("urgence"));
        parametre.put("hosp",i.getStringExtra("hosp"));



        client.post(url,parametre,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String response) {
                super.onSuccess(statusCode, headers, response);
                reponse.setText(response);
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                reponse.setText(responseString);
            }


        });








    }
}
