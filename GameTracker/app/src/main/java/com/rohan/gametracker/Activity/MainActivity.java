package com.rohan.gametracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.rohan.gametracker.Buildings.Achievement;
import com.rohan.gametracker.Buildings.PowerSource;
import com.rohan.gametracker.Manager.Payment;
import com.rohan.gametracker.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int mTurnNumber = 1;
    private int mProduction = 0;
    private int mConsumption = 0;
    private int mBankBalance = 10;
    private int totalPayments;


    private int switcher = 0;

    private boolean noNewConsumer;
    private boolean noNewProducer;

    private ImageView mLoadingImage;

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
    private ArrayList<Payment> mPaymentList = new ArrayList<>();

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
        mTurnNumberText.setText(Integer.toString(mTurnNumber));
        mProfitText = (TextView) findViewById(R.id.profit);
        mBankBalanceText = (TextView) findViewById(R.id.bank_balance);
        mBankBalanceText.setText(Integer.toString(mBankBalance));

        mLoadingImage = (ImageView)findViewById(R.id.image_view);
        mLoadingImage.bringToFront();
        mLoadingImage.setVisibility(View.GONE);
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
    //OVERLOADED FOR SALES
    private void addProducer(PowerSource.Type powerSource, boolean b) {
        b = true;
        noNewProducer = false;
        mPowerSourceList.add(new PowerSource(powerSource));
        mBankBalance -= mPowerSourceList.get(mPowerSourceList.size()-1).getCost();
        mBankBalance += mPowerSourceList.get(mPowerSourceList.size()-1).getCost();

    }


    private void addConsumer(Achievement.Type consumer, boolean b) {
        b = true;
        noNewConsumer = false;
        mConsumerList.add(new Achievement(consumer));
        mBankBalance -= mConsumerList.get(mConsumerList.size()-1).getCost();
        mBankBalance += mConsumerList.get(mConsumerList.size()-1).getCost();
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
                i = new Intent(this, PaymentActivity.class);
                i.putExtra("ONE_TIME", true);
                i.putExtra("DEPOSIT", true);
                startActivityForResult(i, 3);
                break;

            case R.id.add_rec_deposit:
                i = new Intent(this, PaymentActivity.class);
                i.putExtra("ONE_TIME", false);
                i.putExtra("DEPOSIT", true);
                startActivityForResult(i, 4);
                break;


            case R.id.add_one_payment:
                i = new Intent(this, PaymentActivity.class);
                i.putExtra("ONE_TIME", true);
                i.putExtra("DEPOSIT", false);
                startActivityForResult(i, 5);
                break;

            case R.id.add_rec_payment:
                i = new Intent(this, PaymentActivity.class);
                i.putExtra("ONE_TIME", false);
                i.putExtra("DEPOSIT", false);
                startActivityForResult(i, 6);
                break;

            case R.id.sell_producer:

                i = new Intent(this, SellActivity.class);
                i.putExtra("HAVE_BUILDING", haveBuilding);
                i.putExtra("BALANCE", mBankBalance);
                i.putExtra("TYPE_NUMS", getPowerSourceNumbers());
                startActivityForResult(i, 2);

                break;

            case R.id.check_status:

                i = new Intent(this, StatusActivity.class);
                i.putExtra("HAVE_BUILDING", haveBuilding);
                i.putExtra("BALANCE", mBankBalance);
                i.putExtra("TYPE_NUMS", getPowerSourceNumbers());
                startActivity(i);

                break;

            case R.id.next_turn:
                nextTurn();
                break;


        }
    }

    private void nextTurn() {

       // mLoadingImage.setImageDrawable(getResources().getDrawable(R.drawable.hourglass));
        mLoadingImage.setVisibility(View.VISIBLE);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(mLoadingImage);
        Glide.with(this).load(R.drawable.hourglass1).into(imageViewTarget);

        MyCountDown timer = new MyCountDown(2000, 1000);
        timer.start();

        updateValues();
        mBankBalance += (mProduction - mConsumption);
        mBankBalanceText.setText(Integer.toString(mBankBalance));
        mTurnNumber++;
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
        return(mConsumerList.size() + mPowerSourceList.size() < 16);
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
        int amount;
        int turns;

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
                break;
            case(2):

                if (resultCode == Activity.RESULT_OK) {
                    sellItems(data.getBooleanArrayExtra("NEW_CONSUMERS"), data.getIntArrayExtra("NEW_PRODUCERS"), data.getIntExtra("INCOME", 0));
                }
                break;
            case (3):
                //one deposit
                if (resultCode == Activity.RESULT_OK) {
                    turns = 1;
                    amount = data.getIntExtra("AMOUNT", 0);
                    mPaymentList.add(new Payment(amount, turns, true));
                }

                break;
            case (4):
                //rec deposit
                if (resultCode == Activity.RESULT_OK) {
                    turns = data.getIntExtra("TURNS", 0);
                    amount = data.getIntExtra("AMOUNT", 0);
                    mPaymentList.add(new Payment(amount, turns, true));
                }

                break;
            case (5):
                //one payment
                if (resultCode == Activity.RESULT_OK) {
                    turns = 1;
                    amount = data.getIntExtra("AMOUNT", 0);
                    mPaymentList.add(new Payment(amount, turns, false));
                }

                break;
            case(6):
                //rec payment
                if (resultCode == Activity.RESULT_OK) {
                    turns = data.getIntExtra("TURNS", 0);
                    amount = data.getIntExtra("AMOUNT", 0);
                    mPaymentList.add(new Payment(amount, turns, false));
                }

                break;
        }
        updateValues();

    }
    private void updateValues(){
        if (!noNewConsumer) {
            mConsumption = 0;
            for (Achievement a : mConsumerList) {
                mConsumption += a.getConsumption();
            }
        }
        if (!noNewProducer) {
            mProduction = 0;
            for (PowerSource ps : mPowerSourceList) {
                mProduction += ps.getProduction();
            }
        }
        noNewProducer = true;
        noNewConsumer = true;
        makePayments();
        mBankBalanceText.setText(Integer.toString(mBankBalance));
        mConsumptionText.setText(Integer.toString(mConsumption));
        mProductionText.setText(Integer.toString(mProduction));
        mProfitText.setText(Integer.toString(mProduction-mConsumption));

        if(mConsumerList.size() == 5){
            youWin();

        }
        if(mBankBalance < 0 ){
            youLose();
        }

    }

    private int[] getPowerSourceNumbers(){
        int[] typeArray = {0,0,0,0,0};
        for (PowerSource p : mPowerSourceList){
            switch(p.getType()){
                case COAL:
                    typeArray[0]++;
                    break;
                case WIND:
                    typeArray[1]++;
                    break;
                case SOLAR:
                    typeArray[2]++;
                    break;
                case HYDRO:
                    typeArray[3]++;
                    break;
                case NUCLEAR:
                    typeArray[4]++;
                    break;
            }
        }
        return typeArray;
    }
    private void youWin(){

    }
    private void youLose(){

    }

    private class MyCountDown extends CountDownTimer
    {
        long duration, interval;
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
            start();
        }

        @Override
        public void onFinish() {

            mLoadingImage.setVisibility(View.GONE);

        }

        @Override
        public void onTick(long duration) {
            // could set text for a timer here
        }
    }

    private void sellItems(boolean[] newHaveBuilding, int[] newTypeArray, int incomeFromSale){
        mBankBalance += incomeFromSale;
        int i = 0;
        int j = 0;

        mConsumerList.clear();
        mPowerSourceList.clear();
        addConsumer(Achievement.Type.HOUSE);


        for (boolean b : newHaveBuilding){
            if(b && haveBuilding[i]) {

                addConsumer(getAchievementType(i), false);
            }
            haveBuilding[i] = (b && haveBuilding[i]);
            i++;
        }

        for(int num : newTypeArray){
            for(int k = 0; k < num; k++){
                addProducer(getPowerSourceType(j), false);
            }
            j++;
        }

        noNewProducer = false;
        noNewConsumer = false;
        updateValues();
    }

    public void makePayments(){
        if(!mPaymentList.isEmpty()){
            totalPayments = 0;
            for(Payment p : mPaymentList){
                if(p.getTurns() > 0) {
                    totalPayments += p.getAmount();
                    mBankBalance += p.getAmount();
                    p.setTurns(p.getTurns() - 1);
                }
            }
            ((TextView) findViewById(R.id.fees_deposit)).setText(Integer.toString(totalPayments));
        }
    }

}