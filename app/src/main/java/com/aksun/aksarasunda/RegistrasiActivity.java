package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrasiActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnBA;
    private TextInputEditText emailInput, passInput, fullNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnBA = (Button) findViewById(R.id.buttonBuatAkun);
        emailInput = (TextInputEditText) findViewById(R.id.email);
        passInput = (TextInputEditText) findViewById(R.id.password);
        fullNameInput = (TextInputEditText) findViewById(R.id.fullName);

        mAuth = FirebaseAuth.getInstance();

        btnBA.setOnClickListener(v -> {
            if (emailInput.getText().toString().isEmpty() || passInput.getText().toString().isEmpty() || fullNameInput.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Masukan data yang diperlukan terlebih dahulu", Toast.LENGTH_LONG).show();
            }
            else{
                registerNewUser();
            }
        });
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
        String email, password, fullName;
        email = emailInput.getText().toString();
        password = passInput.getText().toString();
        fullName = fullNameInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrasiActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrasiActivity.this, "Registrasi berhasil!", Toast.LENGTH_LONG).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String, Object> user = new HashMap<>();
                            user.put("email", email);
                            user.put("fullName", fullName);
                            db.collection("users").document(email)
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });

                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            Intent intent = new Intent(RegistrasiActivity.this, PilihAvatarActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistrasiActivity.this, "Registrasi gagal! Silahkan coba kembali nanti", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
