package com.example.myapplication;

public class ModelOceny {
    private String nazwa;
    private int ocena;
    private int id;
    static int count = 0;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
        this.id = this.count;
        this.count++;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public int getId() {
        return id;
    }
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}


