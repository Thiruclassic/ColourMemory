package colourmemory.thiru.accedo.com.colourmemory.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Set;

import colourmemory.thiru.accedo.com.colourmemory.R;

/**
 * Created by Thirumal on 28/3/2018.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private DisplayMetrics metrics;
    public static int[] randomIds = new int[16];

    public ImageAdapter(Context context,DisplayMetrics metrics)
    {
        this.mContext = context;
        this.metrics = metrics;
        randomizeId();
    }


    private void randomizeId()
    {
        Set<Integer> idList = new HashSet<>();
        while(idList.size()<16)
        {
            int random = (int)(Math.random()*100)%16;
            if(idList.contains(random)) {
                continue;
            }
            randomIds[idList.size()] = random;
            idList.add(random);
        }

        for(int i=0;i<16;i++)
        {
            Log.d(String.valueOf(i),String.valueOf(randomIds[i]));
        }
    }

    // references to colour images
    public static Integer[] mThumbIds = {
            R.drawable.colour1,R.drawable.colour2,R.drawable.colour3,R.drawable.colour4,R.drawable.colour5,
            R.drawable.colour6,R.drawable.colour7,R.drawable.colour8,R.drawable.colour1,R.drawable.colour2,R.drawable.colour3,R.drawable.colour4,R.drawable.colour5,
            R.drawable.colour6,R.drawable.colour7,R.drawable.colour8
    };

    @Override
    public int getCount() {

        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return mThumbIds[i];
    }

    @Override
    public boolean isEnabled(int position) {
        // according to position return here true or false to enable or disable respectively
        return (position != -1);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(metrics.widthPixels/5, metrics.heightPixels/5));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setTag(1);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(R.drawable.cardfront);
        return imageView;
    }


}
