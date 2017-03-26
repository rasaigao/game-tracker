package com.rohan.gametracker.Buildings;

/**
 * Created by Rohan Saigaonkar on 2017-03-11.
 */
public class PowerSource {
    private int mCost;
    public enum Type{
        COAL,
        WIND,
        SOLAR,
        HYDRO,
        NUCLEAR
    }
    private Type mType;
    private int mProduction;
    private boolean mIsClean;
    public PowerSource(Type type) {
        this.mType = type;
        setDetails();
    }
    private void setDetails(){
        switch (mType){
            case COAL:
                addValues(5, 3, false);
                break;
            case WIND:
                addValues(10, 7, true);
                break;
            case SOLAR:
                addValues(15, 11, true);
                break;
            case HYDRO:
                addValues(25, 15, true);
                break;
            case NUCLEAR:
                addValues(50, 20, true);
                break;

        }

    }
    private void addValues(int cost, int production, boolean isClean){
        mCost = cost;
        mProduction = production;
        mIsClean = isClean;
    }
    public Type getType(){
        return this.mType;
    }
    public int getCost(){
        return mCost;
    }
    public int getProduction(){
        return mProduction;
    }
    public boolean isClean(){
        return mIsClean;
    }

}
