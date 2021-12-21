package com.aksun.aksarasunda;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends Fragment {

    CardView klikNgalagena, klikSwara, klikRarangken, klikAngka;
    Button lihatSemua;
//    ImageButton buttonTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        klikNgalagena = view.findViewById(R.id.ngalagena);
        klikSwara = view.findViewById(R.id.swara);
        klikAngka = view.findViewById(R.id.angka);
        klikRarangken = view.findViewById(R.id.rarangken);
        lihatSemua = view.findViewById(R.id.buttonLihatSemua);

        lihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new MateriFragment());
                fragmentTransaction.commit();
            }
        });

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

        // Inflate the layout for this fragment
        return view;
    }

//    public void ngalagena (View view){
//        Intent intent = new Intent(getActivity(), AksaraNgalagenaActivity.class);
//        startActivity(intent);
//    }

//    public void swara (View view){
//        Intent intent = new Intent(getActivity(), AksaraSwaraActivity.class);
//        startActivity(intent);
//    }
//
//    public void angka (View view){

//    }
//
//    public void rarangken (View view){
//        Intent intent = new Intent(getActivity(), AksaraRarangkenActivity.class);
//        startActivity(intent);
//    }
//
//    public void pungtuasi (View view){
//        Intent intent = new Intent(getActivity(), PungtuasiActivity.class);
//        startActivity(intent);
//    }
//
////    public void soalpg (View view){
////        Intent intent = new Intent(getActivity(), soalpg.class);
////        startActivity(intent);
////    }
////
////    public void soalgambar (View view){
////        Intent intent = new Intent(getActivity(), soalgambar.class);
////        startActivity(intent);
////    }
//
//    public void konversi (View view){
//        Intent intent = new Intent(getActivity(), KonversiAksaraActivity.class);
//        startActivity(intent);
//    }

}