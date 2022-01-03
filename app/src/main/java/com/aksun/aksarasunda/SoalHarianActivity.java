package com.aksun.aksarasunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SoalHarianActivity extends AppCompatActivity {

    TextView kuis;
    RadioGroup rg;
    RadioButton PilihanA, PilihanB, PilihanC;
    Button next;
    int nomor = 0;
    public static int hasil, benar, salah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_harian);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        String noDocument = intent.getStringExtra("nomorDoc");

        DocumentReference docRef = db.collection("soal").document(noDocument);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<String> soal = (List<String>) document.get("soal");
                        List<String> pilihan = (List<String>) document.get("pilihan");
                        List<String> jawaban = (List<String>) document.get("jawaban");

                        kuis = (TextView) findViewById(R.id.kuis);
                        rg = (RadioGroup) findViewById(R.id.pilihan);
                        PilihanA = (RadioButton) findViewById(R.id.pilihanA);
                        PilihanB = (RadioButton) findViewById(R.id.pilihanB);
                        PilihanC = (RadioButton) findViewById(R.id.pilihanC);
                        next = (Button) findViewById(R.id.next);

                        rg.check(0);
                        benar = 0;
                        salah = 0;

                        kuis.setText(soal.get(nomor));
                        PilihanA.setText(pilihan.get(0));
                        PilihanB.setText(pilihan.get(1));
                        PilihanC.setText(pilihan.get(2));

                        next.setOnClickListener(v -> {
                            if (PilihanA.isChecked() || PilihanB.isChecked() || PilihanC.isChecked()) {

                                RadioButton jawaban_user = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                                String ambil_jawaban_user = jawaban_user.getText().toString();
                                rg.check(0);
                                if (ambil_jawaban_user.equalsIgnoreCase(jawaban.get(nomor))) {
                                    benar++;
                                } else {
                                    salah++;
                                }
                                nomor++;
                                if (nomor < soal.size()) {
                                    kuis.setText(soal.get(nomor));
                                    PilihanA.setText(pilihan.get((nomor * 3) + 0));
                                    PilihanB.setText(pilihan.get((nomor * 3) + 1));
                                    PilihanC.setText(pilihan.get((nomor * 3) + 2));

                                } else {
                                    hasil = benar * 20;
                //                    Intent selesai = new Intent(getApplicationContext(), hasilsoalpg.class);
                //                    startActivity(selesai);
//                                    Toast.makeText(getApplicationContext(),"Kamu Jawab Dulu"+hasil,Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Kamu Jawab Dulu",Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Tidak mendapatkan data", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Gagal menjalankan query", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}