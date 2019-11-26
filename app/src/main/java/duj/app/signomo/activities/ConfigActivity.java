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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import duj.app.signomo.Fragments.AvaliarFragment;
import duj.app.signomo.Fragments.ConfigFragment;
import duj.app.signomo.R;
import duj.app.signomo.SharedPreference.PreferenceUtils;

public class ConfigActivity extends AppCompatActivity implements ImageView.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageButton mBtnBack;
    private ImageButton btnMenu;
    private static TextView tvNome;
    private static TextView tvDataSigno;
    private ImageView img;
    private static final int RESULT_LOAD_IMAGE = 1;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public static String str_bitmap;
//    private Button btnSalvar;
//    private EditText edtNome;
//    private EditText edtEmail;
//    private EditText edtSenha;
//    private EditText datePicker;

    private LinearLayout fragmentContainer;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_config);
        img = (ImageView) findViewById(R.id.configProfilepic);
        img.setClipToOutline(true);
        img.setOnClickListener(this);
        tvNome = (TextView) findViewById(R.id.configProfilename);
        tvDataSigno = (TextView) findViewById(R.id.configTvDataSigno);
        drawer = findViewById(R.id.drawer_layout);
        btnMenu = findViewById(R.id.configBtnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentContainer = findViewById(R.id.account_fragment_container);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.account_fragment_container, new ConfigFragment());
        fragmentTransaction.commit();

//        btnSalvar = (Button) findViewById(R.id.configBtnSalvar);
//        edtNome = (EditText)findViewById(R.id.edtConfigNome);
//        edtEmail = (EditText)findViewById(R.id.edtConfigEmail);
//        edtSenha = (EditText)findViewById(R.id.edtConfigSenha);
//        datePicker = (EditText)findViewById(R.id.configDatePicker);


//        datePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dialog = new DatePickerDialog(
//                        v.getContext(),android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener,year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//            }
//        });
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                datePicker.setText(dayOfMonth+"/"+(month+1)+"/"+year);
//            }
//        };

        img.setImageResource(R.drawable.ic_person_24dp);

        if (PreferenceUtils.getNome(this) != null && PreferenceUtils.getNasc(this) != null) {
            tvNome.setText(PreferenceUtils.getNome(this));
            String signoSalvo = PreferenceUtils.getSigno(this);
            tvDataSigno.setText(signoSalvo + ", " + PreferenceUtils.getNasc(this));
        }

        if (PreferenceUtils.getImage(this) != null) {
            String str_bitmap;
            Bitmap bitmap;
            str_bitmap = PreferenceUtils.getImage(this);
            bitmap = decodeBase64(str_bitmap);
            img.setImageBitmap(bitmap);
        }

        mBtnBack = (ImageButton) findViewById(R.id.configImgBtnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            img.setImageURI(selectedImage);
            Bitmap bitmapImg = null;
            try {
                bitmapImg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (Exception ex) {
            }
            str_bitmap = BitMapToString(bitmapImg);
        }
    }

    public static void handleNameChange(String newName) {
        tvNome.setText(newName);
    }

    public static void handleSignChange(String newSign) {
        tvDataSigno.setText(newSign);
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] arr = baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_config:
                getSupportFragmentManager().beginTransaction().replace(R.id.account_fragment_container,
                        new ConfigFragment()).commit();

                break;
            case R.id.nav_review:
                getSupportFragmentManager().beginTransaction().replace(R.id.account_fragment_container,
                        new AvaliarFragment()).commit();
                break;
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }
}
