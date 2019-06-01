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
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class DefHomemFragment extends Fragment{
    private TextView defHomem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentlayout_def, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defHomem = view.findViewById(R.id.tvDefHomem);
        String signo = PreferenceUtils.getSigno(view.getContext());
        if (signo.equals("Aquário")){
            defHomem.setText(R.string.defAquarioHomem);
        }else if (signo.equals("Peixes")){
            defHomem.setText(R.string.defPeixesHomem);
        }else if (signo.equals("Áries")){
            defHomem.setText(R.string.defAriesHomem);
        }else if (signo.equals("Touro")){
            defHomem.setText(R.string.defTouroHomem);
        }else if (signo.equals("Gêmeos")){
            defHomem.setText(R.string.defGemeosHomem);
        }else if (signo.equals("Câncer")){
            defHomem.setText(R.string.defCancerHomem);
        }else if (signo.equals("Leão")){
            defHomem.setText(R.string.defLeaoHomem);
        }else if (signo.equals("Virgem")){
            defHomem.setText(R.string.defVirgemHomem);
        }else if (signo.equals("Libra")){
            defHomem.setText(R.string.defLibraHomem);
        }else if (signo.equals("Escorpião")){
            defHomem.setText(R.string.defEscorpiaoHomem);
        }else if (signo.equals("Sagitário")){
            defHomem.setText(R.string.defSagitarioHomem);
        }else{
            defHomem.setText(R.string.defCapricornioHomem);
        }
    }
}
