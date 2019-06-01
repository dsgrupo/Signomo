package duj.app.signomo.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import duj.app.signomo.Fragments.DefHomemFragment;
import duj.app.signomo.Fragments.DefMulherFragment;
import duj.app.signomo.Fragments.LoginFragment;
import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class DefinicaoSignoActiviy extends AppCompatActivity {
    private ImageButton myBackbtn;
    private ImageView imagemSigno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_definicao_signo_activiy);
        myBackbtn = (ImageButton) findViewById(R.id.defImgBtnBack);
        imagemSigno = findViewById(R.id.defSignPic);
        imagemSigno.setImageResource(obterIDBaseadoNoSigno(this));
        myBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.def_mainContainer,new DefHomemFragment());
        ft.commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private int obterIDBaseadoNoSigno(Context ctx) {
        String signo = PreferenceUtils.getSigno(ctx);
        if (signo.equals("Aquário")){
            return R.drawable.ic_aquarium;
        }else if (signo.equals("Peixes")){
            return R.drawable.peixes;
        }else if (signo.equals("Áries")){
            return R.drawable.aries;
        }else if (signo.equals("Touro")){
            return R.drawable.touro;
        }else if (signo.equals("Gêmeos")){
            return R.drawable.gemeos;
        }else if (signo.equals("Câncer")){
            return R.drawable.cancer;
        }else if (signo.equals("Leão")){
            return R.drawable.leao;
        }else if (signo.equals("Virgem")){
            return R.drawable.virgem;
        }else if (signo.equals("Libra")){
            return R.drawable.libra;
        }else if (signo.equals("Escorpião")){
            return R.drawable.ic_scorpio;
        }else if (signo.equals("Sagitário")){
            return R.drawable.sagitario;
        }else{
            return R.drawable.capricornio;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_male:
                    selectedFragment = new DefHomemFragment();
                    break;
                case R.id.nav_female:
                    selectedFragment = new DefMulherFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.def_mainContainer, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DefinicaoSignoActiviy.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
