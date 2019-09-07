package com.example.newproject.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.newproject.entity.Student;
import com.example.newproject.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public StudentDaoImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public List<Student> selectAllStudents() {
        String sql = "select * from t_student_info";
        List<Student> students = null;

        // 1. 获取SQLiteDatabase对象
        db = dbHelper.getReadableDatabase();

        // 2. 执行SQL查询
        // Cursor cursor = db.query(Student.TBL_NAME, null, null, null, null, null, null);
        Cursor cursor = db.rawQuery(sql, null);

        // 3. 处理结果
        if (cursor != null && cursor.getCount() > 0) {
            students = new ArrayList<>();
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex("id")));
                student.setName(cursor.getString(cursor.getColumnIndex("student_name")));
                student.setClassmate(cursor.getString(cursor.getColumnIndex("student_classmate")));
                student.setAge(cursor.getString(cursor.getColumnIndex("student_age")));

                students.add(student);
            }
            // 4. 关闭cursor
            cursor.close();
        }
        db.close();
        // 5. 返回结果
        return students;
    }

    @Override
    public void insert(Student student) {
        db = dbHelper.getWritableDatabase();
        String sql = "insert into t_student_info values(null,?,?,?)";
        db.execSQL(sql, new Object[]{
                student.getName(),
                student.getClassmate(),
                student.getAge()});
        db.close();
    }

    @Override
    public void update(Student student) {
        // 1. 获取db对象
        db = dbHelper.getWritableDatabase();
        // 2. 执行sql
        String sql = "update t_student_info set student_name=? , student_classmate=? ,student_age=? where id=?";
        db.execSQL(sql, new Object[]{
                student.getName(),
                student.getClassmate(),
                student.getAge(),
                student.getId()
        });
    }

    @Override
    public void delete(String studentName) {
        // 1. 获取db对象
        db = dbHelper.getWritableDatabase();
        // 2. 执行sql
        String sql = "delete from t_student_info where student_name=?";
        db.execSQL(sql, new Object[]{ studentName });
    }
}
