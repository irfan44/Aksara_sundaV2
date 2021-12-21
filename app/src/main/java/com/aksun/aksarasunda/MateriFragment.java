package com.aksun.aksarasunda;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MateriFragment extends Fragment {

    CardView klikNgalagena, klikSwara, klikRarangken, klikAngka, klikPungtuasi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_materi, container, false);
        klikNgalagena = view.findViewById(R.id.ngalagena);
        klikSwara = view.findViewById(R.id.swara);
        klikAngka = view.findViewById(R.id.angka);
        klikRarangken = view.findViewById(R.id.rarangken);
        klikPungtuasi = view.findViewById(R.id.pungtuasi);

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

                // Inflate the layout for this fragment
        return view;
    }
}