package com.example.mod5.ui.emergency;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.mod5.MainDashboard;
import com.example.mod5.ProfileActivity;
import com.example.mod5.databinding.FragmentEmergencyBinding;

import com.example.mod5.ListLayananActivity;
import com.example.mod5.LoginActivity;
import com.example.mod5.MainDashboard;
import com.example.mod5.R;
import com.example.mod5.databinding.FragmentEmergencyBinding;
import com.example.mod5.layanan.User;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class EmergencyFragment extends Fragment {

    private EmergencyViewModel emergencyViewModel;
    private FragmentEmergencyBinding binding;
    private String stringname;
    private TextView textviewnama;
    private SharedPreferences sp;
    private User loggedUser;
    Gson gson=new Gson();

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//        MainDashboard activity = (MainDashboard) getActivity();
//        String name = activity.getMyName();
//        Toast toast=Toast.makeText(activity.getApplicationContext(),name,Toast. LENGTH_SHORT);
//        toast.show();
//
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Get nama from login
        MainDashboard activity = (MainDashboard) getActivity();
        String name = activity.getMyName();
//        Toast toast=Toast.makeText(activity.getApplicationContext(),name,Toast. LENGTH_SHORT);
//        toast.show();
        sp=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String json = sp.getString("loggedUser", "");
        loggedUser = gson.fromJson(json, User.class);

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");

        // Waktu asia, singapore
        df.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));

        emergencyViewModel =
                new ViewModelProvider(this).get(EmergencyViewModel.class);

        binding = FragmentEmergencyBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

//        View root = inflater.inflate(R.layout.fragment_emergency,container,false);
//        View root = binding.getRoot();


        final TextView textView = binding.usernameView;
        textView.setText(loggedUser.nama);
        final TextView tglTexView=binding.tanggalInfo;
        tglTexView.setText(df.format(date));
//        emergencyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



        // Button RS
        final Button tombol1 = (Button) root.findViewById(R.id.polisi);
        tombol1.setOnClickListener(this::onClick);
        final Button tombol2 = (Button) root.findViewById(R.id.pemadam);
        tombol2.setOnClickListener(this::onClick);
        final Button tombol3 = (Button) root.findViewById(R.id.buttonLogout);
        tombol3.setOnClickListener(this::onClick);
        final Button tombol4 = (Button) root.findViewById(R.id.buttonEditProfile);
        tombol4.setOnClickListener(this::onClick);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    public void onClick(View v){
        switch (v.getId()) {
            case R.id.polisi:
                Intent i2 = new Intent(getActivity(), ListLayananActivity.class);
                i2.putExtra("kode_layanan",1);
                startActivity(i2);

                break;
            case R.id.pemadam:
//                Toast.makeText(getActivity(), "B", Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent(getActivity(), ListLayananActivity.class);
                i3.putExtra("kode_layanan",2);
                startActivity(i3);

                break;
            case R.id.buttonLogout:
//                Toast.makeText(getActivity(), "B", Toast.LENGTH_SHORT).show();
                sp.edit().remove("loggedUser").commit();
                sp.edit().clear();
                sp.edit().apply();
                Intent i4 = new Intent(getActivity(), LoginActivity.class);
                startActivity(i4);

                break;

            case R.id.buttonEditProfile:
//                Toast.makeText(getActivity(), "B", Toast.LENGTH_SHORT).show();
                Intent i5 = new Intent(getActivity(), ProfileActivity.class);
                startActivity(i5);

                break;
        }
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.rumah_sakit:
//                Toast.makeText(getActivity(), "Yuk Pindah Activity", Toast.LENGTH_SHORT).show();
//        }
//    }

}