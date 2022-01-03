package com.aksun.aksarasunda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.LinkedList;

public class TebakGambarActivity extends AppCompatActivity {

    ImageView imageView_tebak;
    EditText editText_jawabb;
    Button button_next;
    LinkedList<String> jawaban;
    LinkedList<Integer>gambar;
    int nomor=0;
    public static  int hasil, benar, salah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak_gambar);

        imageView_tebak = (ImageView)findViewById(R.id.imageView_tebak);
        editText_jawabb = (EditText)findViewById(R.id.editText_jawab);
        button_next = (Button)findViewById(R.id.buttonNext);

        //listJawaban
        jawaban=new LinkedList<String>();
        jawaban.add("Pajajaran");
        jawaban.add("JL. Asia Afrika");
        jawaban.add("Wilujeng Sumping");
        jawaban.add("JL. Braga");
        jawaban.add("JL. Wastukancana");

        //Linked List gambar
        gambar=new LinkedList<Integer>();
        gambar.add(R.drawable.asiaafrika);
        gambar.add(R.drawable.wilujeng1);
        gambar.add(R.drawable.braga);
        gambar.add(R.drawable.wastukancana);

        benar=0;
        salah=0;

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText_jawabb.getText().toString().equals(jawaban.get(nomor)))benar++;
                else salah++;
                nomor++;
                if(nomor < jawaban.size()){
                    imageView_tebak.setImageResource(gambar.get(nomor-1));}
                else{
                    hasil=benar*20;
                    Intent selesai = new Intent(getApplicationContext(), HasilTebakGambarActivity.class);
                    startActivity(selesai);
                }
            }
        });
    }
}