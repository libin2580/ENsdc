package meridian.com.etsdcapp.library;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PdfViewActivity extends AppCompatActivity {
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pdf_view);
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top_libdt);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(PdfViewActivity.this, LibraryActivity.class);
//                startActivity(i);
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String pdf=  getIntent().getExtras().getString("pdf");
        pd = new ProgressDialog(PdfViewActivity.this);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait...");
        pd.setCancelable(true);
        pd.show();

      //  PDFView pdfView= (PDFView) findViewById(R.id.pdfview);
        WebView webView=new WebView(PdfViewActivity.this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        //---you need this to prevent the webview from
        // launching another browser when a url
        // redirection occurs---
        webView.setWebViewClient(new Callback());

        //String pdfURL = "http://dl.dropboxusercontent.com/u/37098169/Course%20Brochures/AND101.pdf";

        webView.loadUrl(
                "http://docs.google.com/gview?embedded=true&url=" + pdf);
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
// do your stuff here
                pd.dismiss();
            }


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {


            }
        });

        setContentView(webView);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }



//        pdfView.fromAsset(pdf)
//                .pages(0, 2, 1, 3, 3, 3)
//                .defaultPage(1)
//                .showMinimap(false)
//                .enableSwipe(true)
//                .load();

        //   ImageView txt= (ImageView) findViewById(R.id.pdfview);
//      PdfViewActivity.fromAsset(pdf)
//                .pages(0, 2, 1, 3, 3, 3)
//                .defaultPage(1)
//                .showMinimap(false)
//                .enableSwipe(true)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
//                .load();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    }

