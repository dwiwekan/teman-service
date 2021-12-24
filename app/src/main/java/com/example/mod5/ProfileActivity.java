package com.example.mod5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mod5.layanan.LayananDatabase;
import com.example.mod5.layanan.User;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {
    private EditText editNama,editAlamat,editEmail,editNotelp,editPassword;
    private RadioGroup jenisKelamin;
    SeekBar umur;
    TextView us;
    SharedPreferences sp;
    private User loggedUser;
    private Button editBtn,deleteBtn;
    Gson gson =new Gson();
    private LayananDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editNama=findViewById(R.id.editNama);
        editAlamat=findViewById(R.id.editAlamat);
        editEmail=findViewById(R.id.editEmail);
        editNotelp=findViewById(R.id.editNotelp);
        editPassword=findViewById(R.id.password);
        umur=findViewById(R.id.umurSb);
        us=findViewById(R.id.umurText);
        editBtn=findViewById(R.id.button1);
        deleteBtn=findViewById(R.id.button);
        jenisKelamin=findViewById(R.id.jenisKelamin);

        database=LayananDatabase.getDbInstance(this);
        sp=getSharedPreferences("pref", Context.MODE_PRIVATE);
        String json = sp.getString("loggedUser", "");
        loggedUser = gson.fromJson(json, User.class);

        editNama.setText(loggedUser.nama);
        editNotelp.setText(loggedUser.telp);
        editEmail.setText(loggedUser.email);
        editAlamat.setText(loggedUser.alamat);
        editPassword.setText(loggedUser.password);
        us.setText(Integer.toString(loggedUser.umur)+" Tahun");
        umur.setProgress(loggedUser.umur);
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
        if(loggedUser.jk.equals("Laki-laki")){
            jenisKelamin.check(R.id.laki);
        }else{
            jenisKelamin.check(R.id.perempuan);
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedUser.nama=editNama.getText().toString();
                loggedUser.alamat=editAlamat.getText().toString();
                loggedUser.email=editEmail.getText().toString();
                loggedUser.telp=editNotelp.getText().toString();
                loggedUser.password=editPassword.getText().toString();
                loggedUser.umur = umur.getProgress();
                RadioButton rd = findViewById(jenisKelamin.getCheckedRadioButtonId());
                loggedUser.jk = rd.getText().toString();
                database.UserDao().updateUserProfile(loggedUser);

                sp.edit().remove("loggedUser").commit();
                sp.edit().clear();
                sp.edit().apply();
                String jsonModel=gson.toJson(loggedUser);
                SharedPreferences.Editor prefsEditor = sp.edit();
                prefsEditor.putString("loggedUser", jsonModel);
                prefsEditor.commit();
                Toast.makeText(getApplicationContext(), "Data terupdate", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainDashboard.class);
                startActivity(intent);
                finish();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Hapus Akun");
                builder.setMessage(Html.fromHtml("<b>Anda akan logout secara otomatis dan akun ada akan dihapus</b><br>Apakah anda yakin ingin menghapus akun?<br><br><b><i>Note : Akun anda tidak bisa dikembalikan</i></b>"));
                builder.setPositiveButton("Hapus Akun", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sp.edit().remove("loggedUser").commit();
                        sp.edit().clear();
                        sp.edit().apply();
                        database.UserDao().deleteProfile(loggedUser);
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}