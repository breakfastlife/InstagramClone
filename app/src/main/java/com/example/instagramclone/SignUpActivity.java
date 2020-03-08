package com.example.instagramclone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    private EditText etUser_Sign;
    private EditText etPassword_Sign;
    private EditText etEmail_Sign;
    private EditText etConfirmPassword;
    private TextView textView;
    private Button btnSignUp_Sign;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        etUser_Sign = findViewById(R.id.etUser_Sign);
        etPassword_Sign = findViewById(R.id.etPassword_Sign);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etEmail_Sign = findViewById(R.id.etEmail_Sign);
        btnSignUp_Sign = findViewById(R.id.btnSignUp_Sign);
        textView =findViewById(R.id.textView);
        Log.i(TAG, "Creating Sing Up view");

        /*etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (etConfirmPassword.getText() != etPassword_Sign.getText())){
                    textView.setVisibility(1);
                }
                if(hasFocus){
                    textView.setVisibility(0);
                }
            }
        });*/

        btnSignUp_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "btnSignUp");
                Log.i(TAG, "User attempting to new sign up");
                String UserName = etUser_Sign.getText().toString();
                String Password = etPassword_Sign.getText().toString();
                String Email = etEmail_Sign.getText().toString();
                SignUpUser(UserName, Password, Email);
            }
        });
    }

    private void SignUpUser(final String userName, final String password, String email) {
        Log.i(TAG, "User: " + userName);
        ParseUser user = new ParseUser();

        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    //user logged in
                    Log.i(TAG, "User singed up successfully");
                    LogInUser(userName, password);
                }
                else{
                    //sign in unsuccessful
                    Log.e(TAG, "User not singed up", e);
                }
            }
        });

    }

    private void LogInUser(String userName, String password) {
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null) {
                    Log.i(TAG, "User: " + ParseUser.getCurrentUser().getUsername() + " Sign in Successful");
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
        Log.i(TAG, "sending User " + ParseUser.getCurrentUser().getUsername() + " to the main activity!");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
