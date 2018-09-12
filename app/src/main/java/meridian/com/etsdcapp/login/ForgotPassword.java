package meridian.com.etsdcapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;


public class ForgotPassword extends AppCompatActivity {
EditText email;
    Button submitem;
    ProgressDialog pd;
    String eml,status;
    String REGISTER_URL="http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_forgot_password);
        // String em = getIntent().getExtras().getString("email");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topfrgt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = (EditText) findViewById(R.id.edtemail);


        submitem = (Button) findViewById(R.id.buttonemail);

        submitem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sub();
            }
        });
    }
    private void sub() {
      eml=     email.getText().toString();

        if (eml.matches("") ) {

            final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("Alert!")
                    .setContentText("Empty Fields")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialog.dismiss();
                        }
                    })
                    .show();


            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));


          /*  MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                    .setTitle("ALERT!")
                    .setDescription("Empty Fields")
                    .setHeaderColor(R.color.butnbakcolr)
                    .setPositiveText("OK")
                    .setStyle(Style.HEADER_WITH_TITLE)
                    // .withDialogAnimation(true, Duration.FAST)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .build();


            dialog.show();*/

         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ForgotPassword.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setIcon(R.drawable.warning_blue);
            alertDialog.setMessage("Empty Field");

            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();


                }
            });
            alertDialog.show();
            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/

        } else {
            NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(getApplicationContext());
            boolean i = networkCheckingClass.ckeckinternet();
            pd = new ProgressDialog(ForgotPassword.this);
            pd.setTitle("Sending...");
            pd.setMessage("Please wait...");
            pd.setCancelable(true);
            pd.show();
            if (i==true)
            {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println("statusforgot"+response+"xxxxx");

                                JSONObject jsonObj = null;
                                try {
                                    jsonObj = new JSONObject(response);
                                    status = jsonObj.getString("status");


                                    pd.dismiss();
                                    final SweetAlertDialog dialog = new SweetAlertDialog(ForgotPassword.this,SweetAlertDialog.NORMAL_TYPE);
                                    dialog.setTitleText(status)
                                            .setContentText(status)
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    if (status.contentEquals("OTP Mailed"))
                                                    {
                                                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                                        startActivity(i);

                                                    }
                                                    else if (status.contentEquals("Incorrect Email")){
                                                        Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
                                                        pd.dismiss();
                                                    }
                                                    else {
                                                        pd.dismiss();
                                                    }

                                                    dialog.dismiss();

                                                }
                                            })
                                            .show();


                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                            /*        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(ForgotPassword.this)
                                            .setTitle(status)
                                            .setDescription(status)
                                            .setHeaderColor(R.color.sliderdrawerclick)
                                            .setPositiveText("OK")
                                            .setStyle(Style.HEADER_WITH_TITLE)
                                            // .withDialogAnimation(true, Duration.FAST)
                                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    if (status.contentEquals("OTP Mailed"))
                                                    {
                                                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                                        startActivity(i);

                                                    }
                                                    else if (status.contentEquals("Incorrect Email")){
                                                        Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
                                                        pd.dismiss();
                                                    }
                                                    else {
                                                        pd.dismiss();
                                                    }

                                                    dialog.dismiss();

                                                }
                                            })
                                            .build();


                                    dialog.show();*/
                                  /*  AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
                                    alertDialog.setTitle(status);
                                    alertDialog.setMessage(status);
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    if (status.contentEquals("OTP Mailed"))
                                                    {
                                                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                                        startActivity(i);

                                                    }
                                                    else if (status.contentEquals("Incorrect Email")){
                                                   Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
                                                        pd.dismiss();
                                                    }
                                                    else {
                                                        pd.dismiss();
                                                    }

                                                    dialog.dismiss();


                                                }
                                            });
                                    alertDialog.show();*/

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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

                                //  Toast.makeText(CourseRegistrationActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("forgot_pwd", eml);



                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } else {
                final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
                dialog.setTitleText("Alert!")
                        .setContentText("Ooops Your Connection Seems Off...")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                dialog.dismiss();
                            }
                        })
                        .show();


                dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

             /*   MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                        .setTitle("ALERT!")
                        .setDescription("Oops Your Connection Seems Off..")
                        .setHeaderColor(R.color.butnbakcolr)
                        .setPositiveText("OK")
                        .setStyle(Style.HEADER_WITH_TITLE)
                        // .withDialogAnimation(true, Duration.FAST)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .build();


                dialog.show();
*/
             /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ForgotPassword.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Oops Your Connection Seems Off..");

                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();


                    }
                });
                alertDialog.show();
                Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                nbutton.setTextColor(getResources().getColor(R.color.Orange));*/
            }


        }

        // new DownloadData().execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {


            super.onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
