//package meridian.com.etsdcapp.schedule;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.inputmethodservice.Keyboard;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.DateFormat;
//import java.text.DateFormatSymbols;
//import java.text.Format;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import meridian.com.etsdcapp.R;
//import meridian.com.etsdcapp.adapter.RegisterdCoursesAdapter;
//import meridian.com.etsdcapp.model.DatelistModel;
//import meridian.com.etsdcapp.model.RegisterdCoursesModel;
//
//public class CalendarRegisteredCoursesActivity extends AppCompatActivity {
//
// ArrayList<RegisterdCoursesModel>  regcrclst;
// ArrayList<DatelistModel> regcrclst1=new ArrayList<DatelistModel>();
//    ArrayList<String> daylist;
//    TextView tvcal, daytop, monthtop;
//    static Context context;
//    Button fulvw;
//    RecyclerView recycl_reglst;
//    RecyclerView rv;
//    RegisterdCoursesAdapter rv_adaptr_regcrc;
//    String userid,DAY,YEAR,months,formatteddate;
//    TableLayout tb;
//    TableRow row;
//    static ArrayList<String> mylist;
//
//    SimpleDateFormat MNTH_FRMT = new SimpleDateFormat("mm");
//    SimpleDateFormat DAY_FORMT = new SimpleDateFormat("DD");
//    SimpleDateFormat YEAR_FORMT = new SimpleDateFormat("yyyy");
//    //DateFormat df1 = new SimpleDateFormat("yyyy/MM/DD");
//
//
//    public Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
//    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
//    DateFormat format = new SimpleDateFormat("DD-mm-yyyy");
//
//    String frst,secnd;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_calendar_registered_courses);
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topsch);
//        setSupportActionBar(toolbar);
//        tb = (TableLayout) findViewById(R.id.tabl_sch);
//
//        final ImageButton showPreviousMonthBut = (ImageButton) findViewById(R.id.prev_button);
//        final ImageButton showNextMonthBut = (ImageButton) findViewById(R.id.next_button);
//       // daytop = (TextView) findViewById(R.id.textViewdaytop);
//       // monthtop = (TextView) findViewById(R.id.textViewmonthtop);
//
//        tvcal = (TextView) findViewById(R.id.textViewcal);
//
//
//        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
//
//
//        tvcal.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
//        System.out.println("++" + currentCalender.getTime());
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent i = new Intent(CalendarRegisteredCoursesActivity.this, MainActivity.class);
////                startActivity(i);
////            }
////        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //recycl_reglst= (RecyclerView) findViewById(R.id.recy_reg_crc);
//        //   LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
//        //   recycl_reglst.setLayoutManager(llm);
//
//        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//        userid = preferences.getString("userid", null);
//        Bundle extras = getIntent().getExtras();
//
//        RequestQueue queue0 = Volley.newRequestQueue(getApplicationContext());
//        String url0 = "http://meridian.net.in/demo/etsdc/response.php?fid=18&userid="+userid;
//
//
//// Request a string response from the provided URL.
//
//        StringRequest stringRequest0 = new StringRequest
//                (Request.Method.GET, url0, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        //  tv.setText("Response is: "+ response);
//
//                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ coursesd :" + response);
//
//
////
//                        JSONObject jsonObject;
//                        try {
//                            JSONArray jsonarray = new JSONArray(response);
//                            daylist=new ArrayList<>();
//                            regcrclst =new ArrayList<RegisterdCoursesModel>();
//
//
//                            System.out.println("00");
//                            for (int i = 0; i < jsonarray.length(); i++)
//
//                            {
//                                RegisterdCoursesModel   rm=  new RegisterdCoursesModel();
//                                System.out.println("01");
//                                JSONObject jsonobject = jsonarray.getJSONObject(i);
//
//                                String userid = jsonobject.getString("userid");
//                                String subcourse = jsonobject.getString("subcourse");
//                                String course = jsonobject.getString("course");
//                                String subcourse_duration = jsonobject.getString("subcourse_duration");
//                                String time = jsonobject.getString("time");
//
//                                //String  url2 = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/course_category/"+thumbnail;
//                                rm.setUserid(userid);
//                                rm.setCourse(course);
////                                rm.setSubcourse(subcourse);
////                                rm.setTime(time);
////                                rm.setDuration(subcourse_duration);
//
////                                System.out.println("getduration"+rm.getDuration());
//                                String[] str = subcourse_duration.split(",");
//
//                                for (String k:str)
//                                {
//                                    int index = 0;
//                                    String[] startend=k.split("/");
//
//
//
//                                    if(startend.length>1)
//                                    {
//
//                                     List<Date> dates = getDates(startend[0], startend[1]);
//
//                                     for (Date date : dates)
//                                     {DatelistModel dm=new DatelistModel();
//                                         formatteddate = format.format(date);
//                                         System.out.println("datessbetween" + formatteddate);
//                                         months = MNTH_FRMT.format(date);
//                                         System.out.println("month" + months);
//                                         DAY = DAY_FORMT.format(date);
//                                         System.out.println("DAY" + DAY);
//                                         daylist.add(DAY);
//
//                                         YEAR = YEAR_FORMT.format(date);
//                                         System.out.println(" YEAR" + YEAR);
//
//                                         rm.setDay(DAY);
//                                         int mth = Integer.parseInt(months);
//                                         String month_name = getMonth(mth);
//                                         rm.setMonth(month_name);
//                                         rm.setYear(YEAR);
//                                         rm.setDatesbtw(formatteddate);
//                                         dm.setDay(DAY);
//                                         dm.setMonth(month_name);
//                                         dm.setYear(YEAR);
//                                         regcrclst1.add(dm);
//
//
//                                     }
//
//
//
//
//
//
//
//                                 }
//
//
//                                }
//
//                                regcrclst.add(rm);
//
//
//
//
//
//                                String crc_list[] = subcourse.split(",");
//                                String crc_listtim[] = time.split(",");
//                                for (int j=0;j<crc_list.length;j++) {
//
//                                    row = new TableRow(getApplicationContext());
//                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                                    row.setLayoutParams(lp);
//                                    TextView tv0 = new TextView(getApplicationContext());
//                                    tv0.setText(" \t\t");
//
//                                    TextView tv = new TextView(getApplicationContext());
//                                    //  row.setBackgroundResource(R.drawable.crossbutton);
//                                    tv.setTextSize(15);
//                                    tv.setTypeface(null, Typeface.BOLD);
//                                    tv.setText(crc_list[j]);
////                                    TextView tv3 = new TextView(getApplicationContext());
////                                    tv3.setText(" \t\t\t\t\t\t\t");
////                                    TextView tv4 = new TextView(getApplicationContext());
////                                    tv4.setText(" \t\t\t\t\t\t\t");
////                                    TextView tv5 = new TextView(getApplicationContext());
////                                    tv5.setText(" \t\t\t\t\t");
//                                    TextView tv1 = new TextView(getApplicationContext());
//                                      row.setBackgroundResource(R.drawable.crossbutton);
//                                   if(crc_listtim.length>1) {
//                                       tv1.setText(crc_listtim[j]);
//                                   }
//
//
//                                    row.addView(tv0);
//                                    row.addView(tv);
////                                    row.addView(tv3);
////                                    row.addView(tv4);
////                                    row.addView(tv5);
//                                    row.addView(tv1);
//
//
//                                    tb.addView(row);
//
//                                }
//
//
//                            }
//
//
////                         rv = (RecyclerView) findViewById(R.id.rv);
////                            rv.setHasFixedSize(true);
////                            Context context = getApplicationContext();
////
////                            LinearLayoutManager llm = new LinearLayoutManager(context);
////                            rv.setLayoutManager(llm);
////                            // Change to gridLayout
////                            //  rv.setLayoutManager(new GridLayoutManager(context, 2));
////
//////                            scroll to position
////                            //llm.scrollToPositionWithOffset(10, 0);
////
////                          RegisterdCoursesAdapter adapter = new RegisterdCoursesAdapter(regcrclst, getApplicationContext());
////                            ///added for animation
////                            rv.scheduleLayoutAnimation();
////
////                            //rv.setAdapter(adapter);
////                            rv.getItemAnimator().setAddDuration(100000);
////
////                            //  rv.setAdapter(new AlphaInAnimationAdapter(adapter));
////                            rv.setAdapter(adapter);
//                            // rv.setAdapter(new SlideInRightAnimationAdapter(adapter));
//
//
//                            //ADDITION ON CLICK 19th Oct
//
//
//                            compactCalendarView.drawSmallIndicatorForEvents(true);
////                            rv.addOnItemTouchListener(
////                                    new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
////                                        @Override
////                                        public void onItemClick(View view, int position) {
////
////                                            Intent in = new Intent(EventsMainActivity.this, ProfileDetailsActivity.class);
////
////
////                                            String clickid=  eventlist.get(position).getId();
////                                            String clkevntnam=  eventlist.get(position).getEvent();
////                                            in.putExtra("pos", clickid);
////                                            in.putExtra("name", clkevntnam);
////                                            System.out.println("clickid1"+clickid);
////                                            startActivity(in);
////
////                                        }
////                                    })
////                            );
//
//
//                            compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//                                @Override
//                                public void onDayClick(Date dateClicked) {
//
//                                    Toast.makeText(getApplicationContext(), "Date" + dateClicked, Toast.LENGTH_SHORT).show();
//
//                                }
//
//                                @Override
//                                public void onMonthScroll(Date firstDayOfNewMonth) {
//                                    //getSupportActionBar().setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
//
//                                    tvcal.setText(dateFormatForMonth.format(firstDayOfNewMonth));
//                                    Toast.makeText(getApplicationContext(), "Date" + Calendar.MONTH, Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            // below allows you to configure color for the current day in the month
//                            compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.White));
//                            // below allows you to configure colors for the current day the user has selected
//                            //compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//
//                            addEvents(compactCalendarView, -1);
//                      //  addEvents(compactCalendarView, Calendar.MONTH);
//                      //  addEvents(compactCalendarView, Calendar.AUGUST);
//                            compactCalendarView.invalidate();
//
//
//                            DateFormat dateFormat = new SimpleDateFormat("dd");
//                            Calendar cal = Calendar.getInstance();
//                            System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
//
////
//                      //     daytop.setText(dateFormat.format(cal.getTime()) + "");
//                            DateFormat dateFormat1 = new SimpleDateFormat("MMM");
//                        //    monthtop.setText(dateFormatForMonth.format(cal.getTime()));
//
//
////
////
////
//
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//
////                        ArrayAdapter<DoctorModel> adapter = new MyListAdapter();
////                        lv.setAdapter(adapter);
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // tv.setText("That didn't work!");
//
//                    }
//                });
//// Add the request to the RequestQueue.
//
//        queue0.add(stringRequest0);
//
//
//        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compactCalendarView.showPreviousMonth();
//            }
//        });
//
//        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compactCalendarView.showNextMonth();
//            }
//        });
//
//
//    }
//
//
//
//
//    private void addEvents(CompactCalendarView compactCalendarView, int month)
//    {
////          regcrclst1=new ArrayList<>();
////        for(int i=0;i<regcrclst1.size();i++)
////        {
////            System.out.println("getsubcoursed"+regcrclst1.get(i).getDuration());
////        }
//
//        ///currentCalender.setTime(new Date());
//        DateFormat format1 = new SimpleDateFormat("dd-mm-yyyy");
//        System.out.println("new date0" + format1.format(currentCalender.getTime()));
//        Date firstDayOfMonth = currentCalender.getTime();
//
//        for (DatelistModel datelistModel:regcrclst1)
//        {
//            if ( datelistModel.getDay() != null) {
//              //  for (String s : daylist) {
//                System.out.println("getdayyy" +datelistModel.getDay());
//
//                currentCalender.setTime(firstDayOfMonth);
//                System.out.println("new date3" + format1.format(currentCalender.getTime()));
//                if (month > -1) {
//                    currentCalender.set(Calendar.MONTH, month);
//                    System.out.println("new date4" + month);
//                }
//
//                    int dayevent = Integer.parseInt(datelistModel.getDay())-1;
//                    System.out.println("new date5" + datelistModel.getDay());
//
//                    int monthint = convertmontntoint(datelistModel.getMonth());
//                    System.out.println("new date6" + monthint);
//                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + Integer.parseInt(datelistModel.getDay()) + "month=" + monthint);
//
//                    currentCalender.add(Calendar.DATE, dayevent);
//
//                    System.out.println("new " +
//                            "date7" + format1.format(currentCalender.getTime()));
//                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + Calendar.DATE);
//                    currentCalender.set(Integer.parseInt(datelistModel.getYear()), monthint, dayevent + 1);
//                    System.out.println("new date8" + format1.format(currentCalender.getTime()));
//                    setToMidnight(currentCalender);
//                    System.out.println("new date9" + format1.format(currentCalender.getTime()));
//                    compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(), Color.parseColor("#f89f1b")), false);
//                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + currentCalender.getTimeInMillis());
//                    compactCalendarView.drawSmallIndicatorForEvents(false);
//
//
//                }
//            }
//        }
//   // }
//
//
//
//
//
//
//
//    private void setToMidnight(Calendar calendar) {
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//    }
//
//    public String getMonth(int month) {
//
//
//        return new DateFormatSymbols().getMonths()[month - 1];
//    }
//
//
//    public int convertmontntoint(String month) {
//        int monthq = 0;
//
//        try {
//            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//            monthq = cal.get(Calendar.MONTH);
//            System.out.println(monthq);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return monthq;
//
//
//    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        if (id == android.R.id.home) {
//
//
//            super.onBackPressed();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//   public static List<Date> getDates(String dateString1, String dateString2)
//    {
//        ArrayList<Date> dates = new ArrayList<Date>();
//        DateFormat df1 = new SimpleDateFormat("DD-mm-yyyy");
//
//        Date date1 = null;
//        Date date2 = null;
//
//        try {
//            date1 = df1.parse(dateString1);
//            date2 = df1 .parse(dateString2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(date1);
//
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(date2);
//
//        while(!cal1.after(cal2))
//        {
//            dates.add(cal1.getTime());
//            cal1.add(Calendar.DATE, 1);
//        }
//        return dates;
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}
