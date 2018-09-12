//package meridian.com.etsdcapp.course;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import meridian.com.etsdcapp.R;
//import meridian.com.etsdcapp.login.LoginActivity;
//
//public class RegActivity extends AppCompatActivity {
//
//    TextView AlReg;
//    EditText edtemail,edtphon,edtfulnam,edtoccp,edtloc,edtpass;
//    String email,phon,fulnam,occ,loc,pass,statusd;
//    Button butsignup;
//    boolean edittexterror=false;
//    String REGISTER_URL="http://meridian.net.in/demo/etsdc/response.php";
//
//    private static final String PASSWORD_PATTERN =
//            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//    //    ProgressDialog pd;
//    ProgressBar progress;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        edtemail= (EditText) findViewById(R.id.edt_email);
//        edtpass= (EditText) findViewById(R.id.edt_pass);
//        edtfulnam= (EditText) findViewById(R.id.edt_fulname);
//        edtphon= (EditText) findViewById(R.id.edt_phone);
//        edtoccp= (EditText) findViewById(R.id.edt_occuptn);
//        edtloc= (EditText) findViewById(R.id.edt_locatn);
//        butsignup= (Button) findViewById(R.id.butsignup);
//        progress = (ProgressBar)findViewById(R.id.progress_bar);
//        progress.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                progress.setVisibility(View.INVISIBLE);
//                return false;
//            }
//        });
//
//        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Lt.otf");
//        edtfulnam.setTypeface(tf, Typeface.BOLD);
//        edtphon.setTypeface(tf, Typeface.BOLD);
//        edtfulnam.setTypeface(tf, Typeface.BOLD);
//        edtphon.setTypeface(tf, Typeface.BOLD);
//        edtoccp.setTypeface(tf, Typeface.BOLD);
//        edtpass.setTypeface(tf, Typeface.BOLD);
//        Typeface ttf = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-Roman.otf");
//        AlReg= (TextView) findViewById(R.id.txtsignin);
//        AlReg.setTypeface(ttf, Typeface.BOLD);
//        AlReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // reg();
//                Intent is = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(is);
//                finish();
//
//            }
//        });
//        butsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                email = edtemail.getText().toString();
//                pass = edtpass.getText().toString();
//                phon = edtphon.getText().toString();
//                fulnam = edtfulnam.getText().toString();
//                occ = edtoccp.getText().toString();
//                loc = edtloc.getText().toString();
//                edittexterror = false;
//                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
//                    edtemail.setError("Invalid Email");
//                    edittexterror = true;
//                }
//                else if (edtemail.getText().toString().isEmpty()) {
//                    edtemail.setError("Enter Email Id");
//                    edittexterror = true;
//                }
//                else if (!isValidPassword(edtpass.getText().toString().trim())) {
//                    edtpass.setError("Password should be minimum 6 characters");
//                    edittexterror = true;
//                }
//
//
//                else if (edtphon.getText().toString().isEmpty()) {
//                    edtphon.setError("Enter Phone");
//                    edittexterror = true;
//                }
//
//                else if (edtfulnam.getText().toString().isEmpty()) {
//                    edtfulnam.setError("Enter Full Name");
//                    edittexterror = true;
//                }
//
//                else if (edtloc.getText().toString().isEmpty()) {
//                    edtloc.setError("Enter Location");
//                    edittexterror = true;
//                }
//                else if (edtoccp.getText().toString().isEmpty()) {
//                    edtoccp.setError("Enter Occupation");
//                    edittexterror = true;
//                }
//
//                else if (email.trim().matches("") || pass.matches("") || pass.trim().matches("") || phon.matches("") || fulnam.matches("") || occ.matches("") || loc.matches("")) {
//
//                    final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(RegActivity.this).create();
//                    alertDialog.setTitle("Alert");
//                    alertDialog.setIcon(R.drawable.warning_blue);
//                    alertDialog.setMessage("Empty Fields");
//
//                    alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            dialog.dismiss();
//
//
//                        }
//                    });
//                    alertDialog.show();
//                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
//                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
//
//                }
//                else   if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
//                    edtemail.setError("Invalid Email");
//                    edittexterror = true;
//                }
//
//
//                else if (edtemail.getText().toString().isEmpty()) {
//                    edtemail.setError("Enter Email Id");
//                    edittexterror = true;
//                }
//                else   if (!isValidPassword(pass)) {
//                    edtpass.setError("Password should be minimum 6 characters");
//                    edittexterror = true;
//                }
//                else if (!android.util.Patterns.PHONE.matcher(edtphon.getText().toString().trim()).matches()) {
//                    edtphon.setError("Invalid Phone");
//                    edittexterror = true;
//                } else if (edtfulnam.getText().toString().isEmpty()) {
//                    edtfulnam.setError("Enter Full Name");
//                    edittexterror = true;
//                } else if (edtloc.getText().toString().isEmpty()) {
//                    edtloc.setError("Enter Location");
//                    edittexterror = true;
//                } else if (edtoccp.getText().toString().isEmpty()) {
//                    edtoccp.setError("Enter Occupation");
//                    edittexterror = true;
//                }
//                JSONObject post_dict = new JSONObject();
//
//                try {
//                    post_dict.put("email", email);
//                    post_dict.put("phone", phon);
//                    post_dict.put("name", fulnam);
//                    post_dict.put("occupation", occ);
//                    post_dict.put("location", loc);
//                    post_dict.put("password", pass);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (post_dict.length() > 0) {
//                    new Json().execute(String.valueOf(post_dict));
//
//                }
//
//            }
//        });
//
//
//
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    public class Json extends AsyncTask<String, String, String> {
//
//        private  final Object TAG ="error" ;
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String JsonResponse = null;
//            String JsonDATA = params[0];
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            try {
//                URL url = new URL(REGISTER_URL);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setDoOutput(true);
//                // is output buffer writter
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
////set headers and method
//                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//                writer.write(JsonDATA);
//// json data
//                writer.close();
//                InputStream inputStream = urlConnection.getInputStream();
////input stream
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    // Nothing to do.
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String inputLine;
//                while ((inputLine = reader.readLine()) != null)
//                    buffer.append(inputLine + "\n");
//                if (buffer.length() == 0) {
//                    // Stream was empty. No point in parsing.
//                    return null;
//                }
//                JsonResponse = buffer.toString();
////response data
//                Log.i(String.valueOf(TAG),JsonResponse);
//                //send to post execute
//                return JsonResponse;
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e(String.valueOf(TAG), "Error closing stream", e);
//                    }
//                }
//            }
//            return null;
//
//        }
//      /*  private void setListAdapter(ArrayAdapter<String> mArrayAdapter) {
//         }
//*/
//         @Override
//         protected void onPostExecute(String s) {
//             super.onPostExecute(s);
//
//         }
//     }
//    private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() > 6) {
//            return true;
//        }
//        return false;
//    }
//    }
