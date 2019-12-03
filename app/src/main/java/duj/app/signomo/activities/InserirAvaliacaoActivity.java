package duj.app.signomo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import duj.app.signomo.DAOS.AvaliacaoDAO;
import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.R;

public class InserirAvaliacaoActivity extends AppCompatActivity {
    Avaliacao avaliacaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_avaliacao);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvaliacaoDAO crud = new AvaliacaoDAO(getBaseContext());
                EditText usuario = (EditText)findViewById(R.id.editText);
                EditText descricao = (EditText)findViewById((R.id.editText2));
                EditText nota = (EditText)findViewById(R.id.editText3);
                EditText recommend = (EditText)findViewById(R.id.editText5);
                String usuarioString = usuario.getText().toString();
                String descricaoString = descricao.getText().toString();
                String notaString = nota.getText().toString();
                String recommendString = recommend.getText().toString();
                String resultado;
                avaliacaoSelecionada = new Avaliacao(usuarioString, descricaoString, notaString, recommendString,0);
                resultado = crud.insereDado(avaliacaoSelecionada);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent i;
                i = new Intent(v.getContext(), AvaliacaoActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }
}
