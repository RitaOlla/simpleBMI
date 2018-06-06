package com.example.rita_ola.simplebmi;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Color;

public class ResultActivity extends AppCompatActivity {
    public static final String RESULT ="result";
    private TextView res;
    private TextView resDes;
    private final static double UNDER_WEIGHT = 18.0;
    private final static double OVER_WEIGHT = 25.0;
    private final static double OBESE = 30.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        res = findViewById(R.id.tvBmiResult);
        resDes = findViewById(R.id.tvDescription);

        Intent intent = this.getIntent();
        double result = intent.getDoubleExtra(RESULT, 0);
        String res_bmi;
        res_bmi = String.format("%.2f", result);
        res.setText(res_bmi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(result < UNDER_WEIGHT){
            res.setTextColor(Color.YELLOW);
            resDes.setText("You are under weight!");

        }else if (result > OBESE){
            res.setTextColor(Color.RED);
            resDes.setText("You are obese!");
        } else if (result < OBESE && result > OVER_WEIGHT){
            res.setTextColor(Color.BLACK);
            resDes.setText("You are overweight!");
        } else {
            res.setTextColor(Color.GREEN);
            resDes.setText("You are normal!");
        }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
}
