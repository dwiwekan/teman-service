package com.example.mod5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mod5.layanan.APIDialog;
import com.example.mod5.layanan.Layanan;
import com.example.mod5.layanan.LayananAdapter;
import com.example.mod5.layanan.LayananDatabase;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListLayananActivity extends AppCompatActivity {
    private TextView title;
    RecyclerView recyclerLayanan;
    com.example.mod5.layanan.LayananDatabase layananDatabase;
    LayananAdapter layananAdapter;
    List<Layanan> list = new ArrayList<>();
    APIDialog apiDialog;
    private Call<List<Layanan>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layanan);
        recyclerLayanan = findViewById(R.id.layananRecycler);
        title=findViewById(R.id.judul);

        Retrofit retrofit= new Retrofit.Builder().baseUrl(APIDialog.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        apiDialog=retrofit.create(APIDialog.class);

        int kodeLayanan=getIntent().getIntExtra("kode_layanan",0);
        if(kodeLayanan==1){
            call=apiDialog.getLayananByKode(1);
            title.setText("Polisi");
        }else{
            call=apiDialog.getLayananByKode(2);
            title.setText("Pemadam Kebakaran");
        }

        recyclerLayanan.setLayoutManager(new LinearLayoutManager(this));
        call.enqueue(new Callback<List<Layanan>>() {
            //Try catch
            //Assertion  Error
            @Override
            public void onResponse(Call<List<Layanan>> call, Response<List<Layanan>> response) {
                if(!response.isSuccessful()){
                    Log.d("dewa27",String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), "Code : " + Integer.toString(response.code()), Toast.LENGTH_LONG).show();
                    return;
                }
                list=response.body();
                layananAdapter = new LayananAdapter(getApplicationContext(),list);
                recyclerLayanan.setAdapter(layananAdapter);

            }

            @Override
            public void onFailure(Call<List<Layanan>> call, Throwable t) {
                Log.d("api",t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                list = layananDatabase.LayananDao().getAllLayanan();
                layananAdapter = new LayananAdapter(getApplicationContext(),list);
                recyclerLayanan.setAdapter(layananAdapter);
            }
        });


        layananDatabase = LayananDatabase.getDbInstance(getApplicationContext());
        list.clear();

        //LIST DI DAPAT DARI SQLITE DATABASE
//        list = layananDatabase.LayananDao().getAllLayanan();
//        layananAdapter = new LayananAdapter(getApplicationContext(),list);

//        increaseData();

//        recyclerLayanan = findViewById(R.id.layananRecycler);
//        recyclerLayanan.setLayoutManager(new LinearLayoutManager(this));
//        recyclerLayanan.setAdapter(layananAdapter);



    }

    private void increaseData(){

        Layanan layanan = new Layanan();
        layanan.setNamaTempat("Mamank");

        layananDatabase.LayananDao().insertLayanan(layanan);
    }
}