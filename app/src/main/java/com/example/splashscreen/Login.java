package com.example.splashscreen;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    private Button Blogin,Bsign;
    private EditText txtName, txtPass;
    private Dialog dialog;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectView();
    }
    private void connectView() {
        db = new DatabaseHelper(this);
        Bsign = (Button) findViewById(R.id.Bsign);
        Blogin = (Button) findViewById(R.id.Blogin);
        txtName = (EditText) findViewById(R.id.idtext);
        txtPass = (EditText) findViewById(R.id.passtext);
        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClickButton();
            }
        });
        Bsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignup();
            }
        });
    }
    private void doSignup() {
        Intent intent = new Intent(this,sign_up.class);
        startActivity(intent);
    }
    private void doClickButton() {
        String name = txtName.getText().toString();
        String pass = txtPass.getText().toString();
        Boolean chekname_pass = db.chekname_pass(name,pass);
        if (chekname_pass == true){
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, playSongs.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
    public void showDialog() {
        dialog = new Dialog(Login.this);
        dialog.setTitle("Musicore");
        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }
}
