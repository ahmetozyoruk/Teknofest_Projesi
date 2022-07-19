package com.example.appteknofest.Socket;


import com.example.appteknofest.Models.Gozluk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable
{
    private Thread thread;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Client()
    {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
    }

    @Override
    public void run() {
        try
        {
            // create new socket and connect to the server
            this.socket = new Socket( "192.168.43.1" , 2222 );
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

        try
        {
            /**Creating object of Organisation **/
            Gozluk gozluk = new Gozluk();
            gozluk.setName("Ahmet");
            gozluk.setAge(24);
            gozluk.setCity("İstanbul");

            this.dataOutputStream.writeBytes(gozluk.toString());
            this.dataOutputStream.flush();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to send" );
            e.printStackTrace();
        }


        while ( true )
        {
            try
            {
                int test = this.dataInputStream.readInt();
                System.out.println( "int received: "+test );

                if ( test == 42 ) break;
            }
            catch ( IOException e )
            {
                System.out.println( "failed to read data" );
                e.printStackTrace();
                break;
            }
        }
    }
}
