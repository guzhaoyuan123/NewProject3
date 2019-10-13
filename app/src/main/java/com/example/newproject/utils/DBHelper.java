package com.example.newproject.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.newproject.entity.Student;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "demo.db-wal", null, 2);
    }
    // 当app发现没有demo.db时会自动调用onCreate创建数据库表


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.TBL_STUDENT);
    }
    // 当app发现有demo.db时，而且version有变化时会自动调用onUpgrade更新数据库表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists t_student_info");

        onCreate(db);
    }
}

