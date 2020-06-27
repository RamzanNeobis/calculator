package ru.startandroid.develop.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String operation = "";
    double result = 0;
    int res = 0;
    String item;
    Button btnActTwo;
    String actTwo;

    private String[] symbol = {"+", "-", "*", "/"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActTwo = (Button) findViewById(R.id.activityTwo);
        btnActTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
            }
        });
        ArrayAdapter<String> symbolArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, symbol);
        symbolArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerSymbol = (Spinner) findViewById(R.id.spinnerSymbol);
        spinnerSymbol.setAdapter(symbolArrayAdapter);
        spinnerSymbol.setPrompt("Выбор арифметических действий");
        spinnerSymbol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                item = (String) parent.getItemAtPosition(position);

                Toast.makeText(getBaseContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void onButtonClick(View v) {
        EditText el1 = (EditText) findViewById(R.id.num1);
        EditText el2 = (EditText) findViewById(R.id.num2);
        //TextView resText = (TextView) findViewById(R.id.Result);


        CheckBox oS = (CheckBox) findViewById(R.id.ostatok);
        boolean isBalance = oS.isChecked();
        if (el1.getText().length() == 0 || el2.getText().length() == 0) {
            Toast.makeText(this, "Введите числа перед тем как продолжить", Toast.LENGTH_SHORT).show();
        } else {

            double num1 = Double.parseDouble(el1.getText().toString());
            double num2 = Double.parseDouble(el2.getText().toString());

            int num3 = Integer.parseInt(el1.getText().toString());
            int num4 = Integer.parseInt(el2.getText().toString());

            if (isBalance) {
                switch (item) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                actTwo = " " + num1 + " " + item + " " + num2 + " = " + result;
            } else {
                switch (item) {
                    case "+":
                        res = num3 + num4;
                        break;
                    case "-":
                        res = num3 - num4;
                        break;
                    case "*":
                        res = num3 * num4;
                        break;
                    case "/":
                        res = num3 / num4;
                        break;
                }
                actTwo = " " + num3 + " " + item + " " + num4
                        + " = " + res;
            }
            Intent intent = new Intent(this, ActivityAnswer.class);
            Log.d("First", actTwo);

            intent.putExtra("answer", actTwo);
            startActivity(intent);

        }

    }


}
