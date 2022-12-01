package com.example.easifyuni;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements adapter {
    private EditText dateEdt;
    private Button pickTimeBtn;
    //private TextView selectedTimeTV;
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.daksh, R.drawable.magnovite, R.drawable.mm};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateEdt = findViewById(R.id.idEdtDate);
        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Events);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                                MainActivity.this,
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

        // on below line we are adding click
        // listener for our pick date button
    }

    public void Dosomething(View view) {
        Intent i1 = new Intent(this, formactivity.class);
        startActivity(i1);
    }

}
