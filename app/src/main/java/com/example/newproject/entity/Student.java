package com.example.newproject.entity;

import java.io.Serializable;

public class Student implements Serializable {
    public static final String TBL_STUDENT= "create table if not exists t_student_info(" +
            "id integer primary key autoincrement, " +
            "student_name varchar(100)," +
            "student_classmate varchar(100)," +
            "student_age varchar(100))";


    private int id;
    private String name;
    private String classmate;
    private String age;


    public Student(){
    }

    public Student(int id, String name, String classmate, String age) {
        this.id = id;
        this.name = name;
        this.classmate = classmate;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassmate() {
        return classmate;
    }

    public void setClassmate(String classmate) {
        this.classmate = classmate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classmate='" + classmate + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
