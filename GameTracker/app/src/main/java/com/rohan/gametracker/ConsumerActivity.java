package com.rohan.gametracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button grocery;
    private Button school;
    private Button cityHall;
    private Button hospital;

    private int valueBalance;
    private boolean[] haveBuilding = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        TextView bankBalance;
        bankBalance = (TextView) findViewById(R.id.balance_text);

        Intent i = getIntent();
        valueBalance = i.getIntExtra("BALANCE", 0);
        haveBuilding = i.getBooleanArrayExtra("HAVE_BUILDING");
        bankBalance.setText("$" + valueBalance);

        grocery = (Button) findViewById(R.id.button_gs);
        school = (Button) findViewById(R.id.button_school);
        cityHall = (Button) findViewById(R.id.button_ch);
        hospital = (Button) findViewById(R.id.button_hosp);

        Button b = (Button) findViewById(R.id.back_button1);
        if(b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
        }

        disableBuiltConsumers();
    }
    private void disableBuiltConsumers(){

        if(haveBuilding[0]){
            grocery.setClickable(false);
            grocery.setEnabled(false);
        }
        grocery.setOnClickListener(this);
        if(haveBuilding[1]){
            school.setClickable(false);
            school.setEnabled(false);
        }
        school.setOnClickListener(this);
        if(haveBuilding[2]){
            cityHall.setClickable(false);
            cityHall.setEnabled(false);
        }
        cityHall.setOnClickListener(this);
        if(haveBuilding[3]){
            hospital.setClickable(false);
            hospital.setEnabled(false);
        }
        hospital.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent resultIntent;
        switch (v.getId()) {
            case R.id.button_gs:

                if(valueBalance >= 20) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 0);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else {
                    notEnough();
                }
                break;

            case R.id.button_school:
                if(valueBalance >= 50) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 1);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            case R.id.button_ch:

                if(valueBalance >= 100) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 2);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            case R.id.button_hosp:

                if(valueBalance >= 200) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 3);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            default:
        }


    }
    private void notEnough(){
        Toast.makeText(this.getApplicationContext(), "Sorry! You cannot afford that!",
                Toast.LENGTH_LONG).show();
    }
}

