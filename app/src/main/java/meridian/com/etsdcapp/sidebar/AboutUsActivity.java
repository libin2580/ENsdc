package meridian.com.etsdcapp.sidebar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.R;

public class AboutUsActivity extends AppCompatActivity {
TextView abt,abte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);
        abt= (TextView) findViewById(R.id.txt_abt);
        abte= (TextView) findViewById(R.id.txtabtit);
        Typeface type4 = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");
        abt.setTypeface(type4);

        Typeface type3 = Typeface.createFromAsset(this.getAssets(), "fonts/roboto-condensed.bold.ttf");
        abte.setTypeface(type3);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topa);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AboutUsActivity.this, MainActivity.class);
//
//                startActivity(i);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
