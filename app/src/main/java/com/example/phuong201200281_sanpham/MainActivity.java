package com.example.phuong201200281_sanpham;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
 private ArrayList<SanPham> ContacList;
    private EditText etSreach;
    private  Adapter ListAdapter;
    private ListView lstContact;
    FloatingActionButton btthem;
    int selectedid = -1;
    private Phuong_SQL db;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        //inflater.inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int id = 0;
        if (requestCode == 100 && resultCode == 200 && data != null) {

         // Lấy dữ liệu từ NewContact gửi về
         Bundle bundle = data.getExtras();
         id = bundle.getInt("Id");
         String ten = bundle.getString("Ten");
         int gia = bundle.getInt("Dongia");

         if (bundle.containsKey("SwitchValue")) {
          boolean switchValue = bundle.getBoolean("SwitchValue");

        // Đặt vào listData
        db.addContact(new SanPham(ten, gia, switchValue));
         ListAdapter.notifyDataSetChanged();
        resetData();
        } else {

        }
}

    }



    private void resetData(){
        db = new Phuong_SQL(MainActivity.this, "ContactDBb",null,1);
        ContacList  = db.GetAllContact();
        ListAdapter = new Adapter(ContacList, MainActivity.this);
        lstContact.setAdapter(ListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContacList = new ArrayList<SanPham>();
        db = new Phuong_SQL(this, "ContactDBb1",null,1);

//       db.addContact(new SanPham("Tivi Sony 23inch", 5100000, true));
//       db.addContact(new SanPham("Tủ Lạnh gen 8th", 120000000, false));
//       db.addContact(new SanPham("Lò vi Sóng chất lượng", 9000000, true));
//       db.addContact(new SanPham("Điều hòa 2 chiều ", 7500000, false));


        ContacList = db.GetAllContact();
        lstContact = findViewById(R.id.listview);
        ListAdapter = new Adapter(ContacList, this);
        etSreach = findViewById(R.id.etSearch);
        btthem = findViewById(R.id.btnAdd);

        //giảm dần về giá đã tính khuyến mãi
          Collections.sort(ContacList, new Comparator<SanPham>() {
             @Override
             public int compare(SanPham o1, SanPham o2) {
                 int giaDaGiam1 = tinhGiaDaGiam(o1);
                int giaDaGiam2 = tinhGiaDaGiam(o2);
             return giaDaGiam2 - giaDaGiam1;
          }

         private int tinhGiaDaGiam(SanPham sanPham) {
            int giatien = sanPham.getGiatien();
           boolean khuyenmai = sanPham.isKhuyenmai();
           if (khuyenmai) {
            // Thực hiện tính toán giá đã giảm giá dựa trên quy tắc giảm giá cụ thể của từng sản phẩm
            // Ví dụ:
             int giaDaGiam = (int) (giatien * 0.9); // Giảm giá 10%
             return giaDaGiam;
        } else {
            return giatien;
           }
       }
      });


        lstContact.setAdapter(ListAdapter);
        btthem.setOnClickListener(v -> {

            Intent intent = new Intent(this, Add.class);
            startActivityForResult(intent,100);

        });
        registerForContextMenu(lstContact);
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                selectedid = position;

                return false;
            }
        });

    }
}