package meridian.com.etsdcapp.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.CalendarDayEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.leolin.shortcutbadger.ShortcutBadger;
import meridian.com.etsdcapp.InterfaceNew;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.RegisterCourseAdapterList;
import meridian.com.etsdcapp.adapter.RegisterCourseAdapterList1;
import meridian.com.etsdcapp.adapter.RegisterdCoursesAdapter;
import meridian.com.etsdcapp.course.CourseEnquireActivity;
import meridian.com.etsdcapp.course.MainCoursesActivity;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.model.DatelistModel;
import meridian.com.etsdcapp.model.RegisterdCoursesModel;

public class CalendarRegisteredCoursesActivity1 extends AppCompatActivity implements InterfaceNew {

    ArrayList<RegisterdCoursesModel> regcrclst;
    ArrayList<RegisterdCoursesModel> regcrclst2;
    static ArrayList<DatelistModel> regcrclst1;
    ArrayList<String> daylist;
    TextView tvcal, daytop, monthtop;
    static Context context;
    Button fulvw;
    RecyclerView recycl_reglst;
    RecyclerView rv;
    RegisterdCoursesAdapter rv_adaptr_regcrc;
    RegisterCourseAdapterList1 registerCourseAdapterList;
    String userid, DAY, YEAR, months, formatteddate;
    TableLayout tb;
    TableRow row;
    ListView lv;
    int counts;
    String subcourse, subcourse_duration, time;
    static String[] crc_list, crc_listtim, crc_duration;
    static ArrayList<String> mylist;
    ProgressBar progress;

    SimpleDateFormat MNTH_FRMT = new SimpleDateFormat("mm");
    SimpleDateFormat DAY_FORMT = new SimpleDateFormat("DD");
    SimpleDateFormat YEAR_FORMT = new SimpleDateFormat("yyyy");
    //DateFormat df1 = new SimpleDateFormat("yyyy/MM/DD");
    RegisterdCoursesModel rm;

    public  Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    DateFormat format = new SimpleDateFormat("DD-mm-yyyy");

    String frst, secnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendar_registered_courses1);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topsch);
        setSupportActionBar(toolbar);
        tb = (TableLayout) findViewById(R.id.tabl_sch);
        SharedPreferences sharedPreferencesns1 = getSharedPreferences("notification", MODE_PRIVATE);
        counts = sharedPreferencesns1.getInt("notifcnt", 0);
        if (counts > 0) {
            ShortcutBadger.removeCount(context);
            SharedPreferences preferences = getSharedPreferences("notification", MODE_PRIVATE);
            preferences.edit().remove("notifcnt").commit();

        }

        final ImageButton showPreviousMonthBut = (ImageButton) findViewById(R.id.prev_button);
        final ImageButton showNextMonthBut = (ImageButton) findViewById(R.id.next_button);
        // daytop = (TextView) findViewById(R.id.textViewdaytop);
        // monthtop = (TextView) findViewById(R.id.textViewmonthtop);

        tvcal = (TextView) findViewById(R.id.textViewcal);


        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);



        tvcal.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        System.out.println("++" + currentCalender.getTime());
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

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);
        Bundle extras = getIntent().getExtras();
        lv = (ListView) findViewById(R.id.listViewreg);
//        rv = (RecyclerView) findViewById(R.id.rv);
//
//        Context context = getApplicationContext();
//
//        LinearLayoutManager llm = new LinearLayoutManager(context);
//        rv.setLayoutManager(llm);

        RequestQueue queue0 = Volley.newRequestQueue(getApplicationContext());
        String url0 = "http://www.app.etsdc.com/response.php?fid=18&userid=" + userid;


// Request a string response from the provided URL.

        StringRequest stringRequest0 = new StringRequest
                (Request.Method.GET, url0, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);


                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ coursesd :" + response);
                        try {
                            if (response != null) {
                                if (response.equals("\"failed\"")) {

                                    final SweetAlertDialog dialog = new SweetAlertDialog(CalendarRegisteredCoursesActivity1.this,SweetAlertDialog.NORMAL_TYPE);
                                    dialog.setTitleText("Alert!")
                                            .setContentText("No registerd course please register")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
                                                    Intent i = new Intent(getApplicationContext(), MainCoursesActivity.class);
                                                    startActivity(i);
                                                }
                                            })
                                            .show();


                                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                                  /*      android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CalendarRegisteredCoursesActivity1.this).create();
                                        alertDialog.setTitle("Alert");
                                        alertDialog.setMessage("No registered courses please register");
                                        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
                                                        Intent i = new Intent(getApplicationContext(), MainCoursesActivity.class);
                                                        startActivity(i);
                                                    }
                                                });
                                        alertDialog.show();
                                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
                                } else {
//
                                    JSONObject jsonObject;
                                    JSONArray jsonarray = new JSONArray(response);
                                    daylist = new ArrayList<>();
                                    regcrclst = new ArrayList<RegisterdCoursesModel>();
                                    regcrclst2 = new ArrayList<RegisterdCoursesModel>();


                                    System.out.println("00");
                                    for (int i = 0; i < jsonarray.length(); i++)

                                    {
                                        rm = new RegisterdCoursesModel();
                                        System.out.println("01");
                                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                                        // String userid = jsonobject.getString("userid");
                                        subcourse = jsonobject.getString("subcourse");
                                        String course = jsonobject.getString("course");
                                        subcourse_duration = jsonobject.getString("subcourse_duration");
                                        time = jsonobject.getString("time");

                                        rm.setUserid(userid);
                                        rm.setCourse(course);
                                        rm.setSubcourseduration(subcourse_duration);
                                        rm.setSubcourse(subcourse);
                                        rm.setTime(time);

                                        regcrclst.add(rm);
                                    }

                                }
                            }

                        }
                    catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

try {


    for (int j = 0; j < regcrclst.size(); j++) {

        crc_list = regcrclst.get(j).getSubcourse().split(",");
        crc_listtim = regcrclst.get(j).getTime().split(",");
        crc_duration = regcrclst.get(j).getSubcourseduration().split(",");
        for (int k = 0; k < crc_list.length; k++) {
            rm = new RegisterdCoursesModel();
            rm.setSubcourse(crc_list[k]);
            System.out.println("regcourse" + crc_list[k]);
            System.out.println("regcours.length" + crc_list.length);

            System.out.println("regcourse.k" + k);
            if (crc_listtim.length <= crc_list.length) {
                rm.setTime(crc_listtim[k]);
                System.out.println("regcoursetim.length" + crc_listtim.length);
                System.out.println("regcoursetim.k" + k);

            }


            if (crc_duration.length <= crc_list.length) {
                rm.setSubcourseduration(crc_duration[k]);
                System.out.println("regduration" + crc_duration[k]);
                System.out.println("regcourseduration.length" + crc_duration.length);
                System.out.println("regcourseduration.k" + k);
            }


            regcrclst2.add(rm);
        }
    }



                                // Code goes here.
                                registerCourseAdapterList = new RegisterCourseAdapterList1(regcrclst2, CalendarRegisteredCoursesActivity1.this);

                                lv.setAdapter(registerCourseAdapterList);


                                System.out.println("scheddd");


                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                        view.setBackgroundResource(R.drawable.schbak);

                                        registerCourseAdapterList.changeIndex(position);


                                        String startend[] = regcrclst2.get(position).getSubcourseduration().split("/");
                                        String colr = regcrclst2.get(position).getColr();

                                        if (startend.length > 1) {

                                            List<Date> dates = getDates(startend[0], startend[1]);
                                            System.out.println("startend" + startend[0] + startend[1]);

                                            regcrclst1= new ArrayList<DatelistModel>();
                                            for (Date date : dates) {
                                                DatelistModel dm = new DatelistModel();
                                                formatteddate = format.format(date);
                                                System.out.println("datessbetween" + formatteddate);
                                                months = MNTH_FRMT.format(date);
                                                System.out.println("month" + months);
                                                DAY = DAY_FORMT.format(date);
                                                System.out.println("DAY" + DAY);
                                                daylist.add(DAY);

                                                YEAR = YEAR_FORMT.format(date);
                                                System.out.println(" YEAR" + YEAR);

//                                                        rm.setDay(DAY);
                                                int mth = Integer.parseInt(months);
                                                String month_name = getMonth(mth);
//                                                        rm.setMonth(month_name);
//                                                        rm.setYear(YEAR);
//                                                        rm.setDatesbtw(formatteddate);
                                                dm.setColor(colr);
                                                dm.setDay(DAY);
                                                dm.setMonth(month_name);
                                                dm.setYear(YEAR);
                                                regcrclst1.add(dm);

                                                //  Toast.makeText(getApplicationContext(),"POSITION"+position,Toast.LENGTH_SHORT).show();


                                            }
                                            addEvents(compactCalendarView,-1);


                                        }


                                    }
                                });

                }
                catch (NullPointerException e)
                {

                }


//                        ArrayAdapter<DoctorModel> adapter = new MyListAdapter();
//                        lv.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // tv.setText("That didn't work!");

                    }
                });
// Add the request to the RequestQueue.

        queue0.add(stringRequest0);
        queue0.getCache().clear();
        compactCalendarView.drawSmallIndicatorForEvents(true);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                //  Toast.makeText(getApplicationContext(), "Date" + dateClicked, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                //getSupportActionBar().setTitle(dateFormatForMonth.format(firstDayOfNewMonth));

                tvcal.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                // Toast.makeText(getApplicationContext(), "Date" + Calendar.MONTH, Toast.LENGTH_SHORT).show();
            }
        });
        // below allows you to configure color for the current day in the month
        //    compactCalendarView.setCurrentDayBackgroundColor(getResources().getColor(R.color.DarkBlue));
        // below allows you to configure colors for the current day the user has selected
        //compactCalendarView.setCurrentSelectedDayBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));


        //  addEvents(compactCalendarView, Calendar.MONTH);
        //  addEvents(compactCalendarView, Calendar.AUGUST);



        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22

//
        //     daytop.setText(dateFormat.format(cal.getTime()) + "");
        DateFormat dateFormat1 = new SimpleDateFormat("MMM");
        //    monthtop.setText(dateFormatForMonth.format(cal.getTime()));


//
//
//


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


    private static void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public String getMonth(int month) {


        return new DateFormatSymbols().getMonths()[month];
    }


    public static int convertmontntoint(String month) {
        int monthq = 0;

        try {
            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
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

    public static List<Date> getDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        System.out.println("function datesss");
        return dates;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);




    }

    @Override
    protected void onResume() {
        super.onResume();
//        Intent i = getIntent();
//        finish();
//        startActivity(i);
    }


    @Override
    public void addEvents(CompactCalendarView compactCalendarView, int month) {

        DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
     //   System.out.println("new date0" + format1.format(currentCalender.getTime()));
        compactCalendarView.removeAllEvents();
        Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
        Date firstDayOfMonth = currentCalender.getTime();
        compactCalendarView.removeAllEvents();
        for (DatelistModel datelistModel : regcrclst1) {
            if (datelistModel.getDay() != null) {
                //  for (String s : daylist) {
               // System.out.println("getdayyy" + datelistModel.getDay());
                currentCalender.setTime(firstDayOfMonth);
             //   System.out.println("new date3" + format1.format(currentCalender.getTime()));
                if (month > -1) {
                    currentCalender.set(Calendar.MONTH, month);
                    System.out.println("new date4" + month);
                }
                int dayevent = Integer.parseInt(datelistModel.getDay()) - 1;
                //System.out.println("new date5" + datelistModel.getDay());
                int monthint = convertmontntoint(datelistModel.getMonth());
              //  System.out.println("new date6" + monthint);
             //   System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + Integer.parseInt(datelistModel.getDay()) + "month=" + monthint);
                currentCalender.add(Calendar.DATE, dayevent);
                String colr = datelistModel.getColor();
//                System.out.println("new " +
//                        "date7" + format1.format(currentCalender.getTime()));
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + Calendar.DATE);
                currentCalender.set(Integer.parseInt(datelistModel.getYear()), monthint, dayevent + 1);
//                System.out.println("new date8" + format1.format(currentCalender.getTime()));

            //    System.out.println("new date9" + format1.format(currentCalender.getTime()));

                compactCalendarView.addEvent(new CalendarDayEvent(currentCalender.getTimeInMillis(), Color.parseColor(colr)), true);
              //  System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + currentCalender.getTimeInMillis());
                compactCalendarView.drawSmallIndicatorForEvents(false);


            }


        }
        // }

    }
}
