package com.example.studentdataapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_update extends AppCompatActivity {

    dbHelper myDatabase;

    EditText Student, Section, Course, Year;
    Button updateButton;

    String id, student, section, course, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Student = findViewById(R.id.Student_update);
        Section = findViewById(R.id.Section_update);
        Course = findViewById(R.id.Course_update);
        Year = findViewById(R.id.Year_update);
        updateButton = findViewById(R.id.btnUpdate);

        getIntentData();

        updateButton.setOnClickListener(view -> {
            myDatabase = new dbHelper(activity_update.this);

            student = Student.getText().toString().trim();
            section = Section.getText().toString().trim();
            course = Course.getText().toString().trim();
            year = Year.getText().toString().trim();

            myDatabase.updateData(id, student, section, course, year);
        });
    }

    void getIntentData() {
        if (getIntent().hasExtra("ID") && getIntent().hasExtra("Student") && getIntent().hasExtra("Section")
                && getIntent().hasExtra("Course") && getIntent().hasExtra("Year")){
            // getting data from intent
            id = getIntent().getStringExtra("ID");
            student = getIntent().getStringExtra("Student");
            section = getIntent().getStringExtra("Section");
            course = getIntent().getStringExtra("Course");
            year = getIntent().getStringExtra("Year");

            // setting intent data
            Student.setText(student);
            Section.setText(section);
            Course.setText(course);
            Year.setText(year);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
