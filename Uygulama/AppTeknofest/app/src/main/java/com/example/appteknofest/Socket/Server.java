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
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

public class Server implements Runnable
{
    private Thread thread;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Context mContext;

    public Server(Context context)
    {
        this.mContext = context;
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
    }

    @Override
    public void run()
    {
        // create a server socket
        try
        {
            this.serverSocket = new ServerSocket( 2222 );
        }
        catch ( IOException e )
        {
            System.out.println( "failed to start server socket" );
            e.printStackTrace();
        }

        // wait for a connection
        System.out.println( "waiting for connections..." );
        try
        {
            this.socket = serverSocket.accept();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to accept" );
            e.printStackTrace();
        }
        System.out.println( "client connected" );

        while(true)
        {
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

            // send some test data
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

            // placeholder recv loop

            //byte test = this.dataInputStream.readByte();
            //byte test = this.dataInputStream.readByte();
            //System.out.println( "byte received: "+test );
            String jsonFileString = Utils.getJsonFromAssets(mContext, dataInputStream);
//            jsonFileString.replace("\0", "");
//            Log.i("data", jsonFileString);
            try {
                if(jsonFileString == "quit")
                    break;

                if(jsonFileString != null)
                {
                    final JSONObject obj = new JSONObject(jsonFileString);
                    System.out.println("Name: "+ obj.getString("name"));
                    String image = obj.getString("image");
                    Bitmap bm = StringToBitMap(image);
                    saveImage(bm, "resim");
                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }
        // create input and output streams

        //Gson gson = new GsonBuilder().setLenient().create();
        //Type listUserType = new TypeToken<List<Gozluk>>() { }.getType();
        //Type listUserType = new TypeToken<Gozluk>() { }.getType();
        //Gozluk users = gson.fromJson(jsonFileString, listUserType);
        //List<Gozluk> users = gson.fromJson(jsonFileString, listUserType);
        //for (int i = 0; i < users.size(); i++) {
        //    Log.i("data", "> Item " + i + "\n" + users.get(i));
        //}
        //System.out.println( "Gözlük name: "+ users.getName().toString() );
        //byte test = this.dataInputStream.readByte();

        System.out.println( "server thread stopped" );
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