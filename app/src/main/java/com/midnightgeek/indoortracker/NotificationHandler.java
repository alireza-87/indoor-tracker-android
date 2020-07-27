package com.midnightgeek.indoortracker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationHandler {
    private static NotificationHandler ourInstance;
    NotificationManager notificationManager;
    public static NotificationHandler getInstance(){
        if (ourInstance==null){
            ourInstance=new NotificationHandler();
        }
        return ourInstance;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creatNotificationChanner(Context context){
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("AlarmChannel", "Alarm", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("Building Alarm");
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(false);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public void raiseNotification(String body,Context context){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            Notification.Builder nBuilder=new Notification.Builder(context, "AlarmChannel")
                    .setContentTitle("Room Over")
                    .setContentText(body)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
            notificationManager.notify(100,nBuilder.build());

        }
    }
}
