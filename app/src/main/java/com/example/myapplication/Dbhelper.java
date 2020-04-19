package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String TAG = "DATABASE_OPERATION";
    private static final String DATABASE_NAME = "Score_Table";
    private static final int DATABASE_VERSION = 1;
    public static final String SCORE_TABLE_NAME = "score_table";
    public static final String SCORE = "score";
    public static final String USER_NAME ="User_name";
    public static final String DATE = "Date";
    private static final String CREATE_QUERY = "CREATE TABLE "+ SCORE_TABLE_NAME+"("+ USER_NAME+" Text,"            + SCORE+" Text,"+ DATE+" Text);";
    // pay attention to spacing carefully 

    public Dbhelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInformation(String name, String score, String Date, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(SCORE, score);
        contentValues.put(DATE, Date);
        db.insert(SCORE_TABLE_NAME, null, contentValues);
        Log.e(TAG, "One row is inserted");
    }

    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projections = {USER_NAME, SCORE, DATE};
        String order = SCORE;
        cursor = db.query(SCORE_TABLE_NAME, projections, null, null, null, null,order);
        return cursor;
    }


    public void deleteInformation(String whereClause, String[] whereArgs, SQLiteDatabase db) {

        db.delete(SCORE_TABLE_NAME, whereClause, whereArgs);

        Log.e(TAG, "One row is deleted with username: " + Arrays.toString(whereArgs));
    }
    }
