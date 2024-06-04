package com.example.crud;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainActivityAffichage extends AppCompatActivity {

    DatabaseHandler db;
    String dataListe = "";
    TextView txtaficher;
    Button btn_add;
    Button btn_update;

    TableRow tableRow;
    TableLayout tableLayout;
    Button btn_delete;

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
        tableLayout = findViewById(R.id.tableLayout);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        DatabaseHandler db = new DatabaseHandler(this);
        List<Client> clientList = db.getAllClients();


        for (Client client : clientList) {
            TableRow tableRow = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(client.getId()));
            idTextView.setPadding(8, 8, 8, 8);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(client.getName());
            nameTextView.setPadding(8, 8, 8, 8);

            TextView emailTextView = new TextView(this);
            emailTextView.setText(client.getEmail());
            emailTextView.setPadding(8, 8, 8, 8);

            TextView passwordTextView = new TextView(this);
            passwordTextView.setText(client.getPassword());
            passwordTextView.setPadding(8, 8, 8, 8);



            tableRow.addView(idTextView);
            tableRow.addView(nameTextView);
            tableRow.addView(emailTextView);
            tableRow.addView(passwordTextView);


            tableLayout.addView(tableRow);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAffichage.this,MainActivityADD.class));
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAffichage.this,MainActivityDelete.class));
            }
        });
       btn_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivityAffichage.this,MainActivityUpdate.class));
           }
       });









    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Home){
            startActivity(new Intent(MainActivityAffichage.this,MainActivityHome.class));
        }else if(id == R.id.Calculatrice){
            startActivity(new Intent(MainActivityAffichage.this,MainActivityCalc.class));

        }else if (id == R.id.Température) {
            startActivity(new Intent(MainActivityAffichage.this,TemperatureCalcActivity.class));

        } else if (id == R.id.liste_client) {
            startActivity(new Intent(MainActivityAffichage.this,MainActivityAffichage.class));

        }
        return super.onOptionsItemSelected(item);
    }



}