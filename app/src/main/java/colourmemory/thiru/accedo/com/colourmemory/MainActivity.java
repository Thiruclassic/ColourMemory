package colourmemory.thiru.accedo.com.colourmemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import colourmemory.thiru.accedo.com.colourmemory.activity.GameActivity;

public class MainActivity extends AppCompatActivity {

    Button playGame;
    Button highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        setListeners();
    }


    public void setListeners()
    {
        View.OnClickListener playButtonListenerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        };
        playGame.setOnClickListener(playButtonListenerClickListener);
    }

    public void initializeView()
    {
        playGame = (Button)findViewById(R.id.play);

    }
}
