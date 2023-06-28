package com.example.phuong201200281_sanpham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Phuong_SQL extends SQLiteOpenHelper {
    //tên bảng
     public static  final String TableName = "SanPham_Phuong";

    public  static final String Id = "Id";
    public  static final String tensanpham = "tensanpham";
    public  static final String giatien = "giatien";
    public  static final String khuyenmai = "khuyenmai";

    public Phuong_SQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Phuong_SQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public Phuong_SQL(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }


    @Override
   public void onCreate(SQLiteDatabase db) {
    String sqlQuery = "CREATE TABLE IF NOT EXISTS " + TableName + "(" +
            Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            tensanpham + " TEXT, " +
            giatien + " TEXT, " +
            khuyenmai + " INTEGER)";
    db.execSQL(sqlQuery);
}


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }
    public ArrayList<SanPham> GetAllContact(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                SanPham contact = new SanPham(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3));
                list.add(contact);
            }
        }
        return list;
    }
   public void addContact(SanPham contact) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(tensanpham, contact.getTensanpham());
    values.put(giatien, contact.getGiatien());
    values.put(khuyenmai, contact.isKhuyenmai() ? 1 : 0);
    db.insert(TableName, null, values);
    db.close();
}

    public void UpdateContact(int id, SanPham contact) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(tensanpham, contact.getTensanpham());
    values.put(giatien, contact.getGiatien());
    values.put(khuyenmai, contact.isKhuyenmai() ? 1 : 0);
    db.update(TableName, values, Id + "=?", new String[]{String.valueOf(id)});
    db.close();
}

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from " + TableName + " Where ID=" + id;
        db.execSQL(sql);
        db.close();
    }


}
