package duj.app.signomo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import duj.app.signomo.R;

public class HoroscopoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_horoscopo);

        ImageView signPic;
        TextView mHorText;
        TextView tvMoneyPercent;
        TextView tvHeartPercent;
        TextView tvHealthPercent;

        signPic = (ImageView) findViewById(R.id.horoscSignPic);
        mHorText = (TextView) findViewById(R.id.horText);
        tvMoneyPercent = (TextView) findViewById(R.id.tvMoneyPercent);
        tvHeartPercent = (TextView) findViewById(R.id.tvHeartPercent);
        tvHealthPercent = (TextView) findViewById(R.id.tvHealthPercent);

        mHorText.setText(getString(getIntent().getIntExtra("horoscopo",R.string.horAquario)));
        tvMoneyPercent.setText(getIntent().getIntExtra("moneyPercent",50)+"%");
        tvHeartPercent.setText(getIntent().getIntExtra("heartPercent",50)+"%");
        tvHealthPercent.setText(getIntent().getIntExtra("healthPercent",50)+"%");
        signPic.setImageResource(getIntent().getIntExtra("signoSelecionado",R.drawable.ic_appico));


        ImageButton myBackButton = (ImageButton) findViewById(R.id.horoscImgBtnBack);
        myBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
