package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PilihAvatarActivity extends AppCompatActivity {

    private String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_avatar);

        RadioGroup rgAvatar = (RadioGroup) findViewById(R.id.rgAvatar);
        RadioButton laki1Button = (RadioButton) findViewById(R.id.radio0);
        RadioButton laki2Button = (RadioButton) findViewById(R.id.radio1);
        RadioButton pr1Button = (RadioButton) findViewById(R.id.radio2);
        RadioButton pr2Button = (RadioButton) findViewById(R.id.radio3);

        Button pilihButton = (Button) findViewById(R.id.buttonPilih);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        pilihButton.setOnClickListener(v -> {
            if (laki1Button.isChecked()){
                avatar = "laki1";
            }
            if (laki2Button.isChecked()){
                avatar = "laki2";
            }
            if (pr1Button.isChecked()){
                avatar = "pr1";
            }
            if (pr2Button.isChecked()){
                avatar = "pr2";
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String, Object> user = new HashMap<>();
            user.put("avatar", avatar);
            db.collection("users").document(email)
                    .set(user, SetOptions.merge())
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

            Intent intent2 = new Intent(PilihAvatarActivity.this, MainActivity.class);
            startActivity(intent2);
        });

    }
}