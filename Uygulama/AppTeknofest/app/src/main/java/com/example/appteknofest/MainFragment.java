package com.example.appteknofest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainFragment extends Fragment {

    ImageView cameraBtn;
    ImageView diagnosticBtn;
    ImageView deviceBtn;
    ImageView appBtn;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cameraBtn =  view.findViewById(R.id.cameraIv);
        diagnosticBtn =  view.findViewById(R.id.diagnosticIv);

        deviceBtn =  view.findViewById(R.id.deviceSettingsIv);
        appBtn = view.findViewById(R.id.appSettingsIv);


        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCameraFragment(view);
            }
        });
        diagnosticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),apiActivity.class);
                startActivity(i);
            }
        });

        deviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDeviceSettingsFragment(view);
            }
        });
        appBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_appSettingsFragment);
            }
        });


    }
    public void goToCameraFragment(View view){
        Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_cameraFragment);
    }

    public void goToDeviceSettingsFragment(View view){
        Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_deviceSettingsFragment);
    }

}