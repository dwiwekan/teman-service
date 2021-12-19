package com.example.mod5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mod5.layanan.DownloadImageHelper;
import com.example.mod5.layanan.Layanan;
import com.google.gson.Gson;

public class DetailLayananActivity extends AppCompatActivity {
    private TextView tv_nama_tempat,tv_deskripsi,tv_phone,tv_alamat;
    private ImageView imgFotoTempat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);
        tv_nama_tempat=findViewById(R.id.tvNamaTempat);
        tv_deskripsi=findViewById(R.id.tvDeskripsi);
        tv_phone=findViewById(R.id.tvNoTelp);
        tv_alamat=findViewById(R.id.tvAlamat);
        imgFotoTempat=findViewById(R.id.imgFotoTempat);
        Gson gson=new Gson();
        String jsonFoodModel= getIntent().getStringExtra("layanan_model");
        Layanan layananModel=gson.fromJson(jsonFoodModel, Layanan.class);

        tv_nama_tempat.setText(layananModel.getNamaTempat());
        tv_deskripsi.setText(layananModel.getDeskripsi());
        tv_alamat.setText(layananModel.getAlamat());
        tv_phone.setText(layananModel.getNoTelp());
        new DownloadImageHelper((ImageView)imgFotoTempat).execute(DownloadImageHelper.imageUrl+layananModel.getFotoTempat());

    }
}