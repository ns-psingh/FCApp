package com.example.premal2.frequencyclub;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.windowsazure.messaging.NotificationHub;

public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegIntentService";

    private NotificationHub hub;

    public RegistrationIntentService() {
        super(TAG);

        Log.d("e","donn2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String resultString = null;
        String regID = null;
        String storedToken = null;

        try {
            Log.d("e","donn6");
            String FCM_token = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "FCM Registration Token: " + FCM_token);

            // Storing the registration ID that indicates whether the generated token has been
            // sent to your server. If it is not stored, send the token to your server,
            // otherwise your server should have already received the token.
            if (((regID=sharedPreferences.getString("registrationID", null)) == null)){


                NotificationHub hub = new NotificationHub(NotificationSettings.HubName,
                        NotificationSettings.HubListenConnectionString, this);
                Log.d(TAG, "Attempting a new registration with NH using FCM token : " + FCM_token);
                regID = hub.register(FCM_token).getRegistrationId();

                // If you want to use tags...
                // Refer to : https://azure.microsoft.com/documentation/articles/notification-hubs-routing-tag-expressions/
                // regID = hub.register(token, "tag1,tag2").getRegistrationId();

                resultString = "New NH Registration Successfully - RegId : " + regID;
                Log.d(TAG, resultString);

                sharedPreferences.edit().putString("registrationID", regID ).apply();
                sharedPreferences.edit().putString("FCMtoken", FCM_token ).apply();
            }

            // Check if the token may have been compromised and needs refreshing.
            else if ((storedToken=sharedPreferences.getString("FCMtoken", "")) != FCM_token) {
                Log.d("e","donn7");
                NotificationHub hub = new NotificationHub(NotificationSettings.HubName,
                        NotificationSettings.HubListenConnectionString, this);
                Log.d(TAG, "NH Registration refreshing with token : " + FCM_token);
                regID = hub.register(FCM_token).getRegistrationId();

                // If you want to use tags...
                // Refer to : https://azure.microsoft.com/documentation/articles/notification-hubs-routing-tag-expressions/
                // regID = hub.register(token, "tag1,tag2").getRegistrationId();

                resultString = "New NH Registration Successfully - RegId : " + regID;
                Log.d(TAG, resultString);

                sharedPreferences.edit().putString("registrationID", regID ).apply();
                sharedPreferences.edit().putString("FCMtoken", FCM_token ).apply();
                Log.d("e","donn9");
            }

            else {

                resultString = "Previously Registered Successfully - RegId : " + regID;
            }
            Log.d("e","donn10");
        } catch (Exception e) {
            Log.e(TAG, resultString="Failed to complete registration", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
        }

        Log.d("e","donn11");
        // Notify UI that registration has completed.
        if (MainActivity.isVisible) {
            Log.d("e","donn12");
            MainActivity.mainActivity.ToastNotify(resultString);
        }
    }

}
