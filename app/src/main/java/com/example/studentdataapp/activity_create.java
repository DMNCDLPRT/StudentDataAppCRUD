package com.example.studentdataapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_create extends AppCompatActivity {
    dbHelper myDatabase;

    EditText Student, Section, Course, Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craete);
        myDatabase = new dbHelper(this);

        Student = findViewById(R.id.Student);
        Section = findViewById(R.id.Section);
        Course = findViewById(R.id.Course);
        Year = findViewById(R.id.Year);
    }

    public void createStudent(View view) {
        boolean create = myDatabase.insertData(Student.getText().toString(),
                Section.getText().toString(),
                Course.getText().toString(),
                Year.getText().toString());
        if (create){
            Toast.makeText(activity_create.this, "Data Inserted successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(activity_create.this, "Error", Toast.LENGTH_LONG).show();
    }
}
