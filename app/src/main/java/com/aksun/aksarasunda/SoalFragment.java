package com.aksun.aksarasunda;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SoalFragment extends Fragment {

    CardView klikSoalHarian, klikTebakGambar;
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soal, container, false);
        klikSoalHarian = view.findViewById(R.id.soalHarian);
        klikTebakGambar = view.findViewById(R.id.tebakGambar);

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

        return view;
    }
}