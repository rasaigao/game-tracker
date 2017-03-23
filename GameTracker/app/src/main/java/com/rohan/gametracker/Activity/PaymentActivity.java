package com.rohan.gametracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.rohan.gametracker.R;

public class PaymentActivity extends AppCompatActivity {

    private NumberPicker mAmount;
    private NumberPicker mTurns;

    private boolean oneTimePayment;
    private boolean deposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent i = getIntent();

        oneTimePayment = i.getBooleanExtra("ONE_TIME", false);
        deposit = i.getBooleanExtra("DEPOSIT", false);


        mTurns = (NumberPicker) findViewById(R.id.payment_turn_length);
        mAmount = (NumberPicker) findViewById(R.id.dollar_amount);


        mTurns.setVisibility(View.GONE);
        ((TextView) findViewById(R.id.turns_title)).setVisibility(View.GONE);
        if(!oneTimePayment){
            ((TextView) findViewById(R.id.turns_title)).setVisibility(View.VISIBLE);
            mTurns.setVisibility(View.VISIBLE);
            mTurns.setMaxValue(50);
            mTurns.setMinValue(0);

        }
        if(oneTimePayment){
            if(deposit){
                ((TextView) findViewById(R.id.payment_title)).setText("One Time Deposit");
            }
            else{
                ((TextView) findViewById(R.id.payment_title)).setText("One Time Payment");
            }
        }
        else{
            if(deposit){
                ((TextView) findViewById(R.id.payment_title)).setText("Recurring Deposit");
            }
            else{
                ((TextView) findViewById(R.id.payment_title)).setText("Recurring Payment");
            }
        }

        mAmount.setMaxValue(200);
        mAmount.setMinValue(0);

        Button b = (Button) findViewById(R.id.back_button);
        if(b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
        }
        Button b2 = (Button) findViewById(R.id.make_payment);
        if(b2 != null) {
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent;
                    resultIntent = new Intent();
                    resultIntent.putExtra("AMOUNT",mAmount.getValue());
                    if(!oneTimePayment){
                        resultIntent.putExtra("TURNS", mTurns.getValue());
                    }

                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                    finish();

                }
            });
        }


    }

}
