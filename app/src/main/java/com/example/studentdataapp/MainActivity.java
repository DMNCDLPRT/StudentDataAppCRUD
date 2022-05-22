package com.example.studentdataapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dbHelper myDatabase;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    recyclerViewAdapter RecyclerViewAdapter;
    ImageView emptyImageView;
    TextView noData;

    ArrayList<String> studentID, studentName, section, course, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new dbHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        emptyImageView = findViewById(R.id.imageView);
        noData = findViewById(R.id.noData_tv);

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
        if  (requestCode == 1) {
            recreate();
        }
    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, activity_create.class);
        startActivity(i);
    }

    void storeData() {
        Cursor cursor = myDatabase.getAllData();
        if (cursor.getCount() == 0) {
            emptyImageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);

        } else {
            while (cursor.moveToNext()) {
                studentID.add(cursor.getString(0));
                studentName.add(cursor.getString(1));
                section.add(cursor.getString(2));
                course.add(cursor.getString(3));
                year.add(cursor.getString(4));
            }
            emptyImageView.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            alertDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Data");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", (dialogInterface, i) ->{
            myDatabase.deleteAllData();
            // Toast.makeText(this, "All Data has been Deleted", Toast.LENGTH_SHORT).show();
            recreate();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> { });

        builder.create().show();
    }


}