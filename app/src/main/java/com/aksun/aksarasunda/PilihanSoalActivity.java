package com.aksun.aksarasunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class PilihanSoalActivity extends AppCompatActivity {

    int jmlhSoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_soal);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("soal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                            }
                            jmlhSoal = task.getResult().size() + 1;

                            LinearLayout layout = (LinearLayout) findViewById(R.id.pilihSoal);

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                            MaterialButton[] btn = new MaterialButton[jmlhSoal];
                            for (int i = 1; i < jmlhSoal; i++) {
                                btn[i] = new MaterialButton(PilihanSoalActivity.this, null, R.attr.materialButtonOutlinedStyle);
                                btn[i].setText("Soal "+ i);
                                //btn[i].setBackground();
                                btn[i].setTextSize(20);
                                btn[i].setAllCaps(false);
                                btn[i].setCornerRadius(16);
                                btn[i].setStrokeColorResource(R.color.main_menu);
                                btn[i].setStrokeWidth(1);
                                //btn[i].setHeight(100);
                                btn[i].setLayoutParams(param);
                                btn[i].setPadding(15, 20, 15, 20);
                                layout.addView(btn[i]);

                                final int j = i;

                                //btn[i].setOnClickListener(handleOnClick(btn[i]));
                                btn[i].setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        String noDoc = Integer.toString(j);
//                                        Toast.makeText(getApplicationContext(), noDoc, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(PilihanSoalActivity.this, SoalHarianActivity.class);
                                        intent.putExtra("nomorDoc", noDoc);
                                        startActivity(intent);
                                    }
                                });

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal menjalankan query", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}