package duj.app.signomo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import duj.app.signomo.R;

public class MapaAstralActivity extends AppCompatActivity {
    private ImageButton myBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapa_astral);
        myBackBtn = (ImageButton) findViewById(R.id.mapImgBtnBack);
        myBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
