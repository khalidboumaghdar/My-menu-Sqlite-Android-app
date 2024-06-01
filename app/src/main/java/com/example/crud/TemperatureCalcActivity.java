package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TemperatureCalcActivity extends AppCompatActivity {
    EditText editTextTemperature;
    RadioGroup radioGroup;
    RadioButton radioButtonCtoF, radioButtonFtoC;
    Button btnConvert;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayoutTemp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextTemperature = findViewById(R.id.editTextTemperature);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonCtoF = findViewById(R.id.radioButtonCtoF);
        radioButtonFtoC = findViewById(R.id.radioButtonFtoC);
        btnConvert = findViewById(R.id.btn_convert);
        textViewResult = findViewById(R.id.textViewResult);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }
    private void convertTemperature() {
        String tempStr = editTextTemperature.getText().toString();
        if (tempStr.isEmpty()) {
            textViewResult.setText("Please enter a temperature value.");
            return;
        }

        double tempValue = Double.parseDouble(tempStr);
        double convertedValue;
        if (radioButtonCtoF.isChecked()) {
            // Celsius to Fahrenheit
            convertedValue = (tempValue * 9 / 5) + 32;
            textViewResult.setText(tempValue + " °C = " + convertedValue + " °F");
        } else if (radioButtonFtoC.isChecked()) {
            // Fahrenheit to Celsius
            convertedValue = (tempValue - 32) * 5 / 9;
            textViewResult.setText(tempValue + " °F = " + convertedValue + " °C");
        } else {
            textViewResult.setText("Please select a conversion type.");
        }
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
            startActivity(new Intent(TemperatureCalcActivity.this,MainActivityHome.class));
        }else if(id == R.id.Calculatrice){
            startActivity(new Intent(TemperatureCalcActivity.this,MainActivityCalc.class));

        } else if (id == R.id.Température) {
            startActivity(new Intent(TemperatureCalcActivity.this,TemperatureCalcActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}