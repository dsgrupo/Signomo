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
    EditText usuario;
    EditText descricao;
    EditText nota;
    Avaliacao avaliacaoSelecionada;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_avaliacao);

        Button botao = (Button)findViewById(R.id.button);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvaliacaoDAO crud = new AvaliacaoDAO(getBaseContext());
                usuario = (EditText)findViewById(R.id.editText);
                descricao = (EditText)findViewById(R.id.editText2);
                nota = (EditText)findViewById(R.id.editText3);

                String cpfString = usuario.getText().toString();
                String nomeString = descricao.getText().toString();
                String idadeString = nota.getText().toString();
                String resultado;

                avaliacaoSelecionada = new Avaliacao(0,cpfString,nomeString,idadeString);
                resultado = crud.insereDado(avaliacaoSelecionada);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i=new Intent(InserirAvaliacaoActivity.this,AvaliacaoActivity.class);
                startActivity(i);
                finish();

            }
        });

        cancelar = (Button) findViewById(R.id.button2);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InserirAvaliacaoActivity.this,AvaliacaoActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
