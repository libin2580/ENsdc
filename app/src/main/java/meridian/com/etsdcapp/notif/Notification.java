package meridian.com.etsdcapp.notif;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cn.pedant.SweetAlert.SweetAlertDialog;
import me.leolin.shortcutbadger.ShortcutBadger;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.model.NotificationModel;
import meridian.com.etsdcapp.adapter.NotificationAdapter;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.RecyclerItemClickListener;

public class Notification extends AppCompatActivity {
    RecyclerView rv;
    ProgressBar progress;
    Context context;
    int counts;

    ArrayList<NotificationModel> not = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notification);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notif_top);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView) findViewById(R.id.notf);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        progress = (ProgressBar) findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        //   pd = new ProgressDialog(Notification.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
        SharedPreferences sharedPreferencesns1 = getSharedPreferences("notification", MODE_PRIVATE);
        counts = sharedPreferencesns1.getInt("notifcnt", 0);
        if (counts > 0) {
            ShortcutBadger.removeCount(context);
            SharedPreferences preferences = getSharedPreferences("notification", MODE_PRIVATE);
            preferences.edit().remove("notifcnt").commit();

        }

        load();
    }

    public void load() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://www.app.etsdc.com/response.php?fid=15";
        progress.setVisibility(ProgressBar.VISIBLE);

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ courses :" + response);

                        JSONArray jsonarray=null;
                        JSONObject jsonobject=null;
                        if (response != null && !response.isEmpty() && !response.equals("null")) {
//
                            try {
                                jsonarray = new JSONArray(response);
                                System.out.println("00");
                                for (int i = 0; i < jsonarray.length(); i++) {
                                    NotificationModel nm = new NotificationModel();
                                    System.out.println("01");
                                    jsonobject = jsonarray.getJSONObject(i);
                                    String notification_id = jsonobject.getString("notification_id");
                                    String notification = jsonobject.getString("notification");
                                    String notification_date = jsonobject.getString("notification_date");
                                    //String thumbnail = jsonobject.getString("thumbnail");
                                    //String  url2 = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/course_category/"+thumbnail;
                                    nm.setNotif(notification);
                                    nm.setNotifid(notification_id);
                                    nm.setNotif_date(notification_date);
                                    // cm.setThumbnail(url2);
                                    System.out.println("02");
                                    not.add(nm);
                                }


                                NotificationAdapter adapter0 = new NotificationAdapter(not, context);
                                System.out.println("1");
                                rv.scheduleLayoutAnimation();
                                rv.setAdapter(adapter0);
                                progress.setVisibility(View.GONE);
                                rv.addOnItemTouchListener(
                                        new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int position) {
//                                            view.setBackgroundResource(R.drawable.opito);
//                                            Intent in = new Intent(FulCourseList.this, SubCoursesActivity.class);
//                                            String id=   arrcrs.get(position).getCoursid();
//                                            System.out.println("ids"+id);
//                                            String nam=   arrcrs.get(position).getCoursenam();
//                                            String img_url=   arrcrs.get(position).getThumbnail();
//                                            in.putExtra("id", id);
//                                            in.putExtra("nam", nam);
//                                            in.putExtra("thmb", img_url);
//                                            startActivity(in);
                                            }
                                        })
                                );
                                System.out.println("2");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            progress.setVisibility(ProgressBar.GONE);



                            final SweetAlertDialog dialog = new SweetAlertDialog(Notification.this,SweetAlertDialog.NORMAL_TYPE);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.setTitleText("Alert!")
                                    .setContentText("Currently you dont have any Notifications")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            dialog.dismiss();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);

                                        }
                                    })
                                    .show();


                            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));



                        /*    final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(Notification.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("Currently you dont have any Notifications");

                            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);




                                }
                            });
                            alertDialog.show();
                            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/
                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tv.setText("That didn't work!");

                    }
                });

        queue.add(stringRequest1);


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        if (menuItem.getItemId() == android.R.id.home) {
//
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }

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


    // }
}