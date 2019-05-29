package duj.app.signomo.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import duj.app.signomo.R;

public class DefMulherFragment extends Fragment implements TextView.OnClickListener{

    private TextView tvOpcaoHomem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentlayout_def_mulher, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvOpcaoHomem = view.findViewById(R.id.btnOpcaoHomem);
        tvOpcaoHomem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.def_mainContainer, new DefHomemFragment());
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
