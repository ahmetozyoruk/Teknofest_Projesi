package com.example.appteknofest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class brightnessFragment extends Fragment {



    SeekBar seekBar;
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brightness, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        seekBar = view.findViewById(R.id.seekBar);

        int cBrightness = Settings.System.getInt(getContext().getContentResolver()
                ,Settings.System.SCREEN_BRIGHTNESS,0);


        seekBar.setProgress(cBrightness);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Context context = getActivity().getApplicationContext();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    boolean canWrite = Settings.System.canWrite(context);
                    if (canWrite){
                        int sBrightness = i*255/255;

                        Settings.System.putInt(context.getContentResolver()
                                ,Settings.System.SCREEN_BRIGHTNESS_MODE
                                ,Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

                        Settings.System.putInt(context.getContentResolver()
                                ,Settings.System.SCREEN_BRIGHTNESS,sBrightness);
                    }else{
                        Intent intents = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        startActivity(intents);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}