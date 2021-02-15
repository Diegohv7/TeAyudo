package com.example.teayudo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teayudo.R;
import com.example.teayudo.retrofit.TeAyudoClient;
import com.example.teayudo.retrofit.TeAyudoService;
import com.example.teayudo.retrofit.request.RequestLogin;
import com.example.teayudo.retrofit.response.ResponseAuth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView tvGoSingUp;
    EditText etEmail, etPassword;
    TeAyudoClient teAyudoClient;
    TeAyudoService teAyudoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        retrofitInit();
        findViews();
        events();
    }

    private void retrofitInit() {
        teAyudoClient = TeAyudoClient.getInstance();
        teAyudoService = teAyudoClient.getTeayudoService();
    }

    private void findViews() {
        btnLogin=findViewById(R.id.buttonLogin);
        tvGoSingUp=findViewById(R.id.textViewGoSingup);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
    }

    private void events() {
        btnLogin.setOnClickListener(this);
        tvGoSingUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.buttonLogin:
                goToLogin();
                break;
            case R.id.textViewGoSingup:
                goToSingUp();
                break;
        }
    }

    private void goToLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.isEmpty()) {
            etEmail.setError("El email es requerido");
        }else if (password.isEmpty()){
            etPassword.setError("La contraseña es requerida");
        } else{
            RequestLogin requestLogin = new RequestLogin(email, password);

            Call<ResponseAuth> call = teAyudoService.doLogin(requestLogin);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sesión Iniciada Correctamente", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, DashboardActivity.class ); //ERROR AL CREAR LA CLASE DASHBOARD
                        startActivity(i);

                            //Destruir este Activity para que ya no aparezca el login.
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Algo salió mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problemas de conexión. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void goToSingUp() {
        Intent i= new Intent(MainActivity.this, SingUpActivity.class);
        startActivity(i);
    }
}