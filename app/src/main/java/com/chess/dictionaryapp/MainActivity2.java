package com.chess.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.chess.dictionaryapp.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // MainActivity'den gelen kelime bilgileri burada aliniyor
        String word_english = getIntent().getStringExtra("word_english");
        String word_turkish = getIntent().getStringExtra("word_turkish");

        // Alinan verileri tasarimimiz uzerinde goruntuluyoruz
        binding.textEnglish.setText(word_english);
        binding.textTurkish.setText(word_turkish);




    }
}