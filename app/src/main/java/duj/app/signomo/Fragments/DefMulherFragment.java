package duj.app.signomo.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class DefMulherFragment extends Fragment{

    private TextView defMulher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentlayout_def_mulher, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        defMulher = view.findViewById(R.id.tvDefMulher);
        String signo = PreferenceUtils.getSigno(view.getContext());
        if (signo.equals("Aquário")){
            defMulher.setText(R.string.defAquarioMulher);
        }else if (signo.equals("Peixes")){
            defMulher.setText(R.string.defPeixesMulher);
        }else if (signo.equals("Áries")){
            defMulher.setText(R.string.defAriesMulher);
        }else if (signo.equals("Touro")){
            defMulher.setText(R.string.defTouroMulher);
        }else if (signo.equals("Gêmeos")){
            defMulher.setText(R.string.defGemeosMulher);
        }else if (signo.equals("Câncer")){
            defMulher.setText(R.string.defCancerMulher);
        }else if (signo.equals("Leão")){
            defMulher.setText(R.string.defLeaoMulher);
        }else if (signo.equals("Virgem")){
            defMulher.setText(R.string.defVirgemMulher);
        }else if (signo.equals("Libra")){
            defMulher.setText(R.string.defLibraMulher);
        }else if (signo.equals("Escorpião")){
            defMulher.setText(R.string.defEscorpiaoMulher);
        }else if (signo.equals("Sagitário")){
            defMulher.setText(R.string.defSagitarioMulher);
        }else{
            defMulher.setText(R.string.defCapricornioMulher);
        }

    }
}
