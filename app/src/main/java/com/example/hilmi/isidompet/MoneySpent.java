package com.example.hilmi.isidompet;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Hilmi on 24/03/2016.
 */

public class MoneySpent extends SugarRecord {

    private String balance;
    private String description;
    private String date;

    public MoneySpent() {
    }

    public MoneySpent(String balance, String description, String date) {
        this.balance = balance;
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getBalance() {
        return balance;
    }

    public String getDate() {
        return date;
    }
}
