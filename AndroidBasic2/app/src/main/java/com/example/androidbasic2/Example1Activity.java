package com.example.androidbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Example1Activity extends AppCompatActivity {

    private Button buttonClickMe;
    private Button buttonBack;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);


        //find obj by its Id
        this.textView1 = (TextView)this.findViewById(R.id.textView1);
        this.textView2 = (TextView)this.findViewById(R.id.textView2);

        this.buttonClickMe = (Button)this.findViewById(R.id.button_click_me);
        this.buttonBack = (Button)this.findViewById(R.id.button_back);

        //get intent from MainActivity
        Intent intent = getIntent();

        //phan tich intent
        String value1 = intent.getStringExtra("text1");
        String value2 = intent.getStringExtra("text2");

        //set Text
        this.textView1.setText(value1);
        this.textView2.setText(value2);

        //Click Me
        this.buttonClickMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                textView2.setText("You click button");
            }
        });

        //Long Click Me
        this.buttonClickMe.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                textView1.setText("You long click button");
                return true;
            }
        });

        //Click Back
        this.buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Example1Activity.this.finish();
            }
        });

    }
}