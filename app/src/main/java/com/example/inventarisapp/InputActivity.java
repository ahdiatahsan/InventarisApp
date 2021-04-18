package com.example.inventarisapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventarisapp.DataBase.DBHelper;
import com.example.inventarisapp.DataBase.DataModel;

public class InputActivity extends AppCompatActivity {

    //deklarasi variabel
    Button btnDaftar;
    TextView Nama, Jumlah, Satuan, Tanggal, Keterangan;
    RadioGroup radioKondisi;
    RadioButton pilihanKondisi, rb_LK, rb_TLK;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //memberikan nilai/object ke variabel
        btnDaftar = findViewById(R.id.btnDaftar);
        Nama = findViewById(R.id.txtNama);
        Jumlah = findViewById(R.id.txtJumlah);
        Satuan = findViewById(R.id.txtSatuan);
        radioKondisi = findViewById(R.id.radioKondisi);
        Tanggal = findViewById(R.id.txtTanggal);
        Keterangan = findViewById(R.id.txtKeterangan);

        rb_LK = findViewById(R.id.layakPakai);
        rb_TLK = findViewById(R.id.tidakLayak);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihanKondisi = findViewById(radioKondisi.getCheckedRadioButtonId());
                String pilihan = pilihanKondisi.getText().toString();
                if (Nama.getText().toString().isEmpty()||Jumlah.getText().toString().isEmpty()
                    ||Satuan.getText().toString().isEmpty()||Tanggal.getText().toString().isEmpty()||Keterangan.getText().toString().isEmpty()) {
                    Toast.makeText(InputActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else{
                    DataModel notes = new DataModel();
                    notes.setNama(Nama.getText().toString());
                    notes.setJumlah(Jumlah.getText().toString());notes.setSatuan(Satuan.getText().toString());
                    notes.setKondisi(pilihan);notes.setTanggal(Tanggal.getText().toString());
                    notes.setKeterangan(Keterangan.getText().toString());
                    db = new DBHelper(InputActivity.this);
                    db.insert(notes);
                    Toast.makeText(getApplicationContext(), "Data Barang " + Nama.getText() + " Berhasil Disimpan !", Toast.LENGTH_SHORT).show();
                    clearText();
                    InputActivity.this.finish();
                }
            }
        });
    }

    void clearText(){
        Nama.setText("");
        Jumlah.setText("");
        Satuan.setText("");
        Tanggal.setText("");
        Keterangan.setText("");
    }

}