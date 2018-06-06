package com.example.rita_ola.simplebmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import static com.example.rita_ola.simplebmi.R.id.author;
import static com.example.rita_ola.simplebmi.R.id.forever;

public class Main2Activity extends AppCompatActivity {

    private EditText massInput;
    private EditText heightInput;
    private Button btnRes;
    private Switch btnSwitch;
    private double dMass;
    private double dHeight;
    private SharedPreferences sp;
    final static String SHARED_PREFERENCES = "shared preferences";
    final static String MASS = "mass";
    final static String HEIGHT ="height";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        massInput = findViewById(R.id.etMass);
        heightInput = findViewById(R.id.etHeight);
        btnSwitch = findViewById(R.id.btnSwitch);
        btnRes = findViewById(R.id.btnCount);

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hints();
            }
        });
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRes();
            }
        });

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void hints()
    {
        if(btnSwitch.isChecked())
        {
            massInput.setHint(R.string.kg);
            heightInput.setHint(R.string.m);
        }
        else
        {
            massInput.setHint(R.string.lb);
            heightInput.setHint(R.string.in);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String massHint = massInput.getHint().toString();
        String heightHint = heightInput.getHint().toString();
        outState.putString(MASS, massHint);
        outState.putString(HEIGHT,heightHint);
        outState.putBoolean("switch",btnSwitch.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        String massHint = savedInstanceState.getString(MASS);
        String heightHint = savedInstanceState.getString(HEIGHT);
        boolean left_right = savedInstanceState.getBoolean("switch");
        massInput.setHint(massHint);
        heightInput.setHint(heightHint);
        btnSwitch.setChecked(left_right);
    }

    private void showRes(){
        boolean switchState = btnSwitch.isChecked();
        double res;
        getMass();
        getHeight();
        if(switchState==true){
            KgMBmiCounter kgMCnt = new KgMBmiCounter(dMass, dHeight);
            res = kgMCnt.calculateBMI();
            try{
                final Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(ResultActivity.RESULT, res);
                startActivity(intent);
            }
            catch (IllegalArgumentException e){
                displayToastErr();
            }
        } else
        {
            LbInBmiCounter lbInCnt = new LbInBmiCounter(dMass,dHeight);
            res = lbInCnt.calculateBMI();
            try {
                final Intent intent = new Intent(Main2Activity.this, ResultActivity.class);
                intent.putExtra(ResultActivity.RESULT, res);
                startActivity(intent);
            } catch (IllegalArgumentException e){
                displayToastErr();
            }
        }

    }

    private void displayToastErr() {
        boolean switchState = btnSwitch.isChecked();
        if (switchState) {
            if ((dHeight <= 0.5 || dHeight >= 2.5) && (dMass <= 10 || dMass >= 250)) {
                Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_LONG).show();
            } else {
                if (dMass <= 10 || dMass >= 250) {
                    Toast.makeText(this, R.string.wrong_mass, Toast.LENGTH_LONG).show();
                } else if (dHeight <= 0.5 || dHeight >= 2.5) {
                    Toast.makeText(this, R.string.wrong_height, Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if ((dHeight <= 10 || dHeight >= 100) && (dMass <= 22 || dMass >= 550)) {
                Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_LONG).show();
            } else {
                if (dMass <= 22 || dMass >= 550) {
                    Toast.makeText(this, R.string.wrong_mass, Toast.LENGTH_LONG).show();
                } else if (dHeight <= 10 || dHeight >= 100) {
                    Toast.makeText(this, R.string.wrong_height, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.author:
                startActivityAuthor();
                return true;
            case R.id.btnSave:
                saveState();
                return true;
            case R.id.clearSave:
                erase();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMass(){
        String sUserMass = massInput.getText().toString();
        if (sUserMass.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.NaN),Toast.LENGTH_LONG).show();
        } else
            dMass = Double.parseDouble(sUserMass);
    }

    private void getHeight(){
        String sUserHeight = heightInput.getText().toString();
        if(sUserHeight.equals("")){
            Toast.makeText(getApplicationContext(),getString(R.string.NaN),Toast.LENGTH_LONG).show();
        } else
            dHeight = Double.parseDouble(sUserHeight);
    }

    private void startActivityAuthor(){
        Intent author = new Intent(this, AuthorActivity.class);
        startActivity(author);
    }

    private void saveState(){

        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(MASS, massInput.getText().toString());
        editor.putString(HEIGHT, heightInput.getText().toString());
        editor.putBoolean("switch", btnSwitch.isChecked());
        editor.putString("hintm", massInput.getHint().toString());
        editor.putString("hinth", heightInput.getHint().toString());
        editor.apply();
        Toast.makeText(getApplicationContext(), getString(R.string.saved), Toast.LENGTH_LONG);
    }

    private void erase(){
        massInput.setText("");
        heightInput.setText("");
        Toast.makeText(getApplicationContext(), getString(R.string.erased), Toast.LENGTH_LONG);
    }

    private void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        massInput.setText(sp.getString(MASS, ""));
        heightInput.setText(sp.getString(HEIGHT,""));
        boolean left_right = sp.getBoolean("switch", false);
        massInput.setHint(sp.getString("hintm", getString(R.string.lb)));
        heightInput.setHint(sp.getString("hinth", getString(R.string.in)));
        btnSwitch.setChecked(left_right);
    }
}
