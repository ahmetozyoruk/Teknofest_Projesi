package com.example.appteknofest.Utilities;

import android.content.Context;

import java.io.DataInputStream;
import java.io.IOException;

public class Utils {

    static public String getJsonFromAssets(Context context, DataInputStream dataInputStream) {
        String jsonString;

        try {
            //byte[] buffer = new byte[size];
            //byte[] buffer = new byte[268435456];
            jsonString = new String("UTF-8");
            jsonString = dataInputStream.readLine();
            System.out.println("Naber laaaIc");



        } catch (IOException e) {
            e.printStackTrace();
            return null;
            }
        return jsonString;
    }
}
