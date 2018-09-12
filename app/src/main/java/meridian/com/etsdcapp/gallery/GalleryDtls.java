package meridian.com.etsdcapp.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.GalleryDetailAdapter;
import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;
import meridian.com.etsdcapp.model.GallerDetailmodel;
import meridian.com.etsdcapp.model.GalleryModel;

public class GalleryDtls extends AppCompatActivity {
    String description,title;
    TextView textView_descriptn,gallery_dtl_name,galry_img_nam;
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    public  ArrayList<GallerDetailmodel> arrglry1;
    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
GalleryDetailAdapter galleryDetailAdapter;
    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;
    ViewPager v;int albumid;
    private Bitmap bmp;
//ImageView tch;
    String imgs;
    TouchImageView tch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gallery_dtls);
        v= (ViewPager) findViewById(R.id.viewpagr);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  tch= (TouchImageView) findViewById(R.id.tch);



    imgs=  getIntent().getExtras().getString("url");

        System.out.println("images"+imgs);
        description=  getIntent().getExtras().getString("description");
        title=  getIntent().getExtras().getString("title");
        albumid = getIntent().getExtras().getInt("albumid");
        System.out.println("gallery clickd img" + albumid);
      //  Picasso.with(getApplicationContext()).load(imgs).placeholder(R.drawable.defaultimg).into(tch);



//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    InputStream in = new URL(imgs).openStream();
//                    bmp = BitmapFactory.decodeStream(in);
//                } catch (Exception e) {
//                    // log error
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result) {
//                if (bmp != null)
//                    tch.setImageBitmap(bmp);
//                System.out.println("bmp"+bmp);
//
//            }
//
//        }.execute();
//        try {
//            URL thumb_u = new URL(imgs);
//            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
//            System.out.println("drawablee1"+thumb_d);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//
//            }
//            //myImageView.setImageDrawable(thumb_d);
//        }
//        catch (Exception e) {
//            // handle it
//        }
//      Drawable d=  LoadImageFromWebOperations(imgs);
//        tch.setImageResource(d);


        //Picasso.with(getApplicationContext()).load(imgs).placeholder(R.drawable.defaultimg).into(img);
       //System.out.println("background_img"+img.getBackground());

    // v.setBackgroundResource( Picasso.with(getApplicationContext()).load(imgs).placeholder(R.drawable.defaultimg).into(img));
     //   System.out.println("gallery clickd img"+img);


        textView_descriptn= (TextView) findViewById(R.id.txt_descrptn);
        gallery_dtl_name= (TextView) findViewById(R.id.toolbar_title);

        RequestQueue queue4 = Volley.newRequestQueue(getApplicationContext());
        String url4 = "http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?fid=5&album_id="+albumid;

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

                            arrglry1 = new ArrayList<>();
                            jsonarray = new JSONArray(response);
                            System.out.println("g21");
                            GallerDetailmodel gdm1=new GallerDetailmodel();
                            gdm1.setImageurl(imgs);
                            arrglry1.add(gdm1);
                            for (int i = 0; i < jsonarray.length(); i++) {
                              GallerDetailmodel gdm=new GallerDetailmodel();
                                System.out.println("g31");
                                jsonobject = jsonarray.getJSONObject(i);
                                String gal_id = jsonobject.getString("gal_id");
                                String image = jsonobject.getString("image");
                                title = jsonobject.getString("title");
                                description = jsonobject.getString("description");

                                String url2 = "http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/uploads/gallery/" + image;
                                if(!url2.contentEquals(imgs)) {

                                    gdm.setImageurl(url2);
                                    arrglry1.add(gdm);
                                }

                            }

                       //  galleryDetailAdapter = new GalleryDetailAdapter(arrglry,GalleryDtls.this);
                         //   v.setVisibility(View.VISIBLE);
                        //  tch.setVisibility(View.INVISIBLE);
                          //  v.setVisibility(View.VISIBLE);

                            v.setAdapter(new TouchImageAdapter(GalleryDtls.this,arrglry1,imgs));


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

    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            System.out.println("drawablee"+d);
            return d;
        } catch (Exception e) {
            return null;
        }
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
}
