package com.mohitsingh.growwassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mohitsingh.growwassignment.adapters.CurrentMonthGridView;
import com.mohitsingh.growwassignment.databinding.ActivityMonthYearBinding;
import com.mohitsingh.growwassignment.databinding.GridViewTextBinding;
import com.mohitsingh.growwassignment.utils.CalendarUtil;
import com.mohitsingh.growwassignment.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthYearActivity extends AppCompatActivity {

    ActivityMonthYearBinding binding;
    int month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonthYearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(getString(R.string.month_details));

        // get values from the intent
        Intent intent = getIntent();
        // find month index from list present in constant java file
        for (int i = 0; i < new Constants().months.length; i++) {
            if (new Constants().months[i].equals(intent.getStringExtra("month"))) {
                month = i;
                break;
            }
        }
        year = Integer.parseInt(intent.getStringExtra("year"));

        // setup views
        setupGridView();

    }

    private void setupGridView() {
        List<String> currentMonthDates = new ArrayList<>();
        for (int i = 1; i <= new CalendarUtil().getNoOfDays(month, year); i++) {
            currentMonthDates.add("" + i);
        }
        CurrentMonthGridView currentMonthGridView = new CurrentMonthGridView(MonthYearActivity.this, currentMonthDates, new Constants().months[month], String.valueOf(year));
        binding.searchedMonthDates.setAdapter(currentMonthGridView);
    }

    public void setupDateClicked(String date, int position) {
        // Clear linear layout views
        binding.weekDaysLinearLayout.removeAllViews();
        // This function is called on click of any date in the grid view
        binding.dateTextView.setText(getString(R.string.days_of_week_are));
        List<Date> daysInWeek = new CalendarUtil().getDatesOfWeek(Integer.parseInt(date), month, year);
        for (Date dateOfWeek : daysInWeek) {
            GridViewTextBinding textBinding = GridViewTextBinding.inflate(getLayoutInflater());
            textBinding.monthTextView.setText("" + new Constants().getDateString(dateOfWeek));
            binding.weekDaysLinearLayout.addView(textBinding.getRoot());
        }
        binding.dateTextView.setVisibility(View.VISIBLE);
        binding.weekDaysLinearLayout.setVisibility(View.VISIBLE);
    }

}
