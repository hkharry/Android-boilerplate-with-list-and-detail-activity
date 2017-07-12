package com.develop.boilerplate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.develop.boilerplate.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       
    }

    public void checkLogin(View view)
    {
        Intent intent=new Intent(this,ListActivity.class);
        startActivity(intent);
        finish();

    }

    public void signUp(View view)
    {
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
