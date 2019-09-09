package com.example.newproject.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newproject.R;
import com.example.newproject.entity.Student;

import java.util.List;


public class StudentAdapter extends BaseAdapter {

    private int selectItem=-2;
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student, viewGroup, false);


        Student student = students.get(i);
            TextView studentname = view.findViewById(R.id.tv_name);
            TextView studentclassmate = view.findViewById(R.id.tv_classmate);
            TextView studentage = view.findViewById(R.id.tv_age);


        studentname.setText(student.getName());
        studentclassmate.setText(student.getClassmate());
        studentage.setText(student.getAge());


        //如果位置相同则设置背景为黄色
        if (i == selectItem) {
            view.setBackgroundColor(Color.YELLOW);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return view;

    }
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
}