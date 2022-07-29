package com.example.appteknofest.RTSPClient;

public class Client implements Runnable{
    private Thread thread;
    public Client()
    {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
    }

    @Override
    public void run() {

    }
}
