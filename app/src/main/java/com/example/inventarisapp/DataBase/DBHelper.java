package com.example.inventarisapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 2;
    static final String DATABASE_NAME = "Inventaris";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_INVENTARIS_TABLE = "CREATE TABLE  " + DataModel.TABLE_SQLite + " (" +
                DataModel.COLUMN_KODE + " INTEGER PRIMARY KEY autoincrement, " +
                DataModel.COLUMN_NAMA + " VARCHAR NOT NULL, " +
                DataModel.COLUMN_JUMLAH + " VARCHAR NOT NULL, " +
                DataModel.COLUMN_SATUAN + " VARCHAR NOT NULL, " +
                DataModel.COLUMN_KONDISI + " VARCHAR NOT NULL," +
                DataModel.COLUMN_TANGGAL + " VARCHAR NOT NULL," +
                DataModel.COLUMN_KETERANGAN + " VARCHAR NOT NULL" +
                " )";
        db.execSQL(SQL_CREATE_INVENTARIS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataModel.TABLE_SQLite);
        onCreate(db);
    }

    public long insert(DataModel note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        values.put(DataModel.COLUMN_NAMA, note.getNama());
        values.put(DataModel.COLUMN_JUMLAH, note.getJumlah());
        values.put(DataModel.COLUMN_SATUAN, note.getSatuan());
        values.put(DataModel.COLUMN_KONDISI, note.getKondisi());
        values.put(DataModel.COLUMN_TANGGAL, note.getTanggal());
        values.put(DataModel.COLUMN_KETERANGAN, note.getKeterangan());
        // insert row
        long id = db.insert(DataModel.TABLE_SQLite, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<DataModel> getAll() {
        List<DataModel> notes = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataModel.TABLE_SQLite + " ORDER BY " +
                DataModel.COLUMN_KODE + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        // looping through all rows and adding to list
        while (!cursor.isAfterLast()) {

            DataModel note = new DataModel();
            note.setKode(cursor.getInt(cursor.getColumnIndex(DataModel.COLUMN_KODE)));
            note.setNama(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_NAMA)));
            note.setJumlah(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_JUMLAH)));
            note.setSatuan(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_SATUAN)));
            note.setKondisi(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_KONDISI)));
            note.setTanggal(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_TANGGAL)));
            note.setKeterangan(cursor.getString(cursor.getColumnIndex(DataModel.COLUMN_KETERANGAN)));
            notes.add(note);
            cursor.moveToNext();

        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + DataModel.TABLE_SQLite;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public long update(DataModel note, int kode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataModel.COLUMN_NAMA, note.getNama());
        values.put(DataModel.COLUMN_JUMLAH, note.getJumlah());
        values.put(DataModel.COLUMN_SATUAN, note.getSatuan());
        values.put(DataModel.COLUMN_KONDISI, note.getKondisi());
        values.put(DataModel.COLUMN_TANGGAL, note.getTanggal());
        values.put(DataModel.COLUMN_KETERANGAN, note.getKeterangan());

        // updating row
        long data = db.update(DataModel.TABLE_SQLite, values, DataModel.COLUMN_KODE + " = ?",
                new String[]{String.valueOf(kode)});
//        long data = db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = "+id, null);
        db.close();
        return data;
    }

    public void delete(DataModel note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataModel.TABLE_SQLite, DataModel.COLUMN_KODE + " = ?",
                new String[]{String.valueOf(note.getKode())});
        db.close();
    }
}
