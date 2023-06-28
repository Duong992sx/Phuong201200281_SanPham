package com.example.phuong201200281_sanpham;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Adapter extends BaseAdapter {
    private ArrayList<SanPham> data;
    private Activity context;
    private LayoutInflater inflater;
    private ArrayList<SanPham> databackup;

    public Adapter(){}
     public Adapter(ArrayList<SanPham> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
       return data.size();
    }

    @Override
    public Object getItem(int position) {
       return data.get(position);
    }

    @Override
    public long getItemId(int position) {
         return data.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v = convertView;
        if (v == null) {
        v = inflater.inflate(R.layout.itemlistview, null);
            }

        TextView ten = v.findViewById(R.id.tvTen);
        TextView gia = v.findViewById(R.id.tvgia);
        TextView giaGiam = v.findViewById(R.id.tvdagiam);
        TextView Giamgia=v.findViewById(R.id.tvgiamgia);

        SanPham sanPham = data.get(position);

        ten.setText(sanPham.getTensanpham());

        int giaTien = sanPham.getGiatien();
        gia.setText(NumberFormat.getInstance(new Locale("vi", "VN")).format(giaTien));

        if (sanPham.isKhuyenmai()) {
             Giamgia.setText("Giảm giá còn ");
         int giaGiamTien = (int) (sanPham.getGiatien() * 0.9); // Assuming 10% discount
        giaGiam.setText(NumberFormat.getInstance(new Locale("vi", "VN")).format(giaGiamTien));
        } else {
          Giamgia.setText("");
         giaGiam.setText(""); // No discount, so clear the discounted price
        }

        return v;
    }
}
