package meridian.com.etsdcapp.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.course.CourseRegistrationActivity;
import meridian.com.etsdcapp.login.LoginActivity;
import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.adapter.AbsDateAdapter;
import pl.rspective.pagerdatepicker.adapter.AbsDateItemHolder;
import pl.rspective.pagerdatepicker.adapter.DatePagerFragmentAdapter;
import pl.rspective.pagerdatepicker.model.DateItem;

import pl.rspective.pagerdatepicker.view.DateRecyclerView;

public class CalendarWeekViewFragment extends Fragment {

    private DateRecyclerView dateList,dateListnw;
    private ViewPager pager;
    public   TextView tvMonth,textmnth;
    TextView mnth;
    ImageView img_crc_dtl;
    public static   String schwek;
    public  static String k;
    Button regsch;
    String userid;
    public static    String  selectedDateFRMT;
    public static    String formattedDate;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MMMM");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_day = new SimpleDateFormat("EEEE");

    public static CalendarWeekViewFragment newInstance() {
        return new CalendarWeekViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        WindowManager.LayoutParams params = getFragmentManager().getAttributes();
//        params.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        View v=inflater.inflate(R.layout.fragment_calendar_week_view, container, false);
        textmnth = (TextView) v.findViewById(R.id.textmnth);
        final Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar_tops_calendr);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.color.White);

        SharedPreferences preferences1 = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        userid = preferences1.getString("userid", null);
        System.out.println("useridd"+userid);


//        img_crc_dtl= (ImageView) v.findViewById(R.id.img_crcdtlnam);
//        img_crc_dtl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
//        toolbar.setNavigationIcon(R.color.White);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//           getActivity().onBackPressed();
//                return false;
//            }
//        });
        return  v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Date date;

        // mnth= (TextView)view.findViewById(R.id.mnth);
        //    final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_top);
        // setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainCoursesActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pager = (ViewPager) view.findViewById(R.id.pager);

        dateList = (DateRecyclerView) view.findViewById(R.id.date_list);
        // dateListnw = (DateRecyclerView) view.findViewById(R.id.date_listnw);
        regsch= (Button) view.findViewById(R.id.regsch);




        //dateList.addItemDecoration(new RecyclerViewInsetDecoration(getActivity(), R.dimen.date_card_insets));

        Date start = null;
        Date end = null;
        Date defaultDate = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("01-05-2016");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2020");
            Calendar c = Calendar.getInstance();
            String p=c.getTime().toString();
            System.out.println("datetody"+p);
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            String formattedDate1 = df.format(c.getTime());

            formattedDate = SIMPLE_DATE_FORMAT_day.format(c.getTime());
            String month = SIMPLE_DATE_FORMAT.format(c.getTime());
            textmnth.setText(month);
            defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse(formattedDate1);
            System.out.println("formattedDate1"+  formattedDate1 );
            System.out.println("defaultDate"+  defaultDate );
            System.out.println("formattedDate"+  formattedDate );

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CustomDateAdapter dateAdapter = new CustomDateAdapter(start, end, defaultDate);
        dateList.setAdapter(dateAdapter);
        //   CustomDateAdapter1 dateAdapter1 = new CustomDateAdapter1(start, end, defaultDate);
        //   dateListnw.setAdapter(dateAdapter1);

        System.out.println("mnthpas"+k);
        final   DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date)
            {

                schwek=SIMPLE_DATE_FORMAT_day.format(date);
                System.out.println("weekss"+schwek);

                return WeekCourseListFragment.newInstance(position, date);

            }
        };



        dateList.setPager(pager);
        pager.setAdapter(fragmentAdapter);
        pager.beginFakeDrag();

        regsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userid!=null)
                {
                    Intent i=new Intent(getActivity(),CalendarRegisteredCoursesActivity1.class);
                    startActivity(i);

                } else
                {

                    final SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alert!")
                            .setContentText("Please Login to View Registerd Courses")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    Intent i=new Intent(getActivity(), LoginActivity.class);
                                    startActivity(i);
                                    dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
                                }
                            })
                            .show();


                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));



                   /* android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please Login to View Registered Courses");
                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent i=new Intent(getActivity(), LoginActivity.class);
                                    startActivity(i);
                                    dialog.dismiss();
//                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
//                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));

                                }
                            });
                    alertDialog.show();
                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                    nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/

                }



            }
        });


        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {
                selectedDateFRMT=SIMPLE_DATE_FORMAT_day.format(dateItem.getDate());

                String k= SIMPLE_DATE_FORMAT.format(dateItem.getDate());
                textmnth.setText(k);
                System.out.println("selectedDateONCE"+ selectedDateFRMT);
                // String  selectedDateFRMT=SIMPLE_DATE_FORMAT_day.format(dateItem);
                System.out.println("selectedDate0"+dateItem.getDate());
                pager.setAdapter(fragmentAdapter);

                System.out.println("selectedDate0"+ position);


            }

            @Override
            public void onDatePickerPageSelected(int position)
            {

            }

            @Override
            public void onDatePickerPageStateChanged(int state)
            {

            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {



            }
        });

        // Set animation for current selected view
        dateAdapter.setCurrentViewAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation));
    }
    public static String s()
    {
        System.out.println("passs_s"+selectedDateFRMT);
        return selectedDateFRMT;


    }
    public static String p()
    {
        System.out.println("passs_p"+formattedDate);
        return   formattedDate;

    }

    public class CustomDateAdapter extends AbsDateAdapter<CustomDateAdapter.CustomDateViewHolder> {

        public CustomDateAdapter(Date start, Date end)
        {
            this(start, end, null);
        }

        public CustomDateAdapter(Date start, Date end, Date defaultSelectedDate) {
            super(start, end, defaultSelectedDate);
        }

        @Override
        protected void onDateItemHolderClick(CustomDateViewHolder itemHolder)
        {
            if (onDateItemListener != null) {
                onDateItemListener.onDateItemClick(getItem(itemHolder.getPosition()), itemHolder.getPosition());
                System.out.println("POSITION"+ itemHolder.getPosition());
            }

            if (selectedDate != -1 && selectedDateView != null) {
                selectedDateView.changeDateIndicatorColor(false);
                selectedDateView.changeTextColor(false);

//           selectedDateFRMT=SIMPLE_DATE_FORMAT_day.format(selectedDate);
//                System.out.println("selectedDateONCE"+ selectedDateFRMT);
            }

            selectedDateView = itemHolder;
            selectedDate = dateItems.get(itemHolder.getPosition()).getDate().getTime();

            System.out.println("selectedDate1"+ selectedDate);
            selectedDateView.changeDateIndicatorColor(true);
            selectedDateView.changeTextColor(true);
        }

        @Override
        public CustomDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View root = inflater.inflate(R.layout.item_view_custom_date, parent, false);
            return new CustomDateViewHolder(root, this);
        }


        @Override
        public void onBindViewHolder(CustomDateViewHolder dateItemHolder, int position)
        {
            DateItem dateItem = dateItems.get(position);

            dateItemHolder.setDay(dateItem.getDate());
            dateItemHolder.setMonthName(dateItem.getDate());
            dateItemHolder.setDayName(dateItem.getDate());
            //dateItemHolder.setMnthtop();
            //  dateItemHolder.setMnth(dateItem.getDate());
            dateItemHolder.itemView.setSelected(true);

            if (isDateSelected(dateItem)) {
                dateItemHolder.updateDateItemView(true);
                selectedDateView = dateItemHolder;
            } else {
                dateItemHolder.updateDateItemView(false);
            }
        }

        public  class CustomDateViewHolder extends AbsDateItemHolder {

            TextView tvDay;
            String dateselsched;
            TextView mnth,mnthtop;
            TextView tvDayName;
            RelativeLayout viewDateIndicator;
            Button butsch;
            Resources resources;

            public CustomDateViewHolder(View itemView, AbsDateAdapter dateAdapter) {
                super(itemView, dateAdapter);

                this.resources = itemView.getResources();

                tvDay = (TextView) itemView.findViewById(R.id.tv_date_picker_day);
               mnth = (TextView) itemView.findViewById(R.id.mnths);
                //  mnthtop = (TextView) itemView.findViewById(R.id.tvmnthtop);
                // mnthtop.setText(k);

                tvMonth = (TextView) itemView.findViewById(R.id.tv_date_picker_month_name);

                tvDayName = (TextView) itemView.findViewById(R.id.tv_date_picker_day_name);

                viewDateIndicator = (RelativeLayout)itemView.findViewById(R.id.view_date_indicator);

            }

            @Override
            public void changeTextColor(boolean isSelected) {
                if (isSelected) {
                    tvDay.setTextColor(resources.getColor(R.color.butreg));
                    tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    tvDay.setBackgroundColor(resources.getColor(R.color.White));
                    tvDayName.setTextColor(resources.getColor(R.color.date_custom_item_day_name));
                    tvDayName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                    //  tvMonth.setTextColor(resources.getColor(R.color.date_custom_item_month_name));
                } else {
                    tvDay.setTextColor(resources.getColor(R.color.White));
                    tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    tvDayName.setTextColor(resources.getColor(R.color.White));
                    tvDayName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    // tvMonth.setTextColor(resources.getColor(R.color.White));
                }
            }

//           public void setMnthtop() {
//               tvmnthtp.setText(k);
//           }

            @Override
            public void setDay(Date date) {
                tvDay.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_FORMAT.format(date));

            }


            @Override
            public void setMonthName(Date date) {
                tvMonth.setVisibility(View.INVISIBLE);
                tvMonth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
                //k= tvMonth.getText().toString();
                //    mnth.setText(k);
                //
                // System.out.println("month"+k);
            }

            //           public  void setMnth(Date date)
//           {
//               mnth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
//           }
            @Override
            public void setDayName(Date date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                tvDayName.setText(weekdayNameFormat.format(date).toUpperCase());
            }

            @Override
            public void changeDateIndicatorColor(boolean isSelected) {
                if (isSelected) {
                    // viewDateIndicator.setBackgroundResource(R.color.White);
                } else {
                    //  viewDateIndicator.setBackgroundResource(R.color.butreg);
                }
            }

            @Override
            protected View getCurrentViewToAnimate() {
                return tvDay;
            }
        }

    }
//    public class CustomDateAdapter1 extends AbsDateAdapter<CustomDateAdapter1.CustomDateViewHolder1> {
//
//        public CustomDateAdapter1(Date start, Date end) {
//            this(start, end, null);
//        }
//
//        public CustomDateAdapter1(Date start, Date end, Date defaultSelectedDate) {
//            super(start, end, defaultSelectedDate);
//        }

//        @Override
//        protected void onDateItemHolderClick(CustomDateViewHolder1 itemHolder) {
//            if (onDateItemListener != null) {
//              //  onDateItemListener.onDateItemClick(getItem(itemHolder.getPosition()), itemHolder.getPosition());
//            }
//
//            if (selectedDate != -1 && selectedDateView != null) {
//                selectedDateView.changeDateIndicatorColor(false);
//                selectedDateView.changeTextColor(false);
//            }
//
//            selectedDateView = itemHolder;
//            selectedDate = dateItems.get(itemHolder.getPosition()).getDate().getTime();
//
//            selectedDateView.changeDateIndicatorColor(true);
//            selectedDateView.changeTextColor(true);
//        }
//
//        @Override
//        public CustomDateViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//            View root = inflater.inflate(R.layout.item_view_custom_date1, parent, false);
//
//            return new CustomDateViewHolder1(root, this);
//        }
//
//
//        @Override
//        public void onBindViewHolder(CustomDateViewHolder1 dateItemHolder, int position) {
//            DateItem dateItem = dateItems.get(position);
//
//           dateItemHolder.setDay(dateItem.getDate());
//
//            dateItemHolder.setMonthName(dateItem.getDate());
//           dateItemHolder.setDayName(dateItem.getDate());
//         // dateItemHolder.setMnth(dateItem.getDate());
//            dateItemHolder.itemView.setSelected(true);
//
//            if (isDateSelected(dateItem)) {
//                dateItemHolder.updateDateItemView(true);
//                selectedDateView = dateItemHolder;
//            } else {
//                dateItemHolder.updateDateItemView(false);
//            }
//        }
//
//        public  class CustomDateViewHolder1 extends AbsDateItemHolder {
//
//            TextView tvDay;
//
//            TextView mnth;
//            TextView tvDayName;
//            LinearLayout viewDateIndicator;
//
//            Resources resources;
//
//            public CustomDateViewHolder1(View itemView, AbsDateAdapter dateAdapter) {
//                super(itemView, dateAdapter);
//
//                this.resources = itemView.getResources();
//
//               tvDay = (TextView) itemView.findViewById(R.id.tv_date_picker_day1);
//          //mnth = (TextView) itemView.findViewById(R.id.mnths);
//
//                tvMonth = (TextView) itemView.findViewById(R.id.tv_date_picker_month_name1);
//
//                tvDayName = (TextView) itemView.findViewById(R.id.tv_date_picker_day_name1);
//
//                viewDateIndicator = (LinearLayout)itemView.findViewById(R.id.view_date_indicator2);
//
//            }
//
//            @Override
//            public void changeTextColor(boolean isSelected) {
//                if (isSelected) {
////                    tvDay.setTextColor(resources.getColor(R.color.butreg));
////                    tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
////                    tvDayName.setTextColor(resources.getColor(R.color.date_custom_item_day_name));
////                    tvDayName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//
//                  tvMonth.setTextColor(resources.getColor(R.color.date_custom_item_month_name));
//                } else {
////                    tvDay.setTextColor(resources.getColor(R.color.White));
////                    tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
////                    tvDayName.setTextColor(resources.getColor(R.color.White));
////                    tvDayName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//                    // tvMonth.setTextColor(resources.getColor(R.color.White));
//                }
//            }
//
//
//            @Override
//            public void setDay(Date date) {
//                tvDay.setText(PagerDatePickerDateFormat.DATE_PICKER_DAY_FORMAT.format(date));
//            }
//
//            @Override
//            public void setMonthName(Date date) {
//               // tvMonth.setVisibility(View.INVISIBLE);
//                tvMonth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
//                // k= tvMonth.getText().toString();
//                //    mnth.setText(k);
//                //
//                // System.out.println("month"+k);
//            }
//
////            public  void setMnth(Date date)
////            {
////                mnth.setText(PagerDatePickerDateFormat.DATE_PICKER_MONTH_NAME_FORMAT.format(date));
////            }
//            @Override
//            public void setDayName(Date date) {
//                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
//                tvDayName.setText(weekdayNameFormat.format(date).toUpperCase());
//            }
//
//            @Override
//            public void changeDateIndicatorColor(boolean isSelected) {
//                if (isSelected) {
//                    // viewDateIndicator.setBackgroundResource(R.color.White);
//                } else {
//                    //  viewDateIndicator.setBackgroundResource(R.color.butreg);
//                }
//            }
//
//            @Override
//            protected View getCurrentViewToAnimate() {
//                return tvDay;
//            }
//        }

    //  }


    @Override
    public void onResume() {
        super.onResume();
    }
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
//            getActivity().onBackPressed();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}

