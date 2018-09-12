package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.ScheduleModel;
import meridian.com.etsdcapp.schedule.CalendarWeekViewFragment;

/**
 * Created by user 1 on 15-06-2016.
 */
public class WeekListAdapter1 extends BaseAdapter{
    List<ScheduleModel> crslist;
    Context context;

    String daynw;
    TableRow row,row1,row2;

    public static String k;
    private static LayoutInflater inflater;

    public WeekListAdapter1(List<ScheduleModel> crslist, Context context) {
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



    public class Holder
    {
        TableLayout tbs;



    }
    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        System.out.println("adapter");
        rowView = inflater.inflate(R.layout.inflated_schwek1, null);
        holder.tbs=(TableLayout) rowView.findViewById(R.id.tbs);

        daynw=CalendarWeekViewFragment.selectedDateFRMT;
        if(daynw != null )
        {
            k= CalendarWeekViewFragment.selectedDateFRMT;
        }
        else {
            k= CalendarWeekViewFragment.newInstance().p();
        }
        System.out.println("wwwkkkkkkkw"+  k);
        String s=crslist.get(i).getDay();
        System.out.println("wwwdaynww"+  daynw);
        System.out.println("wwwswww"+ s);



        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

        final TableLayout tableLayout2 = new TableLayout(context);
        tableLayout2.setPadding(10,0,10,0);
        tableLayout2.setLayoutParams(tableLayoutParams);

        row = new TableRow(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tv = new TextView(context);
        tv.setTextColor(context.getResources().getColor(R.color.White));
        tv.setTextSize(15);


        if(k.contentEquals("Friday"))
        {

            tv.setText("\t"+"Holiday");

            row.addView(tv);
            tableLayout2.addView(row);

        }
        else if(k.contentEquals("Saturday")) {
            tv.setText("\t"+"Holiday");

            row.addView(tv);
            tableLayout2.addView(row);

        }





        if(k.equals(s)&&!k.equals("friday")&&!k.equals("saturday"))
        {

            if(crslist.get(i).getTim().endsWith("AM"))
            {  // holder.imglin.setVisibility(View.GONE);

                row1 = new TableRow(context);
                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row1.setLayoutParams(lp1);

                TextView tv1 = new TextView(context);
                tv1.setTextColor(context.getResources().getColor(R.color.White));
                tv1.setTextSize(15);
                tv1.setText(crslist.get(i).getCrcnam());

                tv1.setGravity(Gravity.START);
                TextView tv2 = new TextView(context);
                tv2.setTextColor(context.getResources().getColor(R.color.White));
                tv2.setTextSize(15);
                tv2.setGravity(Gravity.END);

                tv2.setText(crslist.get(i).getTim());
                row1.addView(tv1);
                row1.addView(tv2);
                tableLayout2.addView(row1);

            }




            if(crslist.get(i).getTim().endsWith("PM"))
            {  // holder.imglin.setVisibility(View.GONE);
                row1 = new TableRow(context);
                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row1.setLayoutParams(lp1);

                TextView tv1 = new TextView(context);
                tv1.setTextColor(context.getResources().getColor(R.color.White));
                tv1.setTextSize(15);
                tv1.setText(crslist.get(i).getCrcnam());
                tv1.setGravity(Gravity.START);
                TextView tv2 = new TextView(context);
                tv2.setTextColor(context.getResources().getColor(R.color.White));
                tv2.setTextSize(15);
                tv2.setText(crslist.get(i).getTim());
                tv2.setGravity(Gravity.END);
                row1.addView(tv1);
                row1.addView(tv2);
                tableLayout2.addView(row1);

            }
//            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("AM")))
//            {
//                row1 = new TableRow(context);
//                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                row1.setLayoutParams(lp1);
//                ImageView imv=new ImageView(context);
//                imv.setBackgroundResource(R.drawable.line);
//                row2 = new TableRow(context);
//                TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                row2.setLayoutParams(lp2);
//
//                TextView tv1 = new TextView(context);
//                tv.setTextColor(context.getResources().getColor(R.color.White));
//                tv.setTextSize(15);
//                tv1.setText(crslist.get(i).getCrcnam());
//                tv1.setPadding(0,0,30,0);
//                TextView tv2 = new TextView(context);
//                tv2.setTextColor(context.getResources().getColor(R.color.White));
//                tv2.setTextSize(15);
//                tv2.setText(crslist.get(i).getTim());
//                row1.addView(tv1);
//                row1.addView(tv2);
//                tableLayout2.addView(row2);
//                tableLayout2.addView(row1);
//                // if(crslist.get(i).getTim().startsWith(12))
//            }
//            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("PM")))
//            {  row1 = new TableRow(context);
//                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                row1.setLayoutParams(lp1);
//
//                TextView tv1 = new TextView(context);
//                tv.setTextColor(context.getResources().getColor(R.color.White));
//                tv.setTextSize(15);
//                tv1.setText(crslist.get(i).getCrcnam());
//                tv1.setPadding(0,0,30,0);
//                TextView tv2 = new TextView(context);
//                tv2.setTextColor(context.getResources().getColor(R.color.White));
//                tv2.setTextSize(15);
//                tv2.setText(crslist.get(i).getTim());
//                row1.addView(tv1);
//                row1.addView(tv2);
//                tableLayout2.addView(row1);
//                // if(crslist.get(i).getTim().startsWith(12))
//            }
//
//            else if(crslist.get(i).getTim().endsWith("AM")&&((crslist.get(i-1).getTim()).endsWith("PM")))
//            {
//
//                row1 = new TableRow(context);
//                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                row1.setLayoutParams(lp1);
//
//                TextView tv1 = new TextView(context);
//                tv.setTextColor(context.getResources().getColor(R.color.White));
//                tv.setTextSize(15);
//                tv1.setText(crslist.get(i).getCrcnam());
//                tv1.setPadding(0,0,30,0);
//                TextView tv2 = new TextView(context);
//                tv2.setTextColor(context.getResources().getColor(R.color.White));
//                tv2.setTextSize(15);
//                tv2.setText(crslist.get(i).getTim());
//                row1.addView(tv1);
//                row1.addView(tv2);
//                tableLayout2.addView(row1);
//            }


        }
        holder.tbs.addView(tableLayout2);

        return rowView;
    }

}
