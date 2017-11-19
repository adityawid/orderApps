package com.devjurnal.orderapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO (1) global variable
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrement(View view) {
        //quantity = quantity -1 ;
        quantity -= 1;
        //Log.d("Decrement", String.valueOf(quantity));
        display(quantity);
    }

    public void display(int quantity) {
        TextView tvQuantity = (TextView) findViewById(R.id.tvQuantity);
        tvQuantity.setText("" +quantity);

    }


    public void increment(View view) {
        //quantity = quantity + 1;
        quantity += 1;
        display(quantity);
    }
}
