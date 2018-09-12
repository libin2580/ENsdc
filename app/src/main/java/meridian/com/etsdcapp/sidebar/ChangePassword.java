package meridian.com.etsdcapp.sidebar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.NetworkCheckingClass;
import meridian.com.etsdcapp.R;

public class ChangePassword extends AppCompatActivity {
    EditText username,oldpass,newpass,cnfrmpass;
    Button button_submt;
    String str_username,str_oldpasss,str_newpass,str_cnfrmpass,status;
    ProgressDialog pd;
    ProgressBar progress;
    String REGISTER_URL="http://www.app.etsdc.com/response.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_change_password);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topchange);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progress = (ProgressBar)findViewById(R.id.progress_bar);
        newpass= (EditText) findViewById(R.id.edt_newpass);
        cnfrmpass= (EditText) findViewById(R.id.edt_cnfrmpass);
        button_submt= (Button) findViewById(R.id.button_submt);
        button_submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub();
            }
        });
    }
    private void sub() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        str_username=  preferences.getString("email",null);

        str_newpass= newpass.getText().toString();
        str_cnfrmpass=cnfrmpass.getText().toString();
        if (  str_newpass.matches("") || str_cnfrmpass.matches("")  ) {
            final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
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

         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ChangePassword.this).create();
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
            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/

        }
        if (!isValidPassword(newpass.getText().toString().trim())) {
            newpass.setError("Password should be minimum 7 characters");

        }
        else if ( !str_newpass.contentEquals( str_cnfrmpass) )
        {
            Toast.makeText(getApplicationContext(),"Password Incorect",Toast.LENGTH_SHORT).show();
        }



        else {
            NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(getApplicationContext());
            boolean i = networkCheckingClass.ckeckinternet();
            progress.setVisibility(ProgressBar.VISIBLE);
//            pd = new ProgressDialog(ChangePassword.this);
//            pd.setTitle("Registering...");
//            pd.setMessage("Please wait...");
//            pd.setCancelable(true);
//            pd.show();
            if (i==true)
            {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                JSONObject jsonObj = null;
                                try {
                                    jsonObj = new JSONObject(response);
                                    status = jsonObj.getString("status");
                                    System.out.println("statussssscours"+status);
                                    progress.setVisibility(ProgressBar.GONE);
                                    // pd.dismiss();

                                    final SweetAlertDialog dialog = new SweetAlertDialog(ChangePassword.this,SweetAlertDialog.NORMAL_TYPE);
                                    dialog.setTitleText(status)
                                            .setContentText(status)
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                                    startActivity(i);

                                                    dialog.dismiss();


                                                }
                                            })
                                            .show();


                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));


                             /*       AlertDialog alertDialog = new AlertDialog.Builder(ChangePassword.this).create();
                                    alertDialog.setTitle("Alert");
                                    alertDialog.setMessage(status);
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                                    startActivity(i);

                                                    dialog.dismiss();


                                                }
                                            });
                                    alertDialog.show();
                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("change_pwd",str_username);
                        params.put("new_password", str_newpass);
                        params.put("confirm_password",str_cnfrmpass);
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        String ttt = sharedPreferences.getString("gcmToken", null);
// Toast.makeText(getApplicationContext()," "+ttt,Toast.LENGTH_SHORT).show();
                        System.out.print("++++++++++++++++++++++++" + ttt);
                        params.put("device_tokens", ttt);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } else {

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



             /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ChangePassword.this).create();
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
                nbutton.setTextColor(getResources().getColor(R.color.Orange));*/

            }


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
    public boolean checkPassWordAndConfirmPassword(String password,String confirmPassword)
    {
        boolean pstatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 7) {
            return true;
        }
        return false;
    }
}
