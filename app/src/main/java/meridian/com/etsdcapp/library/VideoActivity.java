package meridian.com.etsdcapp.library;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import meridian.com.etsdcapp.R;


public class VideoActivity extends Activity {
    String id, name, thmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            System.out.println("dtl" + id);
            //   name = extras.getString("nam");
            //   thmb = extras.getString("thmb");
        }
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
       // setSupportActionBar(toolbar);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//Use a media controller so that you can scroll the video contents
//and also to pause, start the video.
try {


    MediaController mediaController = new MediaController(VideoActivity.this);
    mediaController.setAnchorView(videoView);
    videoView.setMediaController(mediaController);
    Uri video = Uri.parse("http://www.app.etsdc.com/uploads/video/" + id);
    videoView.setVideoURI(video);
    // videoView.setVideoURI(Uri.parse("http://meridian.net.in/demo/etsdc/uploads/video/" + id));
    videoView.start();
}catch (Exception e)
{
    Toast.makeText(getApplicationContext(),"This Video is not supported in your device",Toast.LENGTH_SHORT).show();
}
//        String html = "";
//        html += "<html><body>";
//        html += "<iframe width=\"560\" height=\"315\" src=\"http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/video/"+id+"\" frameborder=\"0\" allowfullscreen></iframe>";
//        html += "</body></html>";
//        String ds="<iframe src=\"http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/video/"+id+"\" \n" +
//                "        width=\"515\" \n" +
//                "        height=\"340\" \n" +
//                "        frameborder=\"0\" \n" +
//                "        webkitallowfullscreen mozallowfullscreen allowfullscreen>\n" +
//                "</iframe>";
//        String venkat="<iframe src=\"http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/video/"+id+"?portrait=0&color=333\" width=\"WIDTH\" height=\"HEIGHT\" frameborder=\"0\" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>";
//        WebView displayVideo = (WebView)findViewById(R.id.webView);
//        displayVideo.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        WebSettings webSettings = displayVideo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        displayVideo.loadData(html, "text/html", "utf-8");
//    }


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

