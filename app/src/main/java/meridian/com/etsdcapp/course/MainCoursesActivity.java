package meridian.com.etsdcapp.course;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.model.CoursesModel;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.MainCourseAdapter;
import meridian.com.etsdcapp.RecyclerItemClickListener;

public class MainCoursesActivity extends AppCompatActivity {
RecyclerView rv;
    ArrayList<CoursesModel> arrcrs=new ArrayList<>();
    MainCourseAdapter adapter0;
    ImageView crc;
    ProgressDialog pd;
    Context context;
    String url2;
   static String nam,maincrc_id;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_courses);
//        crc= (ImageView) findViewById(R.id.img_course);
//        crc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              crc.setBackgroundResource(R.drawable.opito);
//
//            }
//        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
       context=getApplicationContext();
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainCoursesActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView) findViewById(R.id.rv3);
         GridLayoutManager llm = new GridLayoutManager(getApplicationContext(),2);
        rv.setLayoutManager(llm);
        progress.setVisibility(ProgressBar.VISIBLE);
//        pd = new ProgressDialog(MainCoursesActivity.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
        boolean i= networkCheckingClass.ckeckinternet();
        if(i==true) {
       // rv.setHasFixedSize(true);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://www.app.etsdc.com/response.php?fid=3";


// Request a string response from the provided URL.

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ courses :" + response);

//
//                        JSONObject jsonObject = null;
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            System.out.println("00");
                                for (int i = 0; i < jsonarray.length(); i++) {
                                CoursesModel cm=new CoursesModel();
                                    System.out.println("01");
                                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                                    String course_id = jsonobject.getString("course_id");
                                    String course = jsonobject.getString("course");
                                    String thumbnail = jsonobject.getString("thumbnail");
                                    url2 = "http://www.app.etsdc.com/uploads/course_category/"+thumbnail;
                                     cm.setCoursid(course_id);
                                    cm.setCoursenam(course);
                                    cm.setThumbnail(url2);


                                    System.out.println("02");

                                arrcrs.add(cm);


                                }


                            adapter0= new MainCourseAdapter(arrcrs, getApplicationContext());
                        System.out.println("1");
                                rv.scheduleLayoutAnimation();
                            rv.setAdapter(adapter0);
                            rv.setHasFixedSize(true);
                            progress.setVisibility(ProgressBar.GONE);
//                            pd.dismiss();


                            rv.addOnItemTouchListener(
                                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            view.setBackgroundResource(R.drawable.opito);
                                            Intent in = new Intent(MainCoursesActivity.this, SubCoursesActivity1.class);
                                           maincrc_id=   arrcrs.get(position).getCoursid();
                                            System.out.println("ids"+maincrc_id);
                                           nam=   arrcrs.get(position).getCoursenam();
                                            String img_url=   arrcrs.get(position).getThumbnail();
                                            in.putExtra("id", maincrc_id);
                                            in.putExtra("nam", nam);
                                            in.putExtra("thmb", img_url);
                                            startActivity(in);
                                            SharedPreferences sharedPref =context.getSharedPreferences("pref1",Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPref.edit();
                                            editor.putString("thmb", img_url);
                                            editor.putString("coursename", nam);
                                            editor.commit();

                                        }
                                    })
                            );

                            System.out.println("2");





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

        queue.add(stringRequest1);
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

           /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(MainCoursesActivity.this).create();
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
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        if (menuItem.getItemId() == android.R.id.home) {
//
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(in);
        rv.removeAllViews();
//        rv.removeOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                v.setBackgroundResource(R.color.trans);
//            }
//        });
       // rv.setBackgroundResource(R.drawable.bg);
    }
    @Override
    protected void onResume() {
        super.onResume();
        rv.removeAllViews();

        //  rv.setBackgroundResource(R.drawable.bg);

    }
    public String maincorc()
    {

        return maincrc_id ;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {


            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
