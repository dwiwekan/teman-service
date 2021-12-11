package com.example.mod5.ui.emergency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mod5.MainDashboard;
import com.example.mod5.databinding.FragmentEmergencyBinding;

public class EmergencyFragment extends Fragment {

    private EmergencyViewModel emergencyViewModel;
    private FragmentEmergencyBinding binding;
    private String stringname;
    private TextView textviewnama;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainDashboard activity = (MainDashboard) getActivity();
        String name = activity.getMyName();
        Toast toast=Toast.makeText(activity.getApplicationContext(),name,Toast. LENGTH_SHORT);
        toast.show();

        emergencyViewModel =
                new ViewModelProvider(this).get(EmergencyViewModel.class);

        binding = FragmentEmergencyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.usernameView;
        textView.setText(name);
//        emergencyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}