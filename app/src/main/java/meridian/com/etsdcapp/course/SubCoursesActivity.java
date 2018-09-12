//package meridian.com.etsdcapp.course;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import meridian.com.etsdcapp.MainActivity;
//import meridian.com.etsdcapp.NetworkCheckingClass;
//import meridian.com.etsdcapp.R;
//import meridian.com.etsdcapp.RecyclerItemClickListener;
//
//import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;
//import meridian.com.etsdcapp.cart.CartActivity;
//import meridian.com.etsdcapp.cart.DataHelper;
//import meridian.com.etsdcapp.model.CoursesModel;
//import meridian.com.etsdcapp.sidebar.ContactUsActivity;
//
//public class SubCoursesActivity extends AppCompatActivity implements MyInterface {
//RecyclerView rv;
//    ArrayList<CoursesModel> crs_DTLS=new ArrayList<>();
//   ArrayList<CoursesModel> CRS_CLICKD=new ArrayList<>();
//    TextView tv_crc;
//    CoursesModel crc;
//    String clckd_crcnam,crcstrng;
//    ImageView img_crc;
//    CoursesModel cm;
//String crcclck,crcdes;
//    Button reg,enq;
//    String clickdval;
//    SubCourseAdapterNew rvc;
//    ArrayList<String> arrcrc=new ArrayList<>();
//    ProgressDialog pd;
//    Button butregcrc,butcrcenq, but_addcart;
//    String selcrc;
//   static String id,name,thmb;
//  static   String crcnam ;
//    String sub_course_id;
//  static   String course_description;
//    String course_number;
//    String duration;
//    String fee ;
//    String validity;
//    String objectives ;
//    String course_name ;
//    String location ;
//    String aim ;
//    DataHelper dataHelper ;
//    ImageView img_hom,img_crcdn,img_cal;
//    ImageView imgcrt;
//    String availability,target_audience;
//    String approval_certification ;
//  String k;
//   public static TextView notif;
//
//
//Context context;
//    LinearLayout laycal,layhom,laycrc,laycart;
//    String userid;
//static int notifcnt;
// static int count;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_sub_courses);
//
//        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//        userid = preferences.getString("userid", null);
//
//        notif= (TextView) findViewById(R.id.text_notif_no);
//        context = this;
//        dataHelper = new DataHelper(context);
//
//
////    CartModel cartModel=new CartModel();
////  if(cartModel.getCheckcount()==0) {
////      notif.setText(String.valueOf(0));
////  }
////        else { checkcount = cartModel.getCheckcount();
////      notif.setText(String.valueOf(checkcount));
////
////  }
//
//     ///notif.setText(""+dataHelper.getProfilesCount());
//
//
//      //  notifcnt=rvc.getNotifcnt();
//
//
//  // notif.setText(""+notifcnt);
//        System.out.println("n00"+notifcnt);
////        SQLiteDatabase db = dataHelper.getReadableDatabase();
////        long cnt = DatabaseUtils.queryNumEntries(db, DataHelper.TABLE_COURSE);
////        notif.setText(""+cnt);
//
//        TableRow tr = (TableRow) findViewById(R.id.tr);
//        rv = (RecyclerView) findViewById(R.id.rv4);
//      imgcrt = (ImageView) findViewById(R.id.img_crt);
//        img_hom = (ImageView) findViewById(R.id.img_hom);
//        img_crcdn = (ImageView) findViewById(R.id.img_crc);
//        img_cal = (ImageView) findViewById(R.id.img_cal);
//        laycal= (LinearLayout) findViewById(R.id.lay_cal);
//        laycal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycal.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),ContactUsActivity.class);
//                startActivity(i);
//            }
//        });
//        layhom= (LinearLayout) findViewById(R.id.lay_hom);
//        layhom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layhom.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);
//            }
//        });
//        laycrc= (LinearLayout) findViewById(R.id.lay_crc);
//        laycrc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycrc.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),MainCoursesActivity.class);
//                startActivity(i);
//            }
//        });
//        laycart= (LinearLayout) findViewById(R.id.lay_cart);
//        laycart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycart.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),CartActivity.class);
//                i.putExtra("id", id);
//                i.putExtra("selctedcourse", name);
//                selcrc=rvc.getstringval();
//                i.putExtra("append_selsubcrc",selcrc);
//                //   i.putStringArrayListExtra("arrcrc",arrcrc);
//
//                System.out.println("n1"+notifcnt);
//                startActivity(i);
//            }
//        });
//
//        rv.setHasFixedSize(true);
//        imgcrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycart.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),CartActivity.class);
//
//
//                i.putExtra("id", id);
//                i.putExtra("selctedcourse", name);
//                selcrc=rvc.getstringval();
//                i.putExtra("append_selsubcrc",selcrc);
//
//                System.out.println("n1"+notifcnt);
//                //   i.putStringArrayListExtra("arrcrc",arrcrc);
//                startActivity(i);
//
//            }
//        });
//        img_hom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layhom.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);
//            }
//        });
//        img_crcdn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycrc.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),MainCoursesActivity.class);
//                startActivity(i);
//            }
//        });
//        img_cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laycal.setBackgroundColor(getResources().getColor(R.color.cart_bottombutnsclck));
//                Intent i=new Intent(getApplicationContext(),ContactUsActivity.class);
//                startActivity(i);
//            }
//        });
//        //int profile_counts = remote.getProfilesCount(); remote.close();
//
////
////       if(rvc.getclkdcnt()>1)
////{               int notif_cnt=1;
////
//
//
////     }
//
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            id = extras.getString("id");
//
//            System.out.println("dtl" + id);
//            name = extras.getString("nam");
//             thmb = extras.getString("thmb");}
//        else
//        {
//
//
////            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(SubCoursesActivity.this);
////            SharedPreferences.Editor editor=preferences.edit();
////            editor.putString("id",id );
//
//        }
//
//        tv_crc= (TextView) findViewById(R.id.toolbar_CRCNAM);
//
//
////        imgcrt.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////             //  arrcrc= rvc.arrstrng();
////                //arrcrc.add(arrcrc);
////
////
////                //System.out.println("noooo"+notifcnt);
////
////            }
////        });
//
//
//        img_crc= (ImageView) findViewById(R.id.img_crcdtlnam);
//        tv_crc.setText(name);
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tops);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        Picasso.with(getApplicationContext()).load(thmb).resize(100, 100).into(img_crc);
//        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
//        rv.setLayoutManager(llm);
//
//
//        pd = new ProgressDialog(SubCoursesActivity.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
//
//        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
//        boolean i= networkCheckingClass.ckeckinternet();
//        if(i) {
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        String url = "http://meridian.net.in/demo/etsdc/response.php?fid=8&category_id="+id;
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest
//                (Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        //  tv.setText("Response is: "+ response);
//
//                        System.out.println("++++++++++++++RESPONSE+++++++++++++++   course detail :" + response);
//
//
//                        JSONObject jsonObject = null;
//
//                            try {
//                                JSONArray jsonarray = new JSONArray(response);
//
//                                for (int i = 0; i < jsonarray.length(); i++) {
//                                    CoursesModel cm=new CoursesModel();
//
//                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
//                                    crcnam = jsonobject.getString("course_name");
//                                    sub_course_id = jsonobject.getString("course_id");
//                                    course_description= jsonobject.getString("course_description");
//                                    target_audience = jsonobject.getString("target_audience");
//                                    duration = jsonobject.getString("duration");
//                                    fee = jsonobject.getString("fee");
//                                    validity = jsonobject.getString("validity");
//                                    objectives = jsonobject.getString("objectives");
//                                   // course_name = jsonobject.getString("course_name");
//                                    aim = jsonobject.getString("aim");
//                                    location= jsonobject.getString("location");
//                                    availability = jsonobject.getString("availability");
//                                    approval_certification = jsonobject.getString("approval_certification");
//                                    cm.setCrces(crcnam);
//                                    cm.setSubcoursid(sub_course_id);
//                                    cm.setCourse_description(course_description);
//                                    cm.setCourse_number(course_number);
//                                    cm.setDuration(duration);
//                                    cm.setFee(fee);
//                                    cm.setValidity(validity);
//                                    cm.setObjectives(objectives);
//                                    cm.setCoursenam(name);
//                                    cm.setLocation(location);
//                                    cm.setAim(aim);
//                                    cm.setTarget_audience(target_audience);
//                                    cm.setAvailability(availability);
//                                    cm.setApproval_certification(approval_certification);
//                                    crs_DTLS.add(cm);
//                                    pd.dismiss();
//
//                                }
//
//             MyInterface myint=new MyInterface() {
//    @Override
//    public void incrementcartcount(int cartplus) {
//        count=cartplus;
//        notif.setText(""+cartplus);
//        System.out.println("count111"+count);
//    }
//};
//                                rvc = new SubCourseAdapterNew(crs_DTLS, SubCoursesActivity.this, myint);
//
//                                System.out.println("n0d"+notifcnt);
//                                System.out.println("count112"+count);
//                                ///added for animationTLS
//                                rv.scheduleLayoutAnimation();
//                                //rv.setAdapter(adapter);
//                                //  rv.setAdapter(new AlphaInAnimationAdapter(adapter));
//                                rv.setAdapter(rvc);
//
//
//
//                            //    System.out.println(""+notifcnt);
//
//
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
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //tv.setText("That didn't work!");
//
//                    }
//                });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//        }
//        else {
//            final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(SubCoursesActivity.this).create();
//            alertDialog.setTitle("Alert");
//            alertDialog.setMessage("Oops Your Connection Seems Off..");
//
//            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    dialog.dismiss();
//
//
//                }
//            });
//            alertDialog.show();
//
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//    // getMenuInflater().inflate(R.menu.menu_toolbr_cart, menu);
//        return true;
//    }
//    @Override
//
//    public void onBackPressed() {
//        super.onBackPressed();
//        notif.setText(""+count);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        laycal.setBackgroundResource(R.color.cart_bottombutns);
//        layhom.setBackgroundResource(R.color.cart_bottombutns);
//        laycrc.setBackgroundResource(R.color.cart_bottombutns);
//        laycart.setBackgroundResource(R.color.cart_bottombutns);
//
//        notif.setText(""+count);
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        if (id == android.R.id.home) {
//            super.onBackPressed();
//            return true;
//        }
//
//
//
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        notif.setText(""+count);
//
//
//        }
//    public long notif_cnt() {
//        notifcnt = dataHelper.getProfilesCount();
//        return  notifcnt;
//    }
//
//    @Override
//    public void incrementcartcount(int cartplus) {
//        notif.setText(""+cartplus);
//
//    }
//
//
////    public static void incrementcartcount(int cartplus)
////    {
////        notif.setText(""+cartplus);
////    }
//
//
//    }
