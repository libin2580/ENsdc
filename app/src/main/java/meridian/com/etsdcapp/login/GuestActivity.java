package meridian.com.etsdcapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.R;

public class GuestActivity extends AppCompatActivity {
ProgressDialog pd;
    EditText edt_guest;
    Button but_log;
    TextView txt_skip;
    String guest;
    String regexStr = "^[0-9]{10}$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guest);
        edt_guest= (EditText) findViewById(R.id.edt_guest);
        but_log= (Button) findViewById(R.id.but_guest);
        txt_skip= (TextView) findViewById(R.id.txt_skip);
        but_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log();

            }
        });
        txt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                startActivity(i);
                finish();
            }
        });



    }
    public void log() {
        pd = new ProgressDialog(GuestActivity.this);
        pd.setTitle("Logging in...");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();
        guest = edt_guest.getText().toString().trim();
        if (guest.matches(emailPattern) && guest.length() > 0) {

            RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());


            String url1 = "http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?fid=6&email="+guest+"&phone=null";


            StringRequest stringRequest1 = new StringRequest
                    (Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //  tv.setText("Response is: "+ response);

                            System.out.println("++++++++++++++RESPONSE+++++++++++++++ log   :" + response);


                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                String status = jsonObj.getString("status");
                                System.out.println("result" + status);
                                if (status.equals("success")) {
                                    Intent is = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(is);
                                    pd.dismiss();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
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
        }
        else if(guest.matches(regexStr)) {
            RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());


            String url1 = "http://meridian.net.in/demo/etsdc/response.php?fid=6&email=null&phone="+guest;


            StringRequest stringRequest1 = new StringRequest
                    (Request.Method.GET, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //  tv.setText("Response is: "+ response);

                            System.out.println("++++++++++++++RESPONSE+++++++++++++++ log   :" + response);


                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                String status = jsonObj.getString("status");
                                System.out.println("result" + status);
                                if (status.equals("success")) {
                                    Intent is = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(is);
                                    pd.dismiss();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
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
        }
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
