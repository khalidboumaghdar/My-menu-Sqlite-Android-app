package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityADD extends AppCompatActivity {
    EditText Name,Email,Password;
    Button To_validate;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Name = findViewById(R.id.editName1);
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPasswordd);
        To_validate = findViewById(R.id.To_validate);
        To_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivityADD.this, "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivityADD.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivityADD.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Log.d("Insert", "Inserting ....");
                    Client client = new Client();
                    client.setName(name); // Set name
                    client.setEmail(email); // Set email
                    client.setPassword(password); // Set password

                    db.addClient(client); // Add client to database
                    Log.d("Insert", "Inserted: " + client.toString());
                    startActivity(new Intent(MainActivityADD.this, MainActivityADD.class));
                }

            }
        });





    }
}