package com.example.studentdataapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter <recyclerViewAdapter.MyViewHolder> {

    ArrayList<String> studentID, studentName, section, course, year;
    Context context;

    public recyclerViewAdapter(Context context, ArrayList studentID, ArrayList studentName, ArrayList section, ArrayList course,
                               ArrayList year){
        this.context = context;
        this.studentID = studentID;
        this.studentName = studentName;
        this.section = section;
        this.course = course;
        this.year = year;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.studentID_tv.setText(studentID.get(position));
        holder.studentName_tv.setText(studentName.get(position));
        holder.section_tv.setText(section.get(position));
        holder.course_tv.setText(course.get(position));
        holder.year_tv.setText(year.get(position));

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, activity_update.class);
            intent.putExtra("ID", String.valueOf(studentID.get(position)));
            intent.putExtra("Student", String.valueOf(studentName.get(position)));
            intent.putExtra("Section", String.valueOf(section.get(position)));
            intent.putExtra("Course", String.valueOf(course.get(position)));
            intent.putExtra("Year", String.valueOf(year.get(position)));

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return studentID.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView studentID_tv, studentName_tv, section_tv, course_tv, year_tv;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentID_tv = itemView.findViewById(R.id.studentID_tv);
            studentName_tv = itemView.findViewById(R.id.StudentName_tv);
            section_tv = itemView.findViewById(R.id.section_tv);
            course_tv = itemView.findViewById(R.id.course_tv);
            year_tv = itemView.findViewById(R.id.year_tv);

            mainLayout = itemView.findViewById(R.id.recyclerViewMain);

        }
    }
}
