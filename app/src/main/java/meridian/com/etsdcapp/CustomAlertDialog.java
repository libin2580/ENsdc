package meridian.com.etsdcapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.login.LoginActivity;

/**
 * Created by user 1 on 11-07-2016.
 */
public class CustomAlertDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText editText;
    ProgressBar progress;
    String status;
    String REGISTER_URL="http://www.app.etsdc.com/response.php?";

    public CustomAlertDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customalert);
        editText= (EditText) findViewById(R.id.alert_email);
        progress = (ProgressBar)findViewById(R.id.progress_bars);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        yes = (Button) findViewById(R.id.btn_ok);
        no = (Button) findViewById(R.id.btn_cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_ok:
               final String eml= editText.getText().toString();
                if (eml.matches("") )
                {
                    Toast.makeText(c,"Empty Field",Toast.LENGTH_SHORT).show();

                }
                else {
                    NetworkCheckingClass networkCheckingClass = new NetworkCheckingClass(c);
                    boolean i = networkCheckingClass.ckeckinternet();
                    progress.setVisibility(View.VISIBLE);


                    if (i==true)
                    {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println("statusforgot"+response+"xxxxx");

                                        JSONObject jsonObj = null;
                                        try {
                                            jsonObj = new JSONObject(response);
                                            status = jsonObj.getString("status");
                                            if(status.contentEquals("success")) {

                                                Toast.makeText(c,"New password has been sent to Your Email",Toast.LENGTH_SHORT).show();
                                                progress.setVisibility(View.INVISIBLE);
                                                dismiss();

                                            }
                                            else {

                                                Toast.makeText(c,status,Toast.LENGTH_SHORT).show();
                                                progress.setVisibility(View.INVISIBLE);
                                            }


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
                                params.put("forgot_pwd", eml);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(c);
                        requestQueue.add(stringRequest);
                    } else {

                        final SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE);
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


                     /*   final AlertDialog alertDialog = new AlertDialog.Builder(c).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Oops Your Connection Seems Off..");

                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();


                            }
                        });
                        alertDialog.show();*/

                    }


                }

                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }

    }
}