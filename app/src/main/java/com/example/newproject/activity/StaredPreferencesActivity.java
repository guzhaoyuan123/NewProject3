package com.example.newproject.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newproject.R;


public class StaredPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvShow;
    private Button btnLogin;
    private EditText etUsername,etPassword;
    private CheckBox cxRember;
    //
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stared_preferences);

        tvShow = findViewById(R.id.tv_show);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        cxRember = findViewById(R.id.cx_rember);

        btnLogin.setOnClickListener(this);
        //
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //
        etUsername.setText(mSharedPreferences.getString("password",""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (cxRember.isChecked()) {
                    //
                    mEditor.putString("password",etUsername.getText().toString());
                    mEditor.apply();
                    tvShow.setText(mSharedPreferences.getString("password",""));
                }
                else {
                }
                break;
        }
    }
}
