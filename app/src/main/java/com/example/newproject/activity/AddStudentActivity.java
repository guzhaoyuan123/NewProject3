package com.example.newproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.newproject.R;
import com.example.newproject.dao.StudentDaoImpl;
import com.example.newproject.entity.Student;


public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spCode;
    private EditText etName,etAge;
    private Button btnQue,btnExit;
    private Student student;
    private StudentDaoImpl studentdaoimpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentdaoimpl = new StudentDaoImpl(this);

        spCode = findViewById(R.id.spinner_code);
        etAge = findViewById(R.id.et_age);
        etName = findViewById(R.id.et_name);
        btnQue = findViewById(R.id.btn_que);
        btnExit = findViewById(R.id.btn_exit);
        btnQue.setOnClickListener(this);
        btnExit.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_que:
                updateStudent();
                break;
            case R.id.btn_exit:
                finish();
                break;
        }
    }

    private void updateStudent() {
        //单独做spinner控件
        spCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //获取填写的值
        if (student == null) {
            student = new Student();
        }
        student.setName(etName.getText().toString());
        student.setClassmate(String.valueOf(spCode.getSelectedItem()));
        student.setAge(etAge.getText().toString());
        studentdaoimpl.insert(student);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("2", student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
