package duj.app.signomo.Retrofit;

import duj.app.signomo.Models.Avaliacao;
import duj.app.signomo.Retrofit.model.ReviewDetails;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;
public interface AvaliacaoService {

    @POST("users/{user_id}/reviews")
    Call<Avaliacao> saveOne(@Path("user_id") String userId,
                            @Body ReviewDetails details);
}
