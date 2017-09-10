package com.example.potoyang.mybike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_submit_num, et_submit_password;
    private Button btn_submit, btn_add_to_online, btn_add_to_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        et_submit_num = (EditText) findViewById(R.id.et_submit_num);
        et_submit_password = (EditText) findViewById(R.id.et_submit_password);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_add_to_online = (Button) findViewById(R.id.btn_add_to_online);
        btn_add_to_offline = (Button) findViewById(R.id.btn_add_to_offline);

        btn_submit.setOnClickListener(this);
        btn_add_to_online.setOnClickListener(this);
        btn_add_to_offline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (et_submit_num.getText().toString().trim().equals("")) {
                    Toast.makeText(AddDataActivity.this, "请输入车牌号后再提交!", Toast.LENGTH_SHORT).show();
                } else {
                    Check();
                }
                break;
            case R.id.btn_add_to_online:
                Intent intent1 = new Intent(this, OnlineActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btn_add_to_offline:
                Intent intent2 = new Intent(this, OfflineActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }
    }

    private void Check() {
        String num = et_submit_num.getText().toString().trim();
        BmobQuery<bike_num> query = new BmobQuery<>();
        query.addWhereEqualTo("username", num);
        query.findObjects(new FindListener<bike_num>() {
            @Override
            public void done(List<bike_num> list, BmobException e) {
                if (e == null) {
                    if (list.isEmpty()) {
                        Submit();
                    } else {
                        Toast.makeText(AddDataActivity.this, "此车数据已存在!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddDataActivity.this, "提交失败，请检查网络连接!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Submit() {
        bike_num bike_num = new bike_num();
        bike_num.setUsername(et_submit_num.getText().toString().trim());
        bike_num.setPassword(et_submit_password.getText().toString().trim());

        bike_num.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(AddDataActivity.this, "数据提交成功，感谢您的支持😁", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddDataActivity.this, "数据提交失败，请检查网络连接!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
