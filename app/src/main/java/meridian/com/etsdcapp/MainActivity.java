package meridian.com.etsdcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.course.MainCoursesActivity;
import meridian.com.etsdcapp.gallery.GalleryActivity;

import meridian.com.etsdcapp.gcm.RegistrationIntentService;
import meridian.com.etsdcapp.library.LibraryActivity;
import meridian.com.etsdcapp.sidebar.ChangePassword;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.notif.Notification;
//import meridian.com.etsdcapp.gcm.RegistrationIntentService;
import meridian.com.etsdcapp.schedule.CalendarActivity;
import meridian.com.etsdcapp.sidebar.AboutUsActivity;
import meridian.com.etsdcapp.sidebar.ContactUsActivity;
import meridian.com.etsdcapp.sidebar.ContactUsActivity1;
import meridian.com.etsdcapp.sidebar.FeedBackActivity;
import meridian.com.etsdcapp.sidebar.MapsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener {

    private DrawerLayout drawer;
    private static final int MENU_ITEMS = 5;
    private final ArrayList<View> mMenuItems = new ArrayList<>(MENU_ITEMS);
    ImageView imv;
    NavigationView navView;
    private SliderLayout mDemoSlider;
    TextView txtunam;
    LinearLayout l1,l2,l3,l4,l5;
TextView crs,sched,gal,book,remaindr,sched1;
    SliderLayout sliderShow;

    String userid,unams;
    ArrayList<String> imgarry=new ArrayList<>();
    Button  bcrc,bglry,bsch,butbuk,butrem;
    static final int NUM_ITEMS = 4;
    int imageArra[] = { R.drawable.introimg2, R.drawable.introimg3,
            R.drawable.introimg4, R.drawable.introimg5,};
    String REGISTER_URL="http://www.app.etsdc.com/response.php?";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       sliderShow = (SliderLayout) findViewById(R.id.slider);
        sched1= (TextView) findViewById(R.id.txtblw);


        Typeface typek = Typeface.createFromAsset(getAssets(), "fonts/roboto-condensed.bold.ttf");
        sched1.setTypeface(typek);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            sched1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }



        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);


        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);

        String dd=sharedPreferences1.getString("gcmToken",null);
    //    Toast.makeText(getApplicationContext(),"GCM: "+dd,Toast.LENGTH_SHORT).show();
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            System.out.println("tokennnnmainn"+dd);
            startService(intent);
        }
//




        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());
        // "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/response.php?fid=11&contact_person=new%20subin&company=companyname&city=calicut&country=india&phone=9989898989&email=subin@cybraum.net&course=OPITO&subcourse=subcourse1,subcourse2"
        String url1 = "http://meridian.net.in/demo/etsdc/response.php?fid=14";

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++banner    :" + response);



                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++)
                            { DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext());
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String banner_id = jsonobject.getString("banner_id");
                                String banner = jsonobject.getString("banner");
                                 defaultSliderView.image("http://www.app.etsdc.com/uploads/banner/"+banner);
                                sliderShow.addSlider(defaultSliderView);
                            }






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

      queue1.add(stringRequest1);


        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);


        bsch= (Button) findViewById(R.id.but_schedules);
        bsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        butrem= (Button) findViewById(R.id.but_remaindr);
        butrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this,Notification.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });



        butbuk= (Button) findViewById(R.id.but_book);
        butbuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });


        bglry= (Button) findViewById(R.id.but_galry);
        bglry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        bcrc= (Button) findViewById(R.id.but_crs);
        bcrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, MainCoursesActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
//        img= (TextView) findViewById(R.id.imgmain);
//        Typeface type1 = Typeface.createFromAsset(getAssets(), "fonts/roboto-condensed.bold.ttf");
//        img.setTypeface(type1);
      crs= (TextView) findViewById(R.id.txt_crc);
        Typeface type2 = Typeface.createFromAsset(getAssets(), "fonts/avenirltstd-light-webfont.ttf");
        crs.setTypeface(type2);

        sched= (TextView) findViewById(R.id.txt_sch);
        Typeface type3 = Typeface.createFromAsset(getAssets(), "fonts/avenirltstd-light-webfont.ttf");
        sched.setTypeface(type3);
        gal= (TextView) findViewById(R.id.txt_glry);
        Typeface type4 = Typeface.createFromAsset(getAssets(), "fonts/avenirltstd-light-webfont.ttf");
        gal.setTypeface(type4);
        book= (TextView)findViewById(R.id.txt_libr);
        Typeface type5 = Typeface.createFromAsset(getAssets(), "fonts/avenirltstd-light-webfont.ttf");
        book.setTypeface(type5);
        remaindr= (TextView)findViewById(R.id.txt_rem);
        Typeface type6 = Typeface.createFromAsset(getAssets(), "fonts/avenirltstd-light-webfont.ttf");
        remaindr.setTypeface(type6);
   l1= (LinearLayout) findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundColor(Color.TRANSPARENT);

                Intent i = new Intent(MainActivity.this, MainCoursesActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
      l2= (LinearLayout) findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, CalendarActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
       l3= (LinearLayout) findViewById(R.id.l3);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3.setBackgroundColor(Color.TRANSPARENT);

                Intent i = new Intent(MainActivity.this, GalleryActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
       l4= (LinearLayout) findViewById(R.id.l4);
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this, LibraryActivity.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        l5= (LinearLayout) findViewById(R.id.l5);
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setBackgroundColor(Color.TRANSPARENT);
                Intent i = new Intent(MainActivity.this,Notification.class);

                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });



        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




      drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     //   drawer.closeDrawer(Gravity.LEFT);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
      //  drawer.openDrawer(Gravity.LEFT);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.getMenu().getItem(0).setChecked(true);



       NavigationView navdrawtop = (NavigationView) findViewById(R.id.navigation_drawer_top);
        View header =   navdrawtop.inflateHeaderView(R.layout.nav_header_main);
        txtunam= (TextView)header.findViewById(R.id.txt_headruname);
        SharedPreferences preferencesd= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferencesd.getString("userid", null);
        unams = preferences.getString("name", null);
        if(userid!=null)
        {   // condition true means user is already login
            navView.getMenu().getItem(5).setIcon(R.drawable.signout);
            navView.getMenu().getItem(5).setTitle("Log Out");
            navView.getMenu().getItem(4).setVisible(true);
            System.out.println("logoutt");


        }

        else
        {
            navView.getMenu().getItem(5).setIcon(R.drawable.login);
            navView.getMenu().getItem(4).setVisible(false);
            navView.getMenu().getItem(5).setTitle("Log in");
            System.out.println("loginnn");


        }
        if(unams!=null) {
            txtunam.setVisibility(View.VISIBLE);
            txtunam.setText(" " + unams);
        }
        else
        { txtunam.setVisibility(View.INVISIBLE);
        }


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                System.out.println("dd"+id);

                MenuItem s = navView.getMenu().findItem(id).setChecked(true);

                MenuItem sel = navView.getMenu().findItem(id);

                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.menuItem1:

                        i = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.menuItem2:


                        i = new Intent(MainActivity.this, FeedBackActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.menuItem3:
                      //  Toast.makeText(getApplicationContext(), "Contact US", Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this, ContactUsActivity1.class);
                        startActivity(i);

                        return true;
                    case R.id.menuItem4:
                        //   Toast.makeText(getApplicationContext(), "Map", Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this, meridian.com.etsdcapp.MapsActivity.class);
                        startActivity(i);
                        return true;


                    case R.id.menuItem5:
                        //   Toast.makeText(getApplicationContext(), "Map", Toast.LENGTH_SHORT).show();
                        if(userid!=null)
                        {   // condition true means user is already login
                            i = new Intent(MainActivity.this, ChangePassword.class);
                            startActivity(i);



                        }


                        return true;
                    case R.id.menuItem6:
                        logout();
                       i = new Intent(MainActivity.this, LoginActivity.class);
                      startActivity(i);

                        return true;
                    case R.id.menuItem7:

                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://play.google.com/store/apps/details?id=meridian.com.etsdcapp"));
                        startActivity(viewIntent);

                        return true;
                    default:
                      //  i = new Intent(MainActivity.this, MainActivity.class);
                       // startActivity(i);
                        return true;
                }
            }
        });




        // Grab the NavigationView Menu
        final Menu navMenu = navView.getMenu();


        // Install an OnGlobalLayoutListener and wait for the NavigationMenu to fully initialize
//        navView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // Remember to remove the installed OnGlobalLayoutListener
//                navView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                // Loop through and find each MenuItem View
//                for (int i = 0, length = MENU_ITEMS; i < length; i++) {
//                    final String id = "menuItem" + (i + 1);
//                    final MenuItem item = navMenu.findItem(getResources().getIdentifier(id, "id", getPackageName()));
//                  //  navView.findViewsWithText(mMenuItems, item.getTitle(), View.FIND_VIEWS_WITH_TEXT);
//                }
//                // Loop through each MenuItem View and apply your custom Typeface
//                for (final View menuItem : mMenuItems) {
//
//     //               ((TextView) menuItem).setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "MYRIADPRO-REGULAR.OTF"));
//
//                }
//            }
//        });
    }

    private void setNavItemCount(@IdRes int itemId, int count) {
        TextView view = (TextView) navView.getMenu().findItem(itemId).getActionView();
        view.setText(count > 0 ? String.valueOf(count) : null);
    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }


      /*  if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {


        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    protected void onResume() {

        super.onResume();
        l1.setBackgroundResource(R.drawable.b100);
        l2.setBackgroundResource(R.drawable.b100);
        l3.setBackgroundResource(R.drawable.b100);
        l4.setBackgroundResource(R.drawable.b100);
        l5.setBackgroundResource(R.drawable.b100);

    }
//    public class FadePageTransformer implements ViewPager.PageTransformer {
//        public void transformPage(View view, float position) {
//            view.setTranslationX(view.getWidth() * -position);
//
//            if(position <= -1.0F || position >= 1.0F) {
//                view.setAlpha(0.0F);
//            } else if( position == 0.0F ) {
//                view.setAlpha(1.0F);
//            } else {
//                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
//                view.setAlpha(1.0F - Math.abs(position));
//            }
//        }
//    }
@Override
protected void onStop() {
    sliderShow.stopAutoCycle();
    super.onStop();
}


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


public void logout()
{  NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
    boolean i= networkCheckingClass.ckeckinternet();
    if(i) {
        //  Toast.makeText(getApplicationContext(), "Social Media", Toast.LENGTH_SHORT).show();\




        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                   System.out.println("reee"+response);
                        System.out.println("kkk"+response);
                        Intent  i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        userid = preferences.getString("userid", null);
                        preferences.edit().clear().commit();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("logout", "out");
                params.put("usr_id", userid);



                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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


       /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(MainActivity.this).create();
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
