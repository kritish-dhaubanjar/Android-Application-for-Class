package com.charoniv.jin.charon;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1 = "channel1";

    @Override
    public void onCreate() {
        createNotificationChannels();
        super.onCreate();
    }

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_1,
                    "Pusher Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);


    }

}
