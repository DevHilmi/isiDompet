package com.example.hilmi.isidompet.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hilmi.isidompet.MoneyDataAdapter;
import com.example.hilmi.isidompet.MoneySpent;
import com.example.hilmi.isidompet.R;

import java.util.List;

import butterknife.ButterKnife;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;

import static com.example.hilmi.isidompet.R.color.table_header_text;

/**
 * Created by Hilmi on 23/03/2016.
 */
public class TableFragment extends Fragment {
    TableView tableView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        tableView = (TableView) getView().findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(getResources().getColor(R.color.header));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), "BALANCE", "DESCRIPTION", "DATE"));
        tableView.setColumnWeight(1, 2);
        tableView.setColumnWeight(0, 2);
        int rowColorEven = getContext().getResources().getColor(R.color.table_data_row_even);
        int rowColorOdd = getContext().getResources().getColor(R.color.table_data_row_odd);
        tableView.setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
        List<MoneySpent> moneySpentList = MoneySpent.listAll(MoneySpent.class);
        tableView.setDataAdapter(new MoneyDataAdapter(getActivity(), moneySpentList));

    }
}
