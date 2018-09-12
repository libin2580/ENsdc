package meridian.com.etsdcapp.gallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.model.GalleryModel;

public class ImageAdapter extends PagerAdapter {
        Context context;
        ArrayList <GalleryModel> arrglry;

        ImageAdapter(Context context, ArrayList<GalleryModel> arrglry)
        {
            this.context=context;
            this.arrglry=arrglry;
        }
        @Override
        public int getCount() {
            return arrglry.size();
        }



    @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
         //   ImageView txt= (ImageView) findViewById(R.id.txt_glrydtl);
           ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        imageView.setPadding(padding, padding, padding, padding);
          imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Picasso.with(context).load(arrglry.get(position).getGalimag()).placeholder(R.drawable.defaultimg).into(imageView);
            //imageView.setImageResource(arrglry.se);
          ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }