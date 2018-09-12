package meridian.com.etsdcapp.adapter;

/**
 * Created by user 1 on 13-04-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.GalleryModel;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    List<GalleryModel> glrylist;
    Context context;



    public GalleryAdapter(ArrayList<GalleryModel> glrylist, Context context) {
        this.glrylist = glrylist;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView v;
        ViewHolder(View itemView)
        {
            super(itemView);

            v = (ImageView) itemView.findViewById(R.id.img_glry);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_gallery, viewGroup, false);

        ViewHolder pv = new ViewHolder(v);
        return pv;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String s = glrylist.get(position).getGalimag();
        System.out.println("ooo" + s);
        Picasso.with(context).load(s).resize(540,550).placeholder(R.drawable.defaultimg).into(holder.v);
    }


    @Override
    public int getItemCount() {
        return glrylist.size();
    }






}
