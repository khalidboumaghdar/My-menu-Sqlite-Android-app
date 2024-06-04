package com.example.crud;

import android.content.Intent;
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
     EditText clientIdInput;
     EditText clientNameInput;
     EditText clientEmailInput;
     EditText clientPasswordInput;
     Button updateClientButton;
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

        clientIdInput = findViewById(R.id.client_id_input);
        clientNameInput = findViewById(R.id.clientNameInput);
        clientEmailInput = findViewById(R.id.clientEmailInput);
        clientPasswordInput = findViewById(R.id.client_password_input);
        updateClientButton = findViewById(R.id.update_client_button);

        db = new DatabaseHandler(this);

        updateClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clientIdInput.getText().toString().isEmpty()) {
                    int clientId = Integer.parseInt(clientIdInput.getText().toString());
                    String name = clientNameInput.getText().toString();
                    String email = clientEmailInput.getText().toString();
                    String password = clientPasswordInput.getText().toString();

                    Client client = new Client();
                    client.setId(clientId);
                    client.setName(name);
                    client.setEmail(email);
                    client.setPassword(password);

                    updateClient(client);
                } else {
                    Toast.makeText(MainActivityUpdate.this, "Please enter a valid client ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Fetch client data automatically if an ID is provided
        clientIdInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !clientIdInput.getText().toString().isEmpty()) {
                    int clientId = Integer.parseInt(clientIdInput.getText().toString());
                    retrieveClient(clientId);
                }
            }
        });
    }

    private void updateClient(Client client) {
        db.updateClient(client);
        Toast.makeText(MainActivityUpdate.this, "Client updated successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivityUpdate.this,MainActivityAffichage.class));
    }

    private void retrieveClient(int clientId) {
        Client client = db.getClientById(clientId);
        if (client != null) {
            clientNameInput.setText(client.getName());
            clientEmailInput.setText(client.getEmail());
            clientPasswordInput.setText(client.getPassword());
        } else {
            Toast.makeText(MainActivityUpdate.this, "Client not found", Toast.LENGTH_SHORT).show();
        }
    }
}