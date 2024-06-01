package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etemail, etpassword;
    Button bLogin, bSignUp;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Corrected position of setContentView
        EdgeToEdge.enable(this);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        bLogin = findViewById(R.id.bLogin);
        bSignUp = findViewById(R.id.bSignUp);
        db = new DatabaseHandler(this);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = etemail.getText().toString();
                    String password = etpassword.getText().toString();

                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean loggedIn = db.login(email, password);
                        if (loggedIn) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,MainActivityHome.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e("LoginError", "Error during login", e);
                    Toast.makeText(MainActivity.this, "An error occurred during login", Toast.LENGTH_SHORT).show();
                }
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
