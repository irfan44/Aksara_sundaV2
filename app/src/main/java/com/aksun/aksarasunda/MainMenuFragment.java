package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainMenuFragment extends Fragment {

    CardView klikNgalagena, klikSwara, klikRarangken, klikAngka, klikPungtuasi, klikSoalHarian, klikTebakGambar, klikKonversi;
    Button lihatSemua;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

//    ImageButton buttonTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        TextView selamatDatang = (TextView) view.findViewById(R.id.textView);
        if (currentUser != null){
            String email = currentUser.getEmail();
            // Memanggil document menggunakan email sebagai referensi
            DocumentReference docRef = db.collection("users").document(email);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = (String) document.get("fullName");
                            selamatDatang.setText("Wilujeng\nSumping,\n" + name + "!");
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

        klikNgalagena = view.findViewById(R.id.ngalagena);
        klikSwara = view.findViewById(R.id.swara);
        klikAngka = view.findViewById(R.id.angka);
        klikRarangken = view.findViewById(R.id.rarangken);
        klikPungtuasi = view.findViewById(R.id.pungtuasi);
        klikSoalHarian = view.findViewById(R.id.soal);
        klikTebakGambar = view.findViewById(R.id.tebakgambar);
        klikKonversi = view.findViewById(R.id.konversi);

        klikNgalagena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AksaraNgalagenaActivity.class);
                startActivity(intent);
            }
        });

        klikSwara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AksaraSwaraActivity.class);
                startActivity(intent);
            }
        });

        klikAngka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AksaraAngkaActivity.class);
                startActivity(intent);
            }
        });

        klikRarangken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AksaraRarangkenActivity.class);
                startActivity(intent);
            }
        });

        klikPungtuasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PungtuasiActivity.class);
                startActivity(intent);
            }
        });

        klikSoalHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PilihanSoalActivity.class);
                startActivity(intent);
            }
        });

        klikTebakGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TebakGambarActivity.class);
                startActivity(intent);
            }
        });

        klikKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KonversiAksaraActivity.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}