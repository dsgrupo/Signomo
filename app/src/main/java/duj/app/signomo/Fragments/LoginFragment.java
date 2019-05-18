package duj.app.signomo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import duj.app.signomo.R;
import duj.app.signomo.activities.MainActivity;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentlayout_login, container, false);
        TextView myTv = (TextView) rootView.findViewById(R.id.tvWrongCombination);
        myTv.setVisibility(View.INVISIBLE);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView myLinkTxt = (TextView)view.findViewById(R.id.linkDHAccount);
        Button myBtnLogin = (Button)view.findViewById(R.id.btnLogin);
        final View rootView = view;
        myBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputEmail = (EditText)rootView.findViewById(R.id.edtLoginEmail);
                EditText inputPw = (EditText)rootView.findViewById(R.id.edtLoginSenha);
//                if (inputEmail.getText().toString().trim().equals("support@duj.com") && inputPw.getText().toString().trim().equals("123")){
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
//                }else{
                    TextView myTv = (TextView) rootView.findViewById(R.id.tvWrongCombination);
                    myTv.setVisibility(View.VISIBLE);
//                }
            }
        });

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
}
