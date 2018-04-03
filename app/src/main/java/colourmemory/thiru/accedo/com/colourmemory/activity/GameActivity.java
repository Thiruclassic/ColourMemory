package colourmemory.thiru.accedo.com.colourmemory.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import colourmemory.thiru.accedo.com.colourmemory.R;
import colourmemory.thiru.accedo.com.colourmemory.adapter.ImageAdapter;
import colourmemory.thiru.accedo.com.colourmemory.constant.GameConstant;
import colourmemory.thiru.accedo.com.colourmemory.task.GameWorkerThread;

public class GameActivity extends AppCompatActivity {

    GridView gridview;
    private int touchSenser;
    Handler mHandler;
    ImageAdapter imageAdapter;
    TextView scoreView;
    TextView answerView;
    int[] gameCards;
    int gameScore;
    int cardsFound;
    ViewGroup transitionsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeView(this);
        setListeners();
        createHandler();
    }

    public void initializeView(Context context)
    {
        gridview = (GridView) findViewById(R.id.gridview);
        scoreView = (TextView) findViewById(R.id.score);
        answerView = (TextView) findViewById(R.id.answer);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        imageAdapter = new ImageAdapter(context,displayMetrics);
        transitionsContainer = (ViewGroup)findViewById(R.id.transitions_container);
        gridview.setAdapter(imageAdapter);

        gameCards = new int[2];

    }

    public Handler createHandler()
    {
        mHandler = new Handler()
        {
            @Override
            public void handleMessage(Message message)
            {
                String result = String.valueOf(message.obj);
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                if(result.equals(GameConstant.WRONG))
                {
                    scoreView.setText(GameConstant.SCORE+GameConstant.COLON+GameConstant.SPACE+--gameScore);
                    ImageView imageView = (ImageView) gridview.getChildAt(gameCards[0]);
                    imageView.setImageResource(R.drawable.cardfront);
                    imageView.setTag(1);
                    imageView = (ImageView) gridview.getChildAt(gameCards[1]);
                    imageView.setImageResource(R.drawable.cardfront);
                    imageView.setTag(1);
                    answerView.setText("Wrong... Please Try again!!!");
                }
                else
                {
                    answerView.setText("Perfect.. Keep going");
                    gameScore += 2;
                    cardsFound++;
                    scoreView.setText(GameConstant.SCORE+GameConstant.COLON+GameConstant.SPACE+gameScore);
                }
                if(cardsFound == 8)
                {
                    Toast.makeText(getApplicationContext(),"Game over",Toast.LENGTH_SHORT).show();
                    answerView.setText("Game Over");
                }
                gridview.setEnabled(true);

            }
        };

        return  mHandler;

    }

    public void setListeners()
    {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
              /*  Toast.makeText(GameActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();*/
                ImageView imageView = (ImageView)v;
                answerView.setText("");
                if(imageAdapter.isEnabled(Integer.valueOf(String.valueOf(imageView.getTag()))))
                {
                    imageView.setImageResource(ImageAdapter.mThumbIds[ImageAdapter.randomIds[position]]);
                    gameCards[touchSenser] = position;
                    touchSenser++;
                    imageView.setTag(-1);
                    if(touchSenser == 2)
                        {
                        gridview.setEnabled(false);
                        touchSenser = 0;
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        WindowManager manager = GameActivity.this.getWindowManager();
                        manager.getDefaultDisplay().getMetrics(displayMetrics);
                        GameWorkerThread thread = new GameWorkerThread(gameCards,mHandler);
                        thread.start();
                }
                }
            }
        });
    }


}
