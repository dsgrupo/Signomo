package duj.app.signomo.Retrofit;

import duj.app.signomo.Models.Usuario;
import duj.app.signomo.Retrofit.model.EditUserDetails;
import duj.app.signomo.Retrofit.model.LoginDetails;
import duj.app.signomo.Retrofit.model.RegistrationDetails;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("register")
    Call<Usuario> register(@Body RegistrationDetails details);

    @POST("login")
    Call<Usuario> login(@Body LoginDetails details);

    @PUT("users/{user_id}")
    Call<Usuario> update(@Path("user_id") String userId,
                         @Body EditUserDetails details);
}
