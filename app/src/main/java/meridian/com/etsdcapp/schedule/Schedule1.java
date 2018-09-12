package meridian.com.etsdcapp.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.RegisterdCoursesAdapter;
import meridian.com.etsdcapp.model.RegisterdCoursesModel;

public class Schedule1 extends AppCompatActivity {

    ArrayList<RegisterdCoursesModel> regcrclst = new ArrayList<RegisterdCoursesModel>();

    TextView tvcal, daytop, monthtop;
    static Context context;
    Button fulvw;
    RecyclerView recycl_reglst;
    RecyclerView rv;
    RegisterdCoursesAdapter rv_adaptr_regcrc;
    String userid;
TableLayout tb;
    TableRow row;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendar_registered_courses);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topsch);
        setSupportActionBar(toolbar);
        tb = (TableLayout)findViewById(R.id.tabl_sch);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(CalendarRegisteredCoursesActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //recycl_reglst= (RecyclerView) findViewById(R.id.recy_reg_crc);
     //   LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
     //   recycl_reglst.setLayoutManager(llm);

        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);
        Bundle extras=getIntent().getExtras();

        RequestQueue queue0 = Volley.newRequestQueue(getApplicationContext());
        String url0 = "http://app.etsdc.com.php56-3.dfw3-2.websitetestlink.com/response.php?fid=18&userid="+userid;


// Request a string response from the provided URL.

        StringRequest stringRequest0 = new StringRequest
                (Request.Method.GET, url0, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ coursesd :" + response);

//
//                        JSONObject jsonObject = null;
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            System.out.println("00");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                RegisterdCoursesModel rm = new RegisterdCoursesModel();
                                System.out.println("01");
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String userid = jsonobject.getString("userid");
                                String subcourse = jsonobject.getString("subcourse");
                                String course = jsonobject.getString("course");
                                String subcourse_duration = jsonobject.getString("subcourse_duration");
                                String time = jsonobject.getString("time");

                                //String  url2 = "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/uploads/course_category/"+thumbnail;
                                rm.setUserid(userid);
                                rm.setCourse(course);

                                System.out.println("02");
                                regcrclst.add(rm);
                                String duration[] = subcourse.split("/");
                                for (int j = 0; j < duration.length; j++)
                                {
                                    row = new TableRow(getApplicationContext());
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                    row.setGravity(Gravity.CENTER);
                                    row.setLayoutParams(lp);
                                    TextView tv0 = new TextView(getApplicationContext());
                                    tv0.setText(""+duration[j]);

                                }
                                String crc_list[] = subcourse.split(",");
                                String crc_listtim[] =time.split(",");
                                for (int j = 0; j < crc_list.length; j++) {

                                        row = new TableRow(getApplicationContext());
                                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                        row.setLayoutParams(lp);
                                    TextView tv0 = new TextView(getApplicationContext());
                                    tv0.setText(" \t\t");

                                        TextView tv = new TextView(getApplicationContext());
                                        //  row.setBackgroundResource(R.drawable.crossbutton);
                                       tv.setTextSize(15);
                                       tv.setTypeface(null, Typeface.BOLD);
                                        tv.setText(crc_list[j]);
                                        TextView tv3 = new TextView(getApplicationContext());
                                        tv3.setText(" \t\t\t\t\t\t\t");
                                    TextView tv4 = new TextView(getApplicationContext());
                                    tv4.setText(" \t\t\t\t\t\t\t");
                                    TextView tv5 = new TextView(getApplicationContext());
                                    tv5.setText(" \t\t\t\t\t");
                                        TextView tv1 = new TextView(getApplicationContext());
                                        //  row.setBackgroundResource(R.drawable.crossbutton);

                                        tv1.setText(crc_listtim[j]);
                                    row.addView(tv0);
                                        row.addView(tv);
                                        row.addView(tv3);
                                    row.addView(tv4);
                                    row.addView(tv5);
                                        row.addView(tv1);


                                        tb.addView(row);

                                }


//                                    for (int k = 0; k < crc_listtim.length; k++) {
//
//
//                                        row.addView(tv1);
//                                        tb.addView(row);
//                                    }









                            }







//                            rv_adaptr_regcrc= new RegisterdCoursesAdapter(regcrclst, getApplicationContext());
//                            System.out.println("1");
//                            recycl_reglst.scheduleLayoutAnimation();
//                            recycl_reglst.setAdapter(rv_adaptr_regcrc);
//                            recycl_reglst.setHasFixedSize(true);



//                            rv.addOnItemTouchListener(
//                                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                                        @Override
//                                        public void onItemClick(View view, int position) {
////                                            view.setBackgroundResource(R.drawable.opito);
////                                            Intent in = new Intent(FulCourseList.this, SubCoursesActivity.class);
////                                            String id=   arrcrs.get(position).getCoursid();
////                                            System.out.println("ids"+id);
////                                            String nam=   arrcrs.get(position).getCoursenam();
////                                            String img_url=   arrcrs.get(position).getThumbnail();
////                                            in.putExtra("id", id);
////                                            in.putExtra("nam", nam);
////                                            in.putExtra("thmb", img_url);
////                                            startActivity(in);
//                                        }
//                                    })
//                            );

                            System.out.println("2");





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

        queue0.add(stringRequest0);

        final ImageButton showPreviousMonthBut = (ImageButton) findViewById(R.id.prev_button);
        final ImageButton showNextMonthBut = (ImageButton) findViewById(R.id.next_button);

        tvcal = (TextView) findViewById(R.id.textViewcal);

//
//        daytop = (TextView) findViewById(R.id.textViewdaytop);
//        monthtop = (TextView) findViewById(R.id.textViewmonthtop);


        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);


        //getSupportActionBar().setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));


//        Typeface face = Typeface.createFromAsset(getAssets(), "MYRIADPRO-REGULAR.OTF");
//        tvcal.setTypeface(face);

        tvcal.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        String url = "http://zulekhahospitals.com/json-cme.php?fid=100";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest
//                (Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                //  tv.setText("Response is: "+ response);
//
//                                System.out.println("++++++++++++++RESPONSE+++++++++++++++    :" + response);
//
//
//                                JSONObject jsonObj = null;
//                                // eventlist = new ArrayList<EventsModel>();
//
//
////
////
//                                try {
//                                    JSONArray jsonarray = new JSONArray(response);
//
//                                    for (int i = 0; i < jsonarray.length(); i++) {
//
//                                       ScheduleModel pm = new ScheduleModel();
//                                        JSONObject jsonobject = jsonarray.getJSONObject(i);
//                                        String id = jsonobject.getString("id");
//                                        String name = jsonobject.getString("event");
//                                        String tim = jsonobject.getString("event_type");
//
//
//                                        String year=jsonobject.getString("year");
//////
//                                        pm.setCrcnam(name);
//                                        pm.setTim(tim);
//
//
//
//                                       // eventlist.add(pm);
//
//                                        System.out.println("++++++++++" + name);
//
//                                    }
//
//
//                                    context = getApplicationContext();
//
//
////                                    rv = (RecyclerView) findViewById(R.id.rv);
////                                    rv.setHasFixedSize(true);
//                                    Context context = getApplicationContext();
//
//                                    LinearLayoutManager llm = new LinearLayoutManager(context);
                              //      rv.setLayoutManager(llm);
                                    // Change to gridLayout
                                    //  rv.setLayoutManager(new GridLayoutManager(context, 2));

//                            scroll to position
                                    //llm.scrollToPositionWithOffset(10, 0);


//                                    RVAdapterCommercial adapter = new RVAdapterCommercial(eventlist, getApplicationContext());
//                                    ///added for animation
//                                    rv.scheduleLayoutAnimation();
//
//                                    //rv.setAdapter(adapter);
//                                    rv.getItemAnimator().setAddDuration(100000);
//
//                                    //  rv.setAdapter(new AlphaInAnimationAdapter(adapter));
//                                    rv.setAdapter(adapter);
                                    // rv.setAdapter(new SlideInRightAnimationAdapter(adapter));


                                    //ADDITION ON CLICK 19th Oct

//                                    rv.addOnItemTouchListener(
//                                            new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
//                                                @Override
//                                                public void onItemClick(View view, int position) {
//
//                                                    Intent in = new Intent(CalendarRegisteredCoursesActivity.this, CalendarRegisteredCoursesActivity.class);
//
//
//                                                    String clickid=  eventlist.get(position).getId();
//                                                    String clkevntnam=  eventlist.get(position).getEvent();
//                                                    in.putExtra("pos", clickid);
//                                                    in.putExtra("name", clkevntnam);
//                                                    System.out.println("clickid1"+clickid);
//                                                    startActivity(in);
//
//                                                }
//                                            })
//                                    );


                                    compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                                        @Override
                                        public void onDayClick(Date dateClicked) {

                                          //  Toast.makeText(getApplicationContext(), "Date" + dateClicked, Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onMonthScroll(Date firstDayOfNewMonth) {
                                            //getSupportActionBar().setTitle(dateFormatForMonth.format(firstDayOfNewMonth));

                                            tvcal.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                                          //  Toast.makeText(getApplicationContext(), "Date" + Calendar.MONTH, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    compactCalendarView.drawSmallIndicatorForEvents(true);

                                    // below allows you to configure color for the current day in the month
                                    compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.Gray));
                                    // below allows you to configure colors for the current day the user has selected
                                    //compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                                 //   addEvents(compactCalendarView, -1);
                                    // addEvents(compactCalendarView, Calendar.MONTH);
                                    //  addEvents(compactCalendarView, Calendar.AUGUST);
                                    compactCalendarView.invalidate();


                                    DateFormat dateFormat = new SimpleDateFormat("dd");
                                    Calendar cal = Calendar.getInstance();
                                    System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22


//                                    daytop.setText(dateFormat.format(cal.getTime()) + "");
//                                    monthtop.setText(getMonth(Calendar.MONTH - 1));


//
//
//

//                                } catch (JSONException e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                }


//                        ArrayAdapter<DoctorModel> adapter = new MyListAdapter();
//                        lv.setAdapter(adapter);

//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // tv.setText("That didn't work!");

                //    }
//                });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);


        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showPreviousMonth();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendarView.showNextMonth();
            }
        });


    }


    private void addEvents(CompactCalendarView compactCalendarView, int month)
    {
        currentCalender.setTime(new Date());
        System.out.println("new date0" + format.format(currentCalender.getTime()));
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("new date1" + format.format(currentCalender.getTime()));
        Date firstDayOfMonth = currentCalender.getTime();

        System.out.println("new date2" + firstDayOfMonth);
//        for (ScheduleModel evmitr : eventlist) {
//            currentCalender.setTime(firstDayOfMonth);
//            System.out.println("new date3" + format.format(currentCalender.getTime()));
//            if (month > -1)
//            {
//                currentCalender.set(Calendar.MONTH, month);
//                System.out.println("new date4" +  month);
//            }

//            int dayevent = Integer.parseInt(evmitr.getDay()) - 1;
//            System.out.println("new date5" + dayevent);
//
//            int monthint = convertmontntoint(evmitr.getMonth());
//            System.out.println("new date6" +monthint);
//            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + Integer.parseInt(evmitr.getDay()) + "month=" + monthint);

            //currentCalender.add(Calendar.DATE,dayevent);

//evmitr.getYear()

//            currentCalender.add(Calendar.DATE, dayevent);
//            System.out.println("new date7" +format.format(currentCalender.getTime()));
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + Calendar.DATE);
//            currentCalender.set(Integer.parseInt(evmitr.getYear()), monthint, dayevent + 1);
            System.out.println("new date8" +format.format(currentCalender.getTime()));
            setToMidnight(currentCalender);
            System.out.println("new date9" + format.format(currentCalender.getTime()));
            compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(), Color.parseColor("#6ebcf3")), false);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + currentCalender.getTimeInMillis());
            compactCalendarView.drawSmallIndicatorForEvents(false);


        //}
    }


    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public String getMonth(int month) {


        return new DateFormatSymbols().getMonths()[month - 1];
    }


    public int convertmontntoint(String monnth) {
        int monthq = 0;

        try {
            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monnth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthq = cal.get(Calendar.MONTH);
            System.out.println(monthq);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return monthq;


    }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
