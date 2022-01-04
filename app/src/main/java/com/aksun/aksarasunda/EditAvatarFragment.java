package com.aksun.aksarasunda;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditAvatarFragment extends DialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_avatar, container, false);

        RadioGroup rgAvatar = (RadioGroup) view.findViewById(R.id.rgAvatar);
        RadioButton laki1Button = (RadioButton) view.findViewById(R.id.radio0);
        RadioButton laki2Button = (RadioButton) view.findViewById(R.id.radio1);
        RadioButton pr1Button = (RadioButton) view.findViewById(R.id.radio2);
        RadioButton pr2Button = (RadioButton) view.findViewById(R.id.radio3);

        Button cancelButton = view.findViewById(R.id.buttonKembali);
        Button saveButton = view.findViewById(R.id.buttonPilih);

        String emailCheck = currentUser.getEmail();
        // Memanggil document menggunakan email sebagai referensi
        DocumentReference docRef = db.collection("users").document(emailCheck);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String avatar = (String) document.get("avatar");
                        if (avatar.equals("laki1")){
                            laki1Button.isChecked();
                        }
                        if (avatar.equals("laki2")){
                            laki2Button.isChecked();
                        }
                        if(avatar.equals("pr1")){
                            pr1Button.isChecked();
                        }
                        if(avatar.equals("pr2")){
                            pr2Button.isChecked();
                        }
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Profil berhasil diubah", Toast.LENGTH_LONG).show();
                String avatar = "";

                if (laki1Button.isChecked()) {
                    avatar = "laki1";
                }
                if (laki2Button.isChecked()) {
                    avatar = "laki2";
                }
                if (pr1Button.isChecked()) {
                    avatar = "pr1";
                }
                if (pr2Button.isChecked()) {
                    avatar = "pr2";
                }

                DocumentReference docRef = db.collection("users").document(emailCheck);
                docRef
                        .update("avatar", avatar)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });
                getDialog().dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return view;
    }
}