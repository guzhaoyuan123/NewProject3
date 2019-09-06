package com.example.newproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.newproject.R;
import com.example.newproject.adapter.StudentAdapter;
import com.example.newproject.dao.StudentDaoImpl;
import com.example.newproject.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD_REQUEST = 103;
    private static final int MODIFY_REQUEST = 104;
    private Button btnAdd,btnUpdata,btnDelete;
    private ListView lvStudent;
    private StudentDaoImpl studentdaoimpl;
    private StudentAdapter studentAdapter;
    private List<Student> students;
    private Student selectedStudent;
    private int selectedPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_activity);

        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdata = findViewById(R.id.btn_update);

        lvStudent = findViewById(R.id.lv_studentlist);
        btnAdd.setOnClickListener(this);
        btnUpdata.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        initData();


        studentAdapter = new StudentAdapter(students);
        lvStudent.setAdapter(studentAdapter);

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                studentAdapter.setSelectItem(i); //自定义的变量，以便让adapter知道要选中哪一项

                selectedPos = i;
                selectedStudent = (Student) adapterView.getItemAtPosition(i);
                studentAdapter.notifyDataSetInvalidated();//提醒数据已经变动
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent intent = new Intent(StudentListActivity.this,AddStudentActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
                break;
            case R.id.btn_update:
                if(selectedPos != 100){
                    Intent intent1 = new Intent(StudentListActivity.this,UpdataActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updata", selectedStudent);
                    intent1.putExtras(bundle);

                    startActivityForResult(intent1, MODIFY_REQUEST);


                }
                break;
            case R.id.btn_delete:
                if (selectedPos != 1000){
                    // 从SQLite数据库的表中删除
                    studentdaoimpl.delete(selectedStudent.getName());
                    // 移除rooms中的数据，并刷新adapter
                    students.remove(selectedPos);
                    studentAdapter.notifyDataSetChanged();
                }

                break;
        }
    }

    private void initData() {
        // 从SQLite数据库获取宿舍列表
        studentdaoimpl = new StudentDaoImpl(this);
        students = studentdaoimpl.selectAllStudents();

        // 若数据库中没数据，则初始化数据列表，防止ListView报错
        if (students == null) {
            students = new ArrayList<>();
        }
    }

    // 接收addActivity的返回的添加或修改后的student对象，更新students，刷新列表
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            // 更新rooms列表
            selectedStudent = (Student) bundle.get("2");
            if (requestCode == MODIFY_REQUEST) {
                students.set(selectedPos, selectedStudent);
            } else if (requestCode == ADD_REQUEST) {
                students.add(selectedStudent);
            }
            // 刷新ListView
            studentAdapter.notifyDataSetChanged();
        }
    }

}

