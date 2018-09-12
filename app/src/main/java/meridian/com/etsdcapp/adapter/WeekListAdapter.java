package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.ScheduleModel;
import meridian.com.etsdcapp.schedule.CalendarWeekViewFragment;

/**
 * Created by user 1 on 15-06-2016.
 */
public class WeekListAdapter extends BaseAdapter{
    List<ScheduleModel> crslist;
    Context context;

    String daynw;

    public static String k;
    private static LayoutInflater inflater;

    public WeekListAdapter(List<ScheduleModel> crslist,Context context) {
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
        TextView txtsch_crcnm,txttim;
        LinearLayout  layblnk, laylin;
        ImageView imglin;



    }
    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        System.out.println("adapter");
        rowView = inflater.inflate(R.layout.inflate_schedule_week1, null);
        holder.txtsch_crcnm=(TextView) rowView.findViewById(R.id.txt_schcrcnam1);
        holder.txttim=(TextView) rowView.findViewById(R.id.txt_sch_tim1);
        holder.layblnk= (LinearLayout) rowView.findViewById(R.id.layblnk1);
        //holder.laylin= (LinearLayout) rowView.findViewById(R.id.lay_img_lin1);
        holder.imglin= (ImageView)rowView.findViewById(R.id.img_lin1);
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
        if(k.contentEquals("Friday"))
        {
            holder.txtsch_crcnm.setText("Holiday");
            holder.txtsch_crcnm.setGravity(Gravity.CENTER);
            holder.imglin.setVisibility(View.GONE);
            holder.txttim.setVisibility(View.GONE);
        }
        else if(k.contentEquals("Saturday")) {
            holder.txtsch_crcnm.setText("Holiday");
            holder.txtsch_crcnm.setGravity(Gravity.CENTER);
            holder.imglin.setVisibility(View.GONE);
            holder.txttim.setVisibility(View.GONE);
        }


        if(k.equals(s)&&!k.equals("friday")&&!k.equals("saturday"))
        {
            holder.imglin.setVisibility(View.GONE);

            String newv=    holder.txttim.getText().toString();

            if(crslist.get(i).getTim().endsWith("AM"))
            {  // holder.imglin.setVisibility(View.GONE);
                holder.txttim.setText(crslist.get(i).getTim());
                System.out.println("weeklistam"+crslist.get(i).getTim());
                System.out.println("weeklistwwwtest0");
                holder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                // if(crslist.get(i).getTim().startsWith(12))
            }
            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("AM")))
            {
                holder.imglin.setVisibility(View.VISIBLE);
                holder.layblnk.setPadding(0,20,0,0);
                holder.txttim.setText(crslist.get(i).getTim());
                System.out.println("weeklistpm-am"+crslist.get(i).getTim());
                System.out.println("weeklistwwwtest1");
                holder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());

                // if(crslist.get(i).getTim().startsWith(12))
            }
            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("PM")))
            {    //  holder.imglin.setVisibility(View.GONE);
                holder.txttim.setText(crslist.get(i).getTim());
                System.out.println("weeklistpm-pm"+crslist.get(i).getTim());
                System.out.println("weeklisttest2");
                holder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                // if(crslist.get(i).getTim().startsWith(12))
            }

            else if(crslist.get(i).getTim().endsWith("AM")&&((crslist.get(i-1).getTim()).endsWith("PM")))
            {

               holder.imglin.setVisibility(View.VISIBLE);

                holder.txttim.setText(crslist.get(i).getTim());
                System.out.println("weeklistam-pm"+crslist.get(i).getTim());
                System.out.println("weeklisttest3");
                holder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                holder.imglin.setVisibility(View.GONE);
                // if(crslist.get(i).getTim().startsWith(12))
            }


        }
        else {
            holder.layblnk.removeAllViews();


        }


        return rowView;
    }

}
