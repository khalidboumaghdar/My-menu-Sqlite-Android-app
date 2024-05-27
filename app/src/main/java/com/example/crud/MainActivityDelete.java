package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityDelete extends AppCompatActivity {
    Button btn_delete1;
    DatabaseHandler db;
    TextView edittextdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        db = new DatabaseHandler(this);
        edittextdelete = findViewById(R.id.edittextdelete);
        btn_delete1 =findViewById(R.id.btn_delete1);

        btn_delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientName = edittextdelete.getText().toString().trim();
                if (!clientName.isEmpty()) {
                    db.suppClient(clientName);
                    Toast.makeText(MainActivityDelete.this, "Client deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivityDelete.this,MainActivityAffichage.class));
                } else {
                    Toast.makeText(MainActivityDelete.this, "Please enter a client name", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}