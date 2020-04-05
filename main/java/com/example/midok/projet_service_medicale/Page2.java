package com.example.midok.projet_service_medicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
    }

    public void Suivant(View view) {
        // recup des variables envoyés et reenvoie au page suivante
        Intent rec = getIntent();
        Intent env = new Intent(this,Final.class);
        // reenvoie
        env.putExtra("genre",rec.getStringExtra("genre"));
        env.putExtra("jours_hospi",rec.getStringExtra("jours_hospi"));
        env.putExtra("age",rec.getStringExtra("age"));
        env.putExtra("type_medcin",rec.getStringExtra("type_medcin"));

        // recup des variable locaux
        String analyse = ((EditText)findViewById(R.id.analyse)).getText().toString();
        String urgence = ((EditText)findViewById(R.id.urgence)).getText().toString();
        String hosp = ((EditText)findViewById(R.id.hosp)).getText().toString();

        // envoie des variable locaux
        env.putExtra("analyse",analyse);
        env.putExtra("urgence",urgence);
        env.putExtra("hosp",hosp);

        startActivity(env);





    }

}
