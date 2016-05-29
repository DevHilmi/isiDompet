package com.example.hilmi.isidompet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dawn on 4/1/2016.
 */


public class Splash extends AppCompatActivity {

    @Bind(R.id.balanceText2)
    EditText balanceText2;


    @Bind(R.id.saveButton2)
    Button saveButton2;

    static final String KEY_IS_FIRST_TIME = "com.isiDompet.first_time";
    static final String KEY = "com.isiDompet";
    MoneySpent moneySpent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        saveButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                moneySpent = new MoneySpent("+"+balanceText2.getText().toString(), "Nominal Awal", formattedDate);
                moneySpent.save();
                Intent intent = new Intent(Splash.this,Layout.class);
                startActivity(intent);
                getSharedPreferences(KEY, Context.MODE_PRIVATE).edit().putBoolean(KEY_IS_FIRST_TIME, false).commit();

            }
        });
        if (!isFirstTime()) {
            startActivity(new Intent(this, Layout.class));
            finish();
        }


    }

    public boolean isFirstTime() {
        return getSharedPreferences(KEY, Context.MODE_PRIVATE).getBoolean(KEY_IS_FIRST_TIME, true);
    }
}

