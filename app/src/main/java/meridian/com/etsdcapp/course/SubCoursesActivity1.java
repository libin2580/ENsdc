package meridian.com.etsdcapp.course;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.RecyclerItemClickListener;
import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;

import meridian.com.etsdcapp.cart.CartActivityNews;
import meridian.com.etsdcapp.cart.DataHelper;
import meridian.com.etsdcapp.model.CoursesModel;
import meridian.com.etsdcapp.sidebar.ContactUsActivity;

public class SubCoursesActivity1 extends AppCompatActivity  {
RecyclerView rv;
    ArrayList<CoursesModel> crs_DTLS=new ArrayList<>();
   ArrayList<CoursesModel> CRS_CLICKD=new ArrayList<>();
    TextView tv_crc;
    CoursesModel crc;
    String clckd_crcnam,crcstrng;
    ImageView img_crc;
    CoursesModel cm;
String crcclck,crcdes;
    Button reg,enq;
    String clickdval;
    SubCourseAdapterNew rvc;
    ArrayList<String> arrcrc=new ArrayList<>();
    ProgressDialog pd;
    Button butregcrc,butcrcenq, but_addcart;
    String selcrc;
   static String id,name,thmb;
  static   String crcnam ;
    String sub_course_id;
  static   String course_description;
    String course_number;
    String duration;
    String fee ;
    String validity;
    String objectives ;
    String course_name ;
    String location ;
    String aim ;
    DataHelper dataHelper ;
    ImageView img_hom,img_crcdn,img_cal;
    ImageView imgcrt;
    String availability,target_audience;
    String approval_certification ;
  String k;
   public static TextView notif;
    static int cntsub;
    int p;


Context context;
    LinearLayout laycal,layhom,laycrc,laycart;
    String userid;
static int notifcnt;
 static int count;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(savedInstanceState!=null) {
//            cntsub = savedInstanceState.getInt("cntsub");
//            System.out.println("savedInstanceState"+cntsub);
//            Toast.makeText(this, savedInstanceState .getInt("cntsub"), Toast.LENGTH_LONG).show();
//        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sub_courses);

        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);

        notif= (TextView) findViewById(R.id.text_notif_no);
        context = this;
        dataHelper = new DataHelper(context);


//        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
//        count=sharedPref1.getInt("count",0);
//        System.out.println("main"+count);
//        notif.setText(""+count);
//        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
//        count=sharedPref1.getInt("count",0);




//    CartModel cartModel=new CartModel();
//  if(cartModel.getCheckcount()==0) {
//      notif.setText(String.valueOf(0));
//  }
//        else { checkcount = cartModel.getCheckcount();
//      notif.setText(String.valueOf(checkcount));
//
//  }

     ///notif.setText(""+dataHelper.getProfilesCount());


      //  notifcnt=rvc.getNotifcnt();


  // notif.setText(""+notifcnt);
        System.out.println("n00"+notifcnt);
//        SQLiteDatabase db = dataHelper.getReadableDatabase();
//        long cnt = DatabaseUtils.queryNumEntries(db, DataHelper.TABLE_COURSE);
//        notif.setText(""+cnt);

        TableRow tr = (TableRow) findViewById(R.id.tr);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        rv = (RecyclerView) findViewById(R.id.rv4);
      imgcrt = (ImageView) findViewById(R.id.img_crt);
        img_hom = (ImageView) findViewById(R.id.img_hom);
        img_crcdn = (ImageView) findViewById(R.id.img_crc);
        img_cal = (ImageView) findViewById(R.id.img_cal);
        laycal= (LinearLayout) findViewById(R.id.lay_cal);
        laycal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laycal.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),ContactUsActivity.class);
                startActivity(i);
            }
        });
        layhom= (LinearLayout) findViewById(R.id.lay_hom);
        layhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layhom.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        laycrc= (LinearLayout) findViewById(R.id.lay_crc);
        laycrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laycrc.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),MainCoursesActivity.class);
                startActivity(i);
            }
        });
        laycart= (LinearLayout) findViewById(R.id.lay_cart);
        laycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laycart.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),CartActivityNews.class);
                i.putExtra("id", id);
                i.putExtra("selctedcourse", name);
                selcrc=rvc.getstringval();
                i.putExtra("append_selsubcrc",selcrc);
                //   i.putStringArrayListExtra("arrcrc",arrcrc);

                System.out.println("n1"+notifcnt);
                startActivity(i);
            }
        });

        rv.setHasFixedSize(true);
        imgcrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
                count=sharedPref1.getInt("count",0);
                notif.setText(""+count);

                laycart.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),CartActivityNews.class);


                i.putExtra("id", id);
                i.putExtra("selctedcourse", name);
                i.putExtra("count", count);
                selcrc=rvc.getstringval();
                i.putExtra("append_selsubcrc",selcrc);

                System.out.println("n1"+notifcnt);
                //   i.putStringArrayListExtra("arrcrc",arrcrc);
                startActivity(i);

            }
        });
        img_hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layhom.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        img_crcdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laycrc.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),MainCoursesActivity.class);
                startActivity(i);
            }
        });
        img_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laycal.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
                Intent i=new Intent(getApplicationContext(),ContactUsActivity.class);
                startActivity(i);
            }
        });
        //int profile_counts = remote.getProfilesCount(); remote.close();

//
//       if(rvc.getclkdcnt()>1)
//{               int notif_cnt=1;
//


//     }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");

            System.out.println("dtl" + id);
            name = extras.getString("nam");
             thmb = extras.getString("thmb");}
        else
        {


//            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(SubCoursesActivity.this);
//            SharedPreferences.Editor editor=preferences.edit();
//            editor.putString("id",id );

        }

        tv_crc= (TextView) findViewById(R.id.toolbar_CRCNAM);


//        imgcrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             //  arrcrc= rvc.arrstrng();
//                //arrcrc.add(arrcrc);
//
//
//                //System.out.println("noooo"+notifcnt);
//
//            }
//        });


        img_crc= (ImageView) findViewById(R.id.img_crcdtlnam);
        tv_crc.setText(name);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tops);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Picasso.with(getApplicationContext()).load(thmb).resize(100, 100).into(img_crc);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        progress.setVisibility(ProgressBar.VISIBLE);

//        pd = new ProgressDialog(SubCoursesActivity1.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();

        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
        boolean i= networkCheckingClass.ckeckinternet();
        if(i==true) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://www.app.etsdc.com/response.php?fid=8&category_id="+id;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++   course detail :" + response);




                            try {
                                JSONArray jsonarray = new JSONArray(response);

                                for (int i = 0; i < jsonarray.length(); i++) {
                                    CoursesModel cm=new CoursesModel();

                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                    crcnam = jsonobject.getString("course_name");
                                    sub_course_id = jsonobject.getString("course_id");
                                    course_description= jsonobject.getString("course_description");
                                    target_audience = jsonobject.getString("target_audience");
                                    duration = jsonobject.getString("duration");
                                    fee = jsonobject.getString("fee");
                                    validity = jsonobject.getString("validity");
                                    objectives = jsonobject.getString("objectives");
                                   // course_name = jsonobject.getString("course_name");
                                    aim = jsonobject.getString("aim");
                                    location= jsonobject.getString("location");
                                    availability = jsonobject.getString("availability");
                                    approval_certification = jsonobject.getString("approval_certification");
                                    cm.setCrces(crcnam);
                                    cm.setSubcoursid(sub_course_id);
                                    cm.setCourse_description(course_description);
                                    cm.setCourse_number(course_number);
                                    cm.setDuration(duration);
                                    cm.setFee(fee);
                                    cm.setValidity(validity);
                                    cm.setObjectives(objectives);
                                    cm.setCoursenam(name);
                                    cm.setLocation(location);
                                    cm.setAim(aim);
                                    cm.setTarget_audience(target_audience);
                                    cm.setAvailability(availability);
                                    cm.setApproval_certification(approval_certification);
                                    crs_DTLS.add(cm);
                                    progress.setVisibility(ProgressBar.GONE);
                                   // pd.dismiss();

                                }



                                rvc = new SubCourseAdapterNew(crs_DTLS, SubCoursesActivity1.this);




                                System.out.println("n0d"+notifcnt);
                                System.out.println("count112"+count);
                                ///added for animationTLS
                                rv.scheduleLayoutAnimation();
                                rv.setHasFixedSize(true);
                                //rv.setAdapter(adapter);
                                //  rv.setAdapter(new AlphaInAnimationAdapter(adapter));
                                rv.setAdapter(rvc);




                            //    System.out.println(""+notifcnt);


//
//                                rv.addOnItemTouchListener
//                                        (
//                                                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                                                    @Override
//                                                    public void onItemClick(View view, int position) {
////
//
//
//                                                    }
//                                                })
//                                        );
////

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

           /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(SubCoursesActivity1.this).create();
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

    public void onBackPressed() {
     //   super.onBackPressed();
        Intent in=new Intent(getApplicationContext(),MainCoursesActivity.class);
        startActivity(in);
       // SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);

        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
        count=sharedPref1.getInt("count",0);

        notif.setText(""+count);
//        count=sharedPref1.getInt("count",0);c
//        notif.setText(""+count);
    }

    @Override
    protected void onResume() {
        super.onResume();

        laycal.setBackgroundResource(R.color.cart_bottombutns);
        layhom.setBackgroundResource(R.color.cart_bottombutns);
        laycrc.setBackgroundResource(R.color.cart_bottombutns);
        laycart.setBackgroundResource(R.color.cart_bottombutns);

        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
        count=sharedPref1.getInt("count",0);
        notif.setText(""+count);
        if(count==0)
        {
            notif.setText(""+0);
        }

        System.out.println("onresumee"+cntsub);
        String status;

        SharedPreferences sharedPref =getSharedPreferences("sharedstatus",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("status","nothing");
        editor.commit();
        SharedPreferences sharedPref2 =getSharedPreferences("sharedstatus",Context.MODE_PRIVATE);
        status=  sharedPref2.getString("status",null);


        if(status.contains("success"))
        {  SharedPreferences sharedPref3 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor3 = sharedPref3.edit();
            editor3.putInt("count",0);
            editor3.commit();
            count=  sharedPref3.getInt("count",0);
            notif.setText(""+count);
        }
        else  if(status.equals("nothing")||status.equals(null))
        {
            notif.setText(""+count);
        }
        else  if(status.equals("failure"))
        {
            notif.setText(""+count);
        }

       System.out.println("statuses"+status);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbr_cart, menu);
        SearchManager searchManager = (SearchManager) SubCoursesActivity1.this.getSystemService(Context.SEARCH_SERVICE);
        // Associate searchable configuration with the SearchView
        // SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_cart));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(SubCoursesActivity1.this.getComponentName()));



        EditText txtSearch = ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint(getResources().getString(R.string.search_hint));
        txtSearch.setHintTextColor(Color.LTGRAY);
        txtSearch.setTextColor(Color.WHITE);

        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                rvc.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvc.filter(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu);
    return true;
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
            Intent in=new Intent(getApplicationContext(),MainCoursesActivity.class);
            startActivity(in);
            return true;
        }




        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();

//        cntsub= Integer.parseInt(notif.getText().toString());
        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
        count=sharedPref1.getInt("count",0);
        System.out.println("onpause"+cntsub);
        notif.setText(""+count);



        }
    public long notif_cnt() {
        notifcnt = dataHelper.getProfilesCount();
        return  notifcnt;
    }
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        cntsub= Integer.parseInt(notif.getText().toString());
//        savedInstanceState.putInt("cntsub", cntsub);
//
//        // Call the superclass to save the state of all the other controls in the view hierarchy
//        super.onSaveInstanceState(savedInstanceState);
//    }
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        // Call the superclass to restore the state of all the other controls in the view hierarchy
//        super.onRestoreInstanceState(savedInstanceState);
//
//       notif.setText(savedInstanceState.getInt("cntsub"));
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("cntsub", cntsub);
//        super.onSaveInstanceState(outState);
//    }
    //    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//
   //   cntsub= Integer.parseInt(notif.getText().toString());
//        System.out.println("cntsubsubcourses2"+cntsub);
////        notif.setText(""+cntsub);
     //   savedInstanceState.putInt("cntsub", cntsub);
//        super.onSaveInstanceState(savedInstanceState);
//
//    }


    //    public static void incrementcartcount(int cartplus)
//    {
//        notif.setText(""+cartplus);
//    }


    }
