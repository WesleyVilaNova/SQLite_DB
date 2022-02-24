package com.example.recyclerview.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final Integer VERSION = 1;
    static final String NOME_DB = "DB_APP";
    public static final String TB_PRODUTO = "TB_PRODUTO";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nome";
    public static final String COLUMN_ESTOQUE = "estoque";
    public static final String COLUMN_VALOR = "valor";

    public DataBaseHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = " CREATE TABLE IF NOT EXISTS " + TB_PRODUTO
                + " (" + DataBaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataBaseHelper.COLUMN_NAME + " TEXT NOT NULL, "
                + DataBaseHelper.COLUMN_ESTOQUE + " INTEGER NOT NULL, "
                + DataBaseHelper.COLUMN_VALOR + " DOUBLE NOT NULL); ";

        try {
            db.execSQL(sql);

        }catch (Exception e){
            Log.e("ERROR" , "Ocorreu um error no DB" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
