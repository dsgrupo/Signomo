package duj.app.signomo.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import duj.app.signomo.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        TextView myTv = (TextView)findViewById(R.id.splashMessage) ;
        Typeface myFont = Typeface.createFromAsset(this.getAssets(),"fonts/cour.ttf");
        myTv.setTypeface(myFont);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("a");
                Intent i = new Intent(SplashActivity.this,SignActivity.class);
                startActivity(i);
            }
        }, 2000);   //5 seconds

    }
}
