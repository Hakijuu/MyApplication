package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class OcenyActivity extends AppCompatActivity {

    ArrayList<ModelOceny> oceny = new ArrayList<ModelOceny>();
    RecyclerView widokR;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceny);
        button2 = findViewById(R.id.button2);
        String[] nazwyPrzedmiotow = getResources().getStringArray(R.array.tablica);

        Bundle bundleIn = getIntent().getExtras();
        final int liczbaOcen = bundleIn.getInt("liczbaOcen");
        int i;
        for (i = 0; i < liczbaOcen; i++) {
            ModelOceny ocena = new ModelOceny(nazwyPrzedmiotow[i]);
            ocena.setOcena(2);
            oceny.add(ocena);
        }
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, oceny);
        widokR = findViewById(R.id.lista_ocen_rv);
        widokR.setAdapter(adapter);
        widokR.setLayoutManager(new LinearLayoutManager(this));
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle pakunek = new Bundle();
                pakunek.putFloat("srednia", srednia()); //
                Intent powrot = new Intent();
                powrot.putExtras(pakunek);
                setResult(RESULT_OK, powrot);
                finish();
            }
        });
    }

    //oblicz średnią z ocen zawartych w ArrayList z obiektami modelu
    protected float srednia() {
        float avg = 0, suma = 0;
        for (int i = 0; i < oceny.size(); i++) {
            suma += oceny.get(i).getOcena();
        }
        avg = (float) suma / (float) oceny.size();
        return avg;
    }
}


