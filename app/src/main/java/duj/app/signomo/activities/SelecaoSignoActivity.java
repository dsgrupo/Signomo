package duj.app.signomo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import duj.app.signomo.R;

public class SelecaoSignoActivity extends AppCompatActivity{
    private ImageButton mBackBtn;
    private boolean selecaoParaHoroscopo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selecaoParaHoroscopo = getIntent().getBooleanExtra("selecaoParaHoroscopo",true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selecao_signo);
        mBackBtn = (ImageButton) findViewById(R.id.selecImgBtnBack);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void escolherSigno(View v){
        if (!selecaoParaHoroscopo){

        }else{
            Intent i = new Intent(SelecaoSignoActivity.this, HoroscopoActivity.class);
            int drawableid;
            switch(v.getId()){
                case R.id.selecmenuitem1:
                    drawableid = R.drawable.ic_aquarium;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem2:
                    drawableid = R.drawable.peixes;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem3:
                    drawableid = R.drawable.aries;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem4:
                    drawableid = R.drawable.touro;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem5:
                    drawableid = R.drawable.gemeos;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem6:
                    drawableid = R.drawable.cancer;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem7:
                    drawableid = R.drawable.leao;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem8:
                    drawableid = R.drawable.virgem;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem9:
                    drawableid = R.drawable.libra;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem10:
                    drawableid = R.drawable.ic_scorpio;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem11:
                    drawableid = R.drawable.sagitario;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
                case R.id.selecmenuitem12:
                    drawableid = R.drawable.capricornio;
                    i.putExtra("signoSelecionado",drawableid);
                    startActivity(i);
                    break;
            }
        }
    }
}
