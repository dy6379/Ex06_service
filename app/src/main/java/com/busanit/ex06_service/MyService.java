package com.busanit.ex06_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myLog", "onCreate 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myLog","onStartCommand 호출됨.");
        if(intent==null){
            return Service.START_STICKY;
        } else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d("myLog", "command : "+command+", name : "+name);

        for(int i=0; i<5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Log.d("myLog","Waiting "+(i+1)+" seconds.");
        }
        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command","show");
        showIntent.putExtra("name", name+" from service.");
        startActivity(showIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("myLog","onDestroy가 호출됨.");
    }
}