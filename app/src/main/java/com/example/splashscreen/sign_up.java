package com.example.splashscreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    private Button log;
    private Button signup;
    private EditText name;
    private EditText mail;
    private EditText pass;
    public static String username;
    public static String email;
    public static String pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        connectView();
    }
    private void connectView() {
        log = (Button) findViewById(R.id.log);
        signup = (Button) findViewById(R.id.signup);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
        log.setOnClickListener(new View.OnClickListener() {
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
        username = name.getText().toString().trim();
        email = mail.getText().toString().trim();
        pw = pass.getText().toString().trim();
    }

}

