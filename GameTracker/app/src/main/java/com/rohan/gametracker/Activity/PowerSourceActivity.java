package com.rohan.gametracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rohan.gametracker.R;

public class PowerSourceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button coal;
    private Button wind;
    private Button solar;
    private Button hydro;
    private Button nuclear;

    private int valueBalance;
    private boolean[] haveBuilding = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_source);
        TextView bankBalance;
        bankBalance = (TextView) findViewById(R.id.balance_text);

        Intent i = getIntent();
        valueBalance = i.getIntExtra("BALANCE", 0);

        bankBalance.setText("$" + valueBalance);

        coal = (Button) findViewById(R.id.button_coal);
        wind = (Button) findViewById(R.id.button_wind);
        solar = (Button) findViewById(R.id.button_solar);
        hydro = (Button) findViewById(R.id.button_hydro);
        nuclear = (Button) findViewById(R.id.button_nuke);

        coal.setOnClickListener(this);
        wind.setOnClickListener(this);
        solar.setOnClickListener(this);
        hydro.setOnClickListener(this);
        nuclear.setOnClickListener(this);

        Button b = (Button) findViewById(R.id.back_button2);
        if(b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
        }

    }

    @Override
    public void onClick(View v){
        Intent resultIntent;
        switch (v.getId()) {
            case R.id.button_coal:

                if(valueBalance >= 10) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 0);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else {
                    notEnough();
                }
                break;

            case R.id.button_wind:
                if(valueBalance >= 15) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 1);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            case R.id.button_solar:

                if(valueBalance >= 20) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 2);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            case R.id.button_hydro:

                if(valueBalance >= 35) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 3);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            case R.id.button_nuke:

                if(valueBalance >= 50) {
                    resultIntent = new Intent();
                    resultIntent.putExtra("SWITCHER", 4);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                else{
                    notEnough();
                }
                break;

            default:
                break;
        }


    }
    private void notEnough(){
        Toast.makeText(this.getApplicationContext(), "Sorry! You cannot afford that!",
                Toast.LENGTH_LONG).show();
    }
}
