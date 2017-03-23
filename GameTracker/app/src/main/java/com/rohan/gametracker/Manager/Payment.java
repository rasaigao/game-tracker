package com.rohan.gametracker.Manager;

/**
 * Created by Rohan on 2017-03-23.
 */
public class Payment {
    private int mAmount;
    private int mTurns;
    private boolean mDeposit;

    public Payment(int amount, int turns, boolean deposit){

        setDeposit(deposit);
        setAmount(amount);
        setTurns(turns);


    }
    public void setAmount(int amount){
        if(!mDeposit){
            this.mAmount = (0-amount);
        }
        else{
            this.mAmount = amount;
        }

    }

    public void setTurns(int turns){
        this.mTurns = turns;
    }
    public void setDeposit(boolean deposit){
        mDeposit = deposit;
    }

    public int getAmount(){
        return this.mAmount;
    }

    public int getTurns(){
        return this.mTurns;
    }

    public boolean isDeposit() {
        return mDeposit;
    }
}
