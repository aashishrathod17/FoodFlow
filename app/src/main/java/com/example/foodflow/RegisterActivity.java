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

public class RegisterActivity extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;

    Button btnRegister;
    TextView tvLogin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        // =========================
        // FIND VIEWS
        // =========================

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // =========================
        // FIREBASE AUTH
        // =========================

        firebaseAuth = FirebaseAuth.getInstance();

        // =========================
        // REGISTER
        // =========================

        btnRegister.setOnClickListener(v -> registerUser());

        // =========================
        // LOGIN
        // =========================

        tvLogin.setOnClickListener(v -> finish());
    }

    private void registerUser() {

        String name = etName.getText()
                .toString()
                .trim();

        String email = etEmail.getText()
                .toString()
                .trim();

        String password = etPassword.getText()
                .toString()
                .trim();

        String confirmPassword = etConfirmPassword
                .getText()
                .toString()
                .trim();

        // =========================
        // VALIDATION
        // =========================

        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter your name");
            etName.requestFocus();
            return;
        }

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

        if (password.length() < 6) {
            etPassword.setError(
                    "Password must be at least 6 characters"
            );
            etPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError(
                    "Passwords do not match"
            );
            etConfirmPassword.requestFocus();
            return;
        }

        // =========================
        // FIREBASE REGISTER
        // =========================

        btnRegister.setEnabled(false);

        firebaseAuth
                .createUserWithEmailAndPassword(
                        email,
                        password
                )
                .addOnSuccessListener(authResult -> {

                    Toast.makeText(
                            RegisterActivity.this,
                            "Registration successful",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(
                            RegisterActivity.this,
                            MainActivity.class
                    );

                    startActivity(intent);

                    finish();
                })
                .addOnFailureListener(e -> {

                    btnRegister.setEnabled(true);

                    Toast.makeText(
                            RegisterActivity.this,
                            "Registration failed: "
                                    + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                });
    }
}