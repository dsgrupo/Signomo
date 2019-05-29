package duj.app.signomo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import duj.app.signomo.R;

public class ConfigActivity extends AppCompatActivity {
    private ImageButton mBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_config);
        ImageView img = (ImageView) findViewById(R.id.configProfilepic);
        img.setClipToOutline(true);
        mBtnBack = (ImageButton)findViewById(R.id.configImgBtnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
