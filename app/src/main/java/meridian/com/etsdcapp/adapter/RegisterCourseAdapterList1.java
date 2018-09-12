package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.RegisterdCoursesModel;

/**
 * Created by user 1 on 15-06-2016.
 */
public class RegisterCourseAdapterList1 extends BaseAdapter{



    List<RegisterdCoursesModel> crslist;
    Context context;
   public String[] colr=new String[]{"#cad5e3","#6ca1ba","#87d288","#E799A3","#E9967A","#B8860B","#2789e4","#C25283"};
    public String[] txtcolr=new String[]{"#ffffff","#f8b552"};
    String formatteddate,months,DAY,YEAR;
   int j=0;
    SimpleDateFormat MNTH_FRMT = new SimpleDateFormat("mm");
    SimpleDateFormat DAY_FORMT = new SimpleDateFormat("DD");
    SimpleDateFormat YEAR_FORMT = new SimpleDateFormat("yyyy");
    //DateFormat df1 = new SimpleDateFormat("yyyy/MM/DD");
    public int row_index=0;

    public Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    DateFormat format = new SimpleDateFormat("DD-mm-yyyy");


    public static String k;
    private static LayoutInflater inflater;

    public RegisterCourseAdapterList1(List<RegisterdCoursesModel> crslist, Context context) {
        // TODO Auto-generated constructor stub
        this.crslist = crslist;
        this.context = context;

        System.out.println("adapter"+context);
      inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        return crslist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public static class Holder
    {
       static TableLayout tb;


        TextView txt_crc,txt_tim,txt_dur;


    }
    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        RegisterdCoursesModel rm = new RegisterdCoursesModel();
        View rowView;
        System.out.println("adapter");
        rowView = inflater.inflate(R.layout.inflate_registered_courses1, null);
        holder.tb = (TableLayout) rowView.findViewById(R.id.tbreg);
//        holder.txt_crc = (TextView)rowView.findViewById(R.id.txt_course);
//        holder.txt_tim = (TextView) rowView.findViewById(R.id.txt_tim);
//        holder.txt_dur = (TextView)rowView.findViewById(R.id.duration);


        String crcduration = crslist.get(i).getSubcourseduration();
      String[] dates= crcduration.split("/");
        String start_date=dates[0];
        String end_date=dates[1];
        if(row_index==i){
            holder.tb.setBackgroundResource(R.drawable.schbak);


        }
        else
        {


        }

        System.out.println("duration..." +start_date+"....."+end_date);
        String courselst_tim = crslist.get(i).getTime();
        TableRow row, row0, row1, rowfrst,row_end;
        rowfrst = new TableRow(context);
        TableRow.LayoutParams lpf = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        rowfrst.setLayoutParams(lpf);

        TextView tv0 = new TextView(context);
        tv0.setText("\u2022");
        tv0.setTextSize(30);

        if (i < colr.length)
        {   crslist.get(i).setColr(colr[i]);
            tv0.setTextColor(Color.parseColor(colr[i]));
            tv0.setGravity(Gravity.LEFT);
            tv0.setPadding(20, 0, 0, 0);

        }
        else
        {
            crslist.get(i).setColr(txtcolr[1]);
            tv0.setTextColor(Color.parseColor(colr[1]));
            tv0.setGravity(Gravity.LEFT);
            tv0.setPadding(20, 0, 0, 0);

        }



        rowfrst.addView(tv0);

        TextView tv = new TextView(context);
        tv.setTextColor(Color.parseColor(txtcolr[0]));
        String subcourse=crslist.get(i).getSubcourse();
//        if( subcourse.length()>30) {
//            final int mid = subcourse.length() / 2; //get the middle of the String
//            String[] parts = {subcourse.substring(0, mid), subcourse.substring(mid)};
//            System.out.println(parts[0]); //first part
//            System.out.println(parts[1]);
//
//            tv.setText(parts[0] + "\n" + parts[1]);
//        }
//        else
     //   {
            tv.setText(subcourse);

      //  }

        tv.setGravity(Gravity.LEFT);
        tv.setPadding(10,0,10,0);


        TextView tv1 = new TextView(context);

        tv1.setText(courselst_tim);
        tv1.setVisibility(View.GONE);
        tv1.setPadding(10,0,0,0);

        tv1.setTextColor(Color.parseColor(txtcolr[0]));
        tv1.setGravity(Gravity.LEFT);

        rowfrst.addView(tv);

        row0 = new TableRow(context);
        TableRow.LayoutParams lp0 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row0.setLayoutParams(lp0);
        row0.setGravity(Gravity.START);

        TextView tvk = new TextView(context);
        tvk.setText("Start Date");
        tvk.setGravity(Gravity.START);
        tvk.setTextColor(Color.parseColor(txtcolr[0]));
        tvk.setPadding(10,0,20,0);
        TextView tvk1 = new TextView(context);
        tvk1.setText(start_date);
        tvk1.setGravity(Gravity.START);
        tvk1.setTextColor(Color.parseColor(txtcolr[0]));
        tvk1.setPadding(10,0,20,0);
        row0.addView(tvk);
        row0.addView(tvk1);



        row_end = new TableRow(context);
        TableRow.LayoutParams lp_end = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row_end.setLayoutParams(lp_end);
        row_end.setGravity(Gravity.START);

        TextView tvk_end = new TextView(context);
        tvk_end.setText("End Date");
        tvk_end.setGravity(Gravity.START);
        tvk_end.setTextColor(Color.parseColor(txtcolr[0]));
        tvk_end.setPadding(10,0,20,0);
        TextView tvk1_end = new TextView(context);
        tvk1_end.setText(end_date);
        tvk1_end.setGravity(Gravity.START);
        tvk1_end.setTextColor(Color.parseColor(txtcolr[0]));
        tvk1_end.setPadding(10,0,20,0);
        row_end.addView(tvk_end);
        row_end.addView(tvk1_end);


//        if(colr.length>1) {
//      row0.setBackgroundColor(Color.parseColor(colr[i]));
//
           // crslist.get(i).setColr(colr[i]);
//            System.out.println("colorss"+colr[i]);
//
//
//        }


        row = new TableRow(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);


        row1 = new TableRow(context);
        TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row1.setLayoutParams(lp1);

        row1.setGravity(Gravity.LEFT);



       // row.addView(tv);
        row1.addView(tv1);


        holder.tb.addView(rowfrst);
      //  holder.tb.addView(row);
        holder.tb.addView(row0);
        holder.tb.addView(row_end);

        holder.tb.addView(row1);

        return rowView;


    }

    public void changeIndex(int position) {

        this.row_index = position;

        notifyDataSetChanged();
    }



}
