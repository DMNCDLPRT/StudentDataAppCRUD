package com.example.studentdataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    dbHelper myDatabase;
    RecyclerView recyclerView;
    StringBuffer buffer;

    EditText Student, Section, Course, Year;
    RecyclerView.LayoutManager layoutManager;
    recyclerViewAdapter RecyclerViewAdapter;

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new dbHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter = new recyclerViewAdapter(list);
        recyclerView.setAdapter(RecyclerViewAdapter);

        Student = findViewById(R.id.Student);
        Section = findViewById(R.id.Section);
        Course = findViewById(R.id.Course);
        Year = findViewById(R.id.Year);
    }

    public List<String> getAllStudent() {
        Cursor result = myDatabase.getAllData();

        String studentList = "";

        while (result.moveToNext()) {
            buffer.append("Student: ").append(result.getString(0));
            buffer.append(" Section: ").append(result.getString(1));
            buffer.append(" Course: ").append(result.getString(2));
            buffer.append(" Year: ").append(result.getString(3));
            studentList += buffer;

            list.add(studentList);
        }
        return new ArrayList<String>(list);
    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, activity_create.class);
        startActivity(i);
    }
}