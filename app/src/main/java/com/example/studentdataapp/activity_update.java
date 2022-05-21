package com.example.studentdataapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class activity_update extends AppCompatActivity {

    dbHelper myDatabase;

    EditText Student, Section, Course, Year;
    Button updateButton, deleteButton;

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
        deleteButton = findViewById(R.id.btnDelete);

        getIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(student);
        }

        updateButton.setOnClickListener(view -> {
            myDatabase = new dbHelper(activity_update.this);
            student = Student.getText().toString().trim();
            section = Section.getText().toString().trim();
            course = Course.getText().toString().trim();
            year = Year.getText().toString().trim();

            myDatabase.updateData(id, student, section, course, year);
        });

        deleteButton.setOnClickListener( view -> {
            myDatabase = new dbHelper(activity_update.this);
            alertDialog();
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

    void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + student + " ?");
        builder.setMessage("Are you sure you want to delete " + student + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> myDatabase.deleteData(id));
        builder.setNegativeButton("No", (dialogInterface, i) -> { });

        builder.create().show();
    }
}
