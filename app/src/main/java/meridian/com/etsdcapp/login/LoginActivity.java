package meridian.com.etsdcapp.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.CustomAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.WebviewActivity;

import meridian.com.etsdcapp.course.SubCoursesActivity1;
import meridian.com.etsdcapp.model.UserDetailsModel;

public class LoginActivity extends AppCompatActivity {
    Button login,bsignup;
    TextView Reg,txtnew,gst;
    EditText edtusrnam, edtpass;
    String usernam, pass,named;
    ProgressDialog pd;
    TextView fb,twtr,gpls,lnkdlin,frgt;
    WebView wv;
    ArrayList<UserDetailsModel> arr_usrs = new ArrayList<>();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String eml,status;
    String REGISTER_URL="http://meridian.net.in/demo/etsdc/response.php";
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login);
        edtusrnam = (EditText) findViewById(R.id.edit_usernm);

        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        edtpass = (EditText) findViewById(R.id.edt_pass);
        fb= (TextView) findViewById(R.id.txt_idfb);
        frgt= (TextView) findViewById(R.id.txt_frgt);
        twtr= (TextView) findViewById(R.id.txt_idtwtr);
        gpls= (TextView) findViewById(R.id.txt_id_googl);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       String ttt = sharedPreferences.getString("gcmToken", null);
// Toast.makeText(getApplicationContext()," "+ttt,Toast.LENGTH_SHORT).show();
        System.out.print("++++++++++++++++++++++++" + ttt);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
     String chk= preferences.getString("email",null);
        if(chk!=null)
        { Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }




        wv=new WebView(this);

        frgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                CustomAlertDialog cc=new CustomAlertDialog(LoginActivity.this);
                cc.show();



            }
        });



        lnkdlin= (TextView) findViewById(R.id.txt_id_lnkdln);
        lnkdlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {     Intent i=new Intent(getApplicationContext(), WebviewActivity.class);
                i.putExtra("url","https://www.linkedin.com/in/etsdc-abu-dhabi-63443b4b");

               startActivity(i);
            }
        });

          fb.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {
             Intent i=new Intent(getApplicationContext(), WebviewActivity.class);
             i.putExtra("url","https://www.facebook.com/etsdc.uae");

             startActivity(i);

        }
        });
        twtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), WebviewActivity.class);
                i.putExtra("url","https://twitter.com/ETSDCUAE");

                startActivity(i);


            }
        });
        gpls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), WebviewActivity.class);
                i.putExtra("url","https://plus.google.com/+etsdc");
                startActivity(i);


            }
        });

        login = (Button) findViewById(R.id.login);

       bsignup = (Button) findViewById(R.id.butSignups);
        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log();

            }
        });
        //Reg = (TextView) findViewById(R.id.txtregister);

     // Reg.setLinkTextColor(Color.parseColor("#05c5cf"));

//
//        Reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // setupWindowAnimations();
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
       gst = (TextView) findViewById(R.id.txtguest);



       gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   setupWindowAnimations();
                SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();

                editor.putString("userid",null);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
   }
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void setupWindowAnimations() {
//        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.fade);
//        getWindow().setEnterTransition(fade);
//    }

    public void log() {

        usernam = edtusrnam.getText().toString();
        pass = edtpass.getText().toString();
        if(usernam.matches("") ||pass.matches(""))
        {

/*
        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(this);

            dialogBuilder
                    .withTitle("Modal Dialog")
                    .withMessage("This is a modal Dialog.")
                    .withTitleColor("#FFFFFF")
                    .withDividerColor("#11000000")
                    .withMessageColor("#FFFFFFFF")
                    .withDialogColor("#FFE74C3C")
                    .show();*/
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
            dialog.findViewById(R.id.custom_image).setVisibility(View.GONE);


  /* new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Alert")
                    .setContentText("Empty Fields")
                    .setConfirmText("ok")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();*/

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

           /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this).create();
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
            Button nbutton =  alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/

        }
        else
        {
            progress.setVisibility(ProgressBar.VISIBLE);
//        pd = new ProgressDialog(LoginActivity.this);
//        pd.setTitle("Loging in...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();

            NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
           final boolean i= networkCheckingClass.ckeckinternet();
            System.out.println("login....i"+i);

        if(i==true) {
            RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());


            String url1 = "http://www.app.etsdc.com/response.php?fid=2&username=" + usernam + "&password=" + pass;


            StringRequest stringRequest1 = new StringRequest
                    (Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //  tv.setText("Response is: "+ response);

                            System.out.println("++++++++++++++RESPONSE+++++++++++++++ log   :" + response);
                            String userid = null,email=null,phon=null,fulnam = null,occ=null,loc=null;
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                if(jsonObj.has("userid")) {
                                 userid = jsonObj.getString("userid");
                                }
                                if(jsonObj.has("email")) {
                                   email = jsonObj.getString("email");
                                }
                                if(jsonObj.has("phone")) {
                                phon = jsonObj.getString("phone");
                                }
                                if(jsonObj.has("name")) {
                                   fulnam = jsonObj.getString("name");
                                }
                                if(jsonObj.has("occupation")) {
                                 occ = jsonObj.getString("occupation");
                                }
                                if(jsonObj.has("location")) {
                                  loc = jsonObj.getString("location");
                                }
                                System.out.println("result" + status);
                                if (!userid.contentEquals("null"))
                                {
                                    String named = jsonObj.getString("name");
                                    progress.setVisibility(ProgressBar.GONE);
                                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("userid", userid);
                                    editor.putString("email",usernam);
                                    editor.putString("name", named);
                                    editor.commit();
                                    progress.setVisibility(ProgressBar.GONE);
                                    System.out.println("_login..detaillsss........"+userid+"+useridusername="+fulnam+"&phoneno="+phon+"&email="+email);
                                    SharedPreferences preferences1 = getApplicationContext().getSharedPreferences("MyPref_login",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 =preferences1.edit();
                                    editor1.putString("fulnam", fulnam);
                                    editor1.putString("email", email);
                                    editor1.putString("phon", phon);
                                    editor1.putString("occ", occ);
                                    editor1.putString("loc", loc);
                                    editor1.commit();
                                    Intent is = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(is);
                                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                    boolean Islogin= Boolean.parseBoolean("true");
                                    prefs.edit().putBoolean("Islogin", Islogin).commit();
                                    //   pd.dismiss();
                                }
                                else
                                {

                                    progress.setVisibility(ProgressBar.GONE);
                                    final SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                    dialog.setTitleText("Alert!")
                                            .setContentText("Invalid Username or Password")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();


                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));
                                  /*  MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(LoginActivity.this)
                                            .setTitle("ALERT!")
                                            .setDescription("Empty Fields")
                                            .setHeaderColor(R.color.cart_bottombutnsclck)
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
                             /*       final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage("Invalid Username or Password");

                                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();


                                        }
                                    });
                                    alertDialog.show();
                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                    nbutton.setTextColor(getResources().getColor(R.color.Orange));*/
//                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                                }


                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }





                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //tv.setText("That didn't work!");
                            progress.setVisibility(ProgressBar.GONE);
                            Toast.makeText(getApplicationContext(),"Error please try Again",Toast.LENGTH_SHORT).show();

                        }
                    });

            queue1.add(stringRequest1);
            queue1.getCache().clear();
        }
        else {
            SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("Alert!")
                    .setContentText("Oops Your Connection Seems Off..")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));


           /* MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(getApplicationContext())
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
           /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Oops Your Connection Seems Off..");
            progress.setVisibility(ProgressBar.GONE);

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

    }



}
