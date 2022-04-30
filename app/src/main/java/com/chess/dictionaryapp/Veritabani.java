package com.chess.dictionaryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {


    public Veritabani(@Nullable Context context) {
        super(context, "sozluk.sqlite", null, 1);// sozluk.sqlite -> eklenen .sqlite ile ayni adda olmalidir!
    }

    // NOT: Hazir veritabani(DatabaseCopyHelper) kullanirken
    // IF NOT EXISTS ve IF EXISTS kullanilmalidir!
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS`kelimeler` (\n" +
                "\t`kelime_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`ingilizce`\tTEXT,\n" +
                "\t`turkce`\tTEXT\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kelimeler");
        onCreate(sqLiteDatabase);
    }
}
