package meridian.com.etsdcapp.sidebar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;


public class FeedBackActivity extends AppCompatActivity {
    Button butfeed;
    ProgressDialog pd;
    EditText name,email,msg;
    ProgressBar progress;
    boolean edittexterror=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feed_back);

        name = (EditText) findViewById(R.id.nam);
        email = (EditText) findViewById(R.id.emal);
        msg = (EditText) findViewById(R.id.msg);
        butfeed = (Button) findViewById(R.id.but_feed);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        // int cnts=rvc.getclkdcnt();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final String chk= preferences.getString("email",null);
        if(chk!=null) {
            email.setText(chk);

        }



        butfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = name.getText().toString();
                String msgs = msg.getText().toString();
                edittexterror = false;
                if(chk!=null) {
                    email.setText(chk);

                }
                String emls= email.getText().toString();
                System.out.println("g31"+emls);
                if(nam.isEmpty())
                { name.setError("Enter Your Name");
                    edittexterror=true;

                }

                if(msgs.isEmpty())
                {
                    msg.setError("Enter Your Message");
                    edittexterror=true;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher( emls).matches()) {
                    email.setError("Invalid Email");
                    edittexterror = true;
                }
                if(  emls.isEmpty())
                {
                    email.setError("Enter Your Email");
                    edittexterror=true;
                }

                if(nam.isEmpty()||msgs.isEmpty()|| emls.isEmpty()) {

                    final SweetAlertDialog dialog = new SweetAlertDialog(FeedBackActivity.this,SweetAlertDialog.NORMAL_TYPE);
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


                 /*   final android.support.v7.app.AlertDialog alertDialog1= new android.support.v7.app.AlertDialog.Builder(FeedBackActivity.this).create();
                    alertDialog1.setTitle("Alert");
                    alertDialog1.setIcon(R.drawable.warning_blue);
                    alertDialog1.setMessage("Empty Fields");

                    alertDialog1.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();


                        }
                    });
                    alertDialog1.show();
                    Button nbutton = alertDialog1.getButton(DialogInterface.BUTTON_NEUTRAL);
                    nbutton.setTextColor(getResources().getColor(R.color.Orange));*/
                }



                //  String emal = email.getText().toString();

                if (edittexterror == false) {
                    msg.setError(null);
                    name.setError(null);
                    email.setError(null);
                    System.out.println("g32"+emls+msgs+chk+nam);
                    NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(FeedBackActivity.this);
                    boolean i = networkCheckingClass.ckeckinternet();
                    if (i == true) {

                        progress.setVisibility(ProgressBar.VISIBLE);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        String url = "http://www.app.etsdc.com/response.php?fid=13&name=" + nam + "&email=" + emls + "&message=" + msgs;
                        StringRequest stringRequest3 = new StringRequest
                                (Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.
                                        //  tv.setText("Response is: "+ response);
//pd.dismiss();
                                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ gallery :" + response);
                                        if (response != null) {

                                            JSONArray jsonarray;
                                            JSONObject jsonobject = null;

                                            try {

                                                jsonobject = new JSONObject(response);



                                                System.out.println("g3");




                                                String status = jsonobject.getString("status");
                                                System.out.println("statussssscours"+status);
                                                if (status.contentEquals("success"))
                                                {

                                                 Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                                                    progress.setVisibility(ProgressBar.GONE);

                                                    final SweetAlertDialog dialog = new SweetAlertDialog(FeedBackActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                                    dialog.setTitleText("Feedback Submitted")
                                                          //  .setContentText("You have given your feedback")
                                                            .setConfirmText("OK")
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sDialog) {
                                                                    Intent is = new Intent(getApplicationContext(), MainActivity.class);
                                                                    startActivity(is);
                                                                    finish();
                                                                    dialog.dismiss();
                                                                }
                                                            })
                                                            .show();


                                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));



                                                 /*   final AlertDialog alertDialog = new AlertDialog.Builder(FeedBackActivity.this).create();
                                                    alertDialog.setTitle(status);
                                                    alertDialog.setMessage("You have given your feedback");

                                                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent is = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(is);
                                                            finish();
                                                            dialog.dismiss();


                                                        }
                                                    });
                                                    alertDialog.show();
                                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/


                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                                                    progress.setVisibility(ProgressBar.GONE);

                                                }





                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            System.out.println("nothing to displayy");
                                        }
                                    }


                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //tv.setText("That didn't work!");

                                    }
                                });


                        queue.add(stringRequest3);


                    } else {

                        final SweetAlertDialog dialog = new SweetAlertDialog(FeedBackActivity.this,SweetAlertDialog.NORMAL_TYPE);
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


                       /* final AlertDialog alertDialog = new AlertDialog.Builder(FeedBackActivity.this).create();
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
                }
            }

        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tops);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SubCoursesActivity.this, MainCoursesActivity.class);
//                startActivity(i);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Change to gridLayout
        //  rv.setLayoutManager(new GridLayoutManager(context, 2));
        //llm.scrollToPositionWithOffset(10, 0);
//    ArrayList<RegisterdUsersModel>arreg=new ArrayList<>();
//        for(int i=0 ; i<arreg.size() ; i++){
//        String s= arreg.get(i).getRegisemail();
//            System.out.println("val"+s);
//        }
//        RegisterdUsersModel ru=new RegisterdUsersModel();
//        String reguser=ru.getRegisemail();
//        System.out.println("registereduser"+reguser);



    }



    @Override

    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
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