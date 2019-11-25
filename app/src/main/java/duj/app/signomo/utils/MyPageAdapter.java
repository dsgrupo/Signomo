package duj.app.signomo.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import duj.app.signomo.activities.ConfigActivity;
import duj.app.signomo.activities.DefinicaoSignoActiviy;
import duj.app.signomo.activities.InserirAvaliacaoActivity;
import duj.app.signomo.activities.MapaAstralActivity;

public class MyPageAdapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyPageAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = layoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.layout_item2,container,false);
        ImageView imageView;
        TextView title,desc;

        imageView = view.findViewById(R.id.imageSlide);
        title = view.findViewById(R.id.slidetitle);
        desc = view.findViewById(R.id.slideDesc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        container.addView(view,0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                switch(position){
                    case 0:
                        i = new Intent(v.getContext(), MapaAstralActivity.class);
                        v.getContext().startActivity(i);
                        break;
                    case 1:
                        i = new Intent(v.getContext(), DefinicaoSignoActiviy.class);
                        v.getContext().startActivity(i);
                        break;
                    case 2:

                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                        View mView = layoutInflater.inflate(R.layout.testlayout_dialog_asc, null);
                        ImageButton myCloseButton = (ImageButton) mView.findViewById(R.id.dialogCloseBtn);
                        TextView tvNomeUsuario = mView.findViewById(R.id.dialogUserName);
                        TextView tvSignoData = mView.findViewById(R.id.dialogSignoData);
                        tvNomeUsuario.setText(PreferenceUtils.getNome(mView.getContext()));
                        tvSignoData.setText(PreferenceUtils.getSigno(mView.getContext())+", "+PreferenceUtils.getNasc(mView.getContext()));
                        mBuilder.setView(mView);
                        final AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        myCloseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;
                    case 3:
                        i = new Intent(v.getContext(), ConfigActivity.class);
                        v.getContext().startActivity(i);
                        break;

                    case 4:
                        i = new Intent(v.getContext(), InserirAvaliacaoActivity.class);
                        v.getContext().startActivity(i);
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
