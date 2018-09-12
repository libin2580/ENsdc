package meridian.com.etsdcapp.course;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.model.CoursesModel;

public class CourseEnquireActivity extends AppCompatActivity {
    String sel_sub_crc,sel_crc,thmb,crc_descrptn,crc_numbr;
    ImageView crcpc;
    TextView crc,subcrc,obj,dur,aim,loc,val,txt_descr;
    ArrayList<CoursesModel> crsdtls;
    String course_description;
    String course_number;
    String duration;
    String fee ;
    String validity;
    String objectives ;
    String course_name ;
    String location ;
    String aims ;
    String availability;
    EditText edtenq;
    ProgressDialog pd;
    String approval_certification,target_audience,coursesname ;
    Button button_crc_enq;
    String userid,fulnam,email,phon,coursethmb,coursename,edt_enquire=null,thmbs;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_enquire);
        crc = (TextView) findViewById(R.id.txtcrcenq);
        subcrc = (TextView) findViewById(R.id.txtsubenq);
        dur = (TextView)findViewById(R.id.txt_dur);
        val = (TextView)findViewById(R.id.txt_val);
        txt_descr = (TextView)findViewById(R.id.txt_descr);
        edtenq= (EditText) findViewById(R.id.edt_enq);
        button_crc_enq= (Button) findViewById(R.id.but_courseenq);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref_login", MODE_PRIVATE);

        fulnam = preferences.getString("fulnam", null);
        email = preferences.getString("email", null);
        phon = preferences.getString("phon", null);

        System.out.println("_enquireee.sharedd.detaillsss........"+userid+"+useridusername="+fulnam+"&phoneno="+phon+"&email="+email);

        SharedPreferences sharedPref1 = getSharedPreferences("pref1", Context.MODE_PRIVATE);
        thmbs=sharedPref1.getString("thmb",null);
        SharedPreferences sharedPref =getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        // coursethmb =sharedPref.getString("thmb", null);
        coursename = sharedPref.getString("coursename", null);
        SharedPreferences preferences1= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences1.getString("userid", null);
        //  aim = (TextView)findViewById(R.id.txt_crcaim);
        // descr = (TextView)findViewById(R.id.txt_descr);
        //  target_audiences= (TextView)findViewById(R.id.txt_crcnmbr);
        // loc = (TextView)findViewById(R.id.txt_crcloc);
        crcpc = (ImageView) findViewById(R.id.imgcrcenq);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sel_crc = extras.getString("sel_crc");
            System.out.println("sel_sub_crc" +sel_crc);
            crc_descrptn = extras.getString("course_description");
            target_audience = extras.getString("target_audience");
            coursesname = extras.getString("coursesname");
            duration = extras.getString("duration");
            fee = extras.getString("fee");
            validity = extras.getString("validity");
            objectives = extras.getString("objectives");
            aims = extras.getString("aim");
            location = extras.getString("location");
            availability = extras.getString("availability");
            approval_certification = extras.getString("approval_certification)");
            thmb = extras.getString("thmbs");
            //  Picasso.with(getApplicationContext()).load(thmb).fit().into(crcpc);
            int cnt = extras.getInt("cnt");
        }
        crc.setText("" + coursesname);

        subcrc.setText("" + sel_crc);
        Picasso.with(getApplicationContext()).load(thmbs).fit().into(crcpc);
        // obj.setText(objectives);
        //  dur.setText(crc_numbr);
        // aim.setText(aims);
        // loc.setText(location);
        val.setText(validity);

        // descr.setText(crc_descrptn);
        dur.setText(duration);
        txt_descr.setText(crc_descrptn);

        // target_audiences.setText(target_audience);

        button_crc_enq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {



                if (userid!=null)
                {


                    if(edtenq.getText().toString().isEmpty()) {
                        edtenq.setError("Empty Field");
                    }
                    else
                    {
                        enq();
                    }

                } else
                {

                    final SweetAlertDialog dialog = new SweetAlertDialog(CourseEnquireActivity.this,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alert")
                            .setContentText("Please Login to Enquire For this Course")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
                                    Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(i);
                                }
                            })
                            .show();


                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                /*    android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseEnquireActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please Login to Enquire For this Course");
                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
                                    Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(i);
                                }
                            });
                    alertDialog.show();
                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    nbutton.setTextColor(getResources().getColor(R.color.Orange));

*/

                }





            }
        });





        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tops);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CourseEnquireActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void enq()
    {  progress.setVisibility(ProgressBar.VISIBLE);
//
        String course_new=coursesname.replaceAll(" ","%20");
        String subcourse_new=sel_crc.replaceAll(" ","%20");


//        pd = new ProgressDialog(CourseEnquireActivity.this);
//        pd.setTitle("Submitting...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
        edt_enquire=edtenq.getText().toString();
        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
        boolean i= networkCheckingClass.ckeckinternet();
        if(i==true) {



            System.out.println("useridenqryyy........" + userid + "+useridusername=" + fulnam + "&phoneno=" + phon + "&email=" + email + "&course=" + coursesname + "&subcourse=" + sel_crc + "&enquiry=" + edt_enquire);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "http://www.app.etsdc.com/response.php?fid=11&userid=" + userid + "&username=" + fulnam + "&phoneno=" + phon + "&email=" + email + "&course=" + course_new + "&subcourse=" + subcourse_new + "&enquiry=" + edt_enquire;
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest
                    (Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //  tv.setText("Response is: "+ response);

                            System.out.println("++++++++++++++RESPONSE+++++++++++++++   course detail :" + response);


                            JSONObject jsonObject = null;

                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String status = jsonObj.getString("status");
                                System.out.println("result" + status);
                                if (status.equals("success")) {

                                    if (status.equals("success")) {
                                        progress.setVisibility(ProgressBar.GONE);
                                        // pd.dismiss();
                                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseEnquireActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                        dialog.setTitleText("Enquiry Submitted")
                                                .setContentText("")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        Intent i = new Intent(getApplicationContext(), SubCoursesActivity1.class);
                                                        startActivity(i);
                                                        finish();
                                                        dialog.dismiss();

                                                    }
                                                })
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                   /*     final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseEnquireActivity.this).create();
                                        alertDialog.setTitle("Alert");
                                        alertDialog.setMessage(status);

                                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                                Intent i = new Intent(getApplicationContext(), SubCoursesActivity1.class);
                                                startActivity(i);
                                                finish();
                                                dialog.dismiss();


                                            }
                                        });
                                        alertDialog.show();
                                        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                        nbutton.setTextColor(getResources().getColor(R.color.Orange));*/


                                    } else {
                                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseEnquireActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                        dialog.setTitleText(status)
                                                .setContentText("")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                     /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseEnquireActivity.this).create();
                                        alertDialog.setTitle("Alert");
                                        alertDialog.setMessage(status);

                                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();


                                            }
                                        });
                                        alertDialog.show();
                                        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                        nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
                                        //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                                    }

                                }
//

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //tv.setText("That didn't work!");

                        }
                    });
// Add the request to the RequestQueue.
            queue.add(stringRequest);
            queue.getCache().clear();


        }
        else {

            final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("Alert!")
                    .setContentText("Oops Your Connection Seems Off...")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialog.dismiss();
                        }
                    })
                    .show();


            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseEnquireActivity.this).create();
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
            nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/

        }
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