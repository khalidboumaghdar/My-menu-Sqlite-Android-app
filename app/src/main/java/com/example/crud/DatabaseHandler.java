package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private  static  final  int DATABASE_VERSION=1;
    private  static  final  String DATABASE_NAME= "Gestion_Client.db";
    private  static  final  String TABLE_CLIENT="client";
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE table " + TABLE_CLIENT +"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "email TEXT,"+
                "password TEXT"+
                ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);
    }
    Client getClient(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.query(TABLE_CLIENT,new String[]{"name","email","password"},
                "email=?",new String[]{email}
                ,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        Client client = new Client(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return client;

    }
    public  void  addClient(Client client){
        SQLiteDatabase db  = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",client.getName());
        values.put("email",client.getEmail());
        values.put("password",client.getPassword());
        db.insert(TABLE_CLIENT,null,values);
        db.close();

    }


}
