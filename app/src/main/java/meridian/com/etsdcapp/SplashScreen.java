package meridian.com.etsdcapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;
import meridian.com.etsdcapp.gcm.Config;
import meridian.com.etsdcapp.gcm.RegistrationIntentService;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.login.RegisterActivity;




public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    ImageView image;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        image= (ImageView) findViewById(R.id.img_splsh);
        ShortcutBadger.removeCount(getApplicationContext());
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);

            startService(intent);


//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//          Boolean ttt = sharedPreferences.getBoolean("sentTokenToServer",false);
////        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.sharedpref, 0);
////        String regId = pref.getString("regId", null);
//            System.out.println("tokennnnsplashh"+ttt);
           // func(ttt);
        }


//        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("gcmToken")

//        InstanceID instanceID = InstanceID.getInstance(this);
//        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
//        try {
//            // request token that will be used by the server to send push notifications
//            token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
//           // Log.d(TAG, "GCM Registration Token: " + token);
//            System.out.println("tokennnn"+token);
//
//
//
//            // pass along this data
//          //  sendRegistrationToServer(token);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//


        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);

        final String dd=sharedPreferences1.getString("gcmToken",null);
        //    Toast.makeText(getApplicationContext(),"GCM: "+dd,Toast.LENGTH_SHORT).show();

          //  Log.d(TAG, "Failed to complete token refresh", e); }
//
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("first_time", false))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
//            Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
//            image.startAnimation(animation1);



            new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this, IntroScreenActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);


//            Intent i = new Intent(SplashScreen.this, IntroScreenActivity.class);
//            this.startActivity(i);
//            this.finish();
        }
        else
        { Intent i = new Intent(SplashScreen.this, LoginActivity.class);
            this.startActivity(i);
            this.finish();

        }


    }
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (apiAvailability.isUserResolvableError(resultCode))
            {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else
            {
               // Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }



}
