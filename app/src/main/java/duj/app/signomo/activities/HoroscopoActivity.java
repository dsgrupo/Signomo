package duj.app.signomo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import duj.app.signomo.R;

public class HoroscopoActivity extends AppCompatActivity {
    private ImageView signPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_horoscopo);
        signPic = (ImageView) findViewById(R.id.horoscSignPic);
        signPic.setImageResource(getIntent().getIntExtra("signoSelecionado",R.drawable.ic_appico));
        ImageButton myBackButton = (ImageButton) findViewById(R.id.hrscpImgBtnBack);
        myBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
