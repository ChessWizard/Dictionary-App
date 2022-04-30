package com.chess.dictionaryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

// Veritabani icerisindeki nesnelere erismek icin kullanacagimiz sinif
public class Kelimelerdao {

    public ArrayList<Kelimeler> getDataList(Veritabani vt){

        ArrayList<Kelimeler> dataList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM kelimeler",null);

        while(cursor.moveToNext()){
            Kelimeler kelimeler = new Kelimeler(cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                                                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                                                cursor.getString(cursor.getColumnIndexOrThrow("turkce")));
            dataList.add(kelimeler);
        }
        return dataList;
    }

    public ArrayList<Kelimeler> searchData(Veritabani vt,String word){

        ArrayList<Kelimeler> dataList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        // Parametre olarak gelecek olan kelime araniyor
        Cursor cursor = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE '%"+word+"%'",null);

        while(cursor.moveToNext()){
            Kelimeler kelimeler = new Kelimeler(cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                    cursor.getString(cursor.getColumnIndexOrThrow("turkce")));
            dataList.add(kelimeler);
        }
        return dataList;
    }


}
