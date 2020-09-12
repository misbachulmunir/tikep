package com.kgp.salamat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kgp.salamat.R;
import com.kgp.salamat.model.InputSuaraItem;
import com.kgp.salamat.model.PaslonItem;
import com.kgp.salamat.model.ResponseInputSuara;
import com.kgp.salamat.service.RetrofitServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputSuaraActivity extends AppCompatActivity {

    EditText et_NIK, et_NamaRelawan, et_IDTPS, et_NamaTPS, et_JumlahSuara, iv_fotobukti;
    TextView tv_NomorPaslon, tv_NamaPaslon;
//    ImageView iv_fotobukti;
    ProgressDialog loading;
    Button btn_kirimdata;

    PaslonItem paslonItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_suara);

        tv_NomorPaslon = findViewById(R.id.tv_NomorPaslonDetail);
        tv_NamaPaslon = findViewById(R.id.tv_NamaPaslonDetail);
        et_NIK = findViewById(R.id.etInput_NIK);
        et_NamaRelawan = findViewById(R.id.etInput_NamaRelawan);
        et_IDTPS = findViewById(R.id.etInput_idtps);
        et_NamaTPS = findViewById(R.id.etInput_NamaTPS);
        et_JumlahSuara = findViewById(R.id.et_inputjumlahsuara);
//        iv_fotobukti = findViewById(R.id.iv_fotobukti);
        iv_fotobukti = findViewById(R.id.etInput_fotobukti);
        btn_kirimdata = findViewById(R.id.btn_kirimsuara);

        btn_kirimdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputsuara();
            }
        });

        String nomerpaslon = getIntent().getExtras().getString("no_paslon");
        String namapaslon = getIntent().getExtras().getString("nama_paslon");
        String nik = getIntent().getExtras().getString("nik");
        String namarelawan = getIntent().getExtras().getString("nama_lengkap");
        String tps = getIntent().getExtras().getString("tps");


        tv_NamaPaslon.setText(namapaslon);
        tv_NomorPaslon.setText(nomerpaslon);
        et_NIK.setText(nik);
        et_NamaRelawan.setText(namarelawan);
        et_NamaTPS.setText(tps);

    }

    private void inputsuara(){
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.setCancelable(false);
        loading.show();

        String nomerpaslon = tv_NomorPaslon.getText().toString();
        String namapaslon = tv_NamaPaslon.getText().toString();
        String nik = et_NIK.getText().toString();
        String namarelawan = et_NamaRelawan.getText().toString();
        String tps = et_IDTPS.getText().toString();
        String namatps = et_NamaTPS.getText().toString();
        String jumlahsuara = et_JumlahSuara.getText().toString();
        String fotobukti = iv_fotobukti.getText().toString();

        try {
            Call<ResponseInputSuara> call = RetrofitServiceApi.getInstance()
                    .getApi()
                    .add(nomerpaslon, namapaslon, nik, namarelawan, tps, namatps, jumlahsuara, "tolol");
            call.enqueue(new Callback<ResponseInputSuara>() {
                @Override
                public void onResponse(Call<ResponseInputSuara> call, Response<ResponseInputSuara> response) {
                    ResponseInputSuara responseInputSuara = new ResponseInputSuara();
                    responseInputSuara = response.body();
                    String status = responseInputSuara.getStatus();
                    if (status.equals("200")){
                        Toast.makeText(InputSuaraActivity.this, "Sukses kirim data", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ResponseInputSuara> call, Throwable t) {

                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Error bos", Toast.LENGTH_SHORT).show();
        }
    }
}
