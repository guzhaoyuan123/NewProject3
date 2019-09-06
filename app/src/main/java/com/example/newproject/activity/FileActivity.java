package com.example.newproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newproject.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvShow;
    private Button btnLogin;
    private EditText etUsername,etPassword;
    private CheckBox cxRember;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

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

                }
                break;
        }
    }

    private void save(String content){
        FileOutputStream fileOutputStream = null ;
        try {
            fileOutputStream = openFileOutput("text.txt",MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String read(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput("text.txt");
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len = 0;
            while ((len = fileInputStream.read(buff))>0){
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

