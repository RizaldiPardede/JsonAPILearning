package com.example.tugasjson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugasjson.api.RegisterAPI;
import com.example.tugasjson.model.Value;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static  final String URL = "http://192.168.100.11/crud/";
    private RadioButton radioJKButton;
    private ProgressDialog progress;
    String nim,nama,jurusan;

    @BindView(R.id.radioJK)
    RadioGroup radioGroup;
    @BindView(R.id.editTextNIM)
    EditText editTextNIM;
    @BindView(R.id.editTextNama)
    EditText editTextNama;
    @BindView(R.id.editTextJurusan)
    EditText editTextJurusan;

    @OnClick(R.id.buttonDaftar)void daftar(){
        //progress dialognya
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("load...");
        progress.show();

        nim = editTextNIM.getText().toString();
        nama = editTextNama.getText().toString();
        jurusan = editTextJurusan.getText().toString();

        int selectedradiobutton = radioGroup.getCheckedRadioButtonId();

        radioJKButton= (RadioButton) findViewById(selectedradiobutton);
        String jk = radioJKButton.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI reg = retrofit.create(RegisterAPI.class);
        Call<Value>call = reg.daftar(nim,nama,jurusan,jk);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                progress.dismiss();

                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Anda Tidak Berada Dalam Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
}