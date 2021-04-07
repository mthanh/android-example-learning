package com.example.androidbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find button by ID
        this.button1 = (Button) this.findViewById(R.id.button1);


        // Called when the user clicks the button1.
        button1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a Intent:
                // (This object contains content that will be sent to Example1Activity).
                Intent myIntent = new Intent(MainActivity.this, Example1Activity.class);

                // Put parameters
                myIntent.putExtra("text1", "This is text1 sent from MainActivity at " + new Date());
                myIntent.putExtra("text2", "This is text2 sent from MainActivity at " + new Date());

                // Start Example1Activity.
                MainActivity.this.startActivity(myIntent);
            }
        });

    }




}

