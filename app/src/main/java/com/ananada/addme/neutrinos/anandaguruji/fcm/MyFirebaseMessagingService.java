package com.ananada.addme.neutrinos.anandaguruji.fcm;

/**
 * Created by sandeep HR on 05/04/17.
 */


import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import com.ananada.addme.neutrinos.anandaguruji.Logger;
import com.ananada.addme.neutrinos.anandaguruji.R;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static final int NotificationIdBenifits = 100;
    public static final int NotificationIdEvents = 99;
    public static final int NOTIFICATION_ID_COMMENT_REPLAY =101;
    public static final int NOTIFICATION_ID_COMMENT =102;
    public static final int NotificationIdArticles = 98;
    public static final int NotificationIdSurvey = 97;
    public static final int NotificationIdBirthdays = 96;
    public static final int NotificationIdAnniversary = 95;
    private NotificationManager mNotificationManager;
    public static final int NOTIFICATION_ID = 0;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Message messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Message messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData());

        // Check if message contains a notification payload.
        if (remoteMessage.getData() != null) {
            Map<String,String> remoteData = remoteMessage.getData();
            // Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getTitle());
            //sendNotification(remoteMessage.getData());

            String status = remoteData.get("notification_status");

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(Map<String, String> messageBody) {
        JSONObject messDataObj = null;

        try {

            messDataObj = new JSONObject(messageBody.get("data"));

            Logger.logD("Firebase", " title " + messDataObj.getString("title"));
            Logger.logD("Firebase", " message " + messDataObj.getString("message"));
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notificationBuilder.build());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
