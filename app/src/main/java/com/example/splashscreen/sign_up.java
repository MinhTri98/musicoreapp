package com.example.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    DatabaseHelper db;
    private Button log, signup;
    private EditText name, mail, pass;
    public static String username,email, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        connectView();
    }
    private void connectView() {
        log = (Button) findViewById(R.id.log);
        db = new DatabaseHelper(this);
        signup = (Button) findViewById(R.id.signup);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignup();
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLoginnow();
            }
        });
    }
    private void doLoginnow() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    private void doSignup() {
        username = name.getText().toString();
        email = mail.getText().toString();
        pw = pass.getText().toString();
        if (username.equals("") || mail.equals("")){
            Toast.makeText(getApplicationContext(),"Xin mời nhập đầy đủ",Toast.LENGTH_SHORT).show();
        }
        else {
            boolean chkname_email = db.chekname_email(username,email);
            if (chkname_email == true ){
                Boolean insert = db.insert(username,email,pw);
                if (insert == true){
                    Toast.makeText(getApplicationContext(),"Đăng ký thành công!!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Giá trị đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

