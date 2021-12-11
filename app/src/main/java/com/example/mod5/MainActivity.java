package com.example.mod5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final String EXTRA_MESSAGE_NAME = "EXTRA_USER_NAME";

    public void kirim(View view) {
        String name = "Komang";
        Intent i1 = new Intent(getApplicationContext(), MainDashboard.class);
        i1.putExtra("EXTRA_USER_NAME",name);
        startActivity(i1);

    }
}