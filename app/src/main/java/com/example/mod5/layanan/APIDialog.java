package com.example.mod5.layanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIDialog {
    public static String baseUrl="http://192.168.43.5:8000/api/";
    @GET("layanan")
    Call<List<Layanan>> getLayanan();

    @GET("layanan/{kode_layanan}")
    Call<List<Layanan>> getLayananByKode(@Path("kode_layanan") int kode_layanan);
}
