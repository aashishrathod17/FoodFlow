package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;

    Button btnLogin;
    TextView tvRegister;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> loginUser());

        tvRegister.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }

    private void loginUser() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Enter email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            return;
        }

        btnLogin.setEnabled(false);

        firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {

                    Toast.makeText(
                            LoginActivity.this,
                            "Login successful",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(
                            LoginActivity.this,
                            MainActivity.class
                    );

                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {

                    btnLogin.setEnabled(true);

                    Toast.makeText(
                            LoginActivity.this,
                            "Login failed: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                });
    }
}