package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityCalc extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    EditText editText;
    TextView textView;
    String message;
    boolean op;
    double sum;
    double sum1;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    Button btn_plus, btn_minus, btn_mul, btn_div, btn_eq, btn_clear, btn_dot, btn_backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_eq = findViewById(R.id.btn_eq);
        btn_clear = findViewById(R.id.btn_clear);
        btn_dot = findViewById(R.id.btn_dot);
        btn_backspace = findViewById(R.id.btn_backspace);

        textView.setText("Bonjour Tout le monde !!");

        op = false;
        message = "";
        sum = 0;
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String buttonText = b.getText().toString();
                if (editText.getText().toString().equals("0") || op) {
                    editText.setText(buttonText);
                    op = false;
                } else {
                    editText.append(buttonText);
                }
            }
        };
        btn_0.setOnClickListener(numberListener);
        btn_1.setOnClickListener(numberListener);
        btn_2.setOnClickListener(numberListener);
        btn_3.setOnClickListener(numberListener);
        btn_4.setOnClickListener(numberListener);
        btn_5.setOnClickListener(numberListener);
        btn_6.setOnClickListener(numberListener);
        btn_7.setOnClickListener(numberListener);
        btn_8.setOnClickListener(numberListener);
        btn_9.setOnClickListener(numberListener);
        btn_dot.setOnClickListener(numberListener);
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String operator = b.getText().toString();
                message = editText.getText().toString() + " " + operator + " ";
                sum = Double.parseDouble(editText.getText().toString());
                op = true;
                textView.setText(message);
            }
        };
        btn_plus.setOnClickListener(operatorListener);
        btn_minus.setOnClickListener(operatorListener);
        btn_mul.setOnClickListener(operatorListener);
        btn_div.setOnClickListener(operatorListener);
        btn_eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum1 = Double.parseDouble(editText.getText().toString());
                double result = 0;
                if (message.contains("+")) {
                    result = sum + sum1;
                } else if (message.contains("-")) {
                    result = sum - sum1;
                } else if (message.contains("*")) {
                    result = sum * sum1;
                } else if (message.contains("/")) {
                    if (sum1 != 0) {
                        result = sum / sum1;
                    } else {
                        editText.setText("Error");
                        return;
                    }
                }
                message = message + editText.getText().toString() + " = " + result;
                textView.setText(message);
                editText.setText(String.valueOf(result));
                op = true;
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("0");
                textView.setText("");
                message = "";
                sum = 0;
                sum1 = 0;
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
            startActivity(new Intent(MainActivityCalc.this,MainActivityHome.class));
        }else if(id == R.id.Calculatrice){
            startActivity(new Intent(MainActivityCalc.this,MainActivityCalc.class));

        }
        return super.onOptionsItemSelected(item);
    }



}