package com.example.studentdataapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    dbHelper myDatabase;
    RecyclerView recyclerView;
    StringBuffer buffer;

    RecyclerView.LayoutManager layoutManager;
    recyclerViewAdapter RecyclerViewAdapter;

    ArrayList<String> studentID, studentName, section, course, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new dbHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);


        studentID = new ArrayList<>();
        studentName = new ArrayList<>();
        section = new ArrayList<>();
        course = new ArrayList<>();
        year = new ArrayList<>();

        storeData();
        RecyclerViewAdapter = new recyclerViewAdapter(MainActivity.this, this, studentID, studentName, section, course, year);
        recyclerView.setAdapter(RecyclerViewAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if  (requestCode == 0) {
            recreate();
        }
    }

    void storeData() {
        Cursor cursor = myDatabase.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                studentID.add(cursor.getString(0));
                studentName.add(cursor.getString(1));
                section.add(cursor.getString(2));
                course.add(cursor.getString(3));
                year.add(cursor.getString(4));
            }
        }
    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, activity_create.class);
        startActivity(i);
    }
}