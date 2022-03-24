package com.mohitsingh.growwassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mohitsingh.growwassignment.MainActivity;
import com.mohitsingh.growwassignment.MonthYearActivity;
import com.mohitsingh.growwassignment.R;
import com.mohitsingh.growwassignment.databinding.GridViewTextBinding;
import com.mohitsingh.growwassignment.utils.Constants;

import java.util.Calendar;
import java.util.List;

public class CurrentMonthGridView extends ArrayAdapter<String> {

    Context context;
    List<String> monthsList;
    String month;
    String year;

    public CurrentMonthGridView(@NonNull Context context, List<String> monthsList, String month, String year) {
        super(context, 0, monthsList);
        this.context = context;
        this.monthsList = monthsList;
        this.month = month;
        this.year = year;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GridViewTextBinding binding = GridViewTextBinding.inflate(LayoutInflater.from(context));
        Calendar calendar = Calendar.getInstance();
        // Set date in text view
        binding.monthTextView.setText(monthsList.get(position) + " " + month + ", " + year);
        // If date is today make text color red
        if (context instanceof MainActivity) {
            if (Integer.parseInt(monthsList.get(position)) == calendar.get(Calendar.DAY_OF_MONTH)) {
                binding.monthTextView.setTextColor(context.getResources().getColor(R.color.red));
            }
        }
        // setup on click on any date
        setupOnClick(binding, monthsList.get(position), position);
        return binding.getRoot();
    }

    private void setupOnClick(GridViewTextBinding binding, String date, int position) {
        if (context instanceof MonthYearActivity) {
            binding.monthTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MonthYearActivity) context).setupDateClicked(date, position);
                }
            });
        }
    }

}
