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

public class DefHomemFragment extends Fragment implements TextView.OnClickListener{
    private TextView tvOpcaoMulher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentlayout_def, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvOpcaoMulher = view.findViewById(R.id.btnOpcaoMulher);
        tvOpcaoMulher.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.def_mainContainer, new DefMulherFragment());
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
