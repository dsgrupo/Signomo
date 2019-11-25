package duj.app.signomo.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class MapaAstralActivity extends AppCompatActivity {
    private ImageButton myBackBtn;
    private TextView tvNomeUsuario;
    private TextView tvSignoData;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapa_astral);
        myBackBtn = (ImageButton) findViewById(R.id.mapImgBtnBack);
        tvNomeUsuario = findViewById(R.id.mapProfilename);
        tvSignoData = findViewById(R.id.mapSignoData);
        img = findViewById(R.id.mapProfilepic);
        img.setImageResource(R.drawable.ic_person_24dp);
        img.setClipToOutline(true);
        if (PreferenceUtils.getImage(this) != null){
            String str_bitmap;
            Bitmap bitmap;
            str_bitmap = PreferenceUtils.getImage(this);
            bitmap = decodeBase64(str_bitmap);
            img.setImageBitmap(bitmap);
        }
        if (PreferenceUtils.getNome(this) != null && PreferenceUtils.getNasc(this)!=null){
            tvNomeUsuario.setText(PreferenceUtils.getNome(this));
            String signoSalvo = PreferenceUtils.getSigno(this);
            String dataNasc = PreferenceUtils.getNasc(this);
            tvSignoData.setText(signoSalvo+", "+dataNasc);
        }

        myBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,   decodedByte.length);
    }
}
