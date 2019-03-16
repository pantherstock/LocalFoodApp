package com.example.localfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText email, password, cpassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.email_register);
        password = (EditText) findViewById(R.id.password_register);
        cpassword = (EditText) findViewById(R.id.cpassword_register);
        registerBtn = (Button) findViewById(R.id.button_register);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s_email = email.getText().toString();
                String s_password = password.getText().toString();
                String s_cpassword = cpassword.getText().toString();

                if (s_email.equals("")||s_password.equals("")||s_cpassword.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (s_password.equals(s_cpassword)) {
                        Boolean checkemail = db.checkemail(s_email);
                        if (checkemail == true) {
                            Boolean insert = db.insert(s_email, s_password);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginPage();
    }

    /**
     * Click button to go to Registration page
     */
    private void loginPage(){
        Button loginBtn = findViewById(R.id.register_to_login);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
