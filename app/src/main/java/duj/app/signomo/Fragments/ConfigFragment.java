package duj.app.signomo.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import duj.app.signomo.Models.Usuario;
import duj.app.signomo.R;
import duj.app.signomo.Retrofit.model.EditUserDetails;
import duj.app.signomo.activities.ConfigActivity;
import duj.app.signomo.Retrofit.RetrofitConfig;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigFragment extends Fragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Button btnSalvar;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText datePicker;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSalvar = (Button) view.findViewById(R.id.configBtnSalvar);
        edtNome = (EditText) view.findViewById(R.id.edtConfigNome);
        edtEmail = (EditText) view.findViewById(R.id.edtConfigEmail);
        edtSenha = (EditText) view.findViewById(R.id.edtConfigSenha);
        datePicker = (EditText) view.findViewById(R.id.configDatePicker);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(), android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePicker.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigActivity activity = (ConfigActivity) getActivity();
                boolean houveAlteracao = false;
                if (ConfigActivity.str_bitmap != null) {
                    PreferenceUtils.saveImage(ConfigActivity.str_bitmap, v.getContext());
                    houveAlteracao = true;
                }
                if (!(edtNome.getText().toString().trim().isEmpty())) {
                    PreferenceUtils.saveNome(edtNome.getText().toString().trim(), v.getContext());
                    activity.handleNameChange(edtNome.getText().toString().trim());
                    houveAlteracao = true;
                }
                if (!(edtEmail.getText().toString().trim().isEmpty())) {
                    PreferenceUtils.saveEmail(edtEmail.getText().toString().trim(), v.getContext());
                    houveAlteracao = true;
                }
                if (!(edtSenha.getText().toString().trim().isEmpty())) {
                    PreferenceUtils.saveSenha(edtSenha.getText().toString().trim(), v.getContext());
                    houveAlteracao = true;
                }
                if (!(datePicker.getText().toString().trim().isEmpty())) {
                    PreferenceUtils.saveNasc(datePicker.getText().toString().trim(), v.getContext());
                    calcularSigno(datePicker.getText().toString().trim(), v.getContext());
                    String signoSalvo = PreferenceUtils.getSigno(v.getContext());
                    activity.handleSignChange(signoSalvo + ", " + datePicker.getText().toString().trim());
                    houveAlteracao = true;
                }

                if (houveAlteracao) {
                    atualizarWebService();
                    edtNome.setText("");
                    edtEmail.setText("");
                    edtSenha.setText("");
                    datePicker.setText("");
                    Toast.makeText(v.getContext(), "Configurações alteradas", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void atualizarWebService() {
        String userId = PreferenceUtils.getId(getContext());
        String name = PreferenceUtils.getNome(getContext());
        String email = PreferenceUtils.getEmail(getContext());
        String password = PreferenceUtils.getSenha(getContext());
        String birthDate = PreferenceUtils.getNasc(getContext());
        String birthTime = PreferenceUtils.getbirthTime(getContext());

        EditUserDetails editedUser = new EditUserDetails(name,email,password,birthDate,birthTime);

        Call<Usuario> call = new RetrofitConfig().getUsuarioService().update(userId, editedUser);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario callbackResponse = response.body();
                if (callbackResponse == null || callbackResponse.getId() == null) {
                    Toast.makeText(getContext(), "Houve um erro na edição das informações.", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getContext(), "Configurações alteradas!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), "Houve um erro na edição das informações.", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public static void calcularSigno(String nasc, Context ctx) {
        String signo = nasc;

        String[] parts = nasc.split("/");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);
        int dia = Integer.parseInt(parts[0]);
        String mes = parts[1];

        if ((mes.equals("01") && dia >= 20) || (mes.equals("02") && dia <= 18)) {//Aquarium
            signo = "Aquário";
        } else if ((mes.equals("2") && dia >= 19) || (mes.equals("3") && dia <= 19)) {//Peixes
            signo = "Peixes";
        } else if ((mes.equals("3") && dia >= 20) || (mes.equals("4") && dia <= 18)) {//Peixes
            signo = "Áries";
        } else if ((mes.equals("4") && dia >= 19) || (mes.equals("5") && dia <= 19)) {//Peixes
            signo = "Touro";
        } else if ((mes.equals("5") && dia >= 20) || (mes.equals("6") && dia <= 20)) {//Peixes
            signo = "Gêmeos";
        } else if ((mes.equals("6") && dia >= 21) || (mes.equals("7") && dia <= 21)) {//Peixes
            signo = "Câncer";
        } else if ((mes.equals("7") && dia >= 22) || (mes.equals("8") && dia <= 21)) {//Peixes
            signo = "Leão";
        } else if ((mes.equals("8") && dia >= 22) || (mes.equals("9") && dia <= 21)) {//Peixes
            signo = "Virgem";
        } else if ((mes.equals("9") && dia >= 22) || (mes.equals("10") && dia <= 22)) {//Peixes
            signo = "Libra";
        } else if ((mes.equals("10") && dia >= 23) || (mes.equals("11") && dia <= 21)) {//Peixes
            signo = "Escorpião";
        } else if ((mes.equals("11") && dia >= 22) || (mes.equals("12") && dia <= 21)) {//Peixes
            signo = "Sagitário";
        } else if ((mes.equals("12") && dia >= 21) || (mes.equals("1") && dia <= 19)) {//Peixes
            signo = "Capricórnio";
        }

        PreferenceUtils.saveSigno(signo, ctx);
    }

}
