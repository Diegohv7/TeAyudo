package com.example.teayudo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teayudo.R;

public class SingUpActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSingUp;
    TextView tvGoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        getSupportActionBar().hide();

        btnSingUp=findViewById(R.id.buttonSingUp);
        tvGoLogin=findViewById(R.id.textViewGoLogin);

        btnSingUp.setOnClickListener(this);
        tvGoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.buttonSingUp:
                break;
            case R.id.textViewGoLogin:
                goToLogin();
                break;
        }
    }

    private void goToLogin() {
        Intent i= new Intent(SingUpActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}