package meridian.com.etsdcapp.adapter;

/**
 * Created by user 1 on 13-04-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.NotificationModel;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    List<NotificationModel> not;
    Context context;



  public   NotificationAdapter(ArrayList<NotificationModel> not, Context context) {
        this.not = not;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

      TextView txtnot,txt_notif_date;
        LinearLayout ln;

        ViewHolder(View itemView)
        {
            super(itemView);
           txtnot = (TextView) itemView.findViewById(R.id.txt_not);
            txt_notif_date = (TextView) itemView.findViewById(R.id.txt_notif_date);
            ln= (LinearLayout) itemView.findViewById(R.id.lin);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_notification, viewGroup, false);
        ViewHolder pv = new ViewHolder(v);
        return pv;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String s = not.get(position).getNotif();
        String date = not.get(position).getNotif_date();
        holder.txtnot.setText("\u2022  "+s);
        holder.txt_notif_date.setText("  "+date);
        System.out.println("ooo" + s);
        //Picasso.with(context).load(s).resize(540,550).placeholder(R.drawable.defaultimg).into(holder.v);
    }


    @Override
    public int getItemCount() {
        return not.size();
    }






}
