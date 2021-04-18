package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {
    private final List<ModelOceny> mListaOcen;
    private final LayoutInflater mPompka;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    //tworzy główny element Layout i tworzy pojemnik (holder) dla danego wiersza

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        //utworzenie layouta wiersza na podstawie XMLa
        @SuppressLint("InflateParams") View wiersz = mPompka.inflate(R.layout.wiersz_listy, null);
        //zwrócenie nowego obiektu holdera
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza) {
        //powiązanie grupy przycisków radiowych z wierszem listy
        ocenyViewHolder.grupaOceny.setTag(mListaOcen.get(numerWiersza));
        //ustawienie nazwy przedmiotu
        ocenyViewHolder.etykieta.setText(mListaOcen.get(numerWiersza).getNazwa());
        //ocenyViewHolder.etykieta.setTaxt(element.getNazwa());
        //zaznaczenie odpowiedniego przycisku radiowego

        switch (mListaOcen.get(numerWiersza).getOcena()) {
            case 2:
                ocenyViewHolder.grupaOceny.check(R.id.ocena2Button);
                break;

            case 3:
                ocenyViewHolder.grupaOceny.check(R.id.ocena3Button);
                break;

            case 4:
                ocenyViewHolder.grupaOceny.check(R.id.ocena4Button);
                break;

            case 5:
                ocenyViewHolder.grupaOceny.check(R.id.ocena5Button);
                break;
        }
    }

    //zwraca liczbę elementów
    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    //pojemnik przechowujący referencję do potrzebnych elementów wiersza nadaje się też jako
    //obiket implementujący listenery - każdy wiersz ma własny holder

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements
            RadioGroup.OnCheckedChangeListener {
        RadioGroup grupaOceny;
        TextView etykieta;
        public OcenyViewHolder(@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
            //odczytanie referencji do elementów wiersza
            grupaOceny = glownyElementWiersza.findViewById(R.id.grupaOceny);
            etykieta = glownyElementWiersza.findViewById(R.id.etykieta);
            //ustawienie obsługi zdarzeń w komponentach znajdujących się w wierszu

            grupaOceny.setOnCheckedChangeListener(
                    (group, checkedId) -> {
                        //1 odczytanie z etykiety który obiekt modelu przechowuje dane o zmienionej ocenie
                        ModelOceny element = (ModelOceny) group.getTag();
                        //2 zapisanie zmienionej oceny
                        RadioButton checked = glownyElementWiersza.findViewById(group.getCheckedRadioButtonId());
                        element.setOcena(Integer.parseInt(checked.getText().toString()));
                        mListaOcen.set(element.getId(),element);
                    }
            );
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
        }
        //implementacje interfejsów obsługujących zdarzenia
    }
}
