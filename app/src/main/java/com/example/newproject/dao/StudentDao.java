package com.example.newproject.dao;


import com.example.newproject.entity.Student;

import java.util.List;

public interface StudentDao {

    // 查询所有的学生信息
    List<Student> selectAllStudents();
    // 增删改一个学生
    void insert(Student student);
    void update(Student student);
    void delete(String studentName);

}
