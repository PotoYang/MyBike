package com.example.testhelper2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {

    TextView tv_display;
    Button btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_display = (TextView) findViewById(R.id.tv_dis);
        btn_get = (Button) findViewById(R.id.btn);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GetData();
                } catch (BiffException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void GetData() throws BiffException, IOException {
        InputStream is = null;
        AssetManager manager = getAssets();
        try {
            is = manager.open("questionbank.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Workbook rwb = Workbook.getWorkbook(is);
        rwb.getNumberOfSheets();
        Sheet firstSheet = rwb.getSheet(0);

        int rows = firstSheet.getRows();

        int random_num = (int) (Math.random() * rows);

        Cell cell = firstSheet.getCell(1, random_num);

        tv_display.setText((random_num + 1) + "\t" + cell.getContents());
    }
}
