package meridian.com.etsdcapp.gcm;

import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.SplashScreen;


public class RegistrationIntentService extends IntentService {

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String GCM_TOKEN = "gcmToken";

    // abbreviated tag name
    private static final String TAG = "RegIntentService";
    String token;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        try {
            // request token that will be used by the server to send push notifications
            token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);
            System.out.println("tokennnregistration"+token);
            func(token);
             storetoken(token);


            // pass along this data
            sendRegistrationToServer(token);

        }
        catch (IOException e) {
            e.printStackTrace();
        }




        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Fetch token here
        try {
            // save token
            sharedPreferences.edit().putString(GCM_TOKEN, token).apply();

            // pass along this data
            sendRegistrationToServer(token);



            subscribeTopics(token);
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, false).apply();
        }


    }


    private void storetoken(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.sharedpref, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();




    }

    private void sendRegistrationToServer(final String token) {
        // send network request

        // if registration sent was successful, store a boolean that indicates whether the generated token has been sent to server
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, true).apply();




    }



    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);

        String topic="testtopic";

            pubSub.subscribe(token, "/topics/" + topic, null);

    }

    public  void func(final String token) {
        NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(getApplicationContext());
        boolean i = networkCheckingClass.ckeckinternet();
        if (i)
        {


            StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://www.app.etsdc.com/savedata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println("responseeeee1"+response);
//                            JSONObject jsonObj = null;
//                            try {
//                                jsonObj = new JSONObject(response);
//                                System.out.println("responseeeee2"+response);
//                                    statusd = jsonObj.getString("status");
//                                    System.out.println("statussssscours"+statusd);

                                // pd.dismiss();
//                                AlertDialog alertDialog = new AlertDialog.Builder(SplashScreen.this).create();
//                                alertDialog.setTitle("Alert");
//
//                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//
//
//                                                dialog.dismiss();
//
//
//                                            }
//                                        });
//                                alertDialog.show();

//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseRegistrationActivity.this).create();
//                            alertDialog.setTitle("Alert");
//                            alertDialog.setMessage("Please Login to Register For this Course");
//                            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
//
//                                        }
//                                    });
//                            alertDialog.show();

                           // Toast.makeText(SplashScreen.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    http://meridian.net.in/demo/etsdc/response.php?fid=1&email=" + email + "&phone=" + phon + "&name=" + fulnam + "&occupation=" + occ + "&location=" + loc + "&password=" + pass


                    params.put("device_tokens",token);
                    params.put("device","android");

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            int socketTimeout = 30000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue.add(stringRequest);
        }
    }

}