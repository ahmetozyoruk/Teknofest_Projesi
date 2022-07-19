package com.example.appteknofest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appteknofest.Socket.Server;

public class MainActivity extends AppCompatActivity implements APManager.OnSuccessListener {

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTurnOn).setOnClickListener(v -> {
            APManager apManager = APManager.getApManager(this);
            apManager.turnOnHotspot(this,
                    this,
                    new DefaultFailureListener(this)
            );

        });

        this.server = new Server(getApplicationContext());
    }

    @Override
    public void onSuccess(@NonNull String ssid, @NonNull String password) {
        Toast.makeText(this, ssid + "," + password, Toast.LENGTH_LONG).show();
        System.out.println("password: "+ password);
        System.out.println("Phone ip: " + APManager.getApManager(this).getWifiManager().getConnectionInfo().getIpAddress());

        startActivity(new Intent(this, Introductionragment.class));
    }
}