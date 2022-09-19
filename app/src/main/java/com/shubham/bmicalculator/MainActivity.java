package com.shubham.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity {

    TextView currentHeight, currentAge, currentWeight;
    Button calculateButton;
    ImageView incrementAge,incrementWeight,decrementWeight,decrementAge;
    SeekBar seekbar;
    RelativeLayout femaleLayout,maleLayout;

    int weight = 55;
    int age = 23;
    int currentProgress;
    String mintProgress="165";
    String typeOfUser ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Id mapping..

        currentHeight = findViewById(R.id.currentHeight);
        currentAge = findViewById(R.id.ageNumber);
        currentWeight = findViewById(R.id.weightNumber);
        incrementAge = findViewById(R.id.incrementAge);
        decrementAge = findViewById(R.id.decrementAge);
        incrementWeight = findViewById(R.id.incrementWeight);
        decrementWeight = findViewById(R.id.decrementWeight);
        seekbar = findViewById(R.id.seekbarForHeight);
        calculateButton = findViewById(R.id.captureBtn);

        femaleLayout = findViewById(R.id.femaleLayout);
        maleLayout = findViewById(R.id.maleLayout);

        maleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeOfUser = "Male";

            }
        });
        femaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeOfUser = "Female";

            }
        });
        seekbar.setMax(300);
        seekbar.setProgress(165);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress =progress;
                mintProgress = String.valueOf(currentProgress);
                currentHeight.setText(mintProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        incrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++age;
                currentAge.setText(String.valueOf(age));
            }
        });
        decrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(age<=0){
                    TastyToast.makeText(MainActivity.this,"Age can't be less Than Zero ",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                }else{
                    --age;
                    currentAge.setText(String.valueOf(age));
                }

            }
        });
        incrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++weight;
                currentWeight.setText(String.valueOf(weight));
            }
        });
        decrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(weight<=0){
                    TastyToast.makeText(MainActivity.this,"Weight can't be less Than Zero ",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                }else{
                    --weight;
                    currentWeight.setText(String.valueOf(weight));
                }

            }
        });





        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(typeOfUser.equals("")){
                    TastyToast.makeText(MainActivity.this,"Select Your Gender. ",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
                }else if(mintProgress.equals("0")){
                    TastyToast.makeText(MainActivity.this,"Select Your Height. ",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
                }else{

                    Intent intent = new Intent(MainActivity.this,BmiActivity.class);
                    intent.putExtra("gender",typeOfUser);
                    intent.putExtra("height",mintProgress);
                    intent.putExtra("weight",String.valueOf(weight));
                    intent.putExtra("age",String.valueOf(age));
                    startActivity(intent);
                }



            }
        });
    }
}