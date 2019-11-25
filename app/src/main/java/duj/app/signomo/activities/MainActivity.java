package duj.app.signomo.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;
import duj.app.signomo.utils.MyPageAdapter;
import duj.app.signomo.utils.Model;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private Button inserir;
    ViewPager viewPager;
    MyPageAdapter myPageAdapter;
    List<Model> models;
    private ImageButton myExitBtn;
    private TextView tvProfileName;
    private TextView tvIdadeSigno;
    private TextView tvData1;
    private TextView tvData2;
    private RelativeLayout mContainer;
    private Context mContx;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mContx = this;
        mContainer = (RelativeLayout)findViewById(R.id.mainInformacoesConta);
        myExitBtn = (ImageButton) findViewById(R.id.mainImgBtnExit);
        tvProfileName = (TextView)findViewById(R.id.mainProfilename);
        tvIdadeSigno = (TextView)findViewById(R.id.mainTvSignoIdade);
        calcularSigno(PreferenceUtils.getNasc(this),this);
        tvData1 = (TextView)findViewById(R.id.mainData1);
        tvData2 = (TextView)findViewById(R.id.mainData2);
        img = (ImageView) findViewById(R.id.mainProfilePic);
        img.setImageResource(R.drawable.ic_person_24dp);
        img.setClipToOutline(true);
        Calendar cal = Calendar.getInstance();
        tvData1.setText(""+cal.get(Calendar.DAY_OF_MONTH));
        tvData2.setText(""+cal.get(Calendar.DAY_OF_MONTH));


        if (PreferenceUtils.getNome(this) != null && PreferenceUtils.getNasc(this)!=null){
            tvProfileName.setText(PreferenceUtils.getNome(this));
            String signoSalvo = PreferenceUtils.getSigno(this);
            BigDecimal idadeSalva = new BigDecimal(1);
            try {
                idadeSalva = calculaIdade(PreferenceUtils.getNasc(this));
            }catch (Exception ex){

            }
            tvIdadeSigno.setText(signoSalvo+", "+idadeSalva);
        }
        if (PreferenceUtils.getImage(this) != null){
            String str_bitmap;
            Bitmap bitmap;
            str_bitmap = PreferenceUtils.getImage(this);
            bitmap = decodeBase64(str_bitmap);
            img.setImageBitmap(bitmap);
        }
        RelativeLayout.LayoutParams mLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLP.addRule(RelativeLayout.BELOW, R.id.configNavigationbar);
        mLP.addRule(RelativeLayout.CENTER_IN_PARENT);
        mContainer.setLayoutParams(mLP);


        models = new ArrayList<>();
        models.add(new Model(R.drawable.itembg,"Mapa astral","Clique para ver seu mapa astral."));
        models.add(new Model(R.drawable.itembg,"Definição","Clique para conhecer as definições do seu signo."));
        models.add(new Model(R.drawable.itembg,"Ascendência","Clique para ver sua ascendência."));
        models.add(new Model(R.drawable.itembg,"Configurações","Clique para editar suas configurações."));
        models.add(new Model(R.drawable.itembg,"Avaliação","Clique para adicionar ou alterar uma avaliação."));

        myPageAdapter = new MyPageAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setPadding(110,0,110,0);




        LinearLayout myItemMenu1 = findViewById(R.id.itemMenu1);
        LinearLayout myItemMenu2 = findViewById(R.id.itemMenu2);
        myItemMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HoroscopoActivity.class);
                int drawableid;
                int stringid;
                String signo = PreferenceUtils.getSigno(v.getContext());
                if (signo.equals("Aquário")){
                    drawableid = R.drawable.ic_aquarium;
                    stringid = R.string.horAquario;
                    i.putExtra("moneyPercent", 70);
                    i.putExtra("heartPercent", 20);
                    i.putExtra("healthPercent", 20);
                }else if (signo.equals("Peixes")){
                    drawableid = R.drawable.peixes;
                    stringid = R.string.horPeixes;
                    i.putExtra("moneyPercent", 30);
                    i.putExtra("heartPercent", 60);
                    i.putExtra("healthPercent", 60);
                }else if (signo.equals("Áries")){
                    drawableid = R.drawable.aries;
                    stringid = R.string.horAries;
                    i.putExtra("moneyPercent", 30);
                    i.putExtra("heartPercent", 40);
                    i.putExtra("healthPercent", 40);
                }else if (signo.equals("Touro")){
                    drawableid = R.drawable.touro;
                    stringid = R.string.horTouro;
                    i.putExtra("moneyPercent", 50);
                    i.putExtra("heartPercent", 60);
                    i.putExtra("healthPercent", 50);
                }else if (signo.equals("Gêmeos")){
                    drawableid = R.drawable.gemeos;
                    stringid = R.string.horGemeos;
                    i.putExtra("moneyPercent", 40);
                    i.putExtra("heartPercent", 30);
                    i.putExtra("healthPercent", 60);
                }else if (signo.equals("Câncer")){
                    drawableid = R.drawable.cancer;
                    stringid = R.string.horCancer;
                    i.putExtra("moneyPercent", 40);
                    i.putExtra("heartPercent", 60);
                    i.putExtra("healthPercent", 80);
                }else if (signo.equals("Leão")){
                    drawableid = R.drawable.leao;
                    stringid = R.string.horLeao;
                    i.putExtra("moneyPercent", 50);
                    i.putExtra("heartPercent", 40);
                    i.putExtra("healthPercent", 50);
                }else if (signo.equals("Virgem")){
                    drawableid = R.drawable.virgem;
                    stringid = R.string.horVirgem;
                    i.putExtra("moneyPercent", 20);
                    i.putExtra("heartPercent", 60);
                    i.putExtra("healthPercent", 60);
                }else if (signo.equals("Libra")){
                    drawableid = R.drawable.libra;
                    stringid = R.string.horLibra;
                    i.putExtra("moneyPercent", 40);
                    i.putExtra("heartPercent", 30);
                    i.putExtra("healthPercent", 50);
                }else if (signo.equals("Escorpião")){
                    drawableid = R.drawable.ic_scorpio;
                    stringid = R.string.horEscorpiao;
                    i.putExtra("moneyPercent", 70);
                    i.putExtra("heartPercent", 40);
                    i.putExtra("healthPercent", 60);
                }else if (signo.equals("Sagitário")){
                    drawableid = R.drawable.sagitario;
                    stringid = R.string.horSagitario;
                    i.putExtra("moneyPercent", 20);
                    i.putExtra("heartPercent", 60);
                    i.putExtra("healthPercent", 50);
                }else{
                    drawableid = R.drawable.capricornio;
                    stringid = R.string.horCapricornio;
                    i.putExtra("moneyPercent", 40);
                    i.putExtra("heartPercent", 20);
                    i.putExtra("healthPercent", 50);
                }
                i.putExtra("signoSelecionado",drawableid);
                i.putExtra("horoscopo",stringid);
                startActivity(i);
            }
        });
        myItemMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelecaoSignoActivity.class);
                i.putExtra("selecaoParaHoroscopo",true);
                startActivity(i);
            }
        });

        myExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (PreferenceUtils.getImage(this) != null){
            String str_bitmap;
            Bitmap bitmap;
            str_bitmap = PreferenceUtils.getImage(this);
            bitmap = decodeBase64(str_bitmap);
            img.setImageBitmap(bitmap);
        }
        if (PreferenceUtils.getNome(this) != null && PreferenceUtils.getNasc(this)!=null){
            tvProfileName.setText(PreferenceUtils.getNome(this));
            String signoSalvo = PreferenceUtils.getSigno(this);
            BigDecimal idadeSalva = new BigDecimal(1);
            try {
                idadeSalva = calculaIdade(PreferenceUtils.getNasc(this));
            }catch (Exception ex){

            }
            tvIdadeSigno.setText(signoSalvo+", "+idadeSalva);
        }
    }

    public static String contaDias(String dataInicialBR, String dataFinalBR) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        Date dataInicio = df.parse(dataInicialBR);
        Date dataFim = df.parse(dataFinalBR);
        long dt = (dataFim.getTime() - dataInicio.getTime()) + 3600000;
        Long diasCorridosAnoLong = (dt / 86400000L);

        Integer diasDecorridosInt = Integer.valueOf(diasCorridosAnoLong.toString());

        String diasDecorridos = String.valueOf(diasDecorridosInt); //Sem numeros formatados;

        return diasDecorridos;

    }

    public static String getDataDiaBr(){
        GregorianCalendar calendario = new GregorianCalendar();
        int dia = calendario.get(calendario.DAY_OF_MONTH);
        int mes = calendario.get(calendario.MONTH) + 1;
        int ano = calendario.get(calendario.YEAR);
        String dataIguana = String.valueOf(dia + "/" + mes + "/" + ano);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String diaIguana = df.format(new Date());
        return diaIguana;
    }

    public static BigDecimal calculaIdade(String dataDoMeuNascimento) throws ParseException{
        BigDecimal qtdDias = new BigDecimal(contaDias(dataDoMeuNascimento,getDataDiaBr()));
        BigDecimal ano = new BigDecimal(365.25);
        BigDecimal idade = qtdDias.divide(ano,0, RoundingMode.DOWN);

        return idade;
    }

    public static void calcularSigno(String nasc, Context ctx){
        String signo=nasc;

        String[] parts = nasc.split("/");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);
        int dia = Integer.parseInt(parts[0]);
        String mes = parts[1];

        if ((mes.equals("01") && dia >=20) || (mes.equals("02") && dia <= 18)){//Aquarium
            signo = "Aquário";
        }else if ((mes.equals("02") && dia >=19) || (mes.equals("03") && dia <= 19)){//Peixes
            signo = "Peixes";
        }else if ((mes.equals("03") && dia >=20) || (mes.equals("04") && dia <= 18)){//Peixes
            signo = "Áries";
        }else if ((mes.equals("04") && dia >=19) || (mes.equals("05") && dia <= 19)){//Peixes
            signo = "Touro";
        }else if ((mes.equals("05") && dia >=20) || (mes.equals("06") && dia <= 20)){//Peixes
            signo = "Gêmeos";
        }else if ((mes.equals("06") && dia >=21) || (mes.equals("07") && dia <= 21)){//Peixes
            signo = "Câncer";
        }else if ((mes.equals("07") && dia >=22) || (mes.equals("08") && dia <= 21)){//Peixes
            signo = "Leão";
        }else if ((mes.equals("08") && dia >=22) || (mes.equals("09") && dia <= 21)){//Peixes
            signo = "Virgem";
        }else if ((mes.equals("09") && dia >=22) || (mes.equals("10") && dia <= 22)){//Peixes
            signo = "Libra";
        }else if ((mes.equals("10") && dia >=23) || (mes.equals("11") && dia <= 21)){//Peixes
            signo = "Escorpião";
        }else if ((mes.equals("11") && dia >=22) || (mes.equals("12") && dia <= 21)){//Peixes
            signo = "Sagitário";
        }else if ((mes.equals("12") && dia >=21) || (mes.equals("01") && dia <= 19)){//Peixes
            signo = "Capricórnio";
        }

        PreferenceUtils.saveSigno(signo,ctx);
    }
    public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,   decodedByte.length);
    }
}
