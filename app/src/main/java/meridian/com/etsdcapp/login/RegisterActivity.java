package meridian.com.etsdcapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;

public class RegisterActivity extends AppCompatActivity {
TextView AlReg;
    EditText edtemail,edtphon,edtfulnam,edtoccp,edtloc,edtpass;
    String email,phon,fulnam,occ,loc,pass,statusd;
    Button butsignup;
    boolean edittexterror=false;
    String REGISTER_URL="http://www.app.etsdc.com/userregistration.php";

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//    ProgressDialog pd;
ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        edtemail= (EditText) findViewById(R.id.edt_email);
        edtpass= (EditText) findViewById(R.id.edt_pass);
        edtfulnam= (EditText) findViewById(R.id.edt_fulname);
        edtphon= (EditText) findViewById(R.id.edt_phone);
        edtoccp= (EditText) findViewById(R.id.edt_occuptn);
        edtloc= (EditText) findViewById(R.id.edt_locatn);
       butsignup= (Button) findViewById(R.id.butsignup);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Lt.otf");
      /*  edtfulnam.setTypeface(tf, Typeface.BOLD);
        edtphon.setTypeface(tf, Typeface.BOLD);
        edtfulnam.setTypeface(tf, Typeface.BOLD);
        edtphon.setTypeface(tf, Typeface.BOLD);
        edtoccp.setTypeface(tf, Typeface.BOLD);
        edtpass.setTypeface(tf, Typeface.BOLD);*/
        Typeface ttf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Roman.otf");
        AlReg= (TextView) findViewById(R.id.txtsignin);
        AlReg.setTypeface(ttf, Typeface.BOLD);
        AlReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // reg();
                Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(is);
                finish();

            }
        });
        butsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();



            }
        });
    }

    private void reg() {
        email = edtemail.getText().toString();
        pass = edtpass.getText().toString();
        phon = edtphon.getText().toString();
        fulnam = edtfulnam.getText().toString();
        occ = edtoccp.getText().toString();
        loc = edtloc.getText().toString();
        edittexterror = false;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
            edtemail.setError("Invalid Email");
            edittexterror = true;
        }
        else if(edtemail.getText().toString().isEmpty())
       {
            edtemail.setError("Enter Email Id");
            edittexterror = true;
        }
        else {

        }
         if (!isValidPassword(edtpass.getText().toString().trim())) {
            edtpass.setError("Password should be minimum 7 characters");
            edittexterror = true;
        }
         else if(edtpass.getText().toString().isEmpty())
         {
             edtpass.setError("Enter password");
             edittexterror = true;
         }
         else {


         }

        if (!android.util.Patterns.PHONE.matcher(edtphon.getText().toString().trim()).matches()) {
            edtphon.setError("Invalid Phone");
            edittexterror = true;
        }
        else if (edtphon.getText().toString().isEmpty()) {
            edtphon.setError("Enter Phone");
            edittexterror = true;
        }
        else if(edtphon.getText().toString().length() < 6 ||edtphon.getText().toString().length() > 13){
            edtphon.setError("Invalid Phone");
            edittexterror = true;

        }
        else {

        }


       if (edtfulnam.getText().toString().isEmpty()) {
            edtfulnam.setError("Enter Full Name");
            edittexterror = true;
        }

       if (edtloc.getText().toString().isEmpty()) {
            edtloc.setError("Enter Location");
            edittexterror = true;
        }
       if (edtoccp.getText().toString().isEmpty()) {
            edtoccp.setError("Enter Occupation");
            edittexterror = true;
        }

       if (email.trim().matches("") || pass.matches("") || pass.trim().matches("") || phon.matches("") || fulnam.matches("") || occ.matches("") || loc.matches("")) {
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

       /*    MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
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
           /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setIcon(R.drawable.warning_blue);
            alertDialog.setMessage("Empty Fields");

            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();


                }
            });
            alertDialog.show();
            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/

        }






        if(edittexterror==false)
        {
            edtemail.setError(null);
            edtpass.setError(null);
            edtphon.setError(null);
            edtoccp.setError(null);
            edtloc.setError(null);
            edtfulnam.setError(null);
            NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(getApplicationContext());
            boolean i = networkCheckingClass.ckeckinternet();
            if (i)
            {

               progress.setVisibility(ProgressBar.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progress.setVisibility(ProgressBar.GONE);
                                System.out.println("responseeeee1"+response);
                                JSONObject jsonObj = null;
                                try {
                                    jsonObj = new JSONObject(response);
                                    System.out.println("responseeeee2"+response);
                                    statusd = jsonObj.getString("status");
                                    System.out.println("statussssscours"+statusd);
                                    progress.setVisibility(ProgressBar.GONE);
                                    // pd.dismiss();
                                //    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                                  //  alertDialog.setTitle("Alert");
                                    if (statusd.contentEquals("success")) {
                                        edtemail.setText("");
                                        edtpass.setText("");
                                        edtphon.setText("");
                                        edtoccp.setText("");
                                        edtloc.setText("");
                                        edtfulnam.setText("");
                                        final SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                        dialog.setTitleText("Register Successfully")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        dialog.dismiss();
                                                        Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                                                        startActivity(is);

                                                        finish();
                                                       // finish();
                                                    }
                                                })
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                      /*  MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(RegisterActivity.this)
                                                .setTitle("ALERT!")
                                                .setDescription("You have successfully registered")
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
                                        //alertDialog.setMessage("You have successfully registered");
                                    }
                                    else {
                                        final SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                        dialog.setTitleText("Alert!")
                                                .setContentText("Already Registred")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        progress.setVisibility(ProgressBar.GONE);
                                                       /* if (statusd.contentEquals("success"))
                                                        {

                                                            progress.setVisibility(ProgressBar.GONE);


                                                            Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                                                            startActivity(is);

                                                            finish();
                                                        }*/
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                  /*      MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(RegisterActivity.this)
                                                .setTitle(statusd)
                                                .setPositiveText("OK")
                                                .setStyle(Style.HEADER_WITH_TITLE)
                                                // .withDialogAnimation(true, Duration.FAST)
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        if (statusd.contentEquals("success"))
                                                        {

                                                            progress.setVisibility(ProgressBar.GONE);


                                                            Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                                                            startActivity(is);

                                                            finish();
                                                        }
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .build();


                                        dialog.show();*/
                                    //   alertDialog.setMessage(statusd);
                                    }
                              /*     // alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    if (statusd.contentEquals("success"))
                                                    {

                                                        progress.setVisibility(ProgressBar.GONE);


                                        Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(is);

                                        finish();
                                                    }
                                                    dialog.dismiss();


                                                }
                                            });
                                    alertDialog.show();
                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                final SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                dialog.setTitleText("Alert!")
                                        .setContentText("Please Login to Register For this Course")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .show();


                                dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

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

                              Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                      http://meridian.net.in/demo/etsdc/response.php?fid=1&email=" + email + "&phone=" + phon + "&name=" + fulnam + "&occupation=" + occ + "&location=" + loc + "&password=" + pass


                        params.put("email", email);
                        params.put("phone", phon);
                        params.put("name", fulnam);
                        params.put("occupation", occ);
                        params.put("location", loc);

                        params.put("password", pass);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                int socketTimeout = 30000;//30 seconds - change to what you want
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
               stringRequest.setRetryPolicy(policy);
                requestQueue.add(stringRequest);
            } else {
                final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Alert!")
                        .setContentText("Ooops Your Connection Seems Off...")
                        .setConfirmText("OK")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
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


                dialog.show();*/
               /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Oops Your Connection Seems Off..");

                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();


                    }
                });
                alertDialog.show();
*/
            }





//            pd = new ProgressDialog(RegisterActivity.this);
//            pd.setTitle("Registering...");
//            pd.setMessage("Please wait...");
//            pd.setCancelable(true);
//            pd.show();

//            if (i==true)
//            {
//                progress.setVisibility(ProgressBar.VISIBLE);
//
//                RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
//                String url1 = "http://meridian.net.in/demo/etsdc/response.php?fid=1&email=" + email + "&phone=" + phon + "&name=" + fulnam + "&occupation=" + occ + "&location=" + loc + "&password=" + pass;
//
//                StringRequest stringRequest1 = new StringRequest
//                        (Request.Method.GET, url1, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                //  tv.setText("Response is: "+ response);
//
//                                System.out.println("++++++++++++++RESPONSE+++++++++++++++    :" + response);
//
//
//                                try {
//
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    String status = jsonObj.getString("status");
//                                    System.out.println("result" + status);
//                                    if (status.equals("success"))
//                                    {   progress.setVisibility(ProgressBar.GONE);
//                                        Intent is = new Intent(getApplicationContext(), LoginActivity.class);
//                                        startActivity(is);
//                                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPrefN", MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = preferences.edit();
//                                        editor.putString(" fulnam", fulnam);
//                                        editor.putString(" email", email);
//                                        editor.putString(" phon", phon);
//                                        editor.putString(" occ", occ);
//                                        editor.putString(" loc", loc);
//                                        finish();
//                                    }
//                                    else
//                                    {   progress.setVisibility(ProgressBar.GONE);
//                                        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this).create();
//                                        alertDialog.setTitle("Alert");
//                                        alertDialog.setMessage(status);
//
//                                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                dialog.dismiss();
//
//
//                                            }
//                                        });
//                                        alertDialog.show();
////                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
//
//                                    }
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //tv.setText("That didn't work!");
//
//                            }
//                        });
//
//                queue1.add(stringRequest1);
//            } else {
//                progress.setVisibility(ProgressBar.GONE);
//                final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this).create();
//                alertDialog.setTitle("Alert");
//                alertDialog.setMessage("Oops Your Connection Seems Off..");
//
//                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//
//
//                    }
//                });
//                alertDialog.show();
//
//            }
       }
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent is = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(is);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {


           // super.onBackPressed();
            Intent is = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(is);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 7) {
            return true;
        }
        return false;
    }
//    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK &&
//                event.getAction() == KeyEvent.ACTION_UP) {
//            revalidateEditText();
//            return false;
//        }
//        return super.dispatchKeyEvent(event);
//    }
//
//    public void revalidateEditText(){
//        // Dismiss your origial error dialog
//        setError(null);
//        // figure out which EditText it is, you already have this code
//        // call your validator like in the Q
//        validate(editText); // or whatever your equivalent is
//    }
}
