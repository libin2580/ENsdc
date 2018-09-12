package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.DatelistModel;
import meridian.com.etsdcapp.model.RegisterdCoursesModel;

/**
 * Created by user 1 on 07-04-2016.
 */
public class RegisterdCoursesAdapter extends RecyclerView.Adapter<RegisterdCoursesAdapter.ViewHolder> {


    List<RegisterdCoursesModel> crslist;
    Context context;
    String formatteddate,months,DAY,YEAR;
    SimpleDateFormat MNTH_FRMT = new SimpleDateFormat("mm");
    SimpleDateFormat DAY_FORMT = new SimpleDateFormat("DD");
    SimpleDateFormat YEAR_FORMT = new SimpleDateFormat("yyyy");
    //DateFormat df1 = new SimpleDateFormat("yyyy/MM/DD");


    public Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    DateFormat format = new SimpleDateFormat("DD-mm-yyyy");



   public RegisterdCoursesAdapter(List<RegisterdCoursesModel> crslist, Context context) {
        this.crslist = crslist;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       TableLayout tb;


        TextView txt_crc,txt_tim,txt_dur;



        ViewHolder(View itemView) {
            super(itemView);
                     tb = (TableLayout) itemView.findViewById(R.id.tbreg);
            txt_crc = (TextView) itemView.findViewById(R.id.txt_course);
//            txt_tim = (TextView) itemView.findViewById(R.id.txt_tim);
//            txt_dur = (TextView) itemView.findViewById(R.id.duration);

        }
    }


    @Override
    public int getItemCount() {
        return crslist.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_registered_courses1, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder personViewHolder, final int i) {



String subcourse=crslist.get(i).getSubcourse();


        System.out.println("subcrc1"+subcourse);

        String crcduration =   crslist.get(i).getSubcourseduration();
        System.out.println("duration"+crcduration);
        String courselst_tim=  crslist.get(i).getTime();




          TableRow row,row0;
        row0 = new TableRow(context);
        TableRow.LayoutParams lp0 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row0.setLayoutParams(lp0);
        TextView tvk = new TextView(context);
        tvk.setText(""+crcduration);
        row0.setGravity(Gravity.CENTER);
        row0.addView(tvk);


            row = new TableRow(context);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView tv0 = new TextView(context);
            tv0.setText(" \t\t");


            TextView tv = new TextView(context);
            //  row.setBackgroundResource(R.drawable.crossbutton);
            tv.setTextSize(15);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setText(subcourse);

            TextView tv1 = new TextView(context);
            row.setBackgroundResource(R.drawable.crossbutton);

                tv1.setText(courselst_tim);

        row.addView(tv0);
            row.addView(tv);

            row.addView(tv1);

        personViewHolder.tb.addView(row0);
            personViewHolder.tb.addView(row);






    }






    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static List<Date> getDates(String dateString1, String dateString2)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("DD-mm-yyyy");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

}