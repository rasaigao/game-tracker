package com.rohan.gametracker.Buildings;

/**
 * Created by Rohan on 2017-03-11.
 */
public class Achievement {
    private int mCost;
    public enum Type{
        HOUSE,
        GROCERY_STORE,
        SCHOOL,
        CITY_HALL,
        HOSPITAL
    }
    private Type mType;
    private int mConsumption;
    private int mId;
    public Achievement(Type type) {
        this.mType = type;
        setDetails();
    }
    private void setDetails(){
        switch (mType){
            case HOUSE:
                addValues(0, 1, 0);
                break;
            case GROCERY_STORE:
                addValues(20, 10, 1);
                break;
            case SCHOOL:
                addValues(50, 15, 2);
                break;
            case CITY_HALL:
                addValues(100, 30, 3);
                break;
            case HOSPITAL:
                addValues(200, 50, 4);
                break;

        }
    }
    private void addValues(int cost, int consumption, int id){
        mCost = cost;
        mConsumption = consumption;
        mId = id;
    }
    public Type getType(){
        return this.mType;
    }
    public int getCost(){
        return mCost;
    }
    public int getConsumption(){
        return mConsumption;
    }
    public int getId(){
        return mId;
    }
}
