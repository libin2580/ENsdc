package meridian.com.etsdcapp.adapter;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.CoursesModel;

/**
 * Created by user 1 on 07-04-2016.
 */
public class MainCourseAdapter extends RecyclerView.Adapter<MainCourseAdapter.ViewHolder> {


    List<CoursesModel> crslist;
    Context context;



   public MainCourseAdapter(List<CoursesModel> crslist, Context context) {
        this.crslist = crslist;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cv1;
        CardView cv;
        TextView speakr, topic, div, dept, evntnam;
        String imag;
        ImageView v;
        TextView tv;
        // TextView personAge;

        ImageView personPhoto;

        ViewHolder(View itemView) {
            super(itemView);
         cv1 = ( LinearLayout) itemView.findViewById(R.id.cv_crc);
            v = (ImageView) itemView.findViewById(R.id.img_course);
            tv=   (TextView) itemView.findViewById(R.id.txtnam);

            cv1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  cv1.setBackgroundResource(R.drawable.opito);
              }

          });
        }
    }


    @Override
    public int getItemCount() {
        return crslist.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_main_courses2, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder personViewHolder, final int i) {


        String s = crslist.get(i).getThumbnail();
        personViewHolder.tv.setText(crslist.get(i).getCoursenam());
        //String txt=crslist.get(i).getCoursenam();
        System.out.println("ooo" + s);

        Picasso.with(context).load(s).resize(150, 150).into(personViewHolder.v);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}