package com.example.crud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivityHome extends AppCompatActivity {

Button btn_logout;
DatabaseHandler db;
TextView txt_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHandler(this);
        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.logoutUser();
                Intent intent = new Intent(MainActivityHome.this, MainActivity.class);
                 startActivity(intent);
                 finish();

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
            startActivity(new Intent(MainActivityHome.this,MainActivityHome.class));
        }else if(id == R.id.Calculatrice){
            startActivity(new Intent(MainActivityHome.this,MainActivityCalc.class));

        }else if (id == R.id.Température) {
            startActivity(new Intent(MainActivityHome.this,TemperatureCalcActivity.class));

        } else if (id == R.id.liste_client) {
            startActivity(new Intent(MainActivityHome.this,MainActivityAffichage.class));

        }
        return super.onOptionsItemSelected(item);
    }
}