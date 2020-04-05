package com.example.midok.projet_service_medicale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        Intent i = getIntent();
        // Definition des spinners de type medcin et ages
        Spinner dropdown = findViewById(R.id.typemedcin);
        String[] items = new String[]{"Chirurgien","Dieteticien"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown2 = findViewById(R.id.age);
        String[] items2 = new String[]{"0-10","10-20","20-30","30-40","40-50","50-60","60-70","70-80","80-90","90-100"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);








    }

    public void Suivant(View view) {
        // recupe des variables locaux
        String jours_hospi = ((EditText)findViewById(R.id.jours_hospi)).getText().toString();
        // groupe du genre
        String genre;
        RadioGroup groupgenre = findViewById(R.id.genre);
        if(groupgenre.getCheckedRadioButtonId() == R.id.male) genre="homme";
        else genre="femme";
        // swing d age
        String age;
        Spinner agee = findViewById(R.id.age);
        age = (agee.getSelectedItem()).toString();
        // type medcin
        String typemedcin;
        Spinner medc = findViewById(R.id.typemedcin);
        typemedcin = medc.getSelectedItem().toString();


        // preparation et envoie a la page suivante
        Intent i = new Intent(this,Page2.class);
        i.putExtra("genre",genre);
        i.putExtra("age",age);
        i.putExtra("jours_hospi",jours_hospi);
        i.putExtra("type_medcin",typemedcin);
        startActivity(i);


    }
}
