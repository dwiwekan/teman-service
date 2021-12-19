package com.example.mod5.ui.emergency;

<<<<<<< HEAD
=======
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
>>>>>>> 668014a (Ini wa kerjain)
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.Button;
>>>>>>> 668014a (Ini wa kerjain)
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

<<<<<<< HEAD
import com.example.mod5.MainDashboard;
import com.example.mod5.databinding.FragmentEmergencyBinding;
=======
import com.example.mod5.ListLayananActivity;
import com.example.mod5.LoginActivity;
import com.example.mod5.MainDashboard;
import com.example.mod5.R;
import com.example.mod5.databinding.FragmentEmergencyBinding;
import com.example.mod5.layanan.User;
import com.google.gson.Gson;
>>>>>>> 668014a (Ini wa kerjain)

public class EmergencyFragment extends Fragment {

    private EmergencyViewModel emergencyViewModel;
    private FragmentEmergencyBinding binding;
    private String stringname;
    private TextView textviewnama;
<<<<<<< HEAD

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainDashboard activity = (MainDashboard) getActivity();
        String name = activity.getMyName();
        Toast toast=Toast.makeText(activity.getApplicationContext(),name,Toast. LENGTH_SHORT);
        toast.show();
=======
    private SharedPreferences sp;
    private User loggedUser;
    Gson gson=new Gson();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Get nama from login
        MainDashboard activity = (MainDashboard) getActivity();
        String name = activity.getMyName();
//        Toast toast=Toast.makeText(activity.getApplicationContext(),name,Toast. LENGTH_SHORT);
//        toast.show();
>>>>>>> 668014a (Ini wa kerjain)

        emergencyViewModel =
                new ViewModelProvider(this).get(EmergencyViewModel.class);

        binding = FragmentEmergencyBinding.inflate(inflater, container, false);
<<<<<<< HEAD
        View root = binding.getRoot();
=======
        View root = inflater.inflate(R.layout.fragment_emergency,container,false);
//        View root = binding.getRoot();
>>>>>>> 668014a (Ini wa kerjain)

        final TextView textView = binding.usernameView;
        textView.setText(name);
//        emergencyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
<<<<<<< HEAD
=======
        sp=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        // Button RS
        final Button tombol1 = (Button) root.findViewById(R.id.rumah_sakit);
        tombol1.setOnClickListener(this::onClick);
        final Button tombol2 = (Button) root.findViewById(R.id.pemadam);
        tombol2.setOnClickListener(this::onClick);
        final Button tombol3 = (Button) root.findViewById(R.id.buttonLogout);
        tombol3.setOnClickListener(this::onClick);

>>>>>>> 668014a (Ini wa kerjain)
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
<<<<<<< HEAD
=======

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.rumah_sakit:
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
        }
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.rumah_sakit:
//                Toast.makeText(getActivity(), "Yuk Pindah Activity", Toast.LENGTH_SHORT).show();
//        }
//    }
>>>>>>> 668014a (Ini wa kerjain)
}