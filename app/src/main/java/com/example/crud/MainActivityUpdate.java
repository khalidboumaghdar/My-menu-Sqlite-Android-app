package com.example.crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityUpdate extends AppCompatActivity {
     DatabaseHandler db;
     EditText Name;
     EditText Email;
     EditText Password;
     Button btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHandler(this);
        Name = findViewById(R.id.client_name_input);
        Email = findViewById(R.id.client_email_input);
        Email = findViewById(R.id.client_password_input);
        btn_update = findViewById(R.id.update_client_button);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientName = Name.getText().toString().trim();
                String clientEmail = Email.getText().toString().trim();
                String clientPassword = Email.getText().toString().trim();

                if (!clientName.isEmpty() && !clientEmail.isEmpty() && !clientPassword.isEmpty()) {
                    Client client = new Client(clientName, clientEmail, clientPassword);
                    db.updateClient(client);
                    Toast.makeText(MainActivityUpdate.this, "Client updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivityUpdate.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}