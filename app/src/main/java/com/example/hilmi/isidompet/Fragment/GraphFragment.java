package com.example.hilmi.isidompet.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.hilmi.isidompet.MoneySpent;
import com.example.hilmi.isidompet.R;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Hilmi on 23/03/2016.
 */
public class GraphFragment extends Fragment {
    ValueLineChart mCubicValueLineChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCubicValueLineChart = (ValueLineChart) getView().findViewById(R.id.cubiclinechart);
        loadDataTest();
    }

    public void loadDataTest() {
        List<MoneySpent> moneySpentList = MoneySpent.listAll(MoneySpent.class);
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF63CBB0);
        float balance = 0;
        series.addPoint(new ValueLinePoint("First",0));
        for (int i = 0; i < moneySpentList.size(); i++) {
            String index = String.valueOf(i + 1);
            if (moneySpentList.get(i).getBalance() != null) {
                if (moneySpentList.get(i).getBalance().substring(0, 1).equals("+")) {
                    balance += Double.valueOf(moneySpentList.get(i).getBalance().substring(1));
                    series.addPoint(new ValueLinePoint(index, balance));
                } else if (moneySpentList.get(i).getBalance().substring(0, 1).equals("-")) {
                    balance -= Double.valueOf(moneySpentList.get(i).getBalance().substring(1));
                    series.addPoint(new ValueLinePoint(index, balance));
                }else{

                }
            }

        }
        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }
}
