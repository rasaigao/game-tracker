package com.rohan.gametracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rohan.gametracker.Buildings.Achievement;
import com.rohan.gametracker.Buildings.PowerSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int mTurnNumber = 0;
    private int mProduction = 0;
    private int mConsumption = 0;
    private int mBankBalance = 10;


    private int switcher = 0;

    private boolean noNewConsumer;
    private boolean noNewProducer;

    private boolean[] haveBuilding = {false, false, false, false};


    //Number labels text
    private TextView mTurnNumberText;
    private TextView mProductionText;
    private TextView mConsumptionText;
    private TextView mProfitText;
    private TextView mBankBalanceText;
    private Achievement.Type mAchievement;
    private PowerSource.Type mPowerSource;
    //Building Lists
    private ArrayList<PowerSource> mPowerSourceList = new ArrayList<>();
    private ArrayList<Achievement> mConsumerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Building buttons
        Button addConsumer;
        Button addProducer;

        //Add a Payment aka Lose Money buttons
        Button addOnePayment;
        Button addRecurringPayment;

        //Add a deposit aka Gain Money buttons
        Button addOneDeposit;
        Button addRecurringDeposit;


        //Misc buttons
        Button sellBuilding;
        Button checkStatus;
        Button nextTurn;

        //Adding values to the buttons/TextViews
        mConsumptionText = (TextView) findViewById(R.id.total_consumption);
        mProductionText = (TextView) findViewById(R.id.total_production);
        mTurnNumberText = (TextView) findViewById(R.id.turn_number);
        mProfitText = (TextView) findViewById(R.id.profit);
        mBankBalanceText = (TextView) findViewById(R.id.bank_balance);
        mBankBalanceText.setText(Integer.toString(mBankBalance));

        noNewConsumer = true;
        noNewProducer = true;

        addConsumer = (Button) findViewById(R.id.add_consumer);
        if (addConsumer != null) {
            addConsumer.setOnClickListener(this);
        }
        addProducer = (Button) findViewById(R.id.add_producer);
        if (addProducer != null) {
            addProducer.setOnClickListener(this);
        }
        addOnePayment = (Button) findViewById(R.id.add_one_payment);
        if (addOnePayment != null) {
            addOnePayment.setOnClickListener(this);
        }
        addRecurringPayment = (Button) findViewById(R.id.add_rec_payment);
        if (addRecurringPayment != null) {
            addRecurringPayment.setOnClickListener(this);
        }
        addOneDeposit = (Button) findViewById(R.id.add_one_deposit);
        if (addOneDeposit != null) {
            addOneDeposit.setOnClickListener(this);
        }
        addRecurringDeposit = (Button) findViewById(R.id.add_rec_deposit);
        if (addRecurringDeposit != null) {
            addRecurringDeposit.setOnClickListener(this);
        }
        sellBuilding = (Button) findViewById(R.id.sell_producer);
        if (sellBuilding != null) {
            sellBuilding.setOnClickListener(this);
        }
        checkStatus = (Button) findViewById(R.id.check_status);
        if (checkStatus != null) {
            checkStatus.setOnClickListener(this);
        }
        nextTurn = (Button) findViewById(R.id.next_turn);
        if (nextTurn != null) {
            nextTurn.setOnClickListener(this);
        }


        addConsumer(Achievement.Type.HOUSE);
        updateValues();
    }

    private void addProducer(PowerSource.Type powerSource) {
        noNewProducer = false;
        mPowerSourceList.add(new PowerSource(powerSource));
        mBankBalance -= mPowerSourceList.get(mPowerSourceList.size()-1).getCost();

    }

    private void addConsumer(Achievement.Type consumer) {

        noNewConsumer = false;
        mConsumerList.add(new Achievement(consumer));
        mBankBalance -= mConsumerList.get(mConsumerList.size()-1).getCost();
        mConsumptionText.setText(Integer.toString(mConsumption));
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.add_consumer:
                if(spaceOnBoard()) {
                    i = new Intent(this, ConsumerActivity.class);
                    i.putExtra("BALANCE", mBankBalance);
                    i.putExtra("HAVE_BUILDING", haveBuilding);
                    startActivityForResult(i, 0);
                }
                else{
                    boardFilledMessage();
                }

                break;

            case R.id.add_producer:
                if(spaceOnBoard()) {
                    i = new Intent(this, PowerSourceActivity.class);
                    i.putExtra("BALANCE", mBankBalance);
                    startActivityForResult(i, 1);
                }
                else{
                    boardFilledMessage();
                }
                break;

            case R.id.add_one_deposit:
                break;

            case R.id.add_rec_deposit:
                break;

            case R.id.add_one_payment:
                break;

            case R.id.add_rec_payment:
                break;

            case R.id.sell_producer:
                break;

            case R.id.check_status:

                break;

            case R.id.next_turn:
                nextTurn();
                break;


        }
    }

    private void nextTurn() {

        updateValues();
        mBankBalance += (mProduction - mConsumption);
        mBankBalanceText.setText(Integer.toString(mBankBalance));
        ++mTurnNumber;
        mTurnNumberText.setText(Integer.toString(mTurnNumber));

    }

    public Achievement.Type getAchievementType(int i) {
        switch (i) {
            case 0:
                return Achievement.Type.GROCERY_STORE;

            case 1:
                return Achievement.Type.SCHOOL;

            case 2:
                return Achievement.Type.CITY_HALL;

            case 3:
                return Achievement.Type.HOSPITAL;

            default:
                return null;

        }
    }
    private boolean spaceOnBoard(){
        return(mConsumerList.size() + mPowerSourceList.size() <= 16);
    }
    private void boardFilledMessage(){

        Toast.makeText(this.getApplicationContext(), "Sorry! Your board is filled. Try selling a building before before buying another!",
                Toast.LENGTH_LONG).show();
    }

    public PowerSource.Type getPowerSourceType(int i) {
        switch (i) {
            case 0:
                return PowerSource.Type.COAL;

            case 1:
                return PowerSource.Type.WIND;

            case 2:
                return PowerSource.Type.SOLAR;

            case 3:
                return PowerSource.Type.HYDRO;

            case 4:
                return PowerSource.Type.NUCLEAR;

            default:
                return null;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (0): {
                if (resultCode == Activity.RESULT_OK) {
                    addConsumer(getAchievementType(data.getIntExtra("SWITCHER", 0)));
                    haveBuilding[data.getIntExtra("SWITCHER", 0)] = true;

                    // TODO Update your TextView.
                }

                break;
            }

            case (1):
                if (resultCode == Activity.RESULT_OK) {
                    addProducer(getPowerSourceType(data.getIntExtra("SWITCHER", 0)));
                }
        }
        updateValues();

    }
    private void updateValues(){
        if (!noNewConsumer) {
            for (Achievement a : mConsumerList) {
                mConsumption += a.getConsumption();
            }
        }
        if (!noNewProducer) {
            for (PowerSource ps : mPowerSourceList) {
                mProduction += ps.getProduction();
            }
        }
        noNewProducer = true;
        noNewConsumer = true;
        mBankBalanceText.setText(Integer.toString(mBankBalance));
        mConsumptionText.setText(Integer.toString(mConsumption));
        mProductionText.setText(Integer.toString(mProduction));
        mProfitText.setText(Integer.toString(mProduction-mConsumption));

    }
}