package com.example.potoyang.mybike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OnlineActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_online_num;
    private EditText et_online_password;
    private Button btn_online, btn_online_to_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        Bmob.initialize(OnlineActivity.this, "23f790c817e60450aedbf5b24a30e43e");

        et_online_num = (EditText) findViewById(R.id.et_online_num);
        et_online_password = (EditText) findViewById(R.id.et_online_password);
        btn_online = (Button) findViewById(R.id.btn_online);
        btn_online_to_offline = (Button) findViewById(R.id.btn_online_to_offline);

        btn_online.setOnClickListener(this);
        btn_online_to_offline.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_data:
                Intent intent = new Intent(OnlineActivity.this, AddDataActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_online:
                Query();
                break;
            case R.id.btn_online_to_offline:
                Intent intent = new Intent(this, OfflineActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void Query() {
        String num = et_online_num.getText().toString().trim();
        BmobQuery<bike_num> query = new BmobQuery<>();
        query.addWhereEqualTo("username", num);
        query.findObjects(new FindListener<bike_num>() {
            @Override
            public void done(List<bike_num> list, BmobException e) {
                if (e == null) {
                    if (list.isEmpty()) {
                        Toast.makeText(OnlineActivity.this, "未收录此车信息!", Toast.LENGTH_SHORT).show();
                    } else {
                        for (bike_num feed : list) {
                            et_online_password.setText(feed.getPassword().toString());
                        }
                        Toast.makeText(OnlineActivity.this, "获取成功!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OnlineActivity.this, "获取失败，请检查网络连接或进入离线版!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
