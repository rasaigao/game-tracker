package com.rohan.gametracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class PaymentActivity extends AppCompatActivity {

    private NumberPicker mAmount;
    private NumberPicker mTurns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAmount = (NumberPicker) findViewById(R.id.dollar_amount);
        mTurns = (NumberPicker) findViewById(R.id.payment_turn_length);

        mTurns.setMaxValue(50);
        mTurns.setMinValue(0);

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


    }

}
