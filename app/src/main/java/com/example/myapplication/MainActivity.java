package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    boolean nameChecked = false, lastnameChecked = false, numberChecked = false;

    private EditText name;
    private EditText lastname;
    private EditText number;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tworzenie komponentów, odczytywanie referencji do elementów GUI (findViewById()) ustawienie obsługi zdarzeń, odczytywanie zapisanego stanu aktywności z obiektu savedInstanceState
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.poleImie);
        EditText lastname = findViewById(R.id.poleNazwisko);
        EditText number= findViewById(R.id.poleLiczba);
        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        name.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkName(name.getText().toString())) {
                    name.setError(getString(R.string.imie_EditText));
                    Toast.makeText(MainActivity.this,R.string.imie_EditText,Toast.LENGTH_SHORT).show();
                }
            }
        });

        lastname.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkLastname(lastname.getText().toString())) {
                    lastname.setError(getString(R.string.nazwisko_EditText));
                    Toast.makeText(MainActivity.this,R.string.nazwisko_EditText,Toast.LENGTH_SHORT).show();
                }
            }
        });

        number.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkNumber(number.getText().toString())) {
                    number.setError(getString(R.string.liczba_ocen_EditText));
                    Toast.makeText(MainActivity.this,R.string.liczba_ocen_EditText,Toast.LENGTH_SHORT).show();
                }
            }
        });


        name.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<=0){
                    nameChecked=false;
                    button.setVisibility(View.INVISIBLE);
                }
                else{
                    nameChecked=true;
                    if(lastnameChecked && numberChecked){
                        button.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        lastname.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<=0){
                    lastnameChecked=false;
                    button.setVisibility(View.INVISIBLE);
                }
                else{
                    lastnameChecked=true;
                    if(nameChecked && numberChecked){
                        button.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        number.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int liczba1=0;
                try {
                    liczba1 = Integer.parseInt(s.toString());
                } catch(NumberFormatException e) {
                    System.out.println("Could not parse " + e);
                }
                if(liczba1>15 || liczba1 <5){
                    numberChecked=false;
                    button.setVisibility(View.INVISIBLE);
                }
                else{
                    numberChecked=true;
                    if(nameChecked && lastnameChecked){
                        button.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        if (savedInstanceState != null ) {
            String savedName = savedInstanceState.getString("name");
            String savedLastname = savedInstanceState.getString("lastname");
            String savedNumber = savedInstanceState.getString("number");
            name.setText(savedName);
            lastname.setText(savedLastname);
            number.setText(savedNumber);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        name = findViewById(R.id.poleImie);
        lastname = findViewById(R.id.poleNazwisko);
        number = findViewById(R.id.poleLiczba);

        outState.putString("name", name.getText().toString());
        outState.putString("lastname", lastname.getText().toString());
        outState.putString("number", number.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public boolean checkName(String nameS){
        return nameS.length() != 0;
    }

    public boolean checkLastname(String lastnameS){
        return lastnameS.length() != 0;
    }

    public boolean checkNumber(String liczbaS){
        try {
            int liczba = Integer.parseInt(liczbaS);
            return liczba >= 5 && liczba <= 15;
        } catch (NumberFormatException e) {
            System.out.println("Could not parse " + e);
            return false;
        }

    }


    //Aktywność była niewidoczna
    protected void onRestart(){
        super.onRestart();
        //Czynności wykonywane gdy aktywność wcześniej istniała ale była niewidoczna
    }

    //Aktywność za chwilę stanie się widoczna
    @Override
    protected void onStart() {
        super.onStart();
        //Tworzenie elementów niezbędnych do uaktualniania interfejsu użytkownika
    }

    //Aktywność jest na pierwszym planie
    @Override
    protected void onResume() {
        super.onResume();
    }

    //aktywność traci "focus"/ Zostanie zapauzowana (gdy system ma wznowić inną aktywność).
    @Override
    protected void onPause() {
        super.onPause();
        //Tutaj należy zwolnić zasoby i zapisać istotne dane w trwałym magazynie. Implemetacja powinna być szybka bo system nie wznowi innej aktywnośći dopóki ta metoda się nie zakończy
    }

    //Aktywność nie jest widoczna. Została wstrzymana.
    @Override
    protected void onStop() {
        super.onStop();
        //Tutaj należy zwolnić zasoby i ew. zapisać istotne elementy stanu. Po wykonaniu tej metody proces z tą aktywnością może zostać "zabity" przez system (bezy wykonywania kolejnych metod cyklu życia).
    }

    //Za chwiolę aktywność zostanie zniszczona (tymczasowo lub w celu zwolnienia pamięci)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //po wykonaniu tej metody proces z tą aktywnością może zostać "zabity" przez system (bez wykonania kolejnych metod cyklu życia).
    }
}