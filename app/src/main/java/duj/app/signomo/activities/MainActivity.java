package duj.app.signomo.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import duj.app.signomo.R;
import duj.app.signomo.utils.MyPageAdapter;
import duj.app.signomo.utils.Model;

public class MainActivity extends AppCompatActivity {

ViewPager viewPager;
MyPageAdapter myPageAdapter;
List<Model> models;
private ImageButton myExitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        myExitBtn = (ImageButton) findViewById(R.id.mainImgBtnExit);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.itembg,"Mapa astral","Clique para ver seu mapa astral."));
        models.add(new Model(R.drawable.itembg,"Definição","Clique para conhecer as definições dos signos."));
        models.add(new Model(R.drawable.itembg,"Ascendência","Clique para ver sua ascendência."));
        models.add(new Model(R.drawable.itembg,"Configurações","Clique para editar suas configurações."));

        myPageAdapter = new MyPageAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setPadding(110,0,110,0);



        ImageView img = (ImageView) findViewById(R.id.configProfilepic);
        img.setClipToOutline(true);
        LinearLayout myItemMenu1 = findViewById(R.id.itemMenu1);
        LinearLayout myItemMenu2 = findViewById(R.id.itemMenu2);
        myItemMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HoroscopoActivity.class);
                int drawableid = R.drawable.ic_aquarium;
                i.putExtra("signoSelecionado",drawableid);
                startActivity(i);
            }
        });
        myItemMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelecaoSignoActivity.class);
                i.putExtra("selecaoParaHoroscopo",true);
                startActivity(i);
            }
        });

        myExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
