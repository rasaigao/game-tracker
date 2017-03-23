package com.rohan.gametracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rohan.gametracker.R;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, android.widget.CompoundButton.OnCheckedChangeListener {
    
    
    
    //Initialize Bottom Section
    private TextView mTotalIncomeOnSale;
    private TextView mBankBalance;

    private Button mCancelSale;
    private Button mConfirmSale;

    private CheckBox[] mConsumerCheckBoxes;

    private int[] saleValuesConsumer = {10, 25, 50, 100};

    private int[] saleValuesProducer = {5, 8, 10, 18, 25};

    private int saleValue = 0;
    private int[] typeArray;
    TextView[] mProducerToSell;
    TextView[] mProducerTotals;

    private int currentCount;
    private int currentTotal;
    private int tempCount;
    private int tempTotal;

    private boolean[] haveBuilding;
    private boolean[] consumerToKeep = {false, false, false, false};
    
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
        mConsumerCheckBoxes = new CheckBox[]{
                (CheckBox) findViewById(R.id.gs_check),
                (CheckBox) findViewById(R.id.sc_check),
                (CheckBox) findViewById(R.id.ch_check),
                (CheckBox) findViewById(R.id.hosp_check)
        };

        //Initialize Producer Total TextViews
        mProducerTotals = new TextView[]{
                (TextView) findViewById(R.id.coal_total),
                (TextView) findViewById(R.id.wind_total),
                (TextView) findViewById(R.id.solar_total),
                (TextView) findViewById(R.id.hydro_total),
                (TextView) findViewById(R.id.nuke_total)
        };

        //Initialize Producer ToSell TextViews
        mProducerToSell = new TextView[]{
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



        mTotalIncomeOnSale = (TextView) findViewById(R.id.total_income_on_sale);
        mConfirmSale = (Button) findViewById(R.id.confirm_sale);
        mCancelSale = (Button) findViewById(R.id.back_button);

        Intent i = getIntent();
        int valueBalance = i.getIntExtra("BALANCE", 0);
        typeArray = i.getIntArrayExtra("TYPE_NUMS");



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


        tempCount = currentCount;
        tempTotal = currentTotal;

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
        mTotalIncomeOnSale.setText(Integer.toString(saleValue));


    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
            switch(compoundButton.getId()){
                case R.id.gs_check:
                    consumerToKeep[0] = isChecked;
                    if(isChecked){
                        saleValue += saleValuesConsumer[0];
                    }
                    else{
                        saleValue -= saleValuesConsumer[0];
                    }
                    break;
                case R.id.sc_check:
                    consumerToKeep[1] = isChecked;
                    if(isChecked){
                        saleValue += saleValuesConsumer[1];
                    }
                    else{
                        saleValue -= saleValuesConsumer[1];
                    }
                    break;
                case R.id.ch_check:
                    consumerToKeep[2] = isChecked;
                    if(isChecked){
                        saleValue += saleValuesConsumer[2];
                    }
                    else{
                        saleValue -= saleValuesConsumer[2];
                    }
                    break;
                case R.id.hosp_check:
                    consumerToKeep[3] = isChecked;
                    if(isChecked){
                        saleValue += saleValuesConsumer[3];
                    }
                    else{
                        saleValue -= saleValuesConsumer[3];
                    }
                    break;
                default:
                    break;

            }
        mTotalIncomeOnSale.setText(Integer.toString(saleValue));

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

        currentCount = Integer.parseInt(mProducerToSell[index].getText().toString());
        currentTotal = typeArray[index];
        tempTotal = Integer.parseInt(mProducerTotals[index].getText().toString());

        if(currentCount < currentTotal && currentTotal > 0){
            tempCount = currentCount + 1;
            tempTotal = tempTotal - 1;
            mProducerToSell[index].setText(Integer.toString(tempCount));
            mProducerTotals[index].setText(Integer.toString(tempTotal));
            saleValue += (saleValuesProducer[index]);
        }

    }
    private void decreaseButtonPressed(int index){

        currentCount = Integer.parseInt(mProducerToSell[index].getText().toString());
        tempTotal = Integer.parseInt(mProducerTotals[index].getText().toString());
        currentTotal = typeArray[index];


        if(currentCount > 0 && tempTotal < currentTotal){
            tempCount = currentCount - 1;
            tempTotal = tempTotal + 1;
            mProducerToSell[index].setText(Integer.toString(tempCount));
            mProducerTotals[index].setText(Integer.toString(tempTotal));
            saleValue -= (saleValuesProducer[index]);
        }

    }

    private void confirmSale(){
        int i = 0;
        int j = 0;
        for(CheckBox cb : mConsumerCheckBoxes){


                consumerToKeep[i] = !cb.isChecked();

            i++;
        }

        for(TextView tv : mProducerTotals){

            typeArray[j] = Integer.parseInt(tv.getText().toString());
            j++;
        }


        Intent resultIntent;
        resultIntent = new Intent();
        resultIntent.putExtra("INCOME", saleValue);
        resultIntent.putExtra("NEW_CONSUMERS", consumerToKeep);
        resultIntent.putExtra("NEW_PRODUCERS", typeArray);

        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

}
