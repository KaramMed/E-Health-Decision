package com.example.midok.projet_service_medicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
    }

    public void Commencer(View view) {
        Intent i = new Intent(this,Page1.class);
        startActivity(i);
    }
}
