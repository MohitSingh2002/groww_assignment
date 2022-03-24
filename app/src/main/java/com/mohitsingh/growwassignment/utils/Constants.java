package com.mohitsingh.growwassignment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Used to create constant string throughout the application
public class Constants {

    // List of months
    public String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public String getDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM, yyyy (EEEE)", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

}
