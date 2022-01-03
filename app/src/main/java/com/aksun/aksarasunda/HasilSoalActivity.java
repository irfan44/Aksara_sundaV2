package com.aksun.aksarasunda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HasilSoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_soal);

        TextView hasil = (TextView)findViewById(R.id.hasil);
        TextView nilai = (TextView)findViewById(R.id.nilai);

        Button selesaiButton = (Button) findViewById(R.id.buttonSelesai);

        hasil.setText("Jawaban Benar :"+SoalHarianActivity.benar+"\nJawaban Salah :"+SoalHarianActivity.salah);
        nilai.setText(""+SoalHarianActivity.hasil);

        selesaiButton.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(HasilSoalActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

}