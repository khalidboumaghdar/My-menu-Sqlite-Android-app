package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etemail, etpassword;
    Button bLogin, bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Corrected position of setContentView
        EdgeToEdge.enable(this);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        bLogin = findViewById(R.id.bLogin);
        bSignUp = findViewById(R.id.bSignUp);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivityAffichage.class));
            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivitySignUP.class));
            }
        });
    }
}
