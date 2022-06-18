package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<Bus> {

    public GridAdapter(Context context, ArrayList<Bus> buses) {
        super(context, 0, buses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_element, parent, false);
        }

        Bus tmp_bus = getItem(position);
        TextView tv_bus_num = listitemView.findViewById(R.id.tv_bus_num);
        TextView tv_bus_capcity = listitemView.findViewById(R.id.tv_bus_capcity);
        TextView tv_name_driver = listitemView.findViewById(R.id.tv_name_driver);
        TextView tv_phonenumber_driver = listitemView.findViewById(R.id.tv_phonenumber_driver);
        ImageView iv_bus_grid = listitemView.findViewById(R.id.iv_bus_grid);

       tv_bus_num.setText("bus #"+String.valueOf(tmp_bus.getBus_number()));
        tv_bus_capcity.setText("bus #"+String.valueOf(tmp_bus.getCapacity_of_bus()));
        tv_name_driver.setText(tmp_bus.getName_driver());
        tv_phonenumber_driver.setText(tmp_bus.getPhonenumber_driver());
        iv_bus_grid.setImageResource(R.drawable.bus);
        return listitemView;
    }

}