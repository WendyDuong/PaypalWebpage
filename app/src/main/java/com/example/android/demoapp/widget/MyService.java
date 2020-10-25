package com.example.android.demoapp.widget;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.example.android.demoapp.activity.MainActivity;

public class MyService extends Service {

private static final String TAG = MyService.class.getSimpleName();


@Override
public IBinder onBind(Intent intent) {
        return null;
        }

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
        registerOverlayReceiver();
        return super.onStartCommand(intent, flags, startId);
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
        Log.d("testmanhinh", action);
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
        showMainActivity(context);
        }
        }
        };

private void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
        }

}
