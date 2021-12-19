package com.example.mod5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mod5.layanan.LayananDatabase;
import com.example.mod5.layanan.User;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText telp,password;
    Button login;
    TextView register;
    LayananDatabase database;
    SharedPreferences sp;
    User loggedUser;
    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = LayananDatabase.getDbInstance(this);
        telp = findViewById(R.id.notelpLogin);
        password = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.login);
        register = findViewById(R.id.registerLogin);
        register.setOnClickListener(view -> startActivity(new Intent(this,RegisterActivity.class)));

        sp= this.getSharedPreferences("pref",0);
        String json = sp.getString("loggedUser", "");
        loggedUser = gson.fromJson(json, User.class);
        if(loggedUser!=null){
            Intent intent = new Intent(LoginActivity.this, MainDashboard.class);
            startActivity(intent);
            finish();
        }


        login.setOnClickListener(view -> {
//            kalo login tidak mengembalikan user sama sekali, gagal login
            List<User> listUser=database.UserDao().login(telp.getText().toString(),password.getText().toString());
            if(listUser.size()==0){
                Toast.makeText(getBaseContext(), "TELP OR PASSWORD SALAH", Toast.LENGTH_LONG).show();
            }else{
                User user=listUser.get(0);
                SharedPreferences.Editor prefsEditor = sp.edit();
                String jsonModel = gson.toJson(user);
                prefsEditor.putString("loggedUser", jsonModel);
                prefsEditor.commit();
                startActivity(new Intent(this,MainDashboard.class));
                finish();
            }
        });
    }
}