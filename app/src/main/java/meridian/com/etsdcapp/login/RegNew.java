package meridian.com.etsdcapp.login;

/**
 * Created by user 1 on 27-07-2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.android.gms.plus.model.people.Person;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.UserDetailsModel;


public class RegNew extends AppCompatActivity {

    TextView AlReg;
    EditText edtemail,edtphon,edtfulnam,edtoccp,edtloc,edtpass;
  static   String email,phon,fulnam,occ,loc,pass,statusd;
    Button butsignup;
    boolean edittexterror=false;
    String REGISTER_URL="http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?";

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    //    ProgressDialog pd;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // get reference to the views
        edtemail= (EditText) findViewById(R.id.edt_email);
        edtpass= (EditText) findViewById(R.id.edt_pass);
        edtfulnam= (EditText) findViewById(R.id.edt_fulname);
        edtphon= (EditText) findViewById(R.id.edt_phone);
        edtoccp= (EditText) findViewById(R.id.edt_occuptn);
        edtloc= (EditText) findViewById(R.id.edt_locatn);
        butsignup= (Button) findViewById(R.id.butsignup);
        progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Lt.otf");
        edtfulnam.setTypeface(tf, Typeface.BOLD);
        edtphon.setTypeface(tf, Typeface.BOLD);
        edtfulnam.setTypeface(tf, Typeface.BOLD);
        edtphon.setTypeface(tf, Typeface.BOLD);
        edtoccp.setTypeface(tf, Typeface.BOLD);
        edtpass.setTypeface(tf, Typeface.BOLD);
        Typeface ttf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Roman.otf");
        AlReg= (TextView) findViewById(R.id.txtsignin);
        AlReg.setTypeface(ttf, Typeface.BOLD);
        AlReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reg();
                Intent is = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(is);
                finish();

            }
        });


        // add click listener to Button "POST"
        butsignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtemail.getText().toString();
                pass = edtpass.getText().toString();
                phon = edtphon.getText().toString();
                fulnam = edtfulnam.getText().toString();
                occ = edtoccp.getText().toString();
                loc = edtloc.getText().toString();
                edittexterror = false;
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
                    edtemail.setError("Invalid Email");
                    edittexterror = true;
                }
                else if (edtemail.getText().toString().isEmpty()) {
                    edtemail.setError("Enter Email Id");
                    edittexterror = true;
                }
                else if (!isValidPassword(edtpass.getText().toString().trim())) {
                    edtpass.setError("Password should be minimum 6 characters");
                    edittexterror = true;
                }


                else if (edtphon.getText().toString().isEmpty()) {
                    edtphon.setError("Enter Phone");
                    edittexterror = true;
                }

                else if (edtfulnam.getText().toString().isEmpty()) {
                    edtfulnam.setError("Enter Full Name");
                    edittexterror = true;
                }

                else if (edtloc.getText().toString().isEmpty()) {
                    edtloc.setError("Enter Location");
                    edittexterror = true;
                }
                else if (edtoccp.getText().toString().isEmpty()) {
                    edtoccp.setError("Enter Occupation");
                    edittexterror = true;
                }

                else if (email.trim().matches("") || pass.matches("") || pass.trim().matches("") || phon.matches("") || fulnam.matches("") || occ.matches("") || loc.matches("")) {

                    final SweetAlertDialog dialog = new SweetAlertDialog(RegNew.this,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alert!")
                            .setContentText("Empty Fields")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    dialog.dismiss();
                                }
                            })
                            .show();


                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

              /*      MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(RegNew.this)
                            .setTitle("ALERT!")
                            .setDescription("Empty Fields")
                            .setHeaderColor(R.color.butnbakcolr)
                            .setPositiveText("OK")
                            .setStyle(Style.HEADER_WITH_TITLE)
                            // .withDialogAnimation(true, Duration.FAST)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .build();


                    dialog.show();*/


/*

                    final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegNew.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setIcon(R.drawable.warning_blue);
                    alertDialog.setMessage("Empty Fields");

                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
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
                else   if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
                    edtemail.setError("Invalid Email");
                    edittexterror = true;
                }


                else if (edtemail.getText().toString().isEmpty()) {
                    edtemail.setError("Enter Email Id");
                    edittexterror = true;
                }
                else   if (!isValidPassword(pass)) {
                    edtpass.setError("Password should be minimum 6 characters");
                    edittexterror = true;
                }
                else if (!android.util.Patterns.PHONE.matcher(edtphon.getText().toString().trim()).matches()) {
                    edtphon.setError("Invalid Phone");
                    edittexterror = true;
                } else if (edtfulnam.getText().toString().isEmpty()) {
                    edtfulnam.setError("Enter Full Name");
                    edittexterror = true;
                } else if (edtloc.getText().toString().isEmpty()) {
                    edtloc.setError("Enter Location");
                    edittexterror = true;
                } else if (edtoccp.getText().toString().isEmpty()) {
                    edtoccp.setError("Enter Occupation");
                    edittexterror = true;
                }
                if(edittexterror==false) {
                    new HttpAsyncTask().execute(REGISTER_URL);
                }

            }
        });

    }

    public static String POST(String url, UserDetailsModel userDetailsModel) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", userDetailsModel.getEmail());
            jsonObject.put("phone", userDetailsModel.getPhone());
            jsonObject.put("name", userDetailsModel.getFulnam());
            jsonObject.put("occupation",userDetailsModel.getOccptn());
            jsonObject.put("location", userDetailsModel.getLocatn());
            jsonObject.put("password", userDetailsModel.getPass());
            System.out.println("etsdcresult0" + userDetailsModel.getPhone().toString());
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "text/html");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            System.out.println("etsdcresult1" + inputStream);

            // 10. convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
                System.out.println("etsdcresult2" + result);

            }
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            System.out.println("etsdcresult3" + e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }




    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            UserDetailsModel user=new UserDetailsModel();
          user.setEmail(email);
           user.setFulnam(fulnam);
            user.setLocatn(loc);
            user.setOccptn(occ);
            user.setPass(pass);
            user.setPhone(phon);
            System.out.println("pass"+pass);
            return POST(urls[0],user);

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        }
    }



    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

}