package com.example.inventarisapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventarisapp.DataBase.DBHelper;
import com.example.inventarisapp.DataBase.DataModel;

import java.util.List;

public class RecyclerBarangAdapter extends RecyclerView.Adapter<RecyclerBarangAdapter.ViewHolder> {
    List<DataModel> dataModel;
    Context context;
    DBHelper db;

    public RecyclerBarangAdapter(List<DataModel> dataModel, Context context) {
        this.dataModel = dataModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerBarangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);

        db = new DBHelper(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBarangAdapter.ViewHolder holder, int position) {
        holder.Tanggal.setText(dataModel.get(position).getTanggal());
        holder.Nama.setText(dataModel.get(position).getNama());
        holder.Jumlah.setText(dataModel.get(position).getJumlah());
        holder.Kondisi.setText(dataModel.get(position).getKondisi());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Pilih Aksi");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            v.getContext().startActivity(new Intent(v.getContext(), EditActivity.class)
                                    .putExtra("kode", dataModel.get(position).getKode())
                                    .putExtra("nama", dataModel.get(position).getNama())
                                    .putExtra("jumlah", dataModel.get(position).getJumlah())
                                    .putExtra("satuan", dataModel.get(position).getSatuan())
                                    .putExtra("kondisi", dataModel.get(position).getKondisi())
                                    .putExtra("tanggal", dataModel.get(position).getTanggal())
                                    .putExtra("keterangan", dataModel.get(position).getKeterangan()));
                            
                        } else {
                            delete(position);
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModel != null ? dataModel.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tanggal, Nama, Jumlah, Kondisi;
        CardView cvMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvMain = itemView.findViewById(R.id.cvMain);
            Tanggal = itemView.findViewById(R.id.tv_tanggal);
            Nama = itemView.findViewById(R.id.tv_nama);
            Jumlah = itemView.findViewById(R.id.tv_jumlah);
            Kondisi = itemView.findViewById(R.id.tv_kondisi);
        }
    }

    private void delete(int position) {
        // deleting the note from db
        db.delete(dataModel.get(position));

        // removing the note from the list
        dataModel.remove(position);
        notifyDataSetChanged();

        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }
}
