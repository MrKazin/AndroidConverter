package com.example.androidconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double meter = 1,kilometer = 0.001,centimeter = 100,foot = 3.281,mile = 0.00062150,inch = 39.37;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Resources resources = getResources();
        Spinner spinner = (Spinner) findViewById(R.id.languagesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.languages));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int var = (int) id;
                changeLanguage(var, resources);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final TextView input = findViewById(R.id.input);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateValue(input.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void changeLanguage(int id,Resources resources){
        String[] languageTitleArray = resources.getStringArray(R.array.languageTitleArray);
        String[] meters = resources.getStringArray(R.array.meters);
        String[] kilometers = resources.getStringArray(R.array.kilometers);
        String[] centimeters = resources.getStringArray(R.array.centimeters);
        String[] miles = resources.getStringArray(R.array.miles);
        String[] foots = resources.getStringArray(R.array.foots);
        String[] inches = resources.getStringArray(R.array.inches);
        setNewText(languageTitleArray[id],(TextView)findViewById(R.id.languageTitle));
        setNewText(meters[id],(RadioButton)findViewById(R.id.radioMeters),(TextView)findViewById(R.id.showMeters));
        setNewText(kilometers[id],(RadioButton)findViewById(R.id.radioKilometers),(TextView)findViewById(R.id.showKilometers));
        setNewText(centimeters[id],(RadioButton)findViewById(R.id.radioCentimeters),(TextView)findViewById(R.id.showCentimeters));
        setNewText(miles[id],(RadioButton)findViewById(R.id.radioMiles),(TextView)findViewById(R.id.showMiles));
        setNewText(foots[id],(RadioButton)findViewById(R.id.radioFoots),(TextView)findViewById(R.id.showFoots));
        setNewText(inches[id],(RadioButton)findViewById(R.id.radioInches),(TextView)findViewById(R.id.showInches));
    }

    private void setNewText(String s, TextView textView){
        textView.setText(s);
    }

    private void setNewText(String s, RadioButton button, TextView textView){
        button.setText(s);
        textView.setText(s);
    }

    private void calculateValue(String value){
        Double inputValue;
        if(value.length() > 0){
            if(value.substring(value.length()-1).equals(".")){
                inputValue = Double.valueOf(value.substring(0,value.length()-1));
            }
            else{
                inputValue = Double.valueOf(value);
            }
        }
        else{
            inputValue = 0.0;
        }
        setOutputValue(inputValue,meter,(TextView) findViewById(R.id.outputMeters));
        setOutputValue(inputValue,kilometer,(TextView) findViewById(R.id.outputKilometers));
        setOutputValue(inputValue,centimeter,(TextView) findViewById(R.id.outputCentimeters));
        setOutputValue(inputValue,foot,(TextView) findViewById(R.id.outputFoots));
        setOutputValue(inputValue,mile,(TextView) findViewById(R.id.outputMiles));
        setOutputValue(inputValue,inch,(TextView) findViewById(R.id.outputInches));
    }

    private void setOutputValue(double input, double coefficient, TextView textView){
        textView.setText(new DecimalFormat("#0.000").format(input*coefficient));
    }

    public void onRadioButtonClicked(View view){
        TextView input = findViewById(R.id.input);
        switch(view.getId()){
            case R.id.radioMeters:
                meter = 1;
                kilometer = 0.001;
                centimeter = 100;
                foot = 3.281;
                mile = 0.00062150;
                inch = 39.37;
                calculateValue(input.getText().toString());
                break;
            case R.id.radioKilometers:
                meter = 1000;
                kilometer = 1;
                centimeter = 100000;
                foot = 3281;
                mile = 1.609;
                inch = 39370;
                calculateValue(input.getText().toString());
                break;
            case R.id.radioCentimeters:
                meter = 0.01;
                kilometer = 0.00001;
                centimeter = 1;
                foot = 0.032808;
                mile = 0.00000621372;
                inch = 0.39370078;
                calculateValue(input.getText().toString());
                break;
            case R.id.radioFoots:
                meter = 0.30478512;
                kilometer = 0.00030478512;
                centimeter = 30.48;
                foot = 1.0;
                mile = 0.0001893939;
                inch = 12;
                calculateValue(input.getText().toString());
                break;
            case R.id.radioMiles:
                meter = 1609;
                kilometer = 1.609;
                centimeter = 160934;
                foot = 5280;
                mile = 1;
                inch = 63360;
                calculateValue(input.getText().toString());
                break;
            case R.id.radioInches:
                meter = 0.0254;
                kilometer = 0.0000254;
                centimeter = 2.54;
                foot = 0.833333;
                mile = 0.00001578282;
                inch = 1;
                calculateValue(input.getText().toString());
                break;
        }
    }
}