package meridian.com.etsdcapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.VideoModel;

/**
 * Created by user 1 on 07-04-2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {


    List<VideoModel> vidlist;
    Context context;



  public VideoAdapter(List<VideoModel> vidlist, Context context) {
        this.vidlist = vidlist;
        this.context = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView speakr, topic, div, dept, evntnam;
        String imag;
        ImageView v;
        TextView tv;
        WebView displayVideo;
        // TextView personAge;
        VideoView videoView;
        ImageView personPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_crc);
     //    displayVideo = (WebView)itemView.findViewById(R.id.webView);
       //  videoView = (VideoView)itemView. findViewById(R.id.videoView);
//Use a media controller so that you can scroll the video contents
//and also to pause, start the video.

        v = (ImageView) itemView.findViewById(R.id.img_course);
            tv=   (TextView) itemView.findViewById(R.id.txtnam);

//            cv.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  cv.setBackgroundResource(R.drawable.opito);
//              }
//
//          });
       }
    }


    @Override
    public int getItemCount() {
        return vidlist.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_video, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder personViewHolder, final int i) {
    String s = vidlist.get(i).getThumbnail();
        System.out.println("ooo" +vidlist.get(i).getTitle());
        Picasso.with(context).load(s).fit().into(personViewHolder.v);
        personViewHolder.tv.setText(vidlist.get(i).getTitle());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}