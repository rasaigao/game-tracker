package com.rohan.gametracker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rohan.gametracker.R;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        TextView[] aArray = {(TextView) findViewById(R.id.gs_have),
                (TextView) findViewById(R.id.sch_have),
                (TextView) findViewById(R.id.ch_have),
                (TextView) findViewById(R.id.hosp_have)};
        TextView bankBalance;
        bankBalance = (TextView) findViewById(R.id.balance_num);

        Intent i = getIntent();
        int valueBalance = i.getIntExtra("BALANCE", 0);
        int[] typeArray = i.getIntArrayExtra("TYPE_NUMS");
        bankBalance.setText("$" + valueBalance);

        boolean[] haveBuilding = i.getBooleanArrayExtra("HAVE_BUILDING");
        int subTotalOne = 0;
        for(int n = 0; n < haveBuilding.length; n++){
            if(haveBuilding[n]){
                aArray[n].setTextColor(getResources().getColor(R.color.green));
                aArray[n].setText("Yes");
                subTotalOne++;
            }

        }
        int total = 0;

        TextView coalNum = (TextView) findViewById(R.id.coal_num);
        TextView windNum = (TextView) findViewById(R.id.wind_num);
        TextView solNum = (TextView) findViewById(R.id.sun_num);
        TextView hydroNum = (TextView) findViewById(R.id.hydro_num);
        TextView nukeNum = (TextView) findViewById(R.id.nuke_num);
        TextView totalNum = (TextView) findViewById(R.id.total_num);
        if(coalNum != null && windNum != null && solNum  != null && hydroNum != null && nukeNum != null){



            coalNum.setText(Integer.toString(typeArray[0]));
            windNum.setText(Integer.toString(typeArray[1]));
            solNum.setText(Integer.toString(typeArray[2]));
            hydroNum.setText(Integer.toString(typeArray[3]));
            nukeNum.setText(Integer.toString(typeArray[4]));

            for(int in : typeArray){
                total += in;
            }
            totalNum.setText(Integer.toString(total));
        }

        Button b = (Button) findViewById(R.id.back_button);
        if(b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
            });
        }

        TextView emptySpaces = (TextView) findViewById(R.id.empty_num);
        emptySpaces.setText(Integer.toString(15-subTotalOne-total) + " Empty Spaces");


    }

}
