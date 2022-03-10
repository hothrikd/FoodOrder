package com.application.samsungfoodorder;

import static com.application.samsungfoodorder.ChannelClass.CHANNEL_ID;
import static com.application.samsungfoodorder.ChannelClass.notificationId;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.application.samsungfoodorder.MainActivity;
import com.application.samsungfoodorder.R;

import java.security.Provider;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NotificationService extends Service {
    Notification notification;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notiintent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                TaskStackBuilder.create(this).
                addNextIntentWithParentStack(notiintent)
                .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notification = new Notification.Builder(
                this,CHANNEL_ID).setContentTitle("Samsung Food")
                .setContentText("Welcome to Samsung")
                .setSmallIcon(R.drawable.ic_baseline_star_24)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(notificationId,notification);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(STOP_FOREGROUND_REMOVE);
    }
}
