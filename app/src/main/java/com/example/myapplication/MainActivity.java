package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean nameChecked = false, lastnameChecked = false, numberChecked = false;
    private EditText name;
    private EditText lastname;
    private EditText number;
    TextView komunikat;
    Button button;
    float srednia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tworzenie komponentów, odczytywanie referencji do elementów GUI (findViewById()) ustawienie obsługi zdarzeń, odczytywanie zapisanego stanu aktywności z obiektu savedInstanceState
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.poleImie);
        lastname = findViewById(R.id.poleNazwisko);
        number= findViewById(R.id.poleLiczba);
        button = findViewById(R.id.button);
        komunikat = findViewById(R.id.komunikat);
        button.setOnClickListener(v -> ocenyActivity());

        name.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkName(name.getText().toString())) {
                    name.setError(getString(R.string.imie_EditText));
                    Toast.makeText(MainActivity.this, R.string.imie_EditText,Toast.LENGTH_SHORT).show();
                }
            }
        });

        lastname.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkLastname(lastname.getText().toString())) {
                    lastname.setError(getString(R.string.nazwisko_EditText));
                    Toast.makeText(MainActivity.this, R.string.nazwisko_EditText,Toast.LENGTH_SHORT).show();
                }
            }
        });

        number.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus) {
                if(!checkNumber(number.getText().toString())) {
                    number.setError(getString(R.string.liczba_ocen_EditText));
                    Toast.makeText(MainActivity.this, R.string.liczba_ocen_EditText,Toast.LENGTH_SHORT).show();
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
            String savedKomunikat = savedInstanceState.getString("komunikat");
            float savedSrednia = savedInstanceState.getFloat("srednia");

            name.setText(savedName);
            lastname.setText(savedLastname);
            number.setText(savedNumber);
            komunikat.setVisibility(View.VISIBLE);
            komunikat.setText(savedKomunikat);
            srednia = savedSrednia;

            if (srednia >= 3.0) {
                button.setText(R.string.buttonSuper);
                button.setOnClickListener(
                        v -> {
                            Toast.makeText(MainActivity.this, R.string.gratulacje,Toast.LENGTH_SHORT).show();
                            finish();
                        }
                );
            } else  if (srednia >= 2.0) {
                button.setText(R.string.buttonPorazka);
                button.setOnClickListener(
                        v -> {
                            Toast.makeText(MainActivity.this, R.string.porazka,Toast.LENGTH_SHORT).show();
                            finish();
                        }
                );
            }
        }
    }


    @SuppressLint("SetTextI18n")
    protected void onActivityResult(int kodZadania, int kodWyniku, Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodWyniku == RESULT_OK) {
            Bundle bundle = dane.getExtras();
            this.srednia = bundle.getFloat("srednia");
            komunikat = findViewById(R.id.komunikat);
            komunikat.setText(getString(R.string.komunikatText) + srednia);
            komunikat.setVisibility(View.VISIBLE);
            pokazWynik();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        name = findViewById(R.id.poleImie);
        lastname = findViewById(R.id.poleNazwisko);
        number = findViewById(R.id.poleLiczba);
        komunikat = findViewById(R.id.komunikat);
        button = findViewById(R.id.button);

        outState.putString("name", name.getText().toString());
        outState.putString("lastname", lastname.getText().toString());
        outState.putString("number", number.getText().toString());
        outState.putString("komunikat", komunikat.getText().toString());
        outState.putFloat("srednia", srednia);
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

    //stwórz intent przenoszący do aktywności OcenyActivity
    public void ocenyActivity()
    {
        Intent ocenyActivity = new Intent(MainActivity.this, OcenyActivity.class);
        ocenyActivity.putExtra("liczbaOcen",Integer.valueOf(Integer.parseInt(number.getText().toString())));
        startActivityForResult(ocenyActivity, 0);
    }

    public void pokazWynik(){
        name.setEnabled(false);
        lastname.setEnabled(false);
        number.setEnabled(false);
        button.setVisibility(View.VISIBLE);
        button = findViewById(R.id.button);
        if (srednia >= 3.0) {
            button.setText(R.string.buttonSuper);
            button.setOnClickListener(
                    v -> {
                        Toast.makeText(MainActivity.this, R.string.gratulacje,Toast.LENGTH_SHORT).show();
                        finish();
                    }
            );
        } else {
            button.setText(R.string.buttonPorazka);
            button.setOnClickListener(
                    v -> {
                        Toast.makeText(MainActivity.this, R.string.porazka,Toast.LENGTH_SHORT).show();
                        finish();
                    }
            );
        }
    }
}