package com.example.mod5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mod5.layanan.LayananDatabase;
import com.example.mod5.layanan.User;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    ImageButton back;
    LayananDatabase database;
    Button daftar;
    EditText nama,email,alamat,telp,password;
    RadioGroup jk;
    CheckBox cb;
    SeekBar umur;
    TextView us;
    SharedPreferences sp;
    Gson gson=new Gson();
    User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        database = LayananDatabase.getDbInstance(this);
        daftar = findViewById(R.id.button1);
        nama = findViewById(R.id.editNama);
        email = findViewById(R.id.editEmail);
        alamat = findViewById(R.id.editAlamat);
        telp = findViewById(R.id.editNotelp);
        password = findViewById(R.id.password);
        jk = findViewById(R.id.jenisKelamin);
        us = findViewById(R.id.umurText);
        cb = findViewById(R.id.reguler);
        umur = findViewById(R.id.umurSb);
        back = findViewById(R.id.back);
        //biar umur di seekbar kelihatan
        umur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                us.setText(i +" Tahun");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //tombol mundur saja
        back.setOnClickListener(view -> {
            finish();
        });
        daftar.setOnClickListener(view -> {
            //validasi data sebelum register
            if(!cb.isChecked()||
                    nama.getText().toString().isEmpty()||
                    email.getText().toString().isEmpty()||
                    alamat.getText().toString().isEmpty()||
                    telp.getText().toString().isEmpty()||
                    password.getText().toString().isEmpty()){
                Toast.makeText(this, "Lengkapi form!",
                        Toast.LENGTH_LONG).show();
            }else{
                User user = new User();
                user.nama = nama.getText().toString();
                user.email = email.getText().toString();
                user.alamat = alamat.getText().toString();
                user.telp = telp.getText().toString();
                user.password = password.getText().toString();
                user.umur = umur.getProgress();
                RadioButton rd = findViewById(jk.getCheckedRadioButtonId());
                user.jk = rd.getText().toString();
                long[] id=database.UserDao().RegisterUser(user);
                Toast.makeText(this, "Berhasil daftar!"+Long.toString(id[0]),
                        Toast.LENGTH_LONG).show();
                User registeredUser=database.UserDao().getUserbyid((int)id[0]);
                String jsonModel=gson.toJson(registeredUser);
                sp= this.getSharedPreferences("pref",0);
                SharedPreferences.Editor prefsEditor = sp.edit();
                prefsEditor.putString("loggedUser", jsonModel);
                prefsEditor.commit();
                Intent intent=new Intent(getApplicationContext(),MainDashboard.class);
                startActivity(intent);

            }
        });
    }
}