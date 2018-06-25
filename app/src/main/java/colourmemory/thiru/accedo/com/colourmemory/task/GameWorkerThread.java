package colourmemory.thiru.accedo.com.colourmemory.task;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import colourmemory.thiru.accedo.com.colourmemory.R;
import colourmemory.thiru.accedo.com.colourmemory.adapter.ImageAdapter;
import colourmemory.thiru.accedo.com.colourmemory.constant.GameConstant;

/**
 * Created by Thirumal on 28/3/2018.
 */

public class GameWorkerThread extends Thread{

    Handler mHandler;
    int[] ids;

    public GameWorkerThread(int[] ids,Handler handler)
    {
        mHandler = handler;
        this.ids = ids;
    }
    @Override
    public void run() {
        try
        {
            Thread.sleep(1000);
            Message message = mHandler.obtainMessage();
            Log.d(String.valueOf(ImageAdapter.mThumbIds[ImageAdapter.randomIds[ids[0]]]),String.valueOf(ImageAdapter.mThumbIds[ImageAdapter.randomIds[ids[1]]]));
            if(ImageAdapter.mThumbIds[ImageAdapter.randomIds[ids[0]]].equals(ImageAdapter.mThumbIds[ImageAdapter.randomIds[ids[1]]]))
            {
                Log.d("correct","answer");
                message.obj = GameConstant.CORRECT;
            }
            else
            {
                message.obj = GameConstant.WRONG;
            }
            mHandler.sendMessage(message);
            //imageView.setImageResource(R.drawable.cardfront);
        }
        catch(Exception e)
        {

        }
    }
}
