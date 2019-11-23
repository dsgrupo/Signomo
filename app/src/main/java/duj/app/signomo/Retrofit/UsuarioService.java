package duj.app.signomo.Retrofit;

import duj.app.signomo.Models.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioService {

    @FormUrlEncoded
    @POST("register")
    Call<Usuario> insertClient(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("birthDate") String birthDate, @Field("birthTime") String birthTime);

    @FormUrlEncoded
    @POST("login")
    Call<Usuario> login(@Field("email") String email, @Field("password") String password);
}
