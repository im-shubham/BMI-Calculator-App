package com.shubham.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class BmiActivity extends AppCompatActivity {
    TextView gender,bmiNumber,symptoms;
    LottieAnimationView bmiAnim,genderAnim;

    Intent intent;
    Button reCalculate;
    float intbmi;
    String height,weight,age,typeOfUser;
    float intheight,intweight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        gender =findViewById(R.id.animal_name);
        bmiNumber= findViewById(R.id.bmiNumber);
        symptoms = findViewById(R.id.symptoms);
        intent = getIntent();
        reCalculate = findViewById(R.id.recalculateButton);
        bmiAnim =findViewById(R.id.bmiAnimation);
        genderAnim= findViewById(R.id.imgTravel);



        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");
        age = intent.getStringExtra("age");
        typeOfUser = intent.getStringExtra("gender");

        intheight =  Float.parseFloat(height);
        intweight = Float.parseFloat(weight);
        intheight /= 100;
        intbmi = intweight/(intheight*intheight);
        if(typeOfUser.equals("Male")){
            genderAnim.setAnimation(R.raw.manicon);
            gender.setText("Male");
        }else{
            genderAnim.setAnimation(R.raw.woman);
            gender.setText("Female");
        }

        bmiNumber.setText(String.valueOf(intbmi));
        if(intbmi<18.5){

            symptoms.setText("Underweight");
            bmiAnim.setAnimation(R.raw.bmidanger);
        }else if(18.5<= intbmi && intbmi<=24.9 ){
            symptoms.setText("You're Healty :) ");
            bmiAnim.setAnimation(R.raw.bmiok);
        }else if(intbmi>=25.0 && intbmi<= 29.9){
            symptoms.setText("You're Overweight");
            bmiAnim.setAnimation(R.raw.bmiwarning);
        }else if(intbmi>=30.0){
            bmiAnim.setAnimation(R.raw.bmidanger);
            symptoms.setText("You're Obese");
        }

        reCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BmiActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}