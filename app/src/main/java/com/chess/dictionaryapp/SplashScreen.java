package com.chess.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Ana sayfaya gecmeden once belirli bir gecikme uyguluyoruzç
        // Bu gecikmeyi de Handler nesnesi (isleyici) -> kontrol eder.
        Handler handler = new Handler();

        // postDelayed(runnable nesnesi, gecikme suresi) -> gecikme gondermek
        // metodu ile gecikmemizi ayarlayabiliriz
        handler.postDelayed(new Runnable() {
            @Override
            // run() metodu icerisinde "gecikme sonrasinda" yapilacak islemler gerceklestirilir.
            // "belirtilen gecikmes suresinden sonra su islemi calistir!" anlamina gelir.
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },500);



        /* Performans acisindan daha az iyidir
        CountDownTimer timer = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        }.start();
        */

    }
}