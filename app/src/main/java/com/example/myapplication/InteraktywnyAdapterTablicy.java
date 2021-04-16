package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    //tworzy główny element Layout i tworzy pojemnik (holder) dla danego wiersza

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        //utworzenie layouta wiersza na podstawie XMLa
        View wiersz = mPompka.inflate(R.layout.wiersz_listy, null);
        //zwrócenie nowego obiektu holdera
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolderHolder, int numerWiersza) {
        //powiązanie grupy przycisków radiowych z wierszem listy
        //...
        //ustawienie nazwy przedmiotu
        //...
        //zaznaczenie odpowiedniego przycisku radiowego
        //...
    }

    //zwraca liczbę elementów
    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    //pojemnik przechowujący referencję do potrzebnych elementów wiersza nadaje się też jako
    //obiket implementujący listenery - każdt wiersz ma własny holder

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements
            RadioGroup.OnCheckedChangeListener {
        public OcenyViewHolder(@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
            //odczytanie referencji do elementów wiersza
            //...
            //ustawienie obsługi zdarzeń w komponentach znajdujących się w wierszu
            //...
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
        //implementacje interfejsów obsługujących zdarzenia
    }

    public class ModelOceny {
        private String nazwa;
        private int ocena;

        //konstruktor
        //gettery/settery
    }

}
