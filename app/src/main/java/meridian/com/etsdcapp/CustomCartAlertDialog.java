//package meridian.com.etsdcapp;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;
//import meridian.com.etsdcapp.course.CourseDetailActivity;
//import meridian.com.etsdcapp.course.SubCoursesActivity1;
//
///**
// * Created by user 1 on 11-07-2016.
// */
//public class CustomCartAlertDialog extends Dialog implements View.OnClickListener {
//
//    public Activity c;
//    public Dialog d;
//    public Button cart,detail,cancel;
//    public EditText editText;
//    ProgressBar progress;
//    String status;
//    String REGISTER_URL="http://meridian.net.in/demo/etsdc/response.php";
//
//    public CustomCartAlertDialog(Activity a) {
//        super(a);
//        // TODO Auto-generated constructor stub
//        this.c = a;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.custom_cart_alert);
//
//        progress = (ProgressBar)findViewById(R.id.progress_bars);
//        progress.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                progress.setVisibility(View.INVISIBLE);
//                return false;
//            }
//        });
//        cart = (Button) findViewById(R.id.btn_addtocart);
//       detail = (Button) findViewById(R.id.btn_detail);
//        cancel = (Button) findViewById(R.id.btn_cancel);
//        cart.setOnClickListener(this);
//        detail.setOnClickListener(this);
//       cancel.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_addtocart:
//                int cntsub=0;
//                cntsub= Integer.parseInt(SubCoursesActivity1.notif.getText().toString());
//
//                SubCourseAdapterNew.PersonViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);
//                Intent in = new Intent(c, CourseDetailActivity.class);
//                coursename = course_details.get(i).getCoursenam();
//                String crcid=course_details.get(i).getCoursid();
//                subcourse_id = course_details.get(i).getSubcoursid();
//                loc = course_details.get(i).getLocation();
//                targt = course_details.get(i).getTarget_audience();
//                obj = course_details.get(i).getObjectives();
//                val = course_details.get(i).getValidity();
//                dur = course_details.get(i).getDuration();
//                crsdes = course_details.get(i).getCourse_description();
//                crsnam = course_details.get(i).getCrces();
//                thmb = course_details.get(i).getThumbnail();
//                in.putExtra("coursesid",crcid);
//                in.putExtra("coursesname", coursename);
//                in.putExtra("subcourse_id",subcourse_id );
//                System.out.println("subcrcidoptions" + subcourse_id );
//                System.out.println("coursesname"+coursename);
//                in.putExtra("selctedsubcourse", dur);
//                in.putExtra("aim", aim);
//                in.putExtra("location", loc);
//                in.putExtra("target_audience", targt);
//                in.putExtra("objectives", obj);
//                in.putExtra("validity", val);
//                in.putExtra("duration", dur);
//                in.putExtra("course_description", crsdes);
//                in.putExtra("selctedcourse", crsnam);
//                in.putExtra("cntsub", cntsub);
//                in.putExtra("thmb", thmb);
//                System.out.println("<<<<<<<<<<<<<selctd>>>>>>>>>>>>>>>>>>>>" + cs);
//                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            c.startActivity(in);
//               dismiss();
//                break;
//            case R.id.btn_detail:
//                dismiss();
//                break;
//            case R.id.btn_cancel:
//                SubCourseAdapterNew.PersonViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);
//
//                dismiss();
//                break;
//            default:
//                break;
//        }
//
//    }
//}