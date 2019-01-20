package com.example.ankitkumar.connect3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView) findViewById(R.id.connect);
        Typeface myfont = Typeface.createFromAsset(this.getAssets(),"fonts/jungle.ttf");

        ImageView icon = (ImageView) findViewById(R.id.homeicon);

        icon.setTranslationY(-2000);
        icon.animate().translationYBy(2000).setDuration(1700);

        txt.setTypeface(myfont);
        txt.setTranslationY(2000);
        txt.animate().translationYBy(-2000).setDuration(1700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, Page1.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
