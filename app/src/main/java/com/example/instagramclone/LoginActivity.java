package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUser_Login;
    private EditText etPassword_Login;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUser_Login = findViewById(R.id.etUser_Login);
        etPassword_Login = findViewById(R.id.etPassword_Login);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User Signing in");
                String UserName = etUser_Login.getText().toString();
                String Password = etPassword_Login.getText().toString();
                LoginUser(UserName, Password);
            }
        });
    }

    private void LoginUser(String userName, String password) {
        Log.i(TAG, "User: " + userName);
        //If Credentials are correct then send to mainactivity
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    Toast.makeText(LoginActivity.this, "User Login Successful", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "User Sign in Successful");
                    goMainActivity();
                }
                else{
                    Log.e(TAG, "User Sign in Unsuccessful", e);
                    return;
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
