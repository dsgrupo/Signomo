package duj.app.signomo.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class ConfigActivity extends AppCompatActivity implements ImageView.OnClickListener{
    private ImageButton mBtnBack;
    private TextView tvNome;
    private TextView tvDataSigno;
    private ImageView img;
    private Button btnSalvar;
    private static final int RESULT_LOAD_IMAGE = 1;
    private String str_bitmap;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_config);
        img = (ImageView) findViewById(R.id.configProfilepic);
        img.setClipToOutline(true);
        img.setOnClickListener(this);
        tvNome = (TextView) findViewById(R.id.configProfilename);
        tvDataSigno = (TextView) findViewById(R.id.configTvDataSigno);
        btnSalvar = (Button) findViewById(R.id.configBtnSalvar);

        edtNome = (EditText)findViewById(R.id.edtConfigNome);
        edtEmail = (EditText)findViewById(R.id.edtConfigEmail);
        edtSenha = (EditText)findViewById(R.id.edtConfigSenha);
        datePicker = (EditText)findViewById(R.id.configDatePicker);


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        v.getContext(),android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePicker.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };

        img.setImageResource(R.drawable.ic_person_24dp);

        if (PreferenceUtils.getNome(this) != null && PreferenceUtils.getNasc(this)!=null){
            tvNome.setText(PreferenceUtils.getNome(this));
            String signoSalvo = PreferenceUtils.getSigno(this);
            tvDataSigno.setText(signoSalvo+", "+PreferenceUtils.getNasc(this));
        }

        if (PreferenceUtils.getImage(this) != null){
            String str_bitmap;
            Bitmap bitmap;
            str_bitmap = PreferenceUtils.getImage(this);
            bitmap = decodeBase64(str_bitmap);
            img.setImageBitmap(bitmap);
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean houveAlteracao = false;
                if (str_bitmap != null){
                    PreferenceUtils.saveImage(str_bitmap,v.getContext());
                    houveAlteracao = true;
                }
                if (!(edtNome.getText().toString().trim().isEmpty())){
                    PreferenceUtils.saveNome(edtNome.getText().toString().trim(),v.getContext());
                    tvNome.setText(edtNome.getText().toString().trim());
                    houveAlteracao = true;
                }
                if (!(edtEmail.getText().toString().trim().isEmpty())){
                    PreferenceUtils.saveEmail(edtEmail.getText().toString().trim(),v.getContext());
                    houveAlteracao = true;
                }
                if (!(edtSenha.getText().toString().trim().isEmpty())){
                    PreferenceUtils.saveSenha(edtSenha.getText().toString().trim(),v.getContext());
                    houveAlteracao = true;
                }
                if (!(datePicker.getText().toString().trim().isEmpty())){
                    PreferenceUtils.saveNasc(datePicker.getText().toString().trim(),v.getContext());
                    calcularSigno(datePicker.getText().toString().trim(),v.getContext());
                    String signoSalvo = PreferenceUtils.getSigno(v.getContext());
                    tvDataSigno.setText(signoSalvo+", "+datePicker.getText().toString().trim());
                    houveAlteracao = true;
                }

                if (houveAlteracao){
                    edtNome.setText("");

                    edtEmail.setText("");
                    edtSenha.setText("");
                    datePicker.setText("");
                    Toast.makeText(v.getContext(),"Configurações alteradas",Toast.LENGTH_LONG).show();
                }

            }
        });
        mBtnBack = (ImageButton)findViewById(R.id.configImgBtnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        }else if ((mes.equals("2") && dia >=19) || (mes.equals("3") && dia <= 19)){//Peixes
            signo = "Peixes";
        }else if ((mes.equals("3") && dia >=20) || (mes.equals("4") && dia <= 18)){//Peixes
            signo = "Áries";
        }else if ((mes.equals("4") && dia >=19) || (mes.equals("5") && dia <= 19)){//Peixes
            signo = "Touro";
        }else if ((mes.equals("5") && dia >=20) || (mes.equals("6") && dia <= 20)){//Peixes
            signo = "Gêmeos";
        }else if ((mes.equals("6") && dia >=21) || (mes.equals("7") && dia <= 21)){//Peixes
            signo = "Câncer";
        }else if ((mes.equals("7") && dia >=22) || (mes.equals("8") && dia <= 21)){//Peixes
            signo = "Leão";
        }else if ((mes.equals("8") && dia >=22) || (mes.equals("9") && dia <= 21)){//Peixes
            signo = "Virgem";
        }else if ((mes.equals("9") && dia >=22) || (mes.equals("10") && dia <= 22)){//Peixes
            signo = "Libra";
        }else if ((mes.equals("10") && dia >=23) || (mes.equals("11") && dia <= 21)){//Peixes
            signo = "Escorpião";
        }else if ((mes.equals("11") && dia >=22) || (mes.equals("12") && dia <= 21)){//Peixes
            signo = "Sagitário";
        }else if ((mes.equals("12") && dia >=21) || (mes.equals("1") && dia <= 19)){//Peixes
            signo = "Capricórnio";
        }

        PreferenceUtils.saveSigno(signo,ctx);
    }

    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            img.setImageURI(selectedImage);
            Bitmap bitmapImg = null;
            try {
                bitmapImg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            }catch (Exception ex){}
            str_bitmap = BitMapToString(bitmapImg);
        }
    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte[] arr = baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input){
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,   decodedByte.length);
    }
}
