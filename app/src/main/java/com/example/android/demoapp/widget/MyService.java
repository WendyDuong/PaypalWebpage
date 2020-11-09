package com.example.android.demoapp.widget;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.android.demoapp.R;
import com.example.android.demoapp.activity.MainActivity;

public class MyService extends Service {
        String CHANNEL_ID = "VoipChannel";
        String CHANNEL_NAME = "Voip Channel";

        @Override
        public void onCreate() {
                createChannel();

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("chạy Service")
                        .setContentTitle("Service chạy ngầm")
                        .setSmallIcon(R.drawable.appicon)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setCategory(NotificationCompat.CATEGORY_CALL);

                Notification incomingCallNotification = null;
                if (notificationBuilder != null) {
                        incomingCallNotification = notificationBuilder.build();
                }
                startForeground(120, incomingCallNotification);
                registerOverlayReceiver();
                super.onCreate();
        }

        @Override
        public IBinder onBind(Intent intent) {
                return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
                return START_STICKY;
        }


        private void registerOverlayReceiver() {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_ON);
                filter.addAction(Intent.ACTION_SCREEN_OFF);
                registerReceiver(overlayReceiver, filter);

        }




        private BroadcastReceiver overlayReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        assert action != null;
                        Log.d("testmanhinh", action);
                        if (action.equals(Intent.ACTION_SCREEN_ON)) {
                                Intent intent1 = new Intent(context,MainActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent1);
                        }

                }
        };

        public void createChannel() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                        notificationChannel.setDescription("no sound");
                        notificationChannel.setSound(null,null);
                        notificationChannel.enableLights(false);
                        notificationChannel.enableVibration(false);
                        notificationManager.createNotificationChannel(notificationChannel);
                }
        }


}