package com.aksun.aksarasunda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HasilTebakGambarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_tebak_gambar);

        TextView hasil = (TextView)findViewById(R.id.hasil);
        TextView nilai = (TextView)findViewById(R.id.nilai);

        Button ulangiButton = (Button) findViewById(R.id.buttonUlangi);
        Button selesaiButton = (Button) findViewById(R.id.buttonSelesai);

        hasil.setText("Jawaban Benar :"+TebakGambarActivity.benar+"\nJawaban Salah :"+TebakGambarActivity.salah);
        nilai.setText(""+TebakGambarActivity.hasil);

        ulangiButton.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(HasilTebakGambarActivity.this, TebakGambarActivity.class);
            startActivity(intent);
        });

        selesaiButton.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(HasilTebakGambarActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}