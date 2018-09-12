package meridian.com.etsdcapp.adapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.cart.DataHelper;
import meridian.com.etsdcapp.course.CourseDetailActivity;
import meridian.com.etsdcapp.course.MainCoursesActivity;
import meridian.com.etsdcapp.course.MyInterface;
import meridian.com.etsdcapp.course.SubCoursesActivity1;
import meridian.com.etsdcapp.model.CartModel;
import meridian.com.etsdcapp.model.CoursesModel;

/**
 * Created by user1 on 14-10-2015.
 */
public class SubCourseAdapterNew_copy extends RecyclerView.Adapter<SubCourseAdapterNew_copy.PersonViewHolder> {

     MyInterface listener;
 private final static int FADE_DURATION = 1000;

    List<CoursesModel>   itemsCopy=new ArrayList<>();
  private int lastPosition = -1;
    static String addstring="";
    String k="",sub_id="";
    CoursesModel crc;
    DataHelper dataHelper;
    Context context;
    static String crc_des,subcourse_id;
    List<CoursesModel> course_details=new ArrayList<>();
    ArrayList<String> arrstrng_crs=new ArrayList<>();
    ArrayList<String>  arrstrng_crsid=new ArrayList<>();
   int cnt;
    String aim,loc,obj,targt,val,dur,crsdes,crsnam,thmb,coursename;
    static String clckd_crcnam="",cs,lk;
   public static long notifcnt;
    String maincrc_id;
    String sub="",subcourse_id_tb="",s="";
    String crc_list[];
    static int count_notif,checknotif;

    public Context mcontext;
    int count=0,sum=0;
    int newi;

    public SubCourseAdapterNew_copy(List<CoursesModel> course_details, Context context){

        this.listener = listener;
        this.context=context;
        this.course_details=course_details;

        this.mcontext=context;
        itemsCopy.addAll(course_details);
    }




    public static class PersonViewHolder extends RecyclerView.ViewHolder  {

        TextView slno,course;
    static   CheckedTextView chk;
        TextView course_flnm;
      LinearLayout back;
        LinearLayout Lay;
        PersonViewHolder(View itemView) {
            super(itemView);
            Lay = (LinearLayout) itemView.findViewById(R.id.lay4);
            slno = (TextView) itemView.findViewById(R.id.slno);
            course = (TextView) itemView.findViewById(R.id.txt_course);
            course_flnm = (TextView) itemView.findViewById(R.id.txt_crc_flnm);
            chk = (CheckedTextView) itemView.findViewById(R.id.chkedtextvw);
           back= (LinearLayout) itemView.findViewById(R.id.back);
            cs=course.getText().toString();




        }

    }


    @Override
    public int getItemCount() {

        return course_details.size();

    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_subcourse, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);

        return pvh;
    }



    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i)
    {   dataHelper=new DataHelper(context);
      //  SQLiteDatabase db = dataHelper.getReadableDatabase();

 final CartModel cartModel=new CartModel();
        MainCoursesActivity mainCoursesActivity=new MainCoursesActivity();
        maincrc_id= mainCoursesActivity.maincorc();
        addstring = "";

        newi=i;
        int s = newi + 1;
        cnt=0;
        personViewHolder.slno.setText(s + ".");
        if (cnt == 0) {
            clckd_crcnam = "";

            System.out.println("1" + clckd_crcnam);
        } else {
            clckd_crcnam = course_details.get(i).getCrces();
            System.out.println("2" + clckd_crcnam);
        }

        System.out.println("crc_description_height" + personViewHolder.course.getHeight()+personViewHolder.course_flnm.getHeight());


        if(course_details.get(i).getCourse_description().isEmpty()) {
            personViewHolder.course_flnm.setVisibility(View.GONE);
            personViewHolder.back.setBackgroundResource(R.color.rowcrc);
           // personViewHolder.course.setGravity(Gravity.BOTTOM);



        }
        else
{   personViewHolder.course_flnm.setVisibility(View.VISIBLE);
    personViewHolder.course_flnm.setText(course_details.get(i).getCourse_description());


}
        personViewHolder.course.setText(course_details.get(i).getCrces());
        //aim= crs_DTLS.get(i).getAim();
        //  lk=rvc.getstringval();

//        if(course_details.get(i).getCrces().length()>30)
//    {
//        final int mid = course_details.get(i).getCrces().length() / 2; //get the middle of the String
//        String[] parts = {course_details.get(i).getCrces().substring(0, mid),course_details.get(i).getCrces().substring(mid)};
//        System.out.println(parts[0]); //first part
//        System.out.println(parts[1]);
//
//        personViewHolder.course.setText(parts[0] + "\n" + parts[1]);
//    }
//        else
//        {  personViewHolder.course.setText(course_details.get(i).getCrces());
//
//        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // Running on something older than API level 11, so disable
            // the drag/drop features that use ClipboardManager APIs
            setAnimation(personViewHolder.course, i);
        }
//        Animation animation = AnimationUtils.loadAnimation(context,
//                (i> lastPosition) ? R.anim.fadein
//                        : R.anim.fadeout);
//        personViewHolder.itemView.startAnimation(animation);
//        lastPosition =i;


        personViewHolder.chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            }
//
//            @Override
//          public boolean onTouch(View v, MotionEvent event) {


                if (!personViewHolder.chk.isChecked())
                {

                   // personViewHolder.chk.setBackgroundResource(R.drawable.checked_mark1);
                   // personViewHolder.chk.setChecked(false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.dialog);
                    builder.setTitle("Please Select any one option");
                    builder.setMessage("Please Select any one option")

                            .setCancelable(false)
                            .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    personViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("  DETAIL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    int cntsub=0;
                                    cntsub= Integer.parseInt(SubCoursesActivity1.notif.getText().toString());

                                    personViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);
                                    Intent in = new Intent(context, CourseDetailActivity.class);
                                    coursename = course_details.get(i).getCoursenam();
                                    String crcid=course_details.get(i).getCoursid();
                                    subcourse_id = course_details.get(i).getSubcoursid();
                                    loc = course_details.get(i).getLocation();
                                    targt = course_details.get(i).getTarget_audience();
                                    obj = course_details.get(i).getObjectives();
                                    val = course_details.get(i).getValidity();
                                    dur = course_details.get(i).getDuration();
                                    crsdes = course_details.get(i).getCourse_description();
                                    crsnam = course_details.get(i).getCrces();
                                    thmb = course_details.get(i).getThumbnail();
                                  aim = course_details.get(i).getAim();
                                    in.putExtra("coursesid",crcid);
                                    in.putExtra("coursesname", coursename);
                                    in.putExtra("subcourse_id",subcourse_id );
                                    System.out.println("subcrcidoptions" + subcourse_id );
                                    System.out.println("coursesname"+coursename);
                                    in.putExtra("selctedsubcourse", dur);
                                    in.putExtra("aim", aim);
                                    in.putExtra("location", loc);
                                    in.putExtra("target_audience", targt);
                                    in.putExtra("objectives", obj);
                                    in.putExtra("validity", val);
                                    in.putExtra("duration", dur);
                                    in.putExtra("course_description", crsdes);
                                    in.putExtra("selctedcourse", crsnam);
                                    in.putExtra("cntsub", cntsub);
                                    in.putExtra("thmb", thmb);
                                    System.out.println("<<<<<<<<<<<<<selctd>>>>>>>>>>>>>>>>>>>>" + cs);
                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(in);
                                    dialog.dismiss();


                                }
                            })

                    .setPositiveButton("ADD TO CART", new DialogInterface.OnClickListener()
                    {
                              public void onClick(DialogInterface dialog, int id) {
                                   cnt=cnt+1;
                                  SharedPreferences sharedPref =context.getSharedPreferences("countnum",Context.MODE_PRIVATE);
                                  count=  sharedPref.getInt("count",0);
                                  count++;
                                  personViewHolder.chk.setBackgroundResource(R.drawable.checked_mark1);
                                    coursename = course_details.get(i).getCoursenam();
                                    clckd_crcnam = course_details.get(i).getCrces();
                                     subcourse_id = course_details.get(i).getSubcoursid();
                                    System.out.println("clicked_coursename"+clckd_crcnam);
                                    crc_des = course_details.get(i).getCourse_description();
                                    if (clckd_crcnam != null) {
                                        arrstrng_crs.add(clckd_crcnam);
                                        arrstrng_crsid.add(subcourse_id);
                                        System.out.println("clickdcrcnm" + arrstrng_crs);
                                        addstring += "," + clckd_crcnam;

                                    }


                                    int _course_ID = 0;
                                    CartModel c=new CartModel();

                                    StringBuilder buffer = new StringBuilder();
                                  StringBuilder buffer1 = new StringBuilder();
                                    boolean processedFirst = false,processedFirst1 = false;
                                    String firstParam = null, secondParam = null;

                                    try {
                                      for (String record : arrstrng_crs)
                                      {
                                          if (processedFirst)
                                              buffer.append(",");
                                          buffer.append(record);
                                          processedFirst = true;
                                      }
                                      firstParam = buffer.toString();
                                  }
                                    finally
                                    {
                                      buffer = null;
                                  }
                                  processedFirst1 = false;
                                  try {
                                      for (String record1 : arrstrng_crsid) {
                                          if (processedFirst1)
                                              buffer1.append(",");
                                          buffer1.append(record1);
                                          processedFirst1 = true;
                                      }
                                      secondParam = buffer1.toString();
                                  } finally {
                                      buffer1 = null;
                                  }
                                  processedFirst1 = false;



//                                    SharedPreferences sharedPref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
//
//                                    coursename=sharedPref.getString("coursename",null);


                                    //  boolean chk=dataHelper.verification(selcrc);
                                    // System.out.println("chk"+chk);
                                   SQLiteDatabase db = dataHelper.getReadableDatabase();





                                    if (dataHelper.verification(coursename))
                                    {


                                        String selectQuery = "SELECT  * FROM " + DataHelper.TABLE_COURSE +" WHERE "+ DataHelper.KEY_MAINCOURSE_ID +" = "+maincrc_id ;
                                        System.out.println("" + selectQuery);
                                        Cursor cursor = db.rawQuery(selectQuery, null);

                                        if (cursor.getCount() > 0) {
                                            while (cursor.moveToNext())
                                            {

                                                c.setCourseid(cursor.getString(cursor.getColumnIndex("_course_ID")));
                                                c.setCrcnam(cursor.getString(cursor.getColumnIndex("_course")));
                                                sub=cursor.getString(cursor.getColumnIndex("_subcourse"));
                                                subcourse_id_tb=cursor.getString(cursor.getColumnIndex("_subcourseid"));


                                                System.out.println("updated_databasesubcoursename_adapter1" +sub);
                                            }




                                        }
                                        String crc_list[] = sub.split("%");

                                        for( int p=0;p<crc_list.length;p++)
                                        {
                                            if( clckd_crcnam.equals(crc_list[p]) ) {
                                               count--;
                                               System.out.println("clckdcrcnam"+clckd_crcnam);

                                                final SweetAlertDialog dialog1 = new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
                                                dialog1.setTitleText("Alert!")
                                                        .setContentText("Item Already in cart")
                                                        .setConfirmText("OK")
                                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                            @Override
                                                            public void onClick(SweetAlertDialog sDialog) {
                                                                dialog1.dismiss();
                                                            }
                                                        })
                                                        .show();


                                                dialog1.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                             /*   AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                                alertDialog.setTitle("Alert");
                                                alertDialog.setMessage("Item Already in cart");
                                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dataHelper.getWritableDatabase();
                                                                dataHelper.updateCourse(maincrc_id, coursename,sub, subcourse_id_tb);

                                                                dialog.dismiss();


                                                            }
                                                        });
                                                alertDialog.show();*/

                                            }
                                            else {


                                                k = sub + "%" + clckd_crcnam;
                                                sub_id = subcourse_id_tb + "%" + subcourse_id;
                                                System.out.println("updated_databasesubcoursename_adapter2" + sub);
                                                System.out.println("updated_coursename_adapter" + coursename);
                                                System.out.println("updated_subcourse_adapter" + k);
                                                System.out.println("updated_subcourse_id_adapter" + sub_id);
                                                System.out.println("updated_maincourseid" + maincrc_id);
                                                dataHelper.getWritableDatabase();
                                                dataHelper.updateCourse(maincrc_id, coursename, k, sub_id);

                                                System.out.println("cnt1"+cnt);

                                            }


                                        }
                                    }

                                    else
                                    {
                                        count_notif+=1;

                                        dataHelper.getWritableDatabase();
                                        System.out.println(" insert_coursename_adapter" + coursename);
                                        System.out.println(" insert_subcoursename_adapter" + clckd_crcnam);
                                        System.out.println("insert_subcoursename_id_adapter" + subcourse_id);
                                       dataHelper.insertData(maincrc_id,coursename, clckd_crcnam,subcourse_id);

                                        System.out.println("cnt2"+cnt);
                                         checknotif= cartModel.getCheckcount();
                                        checknotif=checknotif+1;
                                        cartModel.setCheckcount(checknotif);




                                    }




                                    dialog.dismiss();

                                System.out.println("subcrccount"+count);



//
                                SharedPreferences.Editor editor = sharedPref.edit();
                                  editor.putInt("count",count);
                                  editor.commit();

                                  SubCoursesActivity1.notif.setText(""+count);


                              }




                            });

                    AlertDialog alert = builder.create();

                    alert.show();

                    Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                 //    nbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
                    nbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));

                    Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            //   pbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
                    pbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
                 Button nebutton = alert.getButton(DialogInterface.BUTTON_NEUTRAL);
   //                nebutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
                   nebutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));





                }
                else
                {
                    personViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);

                }

                personViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);

//
          //  return false;
        }




     });






    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public String getstringval()
    {return addstring;


    }
    public String getclkdmaincrc() {
        return coursename;
    }
    public String getclkd() {
        return clckd_crcnam;
    }
    public String getclkdcrcid() {
        return subcourse_id;
    }


    public String getclkddes()
    {return  crc_des ;

    }
//    public static int getclkdcnt()
//    {  System.out.println("clickedcountss"+cnt);
//        return  cnt;
//
//
//
//    }
    public static long getNotifcnt()
    {

      return notifcnt;
    }

    public int count_notif()
    {if(count_notif==0)
    {return 0;
    }
        else {
        return count_notif;
    }

    }
//    public static int getChecknotif()
//    {
//        return checknotif;
//    }

    public ArrayList<String> arrstrng()
    {return arrstrng_crs;


    }
    public ArrayList<String> arrstrngid()
    {return arrstrng_crsid;


    }


    private void setAnimation(View viewToAnimate, int position)
    {



        if (position > lastPosition)
       {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_enter);
            animation.setDuration(FADE_DURATION);
            viewToAnimate.startAnimation(animation);

            lastPosition = position;
       }
    }
    public void filter(String text) {

        if(text.isEmpty()){
            {
                course_details.clear();
                course_details.addAll(itemsCopy);
            }
        } else{
          //  ArrayList<PhoneBookItem> result = new ArrayList<>();
            List<CoursesModel> course_details1=new ArrayList<>();
            text = text.toLowerCase();
            for(CoursesModel item: itemsCopy){
                if(item.getCrces().toLowerCase().contains(text) || item.getCrces().toLowerCase().contains(text)){
                 course_details1.add(item);
                }
            }
          course_details.clear();
            course_details.addAll(course_details1);
        }
        notifyDataSetChanged();
    }


}
