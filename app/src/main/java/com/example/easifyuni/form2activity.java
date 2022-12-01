package com.example.easifyuni;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class form2activity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewpager;
    private EditText dateEdt;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    private TextInputEditText editTextcap;
    private TextInputEditText editTextattendreq;
    private TextInputEditText editTextlink;
    private TextInputEditText editTextloc;
    private TextInputEditText editTextfee;
    private EditText editTextdate;
    private EditText editTexttime;

    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form2_activity);

        db = FirebaseFirestore.getInstance();

        editTextcap = findViewById(R.id.event_attend);
        editTextattendreq = findViewById(R.id.event_attend_req);
        editTextlink = findViewById(R.id.event_link);
        editTextloc = findViewById(R.id.event_loc);
        editTextfee = findViewById(R.id.event_reg_fees);
        editTextdate = findViewById(R.id.event_date);
        editTexttime = findViewById(R.id.event_time);

        findViewById(R.id.buttoncon).setOnClickListener(this);

        chooseTime = findViewById(R.id.event_time);
        dateEdt = findViewById(R.id.event_date);
        tablayout = findViewById(R.id.tab1);
        viewpager = findViewById(R.id.viewpager);
        tablayout.setupWithViewPager(viewpager);
        VPAdapter vpadapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpadapter.addFragment(new Fragment_logo(), "Logo");
        vpadapter.addFragment(new fragment_poster(), "Poster");
        vpadapter.addFragment(new fragment_qr(), "QR");
        viewpager.setAdapter(vpadapter);

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(form2activity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                dateEdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                form2activity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                },
                                year, month, day);
                        datePickerDialog.show();
                    }
                });
            }

        });
    }

    @Override
    public void onClick(View v) {
        int cap = Integer.parseInt(editTextcap.getText().toString().trim());
        int att_req = Integer.parseInt(editTextattendreq.getText().toString().trim());
        String link = editTextlink.getText().toString().trim();
        String loc = editTextloc.getText().toString().trim();
        int fees= Integer.parseInt(editTextfee.getText().toString().trim());
        String date = editTextdate.getText().toString().trim();
        String Time = editTexttime.getText().toString().trim();

        CollectionReference dbReg = db.collection("Event_req");
        New_event event= new New_event(
                cap,
                att_req,
                link,
                loc,
                fees,
                date,
                Time
        );
        dbReg.add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(form2activity.this, "Event Added", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(form2activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Intent i1 = new Intent(this,MainActivity.class);
        startActivity(i1);
    }
}
