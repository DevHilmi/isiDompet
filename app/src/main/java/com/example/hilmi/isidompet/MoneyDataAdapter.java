package com.example.hilmi.isidompet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by Dawn on 3/24/2016.
 */
public class MoneyDataAdapter extends TableDataAdapter<MoneySpent> {

    public MoneyDataAdapter(Context context, List<MoneySpent> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        MoneySpent moneySpent = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderBalance(moneySpent);
                break;
            case 1:
                renderedView = renderDescription(moneySpent);
                break;
            case 2:
                renderedView = renderDate(moneySpent);
                break;
        }

        return renderedView;
    }

    private View renderBalance(MoneySpent moneySpent) {
        TextView textView = new TextView(getContext());
        String x = String.valueOf(moneySpent.getBalance());
        textView.setText(x);
        return textView;
    }

    private View renderDescription(MoneySpent moneySpent) {
        return renderString(moneySpent.getDescription());
    }

    private View renderDate(MoneySpent moneySpent) {
        return renderString(moneySpent.getDate());
    }

    private View renderString(String value) {
        TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(14);
        return textView;
    }
}
