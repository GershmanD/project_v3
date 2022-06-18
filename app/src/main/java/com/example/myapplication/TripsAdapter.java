package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class TripsAdapter extends ArrayAdapter<Trip> {

    Context context;
    List<Trip> objects;

    public TripsAdapter(Context context, int resource, int textViewResourceId, List<Trip> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.objects=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.row_listview_trips, parent, false);

        TextView tv_date = view.findViewById(R.id.tv_date);
        TextView tv_key = view.findViewById(R.id.tv_key);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_place = view.findViewById(R.id.tv_place);

        Trip temp = objects.get(position);
        tv_date.setText(temp.title);
        tv_key.setText(temp.title);
        tv_title.setText(temp.title);
        tv_place.setText(temp.title);
        return view;
    }
}
