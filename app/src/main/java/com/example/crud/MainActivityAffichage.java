package com.example.crud;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;


public class MainActivityAffichage extends AppCompatActivity {
    DatabaseHandler db;
    String dataListe = "";
    TextView txtaficher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_affichage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtaficher = findViewById(R.id.txtaficher1);
        DatabaseHandler db = new DatabaseHandler(this);
        List<Client> clientList = db.getAllClients();
        // Initialize dataListe

        for (Client client : clientList) {
            dataListe += client.getEmail();
        }

        txtaficher.setText(dataListe);



    }



}