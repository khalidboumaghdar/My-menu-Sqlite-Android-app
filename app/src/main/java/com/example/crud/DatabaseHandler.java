package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Gestion_Client.db";
    private static final String TABLE_CLIENT = "client";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_Client_SESSION = "session";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE table " + TABLE_CLIENT + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "password TEXT" +
                ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);
    }

    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_CLIENT;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(cursor.getInt(0));
                client.setName(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                client.setPassword(cursor.getString(3));
                clientList.add(client);
            } while (cursor.moveToNext()); // Correct loop condition
        }
        cursor.close(); // Don't forget to close the cursor
        return clientList;
    }

    Client getClient(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.query(TABLE_CLIENT, new String[]{"name", "email", "password"},
                "email=?", new String[]{email}
                , null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Client client = new Client(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return client;

    }

    public void addClient(Client client) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("password", client.getPassword());
        db.insert(TABLE_CLIENT, null, values);
        db.close();

    }

    public void suppClient(String clientName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENT, "name = ?", new String[]{clientName});
        db.close();
    }

    public void updateClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("password", client.getPassword());

        // Update the client where the ID matches
        db.update(TABLE_CLIENT, values, "id = ?", new String[]{String.valueOf(client.getId())});
        db.close();
    }

    public boolean login(String email, String mot_de_passe) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean loggedIn = false;

        try {
            String[] projection = {COLUMN_ID};
            String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {email, mot_de_passe};

            Log.d("DatabaseHelper", "Query: " + selection + " Args: " + Arrays.toString(selectionArgs));
            cursor = db.query(TABLE_CLIENT, projection, selection, selectionArgs, null, null, null);

            loggedIn = cursor.moveToFirst();
            Log.d("DatabaseHelper", "Logged in: " + loggedIn);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error querying database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return loggedIn;
    }
    public void logoutUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_CLIENT + " SET " + COLUMN_Client_SESSION + " = 0");
        db.close();
    }
    public Client getClientById(int clientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLIENT, new String[]{"id", "name", "email", "password"},
                "id = ?", new String[]{String.valueOf(clientId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("name");
                int emailIndex = cursor.getColumnIndex("email");
                int passwordIndex = cursor.getColumnIndex("password");

                if (idIndex != -1 && nameIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                    Client client = new Client();
                    client.setId(cursor.getInt(idIndex));
                    client.setName(cursor.getString(nameIndex));
                    client.setEmail(cursor.getString(emailIndex));
                    client.setPassword(cursor.getString(passwordIndex));
                    cursor.close();
                    return client;
                }
            }
            cursor.close();
        }
        return null;
    }

}





