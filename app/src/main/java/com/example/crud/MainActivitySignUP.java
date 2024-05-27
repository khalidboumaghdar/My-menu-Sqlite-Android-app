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

public class MainActivitySignUP extends AppCompatActivity {
    EditText editName1, editEmail, editPasswordd;
    Button To_validate;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up); // Corrected position of setContentView
        EdgeToEdge.enable(this);

        editName1 = findViewById(R.id.editName1);
        editEmail = findViewById(R.id.editEmail);
        editPasswordd = findViewById(R.id.editPasswordd);
        To_validate = findViewById(R.id.To_validate);
        db = new DatabaseHandler(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        To_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email ,password;
                name = String.valueOf(editName1.getText());
                email = String.valueOf(editEmail.getText());
                password = String.valueOf(editPasswordd.getText());
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(MainActivitySignUP.this, "Enter your name",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(MainActivitySignUP.this, "Enter your Email",Toast.LENGTH_SHORT).show();
                    return;

                }else if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivitySignUP.this, "Enter your Password",Toast.LENGTH_SHORT).show();
                    return;

                }else{
                    Log.d("Insert","Inserting ....");
                    Client client = new Client();
                    db.addClient(client);
                    Log.d("Insert","Inserting ...."+client.toString());
                    startActivity(new Intent(MainActivitySignUP.this, MainActivity.class));

                }





            }
        });
    }
}
