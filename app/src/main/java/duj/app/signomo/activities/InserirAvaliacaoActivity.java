package duj.app.signomo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import duj.app.signomo.DAOS.AvaliacaoDAO;
import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.R;
import duj.app.signomo.Retrofit.RetrofitConfig;
import duj.app.signomo.Retrofit.model.ReviewDetails;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InserirAvaliacaoActivity extends AppCompatActivity {
    EditText nota;
    EditText descricao;
    Avaliacao avaliacaoSelecionada;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_avaliacao);

        Button botao = (Button) findViewById(R.id.button);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvaliacaoDAO crud = new AvaliacaoDAO(getBaseContext());
                nota = (EditText) findViewById(R.id.editText);
                descricao = (EditText) findViewById(R.id.editText2);

                String id = PreferenceUtils.getId(v.getContext());
                String descricao = InserirAvaliacaoActivity.this.descricao.getText().toString();
                String nota = InserirAvaliacaoActivity.this.nota.getText().toString();
                String resultado;

                avaliacaoSelecionada = new Avaliacao(id, descricao, nota);
                resultado = crud.insereDado(avaliacaoSelecionada);

                atualizarWebService(avaliacaoSelecionada);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent i = new Intent(InserirAvaliacaoActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        cancelar = (Button) findViewById(R.id.button2);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InserirAvaliacaoActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void atualizarWebService(Avaliacao review) {
        String userId = PreferenceUtils.getId(this);
        Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();
        Call<Avaliacao> call = new RetrofitConfig().getAvaliacaoService().saveOne(userId, new ReviewDetails(review.getDescription(),review.getRating()));

        call.enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Avaliacao reviewDetails = response.body();
                if (reviewDetails == null || reviewDetails.getId() == null) {
                    Toast.makeText(getApplicationContext(), "Houve um erro no envio da avaliação", Toast.LENGTH_LONG)
                            .show();
                }else{

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro no envio da avaliação.", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
