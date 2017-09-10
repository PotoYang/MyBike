package com.example.potoyang.mybike;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    private LinearLayout ll;
    private TextView tv_version;//显示版本信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AndroidStudio隐藏标题栏由于是继承至AppCompatActivity不一样
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //去掉信息栏，全屏

        setContentView(R.layout.activity_loading);
        super.onCreate(savedInstanceState);

        ll = (LinearLayout) findViewById(R.id.splash_root);
        tv_version = (TextView) findViewById(R.id.tv_version);

        tv_version.setText(getVersion());

        //设置亮度渐变,从0.0到1.0
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        ll.startAnimation(animation);

        //设置动画监听器，当动画结束的时候，启动新的Activity
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startOnLineActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private String getVersion() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return "Version:" + version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号错误";
        }
    }

    private void startOnLineActivity() {
        Intent intent = new Intent(this, OnlineActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 屏蔽返回键，防止在动画加载过程中退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return false;
    }
}
