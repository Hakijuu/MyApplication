package com.example.myapplication;

public class ModelOceny {
    private String nazwa;
    private int ocena;
    private final int id;
    static int count = 0;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
        this.id = count;
        count++;
    }
    public String getNazwa() {
        return nazwa;
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

    public static void setCount(int count) {
        ModelOceny.count = count;
    }
}


