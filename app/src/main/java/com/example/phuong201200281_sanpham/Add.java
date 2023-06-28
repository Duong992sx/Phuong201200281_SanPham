package com.example.phuong201200281_sanpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Add extends AppCompatActivity {
    EditText editText_name,  editText_dongia;
    Button button_add,btn_back;
     private int id;
     Switch mySwitch;


    @Override
   protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);

    button_add = findViewById(R.id.btnOk);
    btn_back = findViewById(R.id.btnquaylai);
    editText_name = findViewById(R.id.tvTensp);
    editText_dongia = findViewById(R.id.tvGiasp);
    mySwitch = findViewById(R.id.switch1);

    btn_back.setOnClickListener(view -> {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivityForResult(intent1, 0);
    });

    button_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (check()) {
                // Lấy giá trị của switch sau khi người dùng thay đổi trạng thái
                boolean switchValue = mySwitch.isChecked();

                // Tạo intent để trở về MainActivity
                Intent intent = new Intent();

                // Tạo bundle để chứa dữ liệu
                Bundle bundle = new Bundle();
                bundle.putInt("Id", id);
                bundle.putString("Ten", editText_name.getText().toString());
                bundle.putInt("Dongia", Integer.parseInt(editText_dongia.getText().toString()));
                bundle.putBoolean("SwitchValue", switchValue);

                intent.putExtras(bundle);

                // Trả về kết quả và đóng Activity hiện tại
                setResult(200, intent);
                if (button_add.getText() == "Edit")
                    setResult(201, intent);
                finish();
            }
        }
    });
}

    boolean check(){
        if(editText_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập ten!", Toast.LENGTH_SHORT).show();
            return false;
        }



        if(editText_dongia.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập gia!", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
    }
