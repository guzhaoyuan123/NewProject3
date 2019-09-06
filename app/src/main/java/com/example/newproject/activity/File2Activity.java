package com.example.newproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newproject.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

public class File2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvShow;
    private Button btnLogin;
    private EditText etUsername,etPassword;
    private CheckBox cxRember;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file2);

        tvShow = findViewById(R.id.tv_show);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        cxRember = findViewById(R.id.cx_rember);
        btnLogin.setOnClickListener(this);

        //
        etUsername.setText(read());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (cxRember.isChecked()) {
                    save(etUsername.getText().toString());
                    tvShow.setText(read());
                }
                else {
                    tvShow.setText("请勾选记住我");
                }
                break;
        }
    }


    private void save(String password){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("text2.txt", MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String read(){
        FileInputStream fileInputStream = null;
        String data=null;
        try {
            fileInputStream = openFileInput("text2.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            data= reader.readLine();
            reader.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
