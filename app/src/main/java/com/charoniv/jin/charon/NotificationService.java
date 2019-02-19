package com.charoniv.jin.charon;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.charoniv.jin.charon.activities.MainActivity;
import com.google.gson.JsonObject;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationService extends Service {

    private NotificationManagerCompat notificationManager;
    private static final String TAG = "NotificationService";

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        notificationManager = NotificationManagerCompat.from(this);

        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.notificationService = this;
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");
        Pusher pusher = new Pusher("c42d4dd1f78b0670bcab", options);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {

                try {
                    final JSONObject jsonObject = new JSONObject(data);
                    Notification notification = new Notification.Builder(NotificationService.this)
                            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                            .setContentTitle("Charon IV " + jsonObject.getString("name"))
                            .setContentText(jsonObject.getString("message"))
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentIntent(pendingIntent)
                            .build();
                    startForeground(1, notification);
                }catch (JSONException e){
                    Log.e(TAG, "onEvent: " + e.getMessage() );
                }

            }
        });
        pusher.connect();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
        stopSelf();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}