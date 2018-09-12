package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import meridian.com.etsdcapp.schedule.CalendarWeekViewFragment;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.ScheduleModel;

/**
 * Created by user 1 on 07-04-2016.
 */
public class ScheduleWeekAdapter extends RecyclerView.Adapter<ScheduleWeekAdapter.ViewHolder> {


    List<ScheduleModel> crslist;
    Context context;

    String daynw;
   public static String k;



    public ScheduleWeekAdapter(List<ScheduleModel> crslist, Context context) {
        this.crslist = crslist;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView  txttim, topic, div, dept, evntnam;
        String imag;
        ImageView imglin;
        ImageView v;
        TextView tv,txtsch_crcnm;
        LinearLayout layblnk,laylin;
        // TextView personAge;

        ImageView personPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            txttim= (TextView) itemView.findViewById(R.id.txt_sch_tim);
            txtsch_crcnm= (TextView) itemView.findViewById(R.id.txt_schcrcnam);
            layblnk= (LinearLayout) itemView.findViewById(R.id.layblnk);
         //   laylin= (LinearLayout) itemView.findViewById(R.id.lay_img_lin);
            imglin= (ImageView) itemView.findViewById(R.id.img_lin);


        }
    }


    @Override
    public int getItemCount() {
        return crslist.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_schedule_week, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder personViewHolder, int i)
    {


        daynw= CalendarWeekViewFragment.newInstance().s();

        if(daynw != null )
        {
            k= CalendarWeekViewFragment.newInstance().s();

        }
        else {
            k= CalendarWeekViewFragment.newInstance().p();
        }
        System.out.println("kkkkkkk"+  k);

        String s=crslist.get(i).getDay();
        System.out.println("daynw"+  daynw);
        System.out.println("s"+ s);
        if(k.contentEquals("Friday"))
        {
            personViewHolder.txtsch_crcnm.setText("Holiday");
            personViewHolder.txtsch_crcnm.setGravity(Gravity.CENTER);
            personViewHolder.imglin.setVisibility(View.GONE);

        }
        else if(k.contentEquals("Saturday")) {
            personViewHolder.txtsch_crcnm.setText("Holiday");
            personViewHolder.txtsch_crcnm.setGravity(Gravity.CENTER);
            personViewHolder.imglin.setVisibility(View.GONE);
        }



        if(k.equals(s)&&!k.equals("friday")&&!k.equals("saturday"))
        {   String tim=crslist.get(i).getTim();

            System.out.println("timm"+tim);

           String newv= personViewHolder.txttim.getText().toString();

            if(crslist.get(i).getTim().endsWith("AM"))
            {

                personViewHolder.imglin.setVisibility(View.GONE);

                    personViewHolder.txttim.setText(crslist.get(i).getTim());
                    System.out.println("timeeee0"+crslist.get(i).getTim());
                System.out.println("test0");
                    personViewHolder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
               // if(crslist.get(i).getTim().startsWith(12))
            }
            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("AM")))
            {      personViewHolder.imglin.setVisibility(View.VISIBLE);
                personViewHolder.txttim.setText(crslist.get(i).getTim());
                System.out.println("timeeee0"+crslist.get(i).getTim());
                System.out.println("test1");
                personViewHolder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                // if(crslist.get(i).getTim().startsWith(12))
            }
            else if((crslist.get(i).getTim().endsWith("PM"))&&((crslist.get(i-1).getTim()).endsWith("PM")))
            {      personViewHolder.imglin.setVisibility(View.GONE);
                personViewHolder.txttim.setText(crslist.get(i).getTim());
                System.out.println("timeeee0"+crslist.get(i).getTim());
                System.out.println("test1");
                personViewHolder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                // if(crslist.get(i).getTim().startsWith(12))
            }

           else if(crslist.get(i).getTim().endsWith("AM")&&((crslist.get(i-1).getTim()).endsWith("PM")))
            {

                personViewHolder.imglin.setVisibility(View.GONE);
                personViewHolder.txttim.setText(crslist.get(i).getTim());
                System.out.println("timeeee0"+crslist.get(i).getTim());
                System.out.println("test0");
                personViewHolder.txtsch_crcnm.setText(crslist.get(i).getCrcnam());
                // if(crslist.get(i).getTim().startsWith(12))
            }


        }
        else {
            personViewHolder.layblnk.removeAllViews();


        }

        }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}