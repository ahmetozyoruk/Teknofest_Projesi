package com.example.appteknofest.Socket;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.example.appteknofest.Models.Gozluk;
import com.example.appteknofest.Utilities.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable
{
    private Thread thread;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Context mContext;
    String host_address;
    String toDoJob;
    public Bitmap bm;

    public Client(Context context, String toDoJob)
    {
        toDoJob = toDoJob;
        host_address="192.168.1.15";
        this.mContext = context;
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
    }

    @Override
    public void run() {
        try
        {
            // create new socket and connect to the server
            this.socket = new Socket( host_address , 2222 );
        }
        catch( IOException e )
        {
            System.out.println( "failed to create socket" );
            e.printStackTrace();
        }

        System.out.println( "connected" );

        try
        {
            this.dataInputStream = new DataInputStream( new BufferedInputStream( this.socket.getInputStream() ) );
            this.dataOutputStream = new DataOutputStream( new BufferedOutputStream( this.socket.getOutputStream() ) );
        }
        catch ( IOException e )
        {
            System.out.println( "failed to create streams" );
            e.printStackTrace();
        }
//
//        try
//        {
//            /**Creating object of Organisation **/
//            Gozluk gozluk = new Gozluk();
//            gozluk.setName("Ahmet");
//            gozluk.setAge(24);
//            gozluk.setCity("İstanbul");
//
//            this.dataOutputStream.writeBytes(gozluk.toString());
//            this.dataOutputStream.flush();
//        }
//        catch ( IOException e )
//        {
//            System.out.println( "failed to send" );
//            e.printStackTrace();
//        }


            try
            {
                this.dataOutputStream.writeBytes("photo");
//                this.dataOutputStream.writeBytes("Connected to server.");
                this.dataOutputStream.flush();
            }
            catch ( IOException e )
            {
                System.out.println( "failed to send" );
                e.printStackTrace();
            }

            String jsonFileString = Utils.getJsonFromAssets(mContext, dataInputStream);

            try
            {
//                int test = this.dataInputStream.readInt();
//                System.out.println( "int received: "+test );
//
//                if ( test == 42 ) break;

                if(jsonFileString == "dack")
                    thread.stop();

                if(jsonFileString != null)
                {
                    this.dataOutputStream.writeBytes("ack");
                    System.out.println("Alındı: "+ jsonFileString);
//                this.dataOutputStream.writeBytes("Connected to server.");
                    this.dataOutputStream.flush();
                    final JSONObject obj = new JSONObject(jsonFileString);
                    System.out.println("image: "+ obj.getString("image"));
                    String image = obj.getString("image");
                    bm = StringToBitMap(image);
                    saveImage(bm, "resim");
                }

            }
            catch (IOException | JSONException e )
            {
                System.out.println( "failed to read data" );
                e.printStackTrace();
            }
    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private void saveImage(Bitmap bitmap, @NonNull String name) throws IOException {
        boolean saved;
        OutputStream fos;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = mContext.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" + "Teknofest_App");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString() + File.separator + "Teknofest_App";

            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            File image = new File(imagesDir, name + ".png");
            fos = new FileOutputStream(image);

        }

        saved = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
    }

}
