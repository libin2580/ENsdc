package meridian.com.etsdcapp.gallery;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.GalleryModel;

public class GalleryActivity extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener {
    public static ArrayList<GalleryModel> arrglry;
    TabLayout tabLayout;
    String album_name;
    ViewPager viewPager;
    String url2;
    ProgressDialog pd;
    ViewPagerAdapter adapter;
    ArrayList<String> arr;
    ArrayList<String> arrg;
    static int id;


    public static RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gallery);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        String url = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/response.php?fid=4";
//
//
//// Request a string response from the provided URL.
//
//        StringRequest stringRequest1 = new StringRequest
//                (Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        //  tv.setText("Response is: "+ response);
//
//                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ log   :" + response);
//
////
////                        JSONObject jsonObject = null;
//                        try {
//                            JSONArray jsonarray = new JSONArray(response);
//                            System.out.println("00");
//                            for (int i = 0; i < jsonarray.length(); i++) {
//                             GalleryModel gm=new GalleryModel();
//                                System.out.println("01");
//                                JSONObject jsonobject = jsonarray.getJSONObject(i);
//
//                                String album_id = jsonobject.getString("album_id");
//                                String album_name = jsonobject.getString("album_name");
//                                String album_thumbnail = jsonobject.getString("album_thumbnail");
//                                String  url2 = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/course_category/"+thumbnail;
//                         gm.setAlbumid(alb);
//                                arrcrs.add(cm);
//
//
//                            }
//
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //tv.setText("That didn't work!");
//
//                    }
//                });
//
//        queue.add(stringRequest1);

        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);

        tabLayout.setupWithViewPager(viewPager);

    }


    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void setupViewPager( ViewPager viewPager)
    {
//        pd = new ProgressDialog(GalleryActivity.this);
//        pd.setTitle("Logging in...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getApplicationContext());
        boolean i= networkCheckingClass.ckeckinternet();
        if(i==true) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://www.app.etsdc.com/response.php?fid=4";


// Request a string response from the provided URL.

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ log 4  :" + response);

//
//                        JSONObject jsonObject = null;
                        try {
                            arr=new ArrayList<>();
                            arrg=new ArrayList<>();
                            JSONArray jsonarray = new JSONArray(response);
                            System.out.println("00");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                GalleryModel gm=new GalleryModel();
                                System.out.println("01");
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String album_id = jsonobject.getString("album_id");
                                //  int albm=Integer.parseInt(album_id);
                                //String title = jsonobject.getString("title");
                                String album_name = jsonobject.getString("album_name");
                                String album_thumbnail = jsonobject.getString("album_thumbnail");

                                //   String  url2 = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/course_category/"+thumbnail;
                                arr.add(album_name);
                                arrg.add(album_id);


                                id=Integer.parseInt(arrg.get(i));

                                System.out.print("ss"+arr);
                                //adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark), Integer.parseInt(arrg.get(i))), arr.get(i));
                                adapter.addFrag(new GalleryFragment(), arr.get(i));

                             //   pd.dismiss();
                              adapter.notifyDataSetChanged(); System.out.print("ssg"+arr.get(i));

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
//        int j;
//        for(j=1;j<arr.size();j++) {
//            adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark), j), arr.get(j));
//            System.out.println("items"+arr.get(j));
//        }

        queue.add(stringRequest1);
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

         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(GalleryActivity.this).create();
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
        viewPager.setAdapter(adapter);

        //  String arr[]={"","FIRE FIGHTING","FIRST AID","SEA SURVIVAL","HUET","LIFE BOAT","H2S","DESERT DRIVING"};
//        for(int i=1;i<arr.length;i++)
//        {  if(arr[i]!="")
//        {

//        }
//        }

//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),1), "FIRE FIGHTING");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),2), "FIRST AID");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),3), "SEA SURVIVAL");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.ripple_material_light),4), "HUET");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),5), "LIFE BOAT");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),6), "H2S");
//        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.button_material_dark),7), "DESERT DRIVING");


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home)
        {


            super.onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {


            Bundle bundle = new Bundle();
            bundle.putInt("id",id);

            fragment.setArguments(bundle);

            mFragmentList.add(fragment);




            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position)
        {return mFragmentTitleList.get(position);
        }
    }









}