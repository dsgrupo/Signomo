package duj.app.signomo.Retrofit;

import duj.app.signomo.Models.Usuario;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://login-service-signomo.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UsuarioService getUsuarioService() {
        return this.retrofit.create(UsuarioService.class);
    }

    public AvaliacaoService getAvaliacaoService() {
        return this.retrofit.create(AvaliacaoService.class);
    }
}
