package com.rohan.gametracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener {
    
    
    
    //Initialize Bottom Section
    private TextView mTotalIncomeOnSale;
    private TextView mBankBalance;

    private Button mCancelSale;
    private Button mConfirmSale;

    private int[] typeArray;

    private boolean[] haveBuilding;
    private boolean[] consumerToSell = {false, false, false, false};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        //Initialize Consumer TextViews
        TextView[] mConsumerHave = {
                (TextView) findViewById(R.id.gs_have),
                (TextView) findViewById(R.id.sch_have),
                (TextView) findViewById(R.id.ch_have),
                (TextView) findViewById(R.id.hosp_have)
        };

        //Initialize Consumer CheckBoxes
        CheckBox[] mConsumerCheckBoxes = {
                (CheckBox) findViewById(R.id.gs_check),
                (CheckBox) findViewById(R.id.sc_check),
                (CheckBox) findViewById(R.id.ch_check),
                (CheckBox) findViewById(R.id.hosp_check)
        };

        //Initialize Producer Total TextViews
        TextView[] mProducerTotals = {
                (TextView) findViewById(R.id.coal_total),
                (TextView) findViewById(R.id.wind_total),
                (TextView) findViewById(R.id.solar_total),
                (TextView) findViewById(R.id.hydro_total),
                (TextView) findViewById(R.id.nuke_total)
        };

        //Initialize Producer ToSell TextViews
        TextView[] mProducerToSell = {
                (TextView) findViewById(R.id.coal_to_sell),
                (TextView) findViewById(R.id.wind_to_sell),
                (TextView) findViewById(R.id.solar_to_sell),
                (TextView) findViewById(R.id.hydro_to_sell),
                (TextView) findViewById(R.id.nuke_to_sell)
        };

        //Initialize Producer Increase/Decrease Buttons
        TextView[] mProducerIncrease = {
                (TextView) findViewById(R.id.coal_increase),
                (TextView) findViewById(R.id.wind_increase),
                (TextView) findViewById(R.id.solar_increase),
                (TextView) findViewById(R.id.hydro_increase),
                (TextView) findViewById(R.id.nuke_increase)
        };
        
         TextView[] mProducerDecrease = {
                (TextView) findViewById(R.id.coal_decrease),
                (TextView) findViewById(R.id.wind_decrease),
                (TextView) findViewById(R.id.solar_decrease),
                (TextView) findViewById(R.id.hydro_decrease),
                (TextView) findViewById(R.id.nuke_decrease)
        };

        mBankBalance = (TextView) findViewById(R.id.balance_num);

        mConfirmSale = (Button) findViewById(R.id.confirm_sale);
        mCancelSale = (Button) findViewById(R.id.back_button);

        Intent i = getIntent();
        int valueBalance = i.getIntExtra("BALANCE", 0);
        typeArray = i.getIntArrayExtra("TYPE_NUMS");

        mBankBalance.setText("$" + valueBalance);

        haveBuilding = i.getBooleanArrayExtra("HAVE_BUILDING");

        for(int n = 0; n < haveBuilding.length; n++) {
            mConsumerCheckBoxes[n].setClickable(haveBuilding[n]);
            if (haveBuilding[n]) {
                mConsumerHave[n].setTextColor(getResources().getColor(R.color.green));
                mConsumerHave[n].setText("Yes");
            }
        }
        for(int n = 0; n < typeArray.length; n++){
            mProducerTotals[n].setText(Integer.toString(typeArray[n]));

        }

        setListeners(mProducerIncrease, mProducerDecrease, mConsumerCheckBoxes, mCancelSale, mConfirmSale);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.coal_increase:
                increaseButtonPressed(0);
                break;
            case R.id.wind_increase:
                increaseButtonPressed(1);
                break;
            case R.id.solar_increase:
                increaseButtonPressed(2);
                break;
            case R.id.hydro_increase:
                increaseButtonPressed(3);
                break;
            case R.id.nuke_increase:
                increaseButtonPressed(4);
                break;
            case R.id.coal_decrease:
                decreaseButtonPressed(0);
                break;
            case R.id.wind_decrease:
                decreaseButtonPressed(1);
                break;
            case R.id.solar_decrease:
                decreaseButtonPressed(2);
                break;
            case R.id.hydro_decrease:
                decreaseButtonPressed(3);
                break;
            case R.id.nuke_decrease:
                decreaseButtonPressed(4);
                break;

            case R.id.back_button:
                finish();
                break;

            case R.id.confirm_sale:
                confirmSale();
                break;

            default:
                break;

        }

        updateTotalSaleValue();
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
            switch(compoundButton.getId()){
                case R.id.gs_check:
                    consumerToSell[0] = isChecked;
                    break;
                case R.id.sc_check:
                    consumerToSell[1] = isChecked;
                    break;
                case R.id.ch_check:
                    consumerToSell[2] = isChecked;
                    break;
                case R.id.hosp_check:
                    consumerToSell[3] = isChecked;
                    break;
                default:
                    break;
            }
        updateTotalSaleValue();
    }
    
    private void setListeners(TextView[] a, TextView[] b, CheckBox[] cb, Button b1, Button b2){

        for (TextView tv : a){
            tv.setOnClickListener(this);
        }
        for (TextView tv : b){
            tv.setOnClickListener(this);
        }
        for (CheckBox cb1 : cb){
            cb1.setOnCheckedChangeListener(this);
        }

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


    }

    private void increaseButtonPressed(int index){

    }
    private void decreaseButtonPressed(int index){

    }

    private void confirmSale(){


    }

    private void updateTotalSaleValue(){

    }

}
