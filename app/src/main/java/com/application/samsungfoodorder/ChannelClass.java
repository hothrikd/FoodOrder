package com.application.samsungfoodorder;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class ChannelClass extends Application {
    public static final String CHANNEL_ID = "SamsungFoodID";
    public static final String CHANNEL_NAME = "SamsungFoodChannel";
    public static final int notificationId = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID
                    ,CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setLightColor(R.color.lightYellow);
            notificationChannel.enableLights(true);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
