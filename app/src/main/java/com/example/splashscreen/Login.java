package com.example.splashscreen;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Thread.sleep;

public class Login extends AppCompatActivity {
    private Button Blogin;
    private Button Bsign;
    private EditText idtext;
    private EditText passtext;
    private String id="admin";
    private String pass="123";
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectView();
    }
    private void connectView() {
        Bsign = (Button) findViewById(R.id.Bsign);
        Blogin = (Button) findViewById(R.id.Blogin);
        idtext = (EditText) findViewById(R.id.idtext);
        passtext = (EditText) findViewById(R.id.passtext);
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
        String id1 = idtext.getText().toString().trim();
        String pass1 = passtext.getText().toString().trim();
        if(id1.equals(id) && pass1.equals(pass)){
            showDialog();
            Intent intent = new Intent(this,tab.class);
            startActivity(intent);
        }

    }
    public void showDialog() {
        dialog = new Dialog(Login.this);
        dialog.setTitle("Musicore");
        dialog.setContentView(R.layout.dialog);
        dialog.show();
    }
}
