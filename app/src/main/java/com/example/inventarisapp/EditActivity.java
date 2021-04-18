package com.example.inventarisapp;

import android.content.Intent;
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

public class EditActivity extends AppCompatActivity {

    //deklarasi variabel
    Button btnEdit;
    TextView Nama, Jumlah, Satuan, Tanggal, Keterangan;
    RadioGroup radioKondisi;
    RadioButton pilihanKondisi, rb_LK, rb_TLK;
    String kondisiTerpilih;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //memberikan nilai/object ke variabel
        btnEdit = findViewById(R.id.btnEdit);
        Nama = findViewById(R.id.txtNama1);
        Jumlah = findViewById(R.id.txtJumlah1);
        Satuan = findViewById(R.id.txtSatuan1);
        radioKondisi = findViewById(R.id.radioKondisi1);
        Tanggal = findViewById(R.id.txtTanggal1);
        Keterangan = findViewById(R.id.txtKeterangan1);

        rb_LK = findViewById(R.id.layakPakai1);
        rb_TLK = findViewById(R.id.tidakLayak1);
        data();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihanKondisi = findViewById(radioKondisi.getCheckedRadioButtonId());
                String pilihan = pilihanKondisi.getText().toString();

                if (Nama.getText().toString().isEmpty()||Jumlah.getText().toString().isEmpty()
                        ||Satuan.getText().toString().isEmpty()||Tanggal.getText().toString().isEmpty()||Keterangan.getText().toString().isEmpty()) {
                    Toast.makeText(EditActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else{
                    DataModel notes = new DataModel();
                    notes.setNama(Nama.getText().toString());
                    notes.setJumlah(Jumlah.getText().toString());notes.setSatuan(Satuan.getText().toString());
                    notes.setKondisi(pilihan);notes.setTanggal(Tanggal.getText().toString());
                    notes.setKeterangan(Keterangan.getText().toString());
                    db = new DBHelper(EditActivity.this);
                    db.update(notes, getIntent().getIntExtra("kode", 0));
                    Toast.makeText(getApplicationContext(), "Data Barang " + Nama.getText() + " Berhasil Diubah !", Toast.LENGTH_SHORT).show();
                    clearText();
                    Intent reloadView = new Intent(EditActivity.this, ListActivity.class);
                    reloadView.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(reloadView);
                    EditActivity.this.finish();
                }
            }
        });
    }

    void data(){

        Nama.setText(getIntent().getStringExtra("nama"));
        Jumlah.setText(getIntent().getStringExtra("jumlah"));
        Satuan.setText(getIntent().getStringExtra("satuan"));
        switch (getIntent().getStringExtra("kondisi")) {
            case "Layak Pakai":
                rb_LK.setChecked(true);
                kondisiTerpilih = "Layak Pakai";
                break;

            case "Tidak Layak Pakai":
                rb_TLK.setChecked(true);
                kondisiTerpilih = "Tidak Layak Pakai";
                break;
        }

        Tanggal.setText(getIntent().getStringExtra("tanggal"));
        Keterangan.setText(getIntent().getStringExtra("keterangan"));

    }

    void clearText(){
        Nama.setText("");
        Jumlah.setText("");
        Satuan.setText("");
        Tanggal.setText("");
        Keterangan.setText("");
    }

}