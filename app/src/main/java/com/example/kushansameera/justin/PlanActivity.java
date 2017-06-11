package com.example.kushansameera.justin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlanActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlan;
    private ImageButton btnStartLocation, btnEndLocation, btnStartDate, btnEndDate, btnStartTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    private TextView txtStartLocaton, txtEndLocation, txtStartDate, txtEndDate, txtStartTime;
    String AM_PM;
    public static double startLatitude, startLongitude, endLatitude, endLongitude;
    public static String startName, endName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        setTitle("Trip Profile");
        btnPlan = (Button) findViewById(R.id.btnPlan);
        btnStartLocation = (ImageButton) findViewById(R.id.btnStartLocation);
        btnEndLocation = (ImageButton) findViewById(R.id.btnEndLocation);
        btnStartDate = (ImageButton) findViewById(R.id.btnStartDate);
        btnEndDate = (ImageButton) findViewById(R.id.btnEndDate);
        btnStartTime = (ImageButton) findViewById(R.id.btnStartTime);
        txtStartLocaton = (TextView) findViewById(R.id.txtStartLocation);
        txtEndLocation = (TextView) findViewById(R.id.txtEndLocation);
        txtStartDate = (TextView) findViewById(R.id.txtStartDate);
        txtEndDate = (TextView) findViewById(R.id.txtEndDate);
        txtStartTime = (TextView) findViewById(R.id.txtStartTime);

        txtStartLocaton.setText(startName);
        txtEndLocation.setText(endName);

        btnPlan.setOnClickListener(this);
        btnStartDate.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);
        btnStartTime.setOnClickListener(this);
        btnStartLocation.setOnClickListener(this);
        btnEndLocation.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == btnPlan) {
            Intent intent = new Intent(PlanActivity.this, TripActivity.class);
            startActivity(intent);
        }
        if (view == btnStartDate) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtStartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == btnEndDate) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == btnStartTime) {
            final Calendar c = Calendar.getInstance();
            final int mHour = c.get(Calendar.HOUR);
            int mMinute = c.get(Calendar.MINUTE);


            timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    int nHour = 0;
                    if (hour < 12 && hour > 0) {
                        nHour = hour;
                        AM_PM = "AM";
                    } else if (hour == 12) {
                        nHour = 12;
                        AM_PM = "PM";
                    } else if (hour == 0) {
                        nHour = 12;
                        AM_PM = "AM";
                    } else {
                        nHour = hour - 12;
                        AM_PM = "PM";
                    }
                    txtStartTime.setText(nHour + ":" + minute + " " + AM_PM);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (view == btnStartLocation) {
            Intent intent = new Intent(PlanActivity.this, MapActivity.class);
            //startActivity(intent);
            startActivityForResult(intent,0);
            MapActivity.select = "Start";
        }

        if (view == btnEndLocation) {
            Intent intent = new Intent(PlanActivity.this, MapActivity.class);
            //startActivity(intent);
            startActivityForResult(intent,0);
            MapActivity.select = "End";
        }

    }
}
