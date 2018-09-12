package meridian.com.etsdcapp.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import me.leolin.shortcutbadger.ShortcutBadger;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.notif.Notification;
import meridian.com.etsdcapp.schedule.CalendarRegisteredCoursesActivity1;

/**
 * Created by Rashid on 5/18/2016.
 */
public class GcmMessageHandler extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;
    int badgecnt=0,counts;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");


        System.out.println("@@@@##########@@@@@@   :"+message);



       // ShortcutBadger.applyCount(getApplicationContext(),badgecnt);


        if(message.equals("Course Approved"))
        {

            Intent resultIntent = new Intent(this,CalendarRegisteredCoursesActivity1.class);

// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );


            Context context = getBaseContext();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("ETSDC NOTIFICATION")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND|NotificationCompat.DEFAULT_LIGHTS|NotificationCompat.DEFAULT_VIBRATE);;
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
            SharedPreferences sharedPreferencesns1 =getSharedPreferences("notification",MODE_PRIVATE);
            counts=  sharedPreferencesns1.getInt("notifcnt",0);
            counts=counts+1;
            System.out.println("countss1"+counts);
            if(counts>0)
            {
                ShortcutBadger.applyCount(getApplicationContext(),counts);
                SharedPreferences preferences= getApplicationContext().getSharedPreferences("notification", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("notifcnt",counts);
                System.out.println("countss2"+counts);
                editor.commit();
            }

        }


        else {

            createNotification("ETSDC NOTIFICATION", message);

            SharedPreferences sharedPreferencesns1 =getSharedPreferences("notification",MODE_PRIVATE);
            counts=  sharedPreferencesns1.getInt("notifcnt",0);
            counts=counts+1;
            System.out.println("countss1"+counts);
            if(counts>0)
            {
                ShortcutBadger.applyCount(getApplicationContext(),counts);
                SharedPreferences preferences= getApplicationContext().getSharedPreferences("notification", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("notifcnt",counts);
                System.out.println("countss2"+counts);
                editor.commit();
            }




        }
    }

    // Creates notification based on title and body received
    private void createNotification(String title, String body) {


        Intent resultIntent = new Intent(this, Notification.class);

// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title)
                .setContentText(body)
.setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND|NotificationCompat.DEFAULT_LIGHTS|NotificationCompat.DEFAULT_VIBRATE);;
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }

}
