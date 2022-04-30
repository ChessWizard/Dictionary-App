package com.chess.dictionaryapp;


import java.io.Serializable;

// Kelimeler tablosu kullanilmak uzere modelleniyor

// Serializable interface'i nesnemize "tasinabilme ozelligi" katar.
// Buradaki blgileri detayli olarak baska bir aktivitede goruntulemek istiyoruz.
// Bundan dolayi Serializable ile tum verileri Serilestirip tek format haline getirerek
// kolayca tasinmasini sagliyoruz

public class Kelimeler implements Serializable {

    // Properties
    private int kelime_id;
    private String ingilizce;
    private String turkce;

    // Constrcutors

    public Kelimeler(int kelime_id, String ingilizce, String turkce) {
        this.kelime_id = kelime_id;
        this.ingilizce = ingilizce;
        this.turkce = turkce;
    }

    public Kelimeler() {
    }

    // Getters & Setters

    public int getKelime_id() {
        return kelime_id;
    }

    public void setKelime_id(int kelime_id) {
        this.kelime_id = kelime_id;
    }

    public String getIngilizce() {
        return ingilizce;
    }

    public void setIngilizce(String ingilizce) {
        this.ingilizce = ingilizce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }
}
