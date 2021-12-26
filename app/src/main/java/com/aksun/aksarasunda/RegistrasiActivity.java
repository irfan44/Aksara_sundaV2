package com.aksun.aksarasunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrasiActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnBA;
    private TextInputEditText emailInput, passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnBA = (Button) findViewById(R.id.buttonBuatAkun);
        emailInput = (TextInputEditText) findViewById(R.id.email);
        passInput = (TextInputEditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        btnBA.setOnClickListener(v -> registerNewUser());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(getApplicationContext(), "Kamu sudah masuk, silahkan ulang aplikasi", Toast.LENGTH_LONG).show();
        }
    }

    private void registerNewUser() {
        String email, password;
        email = emailInput.getText().toString();
        password = passInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrasiActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrasiActivity.this, "Registrasi berhasil!", Toast.LENGTH_LONG).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(RegistrasiActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrasiActivity.this, "Registrasi gagal! Silahkan coba kembali nanti", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
