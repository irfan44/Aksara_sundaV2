package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    Button signOut;
    ImageButton fotoProfil;
    TextView emailView, fullNameView;
    ImageButton editButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        signOut = view.findViewById(R.id.keluar);
        fotoProfil = view.findViewById(R.id.fotoProfil); //masi belum kepanggil ini, ntar benerin coba
        emailView = view.findViewById(R.id.email);
        fullNameView = view.findViewById(R.id.fullName);
        editButton = view.findViewById(R.id.editButton);

        if (currentUser != null){
            String emailCheck = currentUser.getEmail();
            // Memanggil document menggunakan email sebagai referensi
            DocumentReference docRef = db.collection("users").document(emailCheck);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String fullName = (String) document.get("fullName");
                            String email = (String) document.get("email");
                            String avatar = (String) document.get("avatar");
                            switch(avatar){
                                case "laki1":
                                    fotoProfil.setImageResource(R.drawable.laki1);
                                    break;
                                case "laki2":
                                    fotoProfil.setImageResource(R.drawable.laki2);
                                    break;
                                case "pr1":
                                    fotoProfil.setImageResource(R.drawable.pr1);
                                    break;
                                case "pr2":
                                    fotoProfil.setImageResource(R.drawable.pr2);
                                    break;
                            }

                            fullNameView.setText(fullName);
                            emailView.setText(email);

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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                EditProfilFragment dialog = new EditProfilFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "EditProfilFragment");
            }
        });

        fotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                EditAvatarFragment dialog = new EditAvatarFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "EditAvatarFragment");
            }
        });



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Berhasil Keluar!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}