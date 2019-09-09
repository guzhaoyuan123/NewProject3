package com.example.newproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.newproject.R;
import com.example.newproject.dao.StudentDaoImpl;
import com.example.newproject.entity.Student;


public class UpdataActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spCode;
    private EditText etName,etAge;
    private Button btnQue,btnExit;
    private Student student;
    private StudentDaoImpl studentdaoimpl;
    private int flag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);

        studentdaoimpl = new StudentDaoImpl(this);


        spCode = findViewById(R.id.spinner_code);
        etAge = findViewById(R.id.et_age);
        etName = findViewById(R.id.et_name);
        btnQue = findViewById(R.id.btn_que);
        btnExit = findViewById(R.id.btn_exit);

        initData();

        btnQue.setOnClickListener(this);
        btnExit.setOnClickListener(this);


    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            student = (Student) bundle.getSerializable("updata");
            if (student != null) {
                SpinnerAdapter spinnerAdapter  = spCode.getAdapter();
                for (int i=0;i<spinnerAdapter.getCount();i++){
                    if (spinnerAdapter.getItem(i).toString().equals(student.getClassmate())){
                        spCode.setSelection(i);
                    }
                }
                flag1=student.getId();
                etName.setText(student.getName());
//                spCode.setTextAlignment(Integer.parseInt(student.getClassmate()));
                etAge.setText(String.valueOf(student.getAge()));
            }
        }
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
        student.setId(flag1);
        student.setName(etName.getText().toString());
        student.setClassmate(String.valueOf(spCode.getSelectedItem()));
        student.setAge(etAge.getText().toString());

        studentdaoimpl.update(student);


        // 将修改的数据返回MainActivity
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("2", student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}

