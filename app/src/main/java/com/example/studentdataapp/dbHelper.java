package com.example.studentdataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Student.db";
    public static final String TABLE_NAME = "studentTable";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "STUDENT";
    public static final String COLUMN_3 = "SECTION";
    public static final String COLUMN_4 = "COURSE";
    public static final String COLUMN_5 = "YEAR";

    public dbHelper (Context context){
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUDENT TEXT , SECTION TEXT, COURSE TEXT, YEAR TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String student, String section, String course, String year ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, student);
        contentValues.put(COLUMN_3, section);
        contentValues.put(COLUMN_4, course);
        contentValues.put(COLUMN_5, year);
        final long result = db.insert(TABLE_NAME, null, contentValues);
        return result != 1;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }

    public Cursor getOneData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id, null);
    }

    public boolean updateData(String id, String student, String section, String course, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, id);
        contentValues.put(COLUMN_2, student);
        contentValues.put(COLUMN_3, section);
        contentValues.put(COLUMN_4, course);
        contentValues.put(COLUMN_5, year);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { id } );
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}

