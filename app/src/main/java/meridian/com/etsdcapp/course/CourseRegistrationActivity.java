package meridian.com.etsdcapp.course;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.cart.DataHelper;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.model.JsonModel;
import meridian.com.etsdcapp.R;

public class  CourseRegistrationActivity extends AppCompatActivity {

    TextView AlReg;
    EditText edt_cntctprsn,edt_cmpny,edt_cty,edt_cntry,edt_tel,edt_eml,edt_crc,edt_subcrc;
    String cntctprsn,cmpny,cty,cntry,tel,eml,crc,subcrc,othr_crc;
    Button but_regcrc1,but_enqcrc1;
    ProgressDialog pd;
    String userid;
    ArrayList<JsonModel>   array_lstcart=new ArrayList<>();
    String _course,_subcourse;
    SQLiteDatabase db;
    Context context;
String statusd;
    ProgressBar progress;
 String string_apnd,_subcourseid;
    String ttt;
    String sel_sub_crc,sel_crc,replc_sel_sub_crc,replc_sel_crc;
    String REGISTER_URL="http://www.app.etsdc.com/course.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_registration);
       edt_cntctprsn= (EditText) findViewById(R.id.edt_cntct_persn);
        edt_cmpny= (EditText) findViewById(R.id.edt_cmpny);
        edt_cty= (EditText) findViewById(R.id.edt_cty);
        edt_cntry= (EditText) findViewById(R.id.edt_cntry);
        edt_tel= (EditText) findViewById(R.id.edt_tel);
        edt_eml= (EditText) findViewById(R.id.edt_eml);
//        edt_crc= (EditText) findViewById(R.id.edt_crc);
//        edt_subcrc= (EditText) findViewById(R.id.edt_subcrc);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        but_regcrc1= (Button) findViewById(R.id.but_regcrc1);
        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);

        Bundle extras=getIntent().getExtras();

//        if(extras!=null)
//        {
//            sel_sub_crc = extras.getString("selctedsubcourse");
//            // replc_sel_sub_crc=  sel_crc.replaceAll(" ","%20");
//            System.out.println(" sel_sub_crc" + sel_sub_crc);
//            sel_crc = extras.getString("selctedcourse");
////            edt_crc.setText(""+sel_crc);
////            edt_subcrc.setText(""+sel_sub_crc);
//        }
//        else
//        {
//            edt_crc.setText("");
//            edt_subcrc.setText("");
//        }


 //   replc_sel_crc=  sel_crc.replaceAll(" ","%20");

        System.out.println(" selcrc" +sel_crc);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topreg);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(CourseRegistrationActivity.this, SubCoursesActivity.class
//                );
//               // i.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
//                startActivity(i);
//
//        }
//        });
        final DataHelper dataHelper = new DataHelper(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = dataHelper.getReadableDatabase();

        Gson gson=new Gson();
        db.beginTransaction();
        try {
            String selectQuery =  "SELECT  * FROM " +DataHelper.TABLE_COURSE  ;
            System.out.println("" + selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {



                    _course = cursor.getString(cursor.getColumnIndex("_course"));



                _subcourse = cursor.getString(cursor.getColumnIndex("_subcourse"));
                    _subcourseid = cursor.getString(cursor.getColumnIndex("_subcourseid"));


                    JsonModel s=new JsonModel();

                    System.out.println("course2" + _course);
                    System.out.println("sub_coursecoursereg" + _subcourse);
                    System.out.println("sub_coursecourseregid" + _subcourseid);
                    String sc=_subcourse.replaceAll("%",",");
                    String sid=_subcourseid.replaceAll("%",",");
                    System.out.println("sub_coursecoursereg" + sc);
                    System.out.println("sub_coursecourseregid" + sid);
                    s.setSubcourse(sc);
                    s.setCourse(_course);
                    s.setSubcourseid(sid);
                    array_lstcart.add(s);




                }
                String crc_list[] = _subcourse.split("%");

                System.out.println("jsonnnarry"+  array_lstcart);
                string_apnd =gson.toJson(array_lstcart );
                System.out.println("jsonnn"+ string_apnd);

            }

            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database


        }



      //  Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Lt.otf");
//        edtfulnam.setTypeface(tf, Typeface.BOLD);
//        edtphon.setTypeface(tf, Typeface.BOLD);
//        edtfulnam.setTypeface(tf, Typeface.BOLD);
//        edtphon.setTypeface(tf, Typeface.BOLD);
//        edtoccp.setTypeface(tf, Typeface.BOLD);
//        edtpass.setTypeface(tf, Typeface.BOLD);

        but_regcrc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (userid!=null)
                    {  reg();

                    } else
                    {

                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseRegistrationActivity.this,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText("Alert!")
                                .setContentText("Please Login to Register For this Course")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(i);
                                        dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));

                                    }
                                })
                                .show();


                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));



                     /*   android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseRegistrationActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Please Login to Register For this Course");
                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(i);
                                        dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));

                                    }
                                });
                        alertDialog.show();
                        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                        nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/

                }


            }
        });
    }

    private void reg() {
        cntctprsn = edt_cntctprsn.getText().toString();
        cmpny = edt_cmpny.getText().toString();
        cty = edt_cty.getText().toString();
        cntry = edt_cntry.getText().toString();
        tel = edt_tel.getText().toString();
        eml = edt_eml.getText().toString();
        context = this;


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
 ttt = sharedPreferences.getString("gcmToken", null);
// Toast.makeText(getApplicationContext()," "+ttt,Toast.LENGTH_SHORT).show();
        System.out.print("++++++++++++++++++++++++" + ttt);
        System.out.println("userrid___courseregistration"+userid+"......"+cntctprsn+"......"+cmpny+"......"+cty+"......"+cntry+"......"+tel+"......"+eml+"......"+ttt+"......"+string_apnd);

        if ( cntctprsn.matches("") || cmpny.matches("") || cty.matches("") || cntry.matches("") || tel.matches("") || eml.matches("")) {

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


         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseRegistrationActivity.this).create();
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
            nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
        }
        else  if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edt_eml.getText().toString().trim()).matches()) {
            edt_eml.setError("Invalid Email");

        }else {


            edt_eml.setError(null);
            NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(getApplicationContext());
            boolean i = networkCheckingClass.ckeckinternet();
            progress.setVisibility(ProgressBar.VISIBLE);

//            pd = new ProgressDialog(CourseRegistrationActivity.this);
//            pd.setTitle("Registering...");
//            pd.setMessage("Please wait...");
//            pd.setCancelable(true);
//            pd.show();


            final DataHelper dataHelper = new DataHelper(context);


            if (i==true)
            {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                        System.out.println("responseee...course registration"+response);
                                JSONObject jsonObj = null;
                                try {
                                    jsonObj = new JSONObject(response);
                                    statusd = jsonObj.getString("status");
                                    System.out.println("statussssscours"+statusd);
                                    progress.setVisibility(ProgressBar.GONE);
                                   // pd.dismiss();

                                      /*  AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                        alertDialog.setTitle("Alert");*/
                                    if (statusd.contentEquals("success")) {
                                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseRegistrationActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                        dialog.setTitleText("Success!")
                                                .setContentText("You have successfully booed the course/s")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                        // alertDialog.setMessage("You have successfully booked the course/s");
                                    }
                                        else {
                                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseRegistrationActivity.this,SweetAlertDialog.NORMAL_TYPE);
                                               dialog .setContentText(statusd)
                                                .show();


                                        dialog.findViewById(R.id.confirm_button).setVisibility(View.GONE);

                                        //  alertDialog.setMessage(statusd);
                                        }

                                    final SweetAlertDialog dialog = new SweetAlertDialog(CourseRegistrationActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                                    dialog.setTitleText("Success!")
                                        //    .setContentText("Empty Fields")
                                        .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    dataHelper.getWritableDatabase();
                                                    if (statusd.contentEquals("success")) {
                                                        dataHelper.removeAll();
                                                        SharedPreferences sharedPref = getSharedPreferences("sharedstatus", Context.MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sharedPref.edit();
                                                        editor.putString("status", statusd);
                                                        editor.apply();
                                                        SharedPreferences sharedPref3 = getSharedPreferences("countnum", Context.MODE_PRIVATE);
                                                        SharedPreferences.Editor editor3 = sharedPref3.edit();
                                                        editor3.putInt("count", 0);
                                                        editor3.commit();
                                                        Intent i = new Intent(getApplicationContext(),MainCoursesActivity.class);
                                                        finish();
                                                        startActivity(i);
                                                    }
                                                    dialog.dismiss();


                                                }
                                            })
                                            .show();


                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                      /*  alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dataHelper.getWritableDatabase();
                                                        if (statusd.contentEquals("success")) {
                                                            dataHelper.removeAll();
                                                            SharedPreferences sharedPref = getSharedPreferences("sharedstatus", Context.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedPref.edit();
                                                            editor.putString("status", statusd);
                                                            editor.apply();
                                                            SharedPreferences sharedPref3 = getSharedPreferences("countnum", Context.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor3 = sharedPref3.edit();
                                                            editor3.putInt("count", 0);
                                                            editor3.commit();
                                                            Intent i = new Intent(getApplicationContext(),MainCoursesActivity.class);
                                                            finish();
                                                            startActivity(i);
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
                    protected Map<String, String> getParams()
                    {   Map<String, String> params = new HashMap<String, String>();
                        params.put("contact_person", cntctprsn);
                        params.put("company", cmpny);
                        params.put("city", cty);
                        params.put("country", cntry);
                        params.put("phone", tel);
                        params.put("email", eml);
                        params.put("userid", userid);
                        params.put("someJSON",string_apnd);
                        params.put("device_tokens", ttt);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
                requestQueue.getCache().clear();
            }
            else
            {
                final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
                dialog.setTitleText("Alert!")
                        .setContentText("Oops Your Connecton Seems Off...")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                dialog.dismiss();
                            }
                        })
                        .show();


                dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));


          /*      final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseRegistrationActivity.this).create();
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
    }

    @Override
    protected void onResume() {




//            edt_crc.setText(""+sel_crc);
//            edt_subcrc.setText(""+sel_sub_crc);


        super.onResume();
    }

    @Override
    public void onBackPressed() {


            super.onBackPressed();

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
//    public class TestObjectToJson {
//        private int course = 100;
//        private String data2 = "hello";
//
//
//            TestObjectToJson obj = new TestObjectToJson();
//            Gson gson = new Gson();
//
//            //convert java object to JSON format
//            String json = gson.toJson(obj);
//
//
//
//
//    }

}
