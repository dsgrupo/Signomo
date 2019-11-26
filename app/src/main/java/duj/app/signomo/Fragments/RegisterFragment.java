package duj.app.signomo.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import duj.app.signomo.Models.Usuario;
import duj.app.signomo.R;
import duj.app.signomo.Retrofit.RetrofitConfig;
import duj.app.signomo.Retrofit.model.LoginDetails;
import duj.app.signomo.Retrofit.model.RegistrationDetails;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private EditText edtEscolherHora;
    private TimePickerDialog timePickerDialog;
    EditText myDatePicker;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfirmSenha;
    private Button btnRegistrar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentlayout_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        final EditText myDatePicker = (EditText) view.findViewById(R.id.registerDatePicker);
        myDatePicker = (EditText) view.findViewById(R.id.registerDatePicker);
        edtEscolherHora = view.findViewById(R.id.registerTimePicker);
        edtNome = (EditText)view.findViewById(R.id.edtRegisterNome);
        edtEmail = (EditText)view.findViewById(R.id.edtRegisterEmail);
        edtSenha = (EditText)view.findViewById(R.id.edtRegisterSenha);
        edtConfirmSenha = (EditText)view.findViewById(R.id.edtConfirmRegisterSenha);

        TextView myLinkTxt = (TextView)view.findViewById(R.id.linkAHAccount);
        btnRegistrar = (Button)view.findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtNome.getText().toString().equals("")||myDatePicker.getText().toString().equals("")||
                edtEscolherHora.getText().toString().equals("")|| edtEmail.getText().toString().equals("")||
                edtSenha.getText().toString().equals("")||edtConfirmSenha.getText().toString().equals("")){
                    Toast.makeText(v.getContext(),"Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else{
//                    PreferenceUtils.saveNome(edtNome.getText().toString().trim(),v.getContext());
//                    PreferenceUtils.saveEmail(edtEmail.getText().toString().trim(),v.getContext());
//                    PreferenceUtils.saveSenha(edtSenha.getText().toString().trim(),v.getContext());
//                    PreferenceUtils.saveNasc(myDatePicker.getText().toString().trim(),v.getContext());
                    submitRegisterInfo();
                    edtEmail.setText("");
                    edtNome.setText("");
                    edtSenha.setText("");
                    edtConfirmSenha.setText("");
                    myDatePicker.setText("");
                    PreferenceUtils.saveImage(null,v.getContext());
                    Toast.makeText(v.getContext(),"Conta criada com sucesso!", Toast.LENGTH_LONG).show();

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, new LoginFragment());
                    transaction.addToBackStack(null);

                    transaction.commit();
                }

            }
        });

        myLinkTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, new LoginFragment());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        edtEscolherHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        edtEscolherHora.setText(hourOfDay+":"+minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });


        myDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        view.getContext(),android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myDatePicker.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };
    }

    private void submitRegisterInfo() {
        String name = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtSenha.getText().toString();
        String birthDate = myDatePicker.getText().toString();
        String birthTime = edtEscolherHora.getText().toString();

        Call<Usuario> call = new RetrofitConfig().getUsuarioService().register(new RegistrationDetails(name,email,password,birthDate,birthTime));
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.w("Cadastrado com Sucesso", "Com Sucesso");
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.w("Falhou", "Com Falha");
            }
        });
    }

}
