package meridian.com.etsdcapp.gallery;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.GallerDetailmodel;
import meridian.com.etsdcapp.model.GalleryModel;

public class TouchImageAdapter extends PagerAdapter {
    Context context;
    ArrayList<GallerDetailmodel> arrglry;
    String imgs;
    public TouchImageAdapter(Context context, ArrayList<GallerDetailmodel> arrglry,String imgs) {
        this.context=context;
        this.arrglry=arrglry;
    this.imgs=imgs;}

    @Override
            public int getCount() {
        return arrglry.size();
            }

            @Override
            public View instantiateItem(ViewGroup container, int position) {
                final TouchImageView img = new TouchImageView(container.getContext());


                Picasso.with(context).load(arrglry.get(position).getImageurl()).placeholder(R.drawable.defaultimg).into(img);
                System.out.println("gallery1"+arrglry.get(position).getImageurl());
                    container.addView(img, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                    return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                    return view == object;
            }

    }