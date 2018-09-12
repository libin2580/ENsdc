package meridian.com.etsdcapp.gallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.ZoomInTransformer;
import com.daimajia.slider.library.Transformers.ZoomOutSlideTransformer;

import com.google.android.gms.common.api.GoogleApiClient;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.RecyclerItemClickListener;
import meridian.com.etsdcapp.adapter.GalleryAdapter;
import meridian.com.etsdcapp.adapter.GalleryDetailAdapter;
import meridian.com.etsdcapp.adapter.LibraryAdapter;
import meridian.com.etsdcapp.model.GalleryModel;

public class GalleryDetailActivity extends AppCompatActivity {
    String description, title;
    TextView textView_descriptn, gallery_dtl_name, galry_img_nam;
    ArrayList<GalleryModel> arrglry;
    SliderLayout sliderShow;
    int albumid;
    Context context;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gallery_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerdetal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String img = getIntent().getExtras().getString("url");
        description = getIntent().getExtras().getString("description");
        title = getIntent().getExtras().getString("title");
        albumid = getIntent().getExtras().getInt("albumid");
        System.out.println("gallery clickd img" + albumid);
context=getApplicationContext();
        //  galry_img_nam= (TextView) findViewById(R.id.txt_glrydtl2);
        // galry_img_nam.setText(title);
        textView_descriptn = (TextView) findViewById(R.id.txt_descrptn);
        gallery_dtl_name = (TextView) findViewById(R.id.toolbar_title);
        gallery_dtl_name.setText(title);
       // sliderShow = (SliderLayout) findViewById(R.id.slider1);
        //setSupportActionBar(toolbar);
        RequestQueue queue4 = Volley.newRequestQueue(getApplicationContext());
        String url4 = "http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?fid=5&album_id=1";

        StringRequest stringRequest4 = new StringRequest
                (Request.Method.GET, url4, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ gallery11 :" + response);


                        JSONArray jsonarray;
                        JSONObject jsonobject;
                        GalleryModel gm2;
                        try {

                            arrglry = new ArrayList<>();
                            jsonarray = new JSONArray(response);
                            System.out.println("g21");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                gm2 = new GalleryModel();
                                System.out.println("g31");
                                jsonobject = jsonarray.getJSONObject(i);
                                String gal_id = jsonobject.getString("gal_id");
                                String image = jsonobject.getString("image");
                                title = jsonobject.getString("title");
                                description = jsonobject.getString("description");
                                String url2 = "http://meridian.net.in/demo/etsdc/uploads/gallery/" + image;
                                gm2.setGalimag(url2);
                                // gm2.setGalimag(url2);
                                gm2.setGallerytitl(title);
                                gm2.setGallerydescrptn(description);
                                arrglry.add(gm2);
                            }


                            GalleryDetailAdapter adapter0 = new GalleryDetailAdapter(arrglry,context);
                            recyclerView.setAdapter(adapter0);


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

        queue4.add(stringRequest4);


//        ImageAdapter adapters = new ImageAdapter(this,arrglry);
//        viewPager.setAdapter(adapters);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GalleryDetailActivity.this, GalleryActivity.class);
//                startActivity(i);
//            }
//        });

       // DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext());
     //   defaultSliderView.image(img);



      //  sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
     //   sliderShow.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);


//        if(description.contentEquals(""))
//        {
//            textView_descriptn.setText("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ");
//        }
//        else
//        {
        //  textView_descriptn.setText(description);
//        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "GalleryDetail Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://meridian.com.etsdcapp.gallery/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "GalleryDetail Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://meridian.com.etsdcapp.gallery/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

}


