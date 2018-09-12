package meridian.com.etsdcapp.course;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;

import meridian.com.etsdcapp.cart.CartActivityNews;
import meridian.com.etsdcapp.model.CartModel;
import meridian.com.etsdcapp.model.CoursesModel;
import meridian.com.etsdcapp.cart.DataHelper;
import meridian.com.etsdcapp.R;

public class CourseDetailActivity extends AppCompatActivity {
    String sel_sub_crc,sel_crc,thmb,crc_descrptn,crc_numbr;
    ImageView crcpc,backcrcdetl;
    TextView crc,subcrc,dur,val,descr;
    WebView target_audiences;
    WebView aim,loc;
    ArrayList<CoursesModel> crsdtls;
    String course_description;
    String course_number;
    String duration;
    String fee ;
    WebView obj;
    String subs="",subcourse_id="";
    String validity;
    String objectives ;
    String course_name ;
    String location ;
    String aims ;
    DataHelper dataHelper;
    String availability;
    String approval_certification,target_audience ;
    String thmbs,coursename,subcourseid;
    Button addtocrt,reg,enquire;
    static  long notifcnt;
    static  String k="",id="";
    CartModel c=new CartModel();
    ArrayList<String>  arrstrng_crs,arrstrng_crsid;

    String maincrc_id,maincourseid;
    static  int count;
    MyInterface listener;
    int cntsub=0;
    int flag=0;
    int check=0;
    String   selected_crc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_detail);


        crc = (TextView) findViewById(R.id.txtcrcenq);
        subcrc = (TextView) findViewById(R.id.txtsubenq);
        obj = (WebView)findViewById(R.id.txtobj);
        dur = (TextView)findViewById(R.id.txt_dur);
        val = (TextView)findViewById(R.id.txt_val);
        aim = (WebView) findViewById(R.id.txt_crcaim);
        descr = (TextView)findViewById(R.id.txt_descr);
        target_audiences= (WebView)findViewById(R.id.txt_crcnmbr);
        loc = (WebView) findViewById(R.id.txt_crcloc);
        crcpc = (ImageView) findViewById(R.id.imgcrcenq);


        Bundle extras = getIntent().getExtras();
        final CartModel cartModel=new CartModel();

        SharedPreferences sharedPref = getSharedPreferences("pref1", Context.MODE_PRIVATE);
        thmbs=sharedPref.getString("thmb",null);
        MainCoursesActivity mainCoursesActivity=new MainCoursesActivity();
        maincrc_id= mainCoursesActivity.maincorc();
        System.out.println("subcrcmaincrcid"+  maincrc_id);
        // coursename=sharedPref.getString("coursename",null);
        dataHelper = new DataHelper(getApplicationContext());
        if (extras != null) {
            sel_sub_crc = extras.getString("selctedsubcourse");
            coursename=extras.getString("coursesname");
            // courseid=extras.getString("coursesid");
            subcourseid=extras.getString("subcourse_id");
            System.out.println("subcourseidpassed"+subcourseid);
            System.out.println(" sel_sub_crc" + sel_sub_crc);
            crc_descrptn= extras.getString("course_description");
            System.out.println("coursedescriptn"+crc_descrptn);
            target_audience = extras.getString("target_audience");
            sel_crc = extras.getString("selctedcourse");
            duration = extras.getString("duration");
            fee = extras.getString("fee");
            validity= extras.getString("validity");
            objectives= extras.getString("objectives");
            aims = extras.getString("aim");
            cntsub = extras.getInt("cntsub");
            location = extras.getString("location");
            availability= extras.getString("availability");
            approval_certification= extras.getString("approval_certification)");
            // thmb = extras.getString("thmb");
            int cnt = extras.getInt("cnt");
            crc.setText("" +coursename);
            subcrc.setText("" + sel_crc);
            Picasso.with(getApplicationContext()).load(thmbs).fit().into(crcpc);
            objectives.replaceAll("<p>","\n");
            System.out.println("objectives"+objectives);

            dur.setText(crc_numbr);
//            aim.setText(aims);
//            loc.setText(location);
            val.setText(validity);

            if(crc_descrptn.isEmpty()) {
                descr.setText("");

            }
            else {

                descr.setText("(" + crc_descrptn + ")");
            }
            dur.setText(duration);
            //target_audiences.setText(target_audience);
            //Picasso.with(getApplicationContext()).load(thmb).fit().into(crcpc);
        }
        String str="<div style=\'background-color:transparent;padding: 10px ;color:White'>"+objectives+"</div";
        String message ="<font color='White'>"+objectives;
        obj.loadUrl("javascript:document.body.style.color=\"White\";");
        // personViewHolder.detailtv.setText(newcontent);
        obj.setBackgroundColor(Color.TRANSPARENT);
        float fontSize;
        obj.getSettings().setJavaScriptEnabled(true);
        obj.loadDataWithBaseURL("",str, "text/html", "UTF-8", "");
        Resources res = getResources();
        fontSize = res.getDimension(R.dimen.txtSize);
        obj.getSettings().setDefaultFontSize(14);


        String str1="<div style=\'background-color:transparent;padding: 10px ;color:White'>"+target_audience+"</div";
        String message1 ="<font color='White'>"+target_audience;
        target_audiences.loadUrl("javascript:document.body.style.color=\"White\";");
        // personViewHolder.detailtv.setText(newcontent);
        target_audiences.setBackgroundColor(Color.TRANSPARENT);
        target_audiences.getSettings().setJavaScriptEnabled(true);
        target_audiences.loadDataWithBaseURL("",str1, "text/html", "UTF-8", "");
        Resources res1 = getResources();
        //thisWB.getSettings().setTextZoom(thisTV.getTextSize());
        fontSize = res1.getDimension(R.dimen.txtSize);

//        double s= (int) aim.getTextSize();
//        s= s-1.20;
//        System.out.println("size"+s);
        target_audiences.getSettings().setDefaultFontSize(14);


        String str2 ="<div style=\'background-color:transparent;padding: 10px ;color:White'>"+aims+"</div";
        String message2 ="<font color='White'>"+aims;
        aim.loadUrl("javascript:document.body.style.color=\"White\";");
        // personViewHolder.detailtv.setText(newcontent);
        aim.setBackgroundColor(Color.TRANSPARENT);
        float fontSize2;
        aim.getSettings().setJavaScriptEnabled(true);
        aim.loadDataWithBaseURL("", str2, "text/html", "UTF-8", "");
        Resources res2 = getResources();
        fontSize2 = res2.getDimension(R.dimen.txtSize);
        aim.getSettings().setDefaultFontSize(14);

        String str3 ="<div style=\'background-color:transparent;padding: 10px ;color:White'>"+location+"</div";

        loc.loadUrl("javascript:document.body.style.color=\"White\";");
        // personViewHolder.detailtv.setText(newcontent);
        loc.setBackgroundColor(Color.TRANSPARENT);
        float fontSize3;
        loc.getSettings().setJavaScriptEnabled(true);
        loc.loadDataWithBaseURL("", str3, "text/html", "UTF-8", "");
        Resources res3 = getResources();
        fontSize3 = res3.getDimension(R.dimen.txtSize);
        loc.getSettings().setDefaultFontSize(14);



        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tops);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addtocrt= (Button) findViewById(R.id.but_dt_addtocrt);
        addtocrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int _course_ID = 0;
                CartModel c=new CartModel();


                SharedPreferences sharedPref =getSharedPreferences("countnum",Context.MODE_PRIVATE);
                count=  sharedPref.getInt("count",0);
                count++;

                SQLiteDatabase db = dataHelper.getReadableDatabase();

                String appnd="";


                if (dataHelper.verification(coursename))
                {

                    String selectQuery1 = "SELECT  * FROM " + DataHelper.TABLE_COURSE +" WHERE "+ DataHelper.KEY_MAINCOURSE_ID +" = "+maincrc_id ;

                    System.out.println("" + selectQuery1);
                    Cursor cursor1 = db.rawQuery(selectQuery1, null);

                    if (cursor1.getCount() > 0)
                    {
                        while (cursor1.moveToNext()) {
                            int ID= cursor1.getInt(cursor1.getColumnIndex("_course_ID"));
                            c.setCrcnam(cursor1.getString(cursor1.getColumnIndex("_course")));
                            maincourseid = cursor1.getString(cursor1.getColumnIndex("maincourseid"));
                            subs= cursor1.getString(cursor1.getColumnIndex("_subcourse"));
                            subcourse_id= cursor1.getString(cursor1.getColumnIndex("_subcourseid"));



                        }
                    }

//                    for( int p=0;p<crc_list.length;p++)
//                    {
//                        if( sel_crc.equals(crc_list[p]) )
//                        {
//                          count--;
//
//                            android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseDetailActivity.this).create();
//                            alertDialog.setTitle("Alert");
//                            alertDialog.setMessage("Item Already Added to cart");
//                            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//
//
//
//
////                                            dataHelper.getWritableDatabase();
////                                            dataHelper.updateCourse(maincrc_id,coursename,subs,subcourse_id);
//                                            System.out.println("updated_database_subcrc_detail1" +subs);
//                                            System.out.println("updated_database_subcrc_detailid1" +subcourse_id);
//                                           // notifcnt = dataHelper.getProfilesCount();
//
//                                            Intent i=new Intent(getApplicationContext(),CartActivityNews.class);
//                                            startActivity(i);
//                                            dialog.dismiss();
//                                            finish();
//
//
//
//
//
//                                        }
//                                    });
//                            alertDialog.show();
//                            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
//                            nbutton.setTextColor(getResources().getColor(R.color.Orange));
//                        }
//                        else
//                        {
//
//
//                            android.support.v7.app.AlertDialog alertDialog1 = new android.support.v7.app.AlertDialog.Builder(CourseDetailActivity.this).create();
//                            alertDialog1.setTitle("Alert");
//                            alertDialog1.setMessage("Item  Added to cart");
//                            alertDialog1.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//
//
//
//                                            System.out.println("updated_database_subcrc_detail2" +subs);
//                                            k=subs+"%"+sel_crc;
//                                            id=subcourse_id+'%'+subcourseid;
//
//                                            System.out.println("updated_coursename_detail" +coursename);
//                                            System.out.println("updated_subcourse_detail" +  k);
//                                            System.out.println("updated_subcourse_id_detail" +  id);
//                                            System.out.println("veriiitrue" + dataHelper.verification(coursename));
//                                            dataHelper.getWritableDatabase();
//                                            dataHelper.updateCourse(maincrc_id,coursename,k,id);
//                                            Intent i=new Intent(getApplicationContext(),CartActivityNews.class);
//                                            startActivity(i);
//                                          //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
//                                            // notifcnt = dataHelper.getProfilesCount();
//                                            dialog.dismiss();
//                                            finish();
//
//
//
//
//
//                                        }
//                                    });
//                            alertDialog1.show();
//                            Button nbutton = alertDialog1.getButton(DialogInterface.BUTTON_NEUTRAL);
//                            nbutton.setTextColor(getResources().getColor(R.color.Orange));
//
//
//                        }
//                        }




                }

                else
                {


                    check=1;


                    final SweetAlertDialog dialog = new SweetAlertDialog(CourseDetailActivity.this,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alart!")
                            .setContentText("Empty Fields")
                            .setConfirmText("YES")
                            .setCancelText("NO")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    System.out.println(" insert_maincour---id----ename_detail............." +maincrc_id);
                                    System.out.println(" insert_maincoursename_detail............." +coursename);
                                    System.out.println(" insert_subcoursename_detail..............." + sel_crc);
                                    System.out.println("insert_subcoursename_id_detail................." +subcourseid);
                                    dataHelper.getWritableDatabase();
                                    dataHelper.insertData(maincrc_id,coursename,sel_crc, subcourseid);

                                    //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                    // notifcnt = dataHelper.getProfilesCount();
                                    dialog.dismiss();
                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener(){

                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                    // notifcnt = dataHelper.getProfilesCount();
                                    dialog.dismiss();
                                    finish();





                                }
                            })
                            .show();


                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));
                    dialog.findViewById(R.id.cancel_button).setBackgroundColor(Color.parseColor("#10315a"));




                 /*   android.support.v7.app.AlertDialog alertDialog1 = new android.support.v7.app.AlertDialog.Builder(CourseDetailActivity.this).create();
                    alertDialog1.setTitle("Alert");
                    alertDialog1.setMessage("Add item to Cart");
                    alertDialog1.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {


                                    System.out.println(" insert_maincour---id----ename_detail............." +maincrc_id);
                                    System.out.println(" insert_maincoursename_detail............." +coursename);
                                    System.out.println(" insert_subcoursename_detail..............." + sel_crc);
                                    System.out.println("insert_subcoursename_id_detail................." +subcourseid);
                                    dataHelper.getWritableDatabase();
                                    dataHelper.insertData(maincrc_id,coursename,sel_crc, subcourseid);

                                    //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                    // notifcnt = dataHelper.getProfilesCount();
                                    dialog.dismiss();






                                }
                            });
                    alertDialog1.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {



                                    //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                    // notifcnt = dataHelper.getProfilesCount();
                                    dialog.dismiss();
                                    finish();





                                }
                            });
                    alertDialog1.show();
                    alertDialog1.setCancelable(false);
                    Button nbutton = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE);
                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
                    Button nbutton1 = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton1.setTextColor(getResources().getColor(R.color.Orange));


*/
                    //  notifcnt=dataHelper.getProfilesCount();
                    System.out.println("veriiifalse");



                }

//                addtocrt.setTextColor(getResources().getColor(R.color.Black));
//                addtocrt.setBackgroundResource(R.color.White);
//                System.out.println("subcoursescnt"+count);
//                SharedPreferences sharedPref =getSharedPreferences("countnum",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt("count", count);
//                editor.commit();

//                System.out.println(" cntsub"+ cntsub);
//                cntsub=cntsub+count;
//                System.out.println(" cntsub1"+ cntsub);
//               SubCoursesActivity1.notif.setText(""+cntsub);



                String crc_list[] = subs.split("%");

                for( int p=0;p<crc_list.length;p++)
                {
                    if(sel_crc.equals(crc_list[p]) )
                    {
                        flag=1;
                        selected_crc=sel_crc;
                    }

                }


                if(flag==1)
                {
                    count--;
                    final SweetAlertDialog dialog = new SweetAlertDialog(CourseDetailActivity.this,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alert!")
                            .setContentText("Item Already Added to cart")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {


//                                            dataHelper.getWritableDatabase();
//                                            dataHelper.updateCourse(maincrc_id,coursename,subs,subcourse_id);
                                    System.out.println("updated_database_subcrc_detail1" +subs);
                                    System.out.println("updated_database_subcrc_detailid1" +subcourse_id);
                                    // notifcnt = dataHelper.getProfilesCount();


                                    dialog.dismiss();


                                }
                            })
                            .show();


                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));


                 /*   android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CourseDetailActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Item Already Added to cart");
                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {



//                                            dataHelper.getWritableDatabase();
//                                            dataHelper.updateCourse(maincrc_id,coursename,subs,subcourse_id);
                                    System.out.println("updated_database_subcrc_detail1" +subs);
                                    System.out.println("updated_database_subcrc_detailid1" +subcourse_id);
                                    // notifcnt = dataHelper.getProfilesCount();


                                    dialog.dismiss();






                                }
                            });
                    alertDialog.show();
                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
                }
                else {

                    if(check==0) {


                        final SweetAlertDialog dialog = new SweetAlertDialog(CourseDetailActivity.this,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText("Add item to Cart")
                                .setContentText("")
                                .setConfirmText("YES")
                                .setCancelText("NO")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {

                                        System.out.println("updated_maincoursename_detail" + coursename);
                                        System.out.println("updated_database_.....subcrc_detail2.............." + subs);
                                        System.out.println("updated_selected.....subcourse......." + sel_crc);
                                        k = subs + "%" + sel_crc;
                                        id = subcourse_id + '%' + subcourseid;
                                        System.out.println("updated_subcourse_detail......." + k);
                                        System.out.println("updated_subcourse_id_detail.................." + id);
                                        System.out.println("veriiitrue..........................." + dataHelper.verification(coursename));
                                        dataHelper.getWritableDatabase();
                                        dataHelper.updateCourse(maincrc_id, coursename, k, id);

                                        //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                        // notifcnt = dataHelper.getProfilesCount();
                                        dialog.dismiss();

                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener(){

                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                        // notifcnt = dataHelper.getProfilesCount();
                                        dialog.dismiss();
                                        finish();

                                    }
                                })
                                .show();


                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));
                        dialog.findViewById(R.id.cancel_button).setBackgroundColor(Color.parseColor("#10315a"));



              /*          android.support.v7.app.AlertDialog alertDialog1 = new android.support.v7.app.AlertDialog.Builder(CourseDetailActivity.this).create();
                        alertDialog1.setTitle("Alert");
                        alertDialog1.setMessage("Add item to Cart");
                        alertDialog1.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        System.out.println("updated_maincoursename_detail" + coursename);
                                        System.out.println("updated_database_.....subcrc_detail2.............." + subs);
                                        System.out.println("updated_selected.....subcourse......." + sel_crc);
                                        k = subs + "%" + sel_crc;
                                        id = subcourse_id + '%' + subcourseid;
                                        System.out.println("updated_subcourse_detail......." + k);
                                        System.out.println("updated_subcourse_id_detail.................." + id);
                                        System.out.println("veriiitrue..........................." + dataHelper.verification(coursename));
                                        dataHelper.getWritableDatabase();
                                        dataHelper.updateCourse(maincrc_id, coursename, k, id);

                                        //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                        // notifcnt = dataHelper.getProfilesCount();
                                        dialog.dismiss();


                                    }
                                });
                        alertDialog1.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        //  Toast.makeText(getApplicationContext(),"Item Added To cart",Toast.LENGTH_SHORT).show();
                                        // notifcnt = dataHelper.getProfilesCount();
                                        dialog.dismiss();
                                        finish();


                                    }
                                });
                        alertDialog1.show();
                        alertDialog1.setCancelable(false);
                        Button nbutton = alertDialog1.getButton(DialogInterface.BUTTON_POSITIVE);
                        nbutton.setTextColor(getResources().getColor(R.color.Orange));
                        Button nbutton1 = alertDialog1.getButton(DialogInterface.BUTTON_NEGATIVE);
                        nbutton1.setTextColor(getResources().getColor(R.color.Orange));*/
                    }


                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("count",count);
                editor.commit();

                SubCoursesActivity1.notif.setText(""+count);




            }


        });



//        reg= (Button) findViewById(R.id.but_reg_dt_ecrc);
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i;
//                DataHelper dataHelper=new DataHelper(getApplicationContext());
//               // dataHelper.insertData(sel_crc, sel_sub_crc);
//                reg.setTextColor(getResources().getColor(R.color.Black));
//                reg.setBackgroundResource(R.color.White);
//                Intent in=new Intent(getApplicationContext(),CartActivity.class);
////                in.putExtra("selctedsubcourse",sel_crc);
////                in.putExtra("selctedcourse",coursename);
////                System.out.println("selctd"+coursename);
//                startActivity(in);
//            }
//        });

        enquire= (Button) findViewById(R.id.but_enq_dt_ecrc);
        // int cnts=rvc.getclkdcnt();
        enquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enquire.setBackgroundResource(R.color.White);
                enquire.setTextColor(getResources().getColor(R.color.Black));



                Intent in = new Intent(getApplicationContext(), CourseEnquireActivity.class);
                in.putExtra("coursesname",coursename);
                in.putExtra("sel_crc",sel_crc);
                in.putExtra("thmbs", thmb);
                //   coursename=extras.getString("coursesname");
                in.putExtra("course_description",crc_descrptn);
                in.putExtra("duration",duration);
                in.putExtra("validity",validity);

                startActivity(in);
            }



        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        enquire.setBackgroundResource(R.color.butnbakcolr);
        enquire.setTextColor(getResources().getColor(R.color.White));
        addtocrt.setTextColor(getResources().getColor(R.color.White));
        addtocrt.setBackgroundResource(R.color.butnbakcolr);
        SubCoursesActivity1.notif.setText(""+cntsub);



    }
    public  static int coursedetlcnt()
    {

        return count;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        SubCoursesActivity1.notif.setText(""+cntsub);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SubCoursesActivity1.notif.setText(""+cntsub);
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