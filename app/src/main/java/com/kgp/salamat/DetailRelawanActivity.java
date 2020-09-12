package com.kgp.salamat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailRelawanActivity extends AppCompatActivity {

    TextView tv_namaRelawan;
    EditText et_idRelawan, et_alamat, et_nohp, et_email, et_tps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_relawan);

        tv_namaRelawan = findViewById(R.id.tvDetail_NamaRelawan);
        et_idRelawan = findViewById(R.id.etDetail_id);
        et_alamat = findViewById(R.id.etDetail_Alamat);
        et_email = findViewById(R.id.etDetail_email);
        et_nohp = findViewById(R.id.etDetail_nohp);
        et_tps = findViewById(R.id.etDetail_tps);

        String idrelawan = getIntent().getExtras().getString("id_relawan");
        String namarelawan = getIntent().getExtras().getString("nama_lengkap");
        String alamat = getIntent().getExtras().getString("alamat");
        String email = getIntent().getExtras().getString("email");
        String nohp = getIntent().getExtras().getString("no_hp");
        String tps = getIntent().getExtras().getString("tps");

        tv_namaRelawan.setText(namarelawan);
        et_idRelawan.setText(idrelawan);
        et_alamat.setText(alamat);
        et_nohp.setText(nohp);
        et_email.setText(email);
        et_tps.setText(tps);
    }
}