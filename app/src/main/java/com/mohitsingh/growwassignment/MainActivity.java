package com.mohitsingh.growwassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mohitsingh.growwassignment.adapters.CurrentMonthGridView;
import com.mohitsingh.growwassignment.databinding.ActivityMainBinding;
import com.mohitsingh.growwassignment.utils.CalendarUtil;
import com.mohitsingh.growwassignment.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int currentMonth, currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize binding for this activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find current month and year
        Calendar calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        // Setup views
        setupCurrentMonthGridView();
        setupMonthPicker();
        setupYearEditText();
        setupGoButton();
    }

    private void setupCurrentMonthGridView() {
        List<String> currentMonthDates = new ArrayList<>();
        for (int i = 1; i <= new CalendarUtil().getNoOfDays(currentMonth, currentYear); i++) {
            currentMonthDates.add("" + i);
        }
        CurrentMonthGridView currentMonthGridView = new CurrentMonthGridView(MainActivity.this, currentMonthDates, new Constants().months[Calendar.getInstance().get(Calendar.MONTH)], String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        binding.currentMonthDates.setAdapter(currentMonthGridView);
    }

    private void setupMonthPicker() {
        // initialise an array adapter with months
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new Constants().months);
        // set input to 0 in auto complete text view
        binding.monthSpinner.setInputType(0);
        binding.monthSpinner.setThreshold(2);
        // setup adapter in auto complete text view
        binding.monthSpinner.setAdapter(adapter);
    }

    private void setupYearEditText() {
        // Set current year as a text in year edit text
        Calendar calendar = Calendar.getInstance();
        binding.yearEt.setText("" + calendar.get(Calendar.YEAR));
    }

    private void setupGoButton() {
        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If no month is not selected then show toast
                if (binding.monthSpinner.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.please_select_a_month), Toast.LENGTH_SHORT).show();
                } else {
                    // Pass month and year in the intent
                    Intent intent = new Intent(MainActivity.this, MonthYearActivity.class);
                    intent.putExtra("month", binding.monthSpinner.getText().toString());
                    intent.putExtra("year", binding.yearEt.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

}
