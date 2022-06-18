package com.example.myapplication;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class StudentAdpater extends ArrayAdapter<Student> {

    Context context;
    List<Student> objects;

    public StudentAdpater(Context context, int resource, int textViewResourceId, List<Student> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.objects=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.row_student, parent, false);

        TextView tv_st_nm = view.findViewById(R.id.tv_st_nm);
        TextView tv_st_cls = view.findViewById(R.id.tv_st_cls);
        TextView tv_st_phone = view.findViewById(R.id.tv_st_phone);

        Student temp = objects.get(position);
        tv_st_nm.setText(temp.getName());
        tv_st_cls.setText(temp.getKey());
        tv_st_phone.setText(temp.getPhoneNumber());
        return view;
    }
}
