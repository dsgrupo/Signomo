package duj.app.signomo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AvaliarFragment extends Fragment {

    private EditText edtNota;
    private EditText edtDescricao;
    private Button btnEnviarAv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_avaliar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNota = view.findViewById(R.id.edtNota);
        edtDescricao = view.findViewById(R.id.edtDescricao);
        btnEnviarAv = view.findViewById(R.id.btnEnviarAvaliacao);
        btnEnviarAv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAvaliacao(edtDescricao.getText().toString().trim(), edtNota.getText().toString().trim());
            }
        });
    }

    private void salvarAvaliacao(String descricao, String nota) {
        AvaliacaoDAO crud = new AvaliacaoDAO(getContext());

        String id = PreferenceUtils.getId(getContext());
        String resultado;

        Avaliacao avaliacaoSelecionada = new Avaliacao(id, descricao, nota);
        crud.insereDado(avaliacaoSelecionada);

        atualizarWebService(avaliacaoSelecionada);
    }

    private void atualizarWebService(Avaliacao review) {
        String userId = PreferenceUtils.getId(getContext());
        Call<Avaliacao> call = new RetrofitConfig().getAvaliacaoService().saveOne(userId, new ReviewDetails(review.getDescription(), review.getRating()));

        call.enqueue(new Callback<Avaliacao>() {
            @Override
            public void onResponse(Call<Avaliacao> call, Response<Avaliacao> response) {
                Avaliacao reviewDetails = response.body();
                if (reviewDetails == null || reviewDetails.getId() == null) {
                    Toast.makeText(getContext(), "Houve um erro no envio da avaliação", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getContext(), "A avaliação foi enviada!", Toast.LENGTH_SHORT).show();
                    edtNota.setText("");
                    edtDescricao.setText("");
                }
            }

            @Override
            public void onFailure(Call<Avaliacao> call, Throwable t) {
                Toast.makeText(getContext(), "Houve um erro no envio da avaliação.", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
