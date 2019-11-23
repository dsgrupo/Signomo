package duj.app.signomo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import duj.app.signomo.Models.Usuario;
import duj.app.signomo.Retrofit.RetrofitConfig;
import duj.app.signomo.R;
import duj.app.signomo.Retrofit.UsuarioService;
import duj.app.signomo.Retrofit.model.LoginDetails;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import duj.app.signomo.activities.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener, TextInputEditText.OnEditorActionListener{
    private Button myBtnLogin;
    private TextView myLinkTxt;

    private EditText inputEmail;
    private EditText inputPw;
    private TextView myTv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        final View rootView = inflater.inflate(R.layout.fragmentlayout_login, container, false);
//        TextView myTv = (TextView) rootView.findViewById(R.id.tvWrongCombination);
//        myTv.setVisibility(View.INVISIBLE);
        return inflater.inflate(R.layout.fragmentlayout_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myLinkTxt = (TextView)view.findViewById(R.id.linkDHAccount);
        myBtnLogin = (Button)view.findViewById(R.id.btnLogin);
        inputEmail = (EditText)view.findViewById(R.id.edtLoginEmail);
        inputPw = (EditText)view.findViewById(R.id.edtLoginSenha);
        myTv = (TextView) view.findViewById(R.id.tvWrongCombination);
        myTv.setVisibility(View.INVISIBLE);

        inputPw.setOnEditorActionListener(this);

        myBtnLogin.setOnClickListener(this);




        myLinkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicou");

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, new RegisterFragment());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == myBtnLogin.getId()) {
            submitLoginInfo();
        }
    }
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            submitLoginInfo();
            return true;
        }

        return false;
    }
    private void submitLoginInfo() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPw.getText().toString().trim();
        Log.w("Email", email);
        Log.w("Senha", password);
        Call<Usuario> call = new RetrofitConfig().getUsuarioService().login(new LoginDetails(email,password));
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario userDetails = response.body();
                if (userDetails == null || userDetails.getId() == null) {
                    Toast.makeText(getContext(), "Credenciais inválidas", Toast.LENGTH_LONG)
                            .show();
                }else{
                    PreferenceUtils.saveNome(userDetails.getName(),getContext());
                    PreferenceUtils.saveId(userDetails.getId(), getContext());
                    PreferenceUtils.saveEmail(userDetails.getEmail(), getContext());
                    PreferenceUtils.saveNasc(userDetails.getBirthDate(), getContext());
//                  PreferenceUtils.savebirthTime(userDetails.getBirthTime(), getContext());
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), "Houve um erro no login, por favor tente novamente.", Toast.LENGTH_LONG)
                        .show();
//                myTv.setVisibility(View.VISIBLE);
//                Log.d("Message", t.getMessage());
            }
        });
    }
}
