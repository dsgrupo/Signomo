package duj.app.signomo.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import duj.app.signomo.Fragments.DefHomemFragment;
import duj.app.signomo.Fragments.LoginFragment;
import duj.app.signomo.R;

public class DefinicaoSignoActiviy extends AppCompatActivity {
    private ImageButton myBackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_definicao_signo_activiy);
        myBackbtn = (ImageButton) findViewById(R.id.defImgBtnBack);
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
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DefinicaoSignoActiviy.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
