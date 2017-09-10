package com.example.potoyang.mybike;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class OfflineActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_offline_num;
    private EditText et_offline_password;
    private Button btn_offline, btn_offline_to_online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        et_offline_num = (EditText) findViewById(R.id.et_offline_num);
        et_offline_password = (EditText) findViewById(R.id.et_offline_password);
        btn_offline = (Button) findViewById(R.id.btn_offline);
        btn_offline_to_online = (Button) findViewById(R.id.btn_offline_to_online);

        btn_offline.setOnClickListener(this);
        btn_offline_to_online.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_offline:
                try {
                    GetOfflineData();
                } catch (BiffException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_offline_to_online:
                Intent intent = new Intent(this, OnlineActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void GetOfflineData() throws BiffException, IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/offlinedata.xls";
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        InputStream inputStream = new FileInputStream(path);
        Workbook rwb = Workbook.getWorkbook(inputStream);
        rwb.getNumberOfSheets();
        Sheet firstSheet = rwb.getSheet(0);

        int rows = firstSheet.getRows();

        boolean isFind = false;
        for (int i = 1; i < rows; i++) {
            Cell cell = firstSheet.getCell(1, i);
            if (cell.getContents().equals(et_offline_num.getText().toString().trim())) {
                Cell cellPwd = firstSheet.getCell(0, i);
                if (cellPwd.getContents().equals("")) {
                    Toast.makeText(OfflineActivity.this, "此车数据还未更新，敬请期待!", Toast.LENGTH_SHORT).show();
                } else {
                    isFind = true;
                    et_offline_password.setText(cellPwd.getContents());
                }
            }
        }

        if (isFind) {
            Toast.makeText(OfflineActivity.this, "获取成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(OfflineActivity.this, "本地没有此车数据，请进入在线版!", Toast.LENGTH_SHORT).show();
        }
    }

}
