package com.example.hilmi.isidompet.Fragment;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.hilmi.isidompet.MoneySpent;
import com.example.hilmi.isidompet.R;
import com.example.hilmi.isidompet.Splash;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hilmi on 23/03/2016.
 */
public class InputFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.textIsiDompet)
    TextView textIsiDompet;
    @Bind(R.id.textBalance)
    TextView textBalance;
    @Bind(R.id.balanceText)
    EditText balanceText;
    @Bind(R.id.descriptionText)
    EditText descriptionText;
    @Bind(R.id.outButton)
    Button outButton;
    @Bind(R.id.inButton)
    Button inButton;

    MoneySpent moneySpent;
    List<MoneySpent> moneySpentList = MoneySpent.listAll(MoneySpent.class);
    private double balance = Double.parseDouble(moneySpentList.get(0).getBalance());
    private boolean in = false;
    private boolean out = false;
    public static final String MyPREFERENCES = "MyBalance";
    private boolean first = true;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        first = sharedPreferences.getBoolean("first",true);
        balance = Double.parseDouble(sharedPreferences.getString("balance", String.valueOf(balance)));
        textBalance.setText(String.valueOf(balance));
        editor = sharedPreferences.edit();
        inButton.setOnClickListener(this);
        outButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.inButton:
                this.in = true;
                this.out = false;
                Save();
                break;

            case R.id.outButton:
                this.out = true;
                this.in = false;
                Save();
                break;

            default:
                break;
        }
    }

    public void Save() {

        String stringBalance = this.balance();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        String input = String.valueOf(this.balance);
        this.updateBalance();
        textBalance.setText(input);
        if (!balanceText.getText().toString().equals("")) {
            moneySpent = new MoneySpent(stringBalance, descriptionText.getText().toString(), formattedDate);
            moneySpent.save();
        } else {
            TSnackbar.make(getActivity().findViewById(android.R.id.content), "Inputan salah", TSnackbar.LENGTH_LONG).show();
        }
    }

    public String balance() {
        double plusDouble;
        String plus = balanceText.getText().toString();
        if (!balanceText.getText().toString().equals("")) {
            if (plus == null || plus.isEmpty()) {
                plusDouble = 0.0;
            } else {
                plusDouble = Double.parseDouble(balanceText.getText().toString());
            }
            if (in == true) {
                balance = balance + plusDouble;
                return String.format("+" + plus);
            } else if (out == true) {
                double temp = balance - plusDouble;
                double persen = temp / balance * 100;
                if (persen < 50) {
                    TSnackbar.make(getActivity().findViewById(android.R.id.content), "Jangan Boros", TSnackbar.LENGTH_LONG).show();
                }
                balance = balance - plusDouble;
                return String.format("-" + plus);
            }
        }
        return "";

    }

    public void updateBalance() {
        editor.putBoolean("first", false);
        editor.putString("balance", String.valueOf(balance));
        editor.commit();
    }
}
