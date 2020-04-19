package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfScoresActivity extends AppCompatActivity {
    ListView mListView;
    CustomAdapter mCustomAdapter;
    SQLiteDatabase mSQLiteDatabase;
    Dbhelper mDbHelper;
    Cursor mCursor;
    private String gettingUsernameFromItemClicked;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_scores);
        mContext = this;
        mListView = (ListView) findViewById(R.id.list_view);
        mCustomAdapter = new CustomAdapter(mContext, R.layout.created_row);
        mListView.setAdapter(mCustomAdapter);
        getInformation();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = (TextView) view.findViewById(R.id.username_txt);
                gettingUsernameFromItemClicked = textview.getText().toString();
                Toast.makeText(mContext, " " + gettingUsernameFromItemClicked, Toast.LENGTH_SHORT).show();
                deleteAlertDialog();
            }
        });

    }

    public void getInformation() {
        mDbHelper = new Dbhelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getReadableDatabase();
        mCursor = mDbHelper.getInformation(mSQLiteDatabase);
        if (mCursor.moveToFirst()) {
            do {
                String userName = mCursor.getString(0);
                String score = mCursor.getString(1);
                String date = mCursor.getString(2);
                DataProvider dataProvider = new DataProvider(userName, score, date);
                mCustomAdapter.add(dataProvider);
            } while (mCursor.moveToNext());
        }
    }

    public void deleteAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData();
            }
        });
        builder.setTitle("Delete Item? ");
        builder.setMessage("Would you like to delete this score? ");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void deleteData() {
        mDbHelper = new Dbhelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getReadableDatabase();
        String[] whereArgs = new String[]{gettingUsernameFromItemClicked};
        String whereClause = Dbhelper.USER_NAME + " = ?";
        mDbHelper.deleteInformation(whereClause, whereArgs, mSQLiteDatabase);
        recreate();
    }
}

