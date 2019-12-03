package duj.app.signomo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import duj.app.signomo.DAOS.AvaliacaoDAO;
import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.R;
import duj.app.signomo.utils.ConexaoUtil;

public class AlterarAvaliacaoActivity extends AppCompatActivity {
    EditText usuario;
    EditText descricao;
    EditText nota;
    EditText recommend;
    Button alterar;
    Button deletar;
    Cursor cursor;
    AvaliacaoDAO crud;
    String codigo;
    Avaliacao avaliacaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_avaliacao);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new AvaliacaoDAO(getBaseContext());

        usuario = (EditText)findViewById(R.id.editText6);
        descricao = (EditText)findViewById(R.id.editText7);
        nota = (EditText)findViewById(R.id.editText8);
        recommend = (EditText)findViewById(R.id.editText9);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        avaliacaoSelecionada = new Avaliacao(cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.USUARIO)),cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.DESCRICAO)), cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.NOTA)), cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.RECOMMEND)), Integer.parseInt(codigo));
        usuario.setText(avaliacaoSelecionada.getUsuario());
        descricao.setText(avaliacaoSelecionada.getDescri());
        nota.setText(avaliacaoSelecionada.getNota());

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                avaliacaoSelecionada.setUsuario(usuario.getText().toString());
                avaliacaoSelecionada.setDescri(descricao.getText().toString());
                avaliacaoSelecionada.setNota(nota.getText().toString());
                avaliacaoSelecionada.setRecommend(recommend.getText().toString());
                crud.alteraRegistro(avaliacaoSelecionada);
                Intent intent = new Intent(AlterarAvaliacaoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(avaliacaoSelecionada);
                Intent intent = new Intent(AlterarAvaliacaoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
