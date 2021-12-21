package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLogin, btnReg;
    private TextInputEditText emailInput, passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnReg = (Button) findViewById(R.id.buttonRegistrasi);
        emailInput = (TextInputEditText) findViewById(R.id.email);
        passInput = (TextInputEditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> loginUserAccount());
        btnReg.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrasiActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(), "Kamu sudah masuk, silahkan ulang aplikasi", Toast.LENGTH_LONG).show();
    }

    private void loginUserAccount() {

        String email, password;
        email = emailInput.getText().toString();
        password = passInput.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Masuk berhasil!", Toast.LENGTH_LONG).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Log.d("EmailPassword", "signInWithEmail:success");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Gagal masuk! Silahkan coba kembali nanti", Toast.LENGTH_LONG).show();
                        Log.w("EmailPassword", "signInWithEmail:failure", task.getException());
                    }
                    }
                });
    }
}
