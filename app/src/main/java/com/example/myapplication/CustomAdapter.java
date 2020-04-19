package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    List mList = new ArrayList();

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public int getCount() {
        return mList.size();
    }

    public Object getItem(int position) {
        return mList.get(position);
    }

    static class LayoutHandler {
        TextView userName, score, date;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler = new LayoutHandler();
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.created_row, parent, false);
            layoutHandler.userName = (TextView) row.findViewById(R.id.username_txt);
            layoutHandler.score = (TextView) row.findViewById(R.id.score_txt);
            layoutHandler.date = (TextView) row.findViewById(R.id.date_txt);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.userName.setText(dataProvider.getUserName());
        layoutHandler.score.setText(dataProvider.getScore());
        layoutHandler.date.setText(dataProvider.getDate());
        return row;
    }

    public void add(@Nullable Object object) {
        super.add(object);
        mList.add(object);
    }
}

