//package meridian.com.etsdcapp.adapter;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//        import android.view.MotionEvent;
//        import android.view.View;
//        import android.view.ViewGroup;
//import android.widget.CheckedTextView;
//import android.widget.LinearLayout;
//        import android.widget.TextView;
//
//
//import java.util.ArrayList;
//        import java.util.List;
//
//import meridian.com.etsdcapp.PrefManager;
//import meridian.com.etsdcapp.R;
//import meridian.com.etsdcapp.cart.DataHelper;
//import meridian.com.etsdcapp.course.CourseDetailActivity;
//import meridian.com.etsdcapp.course.SubCoursesActivity;
//import meridian.com.etsdcapp.model.CartModel;
//import meridian.com.etsdcapp.model.CoursesModel;
//
///**
// * Created by user1 on 14-10-2015.
// */
//public class SubCourseAdapter extends RecyclerView.Adapter<SubCourseAdapter.PersonViewHolder> {
//
//
//    List<CoursesModel> course_details=new ArrayList<>();
//
//    static String addstring="";
//    CoursesModel crc;
//    Context context;
//    static String crc_des;
//    ArrayList<String> arrstrng_crs=new ArrayList<>();
//    static  int cnt;
//    String aim,loc,obj,targt,val,dur,crsdes,crsnam,thmb,coursename;
//    static String clckd_crcnam="",cs,lk;
//    static  int notifcnt
//
//  public SubCourseAdapter(List<CoursesModel> course_details, Context context){
//
//        this.context=context;
//        this.course_details=course_details;
//    }
//
//    public static class PersonViewHolder extends RecyclerView.ViewHolder  {
//
//        TextView slno,course;
//        public CheckedTextView chk;
//        TextView course_flnm;
//
//        LinearLayout Lay;
//        PersonViewHolder(View itemView) {
//            super(itemView);
//            Lay = (LinearLayout) itemView.findViewById(R.id.lay4);
//            slno = (TextView) itemView.findViewById(R.id.slno);
//            course = (TextView) itemView.findViewById(R.id.txt_course);
//            course_flnm = (TextView) itemView.findViewById(R.id.txt_crc_flnm);
//            chk = (CheckedTextView) itemView.findViewById(R.id.chkedtextvw);
//            cs=course.getText().toString();
//
//
//
//
//        }
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//
//        return course_details.size();
//    }
//
//
//    @Override
//    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_subcourse, viewGroup, false);
//        PersonViewHolder pvh = new PersonViewHolder(v);
//
//        return pvh;
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i) {
//        cnt=0;
//        addstring="";
//
//personViewHolder.course_flnm.setText(course_details.get(i).getCourse_description());
//
//        //aim= crs_DTLS.get(i).getAim();
//      //  lk=rvc.getstringval();
//        personViewHolder.course.setText(course_details.get(i).getCrces());
//        int s = i + 1;
//        if(cnt==0){
//            clckd_crcnam="";
//
//            System.out.println("1"+clckd_crcnam);
//        }
//        else {
//            clckd_crcnam = course_details.get(i).getCrces();
//            System.out.println("2"+clckd_crcnam);
//        }
//        personViewHolder.slno.setText(s + ".");
//        System.out.println("venuetitl");
////        personViewHolder.Lay.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                Intent in=new Intent(context,CourseDetailActivity.class);
////               coursename= course_details.get(i).getCoursenam();
////                loc= course_details.get(i).getLocation();
////                targt= course_details.get(i).getTarget_audience();
////                obj= course_details.get(i).getObjectives();
////                val= course_details.get(i).getValidity();
////                dur= course_details.get(i).getDuration();
////                crsdes= course_details.get(i).getCourse_description();
////                crsnam=course_details.get(i).getCrces();
////                thmb=course_details.get(i).getThumbnail();
////                in.putExtra("coursename",     coursename);
////                in.putExtra("selctedsubcourse",dur);
////                in.putExtra("aim",     aim);
////                in.putExtra("location", loc);
////                in.putExtra("target_audience",  targt);
////                in.putExtra("objectives",  obj);
////                in.putExtra("validity",val);
////                in.putExtra("duration",   dur);
////                in.putExtra("course_description",  crsdes);
////                in.putExtra("selctedcourse",crsnam);
////                in.putExtra("thmb",thmb);
////                System.out.println("<<<<<<<<<<<<<selctd>>>>>>>>>>>>>>>>>>>>"+cs);
////                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(in);
////            }
////        });
//
//
//        personViewHolder.chk.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (personViewHolder.chk.isChecked())
//                {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setMessage("Please Select any one option")
//                            .setCancelable(false)
//                            .setPositiveButton("DETAIL", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    Intent in=new Intent(context,CourseDetailActivity.class);
//                                    coursename= course_details.get(i).getCoursenam();
//                                    loc= course_details.get(i).getLocation();
//                                    targt= course_details.get(i).getTarget_audience();
//                                    obj= course_details.get(i).getObjectives();
//                                    val= course_details.get(i).getValidity();
//                                    dur= course_details.get(i).getDuration();
//                                    crsdes= course_details.get(i).getCourse_description();
//                                    crsnam=course_details.get(i).getCrces();
//                                    thmb=course_details.get(i).getThumbnail();
//                                    in.putExtra("coursename",     coursename);
//                                    in.putExtra("selctedsubcourse",dur);
//                                    in.putExtra("aim",     aim);
//                                    in.putExtra("location", loc);
//                                    in.putExtra("target_audience",  targt);
//                                    in.putExtra("objectives",  obj);
//                                    in.putExtra("validity",val);
//                                    in.putExtra("duration",   dur);
//                                    in.putExtra("course_description",  crsdes);
//                                    in.putExtra("selctedcourse",crsnam);
//                                    in.putExtra("thmb",thmb);
//                                    System.out.println("<<<<<<<<<<<<<selctd>>>>>>>>>>>>>>>>>>>>"+cs);
//                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    context.startActivity(in);
//                                  //  notifcnt=dataHelper.getProfilesCount();
//
//                                }
//                            })
//                            .setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    int _course_ID = 0;
//                                    CartModel c=new CartModel();
//                                    String _subcourse,coursename;
//                                    coursename= course_details.get(i).getCoursenam()
////                                    SharedPreferences sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE);
////
////                                    coursename=sharedPref.getString("coursename",null);
//
//                                    StringBuilder buffer = new StringBuilder();
//                                    boolean processedFirst = false;
//                                    String firstParam = null, secondParam = null;
//
//                                    try {
//                                        for (String record : arrstrng_crs) {
//                                            if (processedFirst)
//                                                buffer.append(",");
//                                            buffer.append(record);
//                                            processedFirst = true;
//                                        }
//                                        firstParam = buffer.toString();
//                                    } finally {
//                                        buffer = null;
//                                    }
//                                    processedFirst = false;
//
//                                    DataHelper dataHelper = new DataHelper(context);
//
//
//
//                                    //  boolean chk=dataHelper.verification(selcrc);
//                                    // System.out.println("chk"+chk);
//                                    SQLiteDatabase db = dataHelper.getReadableDatabase();
//                                    String selectQuery = "SELECT  * FROM " + DataHelper.TABLE_COURSE;
//                                    System.out.println("" + selectQuery);
//                                    Cursor cursor = db.rawQuery(selectQuery, null);
//
//                                    if (cursor.getCount() > 0) {
//                                        while (cursor.moveToNext()) {
//
//                                            c.setCourseid(cursor.getInt(cursor.getColumnIndex("_course_ID")));
//                                            c.setCrcnam(cursor.getString(cursor.getColumnIndex("_course")));
//                                            c.setSubcrcnam(cursor.getString(cursor.getColumnIndex("_subcourse")));
//                                        }
//                                    }
////            if (dataHelper.verification_id(_course_ID)) {
////                dataHelper.updateData(name, firstParam);
////
////            } else
////            {
////
////
////            }
//
//
//                                    if (dataHelper.verification(coursename)) {
//                                        System.out.println("veriiitrue"+dataHelper.verification(coursename));
//                                        dataHelper.getWritableDatabase();
//                                        dataHelper.updateCourse(coursename,firstParam);
//                                        notifcnt=dataHelper.getProfilesCount();
// //                dataHelper.updateData(name, firstParam);
//
//
//                                        //   String selectQuery =  "UPDATE "+dataHelper.TABLE_COURSE+ "SET" +dataHelper.KEY_SUB_COURSE +"="+firstParam +"WHERE"+dataHelper.KEY_COURSE+"="+name;
////                 UPDATE tblMyTable
////                 SET MyCell = MyCell + ',' + @MyParameterll = MyCell + ',' + @MyParameter
//                                    }
//
//                                    else
//                                    {    dataHelper.getWritableDatabase();
//                              coursenamedataHelper.insertData(coursename, firstParam);
//                                        notifcnt=dataHelper.getProfilesCount();
//
//
//                                        System.out.println("veriiifalse");
//
//
//
//                                    }
//
//
//
//                                }
//                            });
//                    Al//                    android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(context).create();
////                    alertDialog.setTitle("Alert");
////                    alertDialog.setMessage("Please Login to Register For this Course");
////                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
////                            new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int which) {
////                                    dialog.dismiss();
//////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
////
////                                }
////                            });
////                    alertDialog.show();
//
////                    android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(context).create();
////                    alertDialog.setTitle("Alert");
////                    alertDialog.setMessage("Please Select any one option");
////                    alertDialog.(AlertDialog.BUTTON_POSITIVE, "DETAIL",
////                            new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int which) {
////
////                                    Intent in=new Intent(context,CourseDetailActivity.class);
////                                    coursename= course_details.get(i).getCoursenam();
////                                    loc= course_details.get(i).getLocation();
////                                    targt= course_details.get(i).getTarget_audience();
////                                    obj= course_details.get(i).getObjectives();
////                                    val= course_details.get(i).getValidity();
////                                    dur= course_details.get(i).getDuration();
////                                    crsdes= course_details.get(i).getCourse_description();
////                                    crsnam=course_details.get(i).getCrces();
////                                    thmb=course_details.get(i).getThumbnail();
////                                    in.putExtra("coursename",     coursename);
////                                    in.putExtra("selctedsubcourse",dur);
////                                    in.putExtra("aim",     aim);
////                                    in.putExtra("location", loc);
////                                    in.putExtra("target_audience",  targt);
////                                    in.putExtra("objectives",  obj);
////                                    in.putExtra("validity",val);
////                                    in.putExtra("duration",   dur);
////                                    in.putExtra("course_description",  crsdes);
////                                    in.putExtra("selctedcourse",crsnam);
////                                    in.putExtra("thmb",thmb);
////                                    System.out.println("<<<<<<<<<<<<<selctd>>>>>>>>>>>>>>>>>>>>"+cs);
////                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                    context.startActivity(in);
////                                    dialog.dismiss();
//////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
////
////                                }
////                            });
////                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "ADD TO CART",
////                            new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int which) {
////                                    dialog.dismiss();
//////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
////
////                                }
////                            });
////                    alertDialog.show();                     crc = new CoursesModel();
//                    personViewHolder.chk.setChecked(false);
//                    crc.setChecked(true);
//                    cnt=cnt-1;
//                    System.out.println("cnt"+cnt);
//                    System.out.println("c "+personViewHolder.chk.isChecked());
//                    clckd_crcnam = course_details.get(i).getCrces();
//                    crc_des=course_details.get(i).getCourse_description();
//                    addstring=addstring.replaceAll(clckd_crcnam,"");
//                    addstring=addstring.replaceAll(clckd_crcnam,"");
//                  //  clckd_crcnam = crs_DTLS.get(i).getCrces();
//                //    StringBuilder tmp;
//
////                    tmp = new StringBuilder();
////                    tmp.append(clckd_crcnam);
//                    System.out.println("3"+clckd_crcnam);
//                  ///  addstring+="";
//                    personViewHolder.chk.setBackgroundResource(R.drawable.tickfalse);
//                    if(cnt==0){
//                        clckd_crcnam="";
//                        addstring="";
//                        System.out.println("4"+clckd_crcnam);
//                    }
//                    else {
//                       // clckd_crcnam = crs_DTLS.get(i).getCrces();
//                        clckd_crcnam="";
//                        addstring=addstring.replaceAll(",,",",");
//                        System.out.println("5"+clckd_crcnam);
//                    }                 } else {rintln("5"+clckd_crcnam);
//                    }
//                } else {
//
//                    crc = new CoursesModel();
//                    personViewHolder.chk.setChecked(true);
//                    System.out.println(" uc"+personViewHolder.chk.isChecked());
//
//                    crc.setChecked(false);
//                    cnt=cnt+1;
//                    System.out.println("cnt"+cnt);
//                    personViewHolder.chk.setBackgroundResource(R.drawable.checked_mark1);
//                    clckd_crcnam  = course_details.get(i).getCrces();
//                    crc_des=course_details.get(i).getCourse_description();
//                    System.out.println("6"+clckd_crcnam);
//                    crc.setClckd(clckd_crcnam);
//
//                    if(clckd_crcnam!=null)
//                    {
//                          arrstrng_crs.add(clckd_crcnam);
//                        System.out.println("clickdcrcnm"+arrstrng_crs);
//                        addstring += ","+ clckd_cr }am;
//
//                    }else {
//                    }
//                }
//
//
//                // Toast.makeText(context,addstring, Toast.LENGTH_S//        personViewHolder.chk.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (personViewHolder.chk.isChecked()) {
////                    personViewHolder.chk.setChecked(false);
////                    personViewHolder.chk.setCheckMarkDrawable(R.drawable.tickfalse);
////                } else {
////                    crc = new CoursesModel();
////                    personViewHolder.chk.setChecked(true);
////                    personViewHolder.chk.setCheckMarkDrawable(R.drawable.ticktrue);
////                    String clckd_crcnam = crs_DTLS.get(i).getCoursenam();
////                    crc.setClckd(clckd_crcnam);
////                    CRS_CLICKD.add(crc);
////                    Toast.makeText(context, crs_DTLS.get(i).getCrces(), Toast.LENGTH_SHORT).show();
////                }
////
////            }
////        });
//    }
//
////        personViewHolder.chkbox.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                    String s=crs_DTLS.get(i).getCoursenam();
////                    CRS_CLICKD.add(s);
////                    Toast.makeText(context,crs_DTLS.get(i).getCrces(),Toast.LENGTH_SHORT).show();
////                    personViewHolder.chkbox.setCheckMarkDrawable(R.drawable.ticktrue);
////
////                return false;
////            }
////        });
//
//
//u    //
////                return false;
////            }
////        });
//
//
//
//    // }
//
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
//    public String getstringval()
//    {return addstring;
//
//
//    }
//
//    public String getclkd() {
//
//        return clckd_crcnam;
//    }
//
//    public String getclkddes()
//    {return  crc_des ;
//
//    }
//    public int getclkdcn ()
//    {return  cnt;
//
//
//    }
//    public ArrayList<String> arrstrng()
//    {return arrstrng_crs;
//
//
//    }
//
//
//
//}
