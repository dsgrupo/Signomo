package duj.app.signomo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import duj.app.signomo.DAOS.AvaliacaoDAO;
import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.R;
import duj.app.signomo.utils.ConexaoUtil;

public class AvaliacaoActivity extends AppCompatActivity {
    private ListView lista;
    private Button inserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        AvaliacaoDAO crud = new AvaliacaoDAO(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]
                {ConexaoUtil.ID, ConexaoUtil.DESCRICAO};
        int[] idViews = new int[] {R.id.idAvaliacao, R.id.nomeAvaliacao};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.avalicao_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(ConexaoUtil.ID));
                Intent intent = new Intent(AvaliacaoActivity.this, AlterarAvaliacaoActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

        inserir = (Button)findViewById(R.id.botaoinserir);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AvaliacaoActivity.this,InserirAvaliacaoActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
