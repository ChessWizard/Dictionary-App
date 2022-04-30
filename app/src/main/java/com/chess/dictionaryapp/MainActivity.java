package com.chess.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.chess.dictionaryapp.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<Kelimeler> dataList;// Veri kumemiz entegre etmek uzere tanimlaniyor
    private DictionaryAdapter adapter;// adapter nesnemiz
    private Veritabani vt = new Veritabani(this);// Veritabani nesnesi tanimlandi

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Kopyalanan veritabani cagrilsin
        copyDatabase();

        // Toolbar tasarimi

        binding.dictionaryToolbar.setTitle("Dictionary App");
        binding.dictionaryToolbar.setTitleTextColor(Color.WHITE);
        binding.dictionaryToolbar.setLogo(R.drawable.ic_dictionary);
        //binding.dictionaryToolbar.setBackgroundColor(getResources().getColor(R.color.dictionary_red));

        // Bu sayfa icin bu toolbar kullanilsin dedik son olarak
        setSupportActionBar(binding.dictionaryToolbar);

        // RecyclerView'umuzu aktivitemize bagliyoruz

        // RecyclerView gorunum
        binding.rv.setHasFixedSize(true);// Her bir ogenin "sabit tasarim boyutunda" listelenmesini sagliyoruz
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

        // RecyclerView veri entegrasyonu

        dataList = new Kelimelerdao().getDataList(vt);

        // NOT: ctrl + mouse sol tus nesne uzerine gelmek -> nesne ile ilgili bilgileri verir
        //Kelimeler k1 = new Kelimeler(1,"Dog","Köpek");
        //Kelimeler k2 = new Kelimeler(1,"Fish","Balık");
        //Kelimeler k3 = new Kelimeler(1,"Table","Masa");
        //dataList.add(k1);
        //dataList.add(k2);
        //dataList.add(k3);




        // Adapter'imiz tanimlaniyor
        adapter = new DictionaryAdapter(MainActivity.this,dataList);// DictionaryAdapter nesnemiz tanimlandi
        binding.rv.setAdapter(adapter);// RecyclerView adapter'imiz olarak entegre edildi


    }

    // Toolbar arama ozelligi ekleme
    // Turkce/İngilizce'sini arayacagimiz kelime icin ekliyoruz

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Oncelikle SearchView'un goruntulenecegi Menu inflate ediliyor
        MenuInflater searchingMenu = getMenuInflater();// MenuInflater yapisi alindi
        searchingMenu.inflate(R.menu.searching_menu,menu);

        // Menu uzerindeki arama sembolune erisiliyor
        MenuItem searchingItem = menu.findItem(R.id.action_searching);

        // SearchView nesnesi yaratilip, searchingItem ile iliskilediliyor
        //xml uzerinde verilen SearchView ozelligi
        // burada yarattigimiz searchView nesnesine entegre edildi (ozellik verildi).
        SearchView searchView = (SearchView) searchingItem.getActionView();

        //implement edilen onQueryText metotlari bu searchView uzerinden kullanilsin denildi son olarak
        searchView.setOnQueryTextListener(this);

        // Islemlerin gerceklesmesi icin true donduruyoruz
        return true;
    }

    // Toolbar uzerinde cikacak SearchView'u kontrol etmeye yarayan metotlar

    // Arama kismina kelimenin tamami yazildiktan sonra aramaya yapilir.
    @Override
    public boolean onQueryTextSubmit(String s) {

        // logcat uzerinde islemimiz goruntulensin.
        //Log.e("Gonderilen Arama Sonucu",s);// Gonderilen Arama Sonucu basligi adi altinda kullanicinin girdigi query(sorgu,ifade) logcat de goruntulenecek.

        // Arama yapmaya yarayan metot burada tanimlandi
        searchData(s);// Kullanicinin girdigi kelimeyi parametre alacak sekilde calisacak!

        return true;
    }

    // Harf girildikce kelime aratilmaya devam eder.
    @Override
    public boolean onQueryTextChange(String s) {

        // Arama yapmaya yarayan metot burada tanimlandi
        searchData(s);// Kullanicinin girdigi kelimeyi parametre alacak sekilde calisacak!

        return true;
    }

    // Veritabanini kopyalamaya yarayan metot

    public void copyDatabase(){
        DatabaseCopyHelper copyHelper = new DatabaseCopyHelper(this);
        try {
            copyHelper.createDataBase();// Veritabani kopyalandi
        } catch (IOException e) {
            e.printStackTrace();
        }

        copyHelper.openDataBase();// Kopyalanan veritabani acildi
    }

    public void searchData(String word){
        dataList = new Kelimelerdao().searchData(vt,word);

        // Arama yaparken goruntulenecek RecyclerView elemanlari degisiklik gosterecegi icin
        // Adapter tekrar tanimlanmalidir!

        adapter = new DictionaryAdapter(MainActivity.this,dataList);// DictionaryAdapter nesnemiz tanimlandi
        binding.rv.setAdapter(adapter);// RecyclerView adapter'imiz olarak entegre edildi
    }

}