package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OcenyActivity extends AppCompatActivity {

    ArrayList<ModelOceny> oceny = new ArrayList<>();
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
        ModelOceny.setCount(0);
        for (int i = 0; i < liczbaOcen; i++) {
            ModelOceny ocena = new ModelOceny(nazwyPrzedmiotow[i]);
            ocena.setOcena(2);
            oceny.add(ocena);
        }
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, oceny);
        widokR = findViewById(R.id.lista_ocen_rv);
        widokR.setAdapter(adapter);
        widokR.setLayoutManager(new LinearLayoutManager(this));
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Bundle pakunek = new Bundle();
            pakunek.putFloat("srednia", srednia()); //
            Intent powrot = new Intent();
            powrot.putExtras(pakunek);
            setResult(RESULT_OK, powrot);
            finish();
        });

        if (savedInstanceState != null ) {
            int[] ocenyZapisaneTab = savedInstanceState.getIntArray("ocenyTab");
            for (int i = 0; i < ocenyZapisaneTab.length; i++)
                oceny.get(i).setOcena(ocenyZapisaneTab[i]);
        }
    }

    //oblicz średnią z ocen zawartych w ArrayList z obiektami modelu
    protected float srednia() {
        float avg, suma = 0;
        for (int i = 0; i < oceny.size(); i++)
            suma += oceny.get(i).getOcena();
        avg = suma / (float) oceny.size();
        return avg;
    }

    //przy obróceniu urządzenia
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int[] ocenyTab = new int[oceny.size()];
        for (int i = 0; i < ocenyTab.length; i++)
            ocenyTab[i] = oceny.get(i).getOcena();
        outState.putIntArray("ocenyTab", ocenyTab);
        super.onSaveInstanceState(outState);
    }
}


