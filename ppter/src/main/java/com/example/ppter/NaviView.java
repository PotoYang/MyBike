package com.example.ppter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by 71579 on 2016/11/25.
 */

public class NaviView extends View {

    Paint bg_rect, login_circle, div_line, reg_circle;
    Paint login_round, reg_round;
    Path login_path, reg_path;
    Paint login_text;

    public NaviView(Context context) {
        super(context);
        bg_rect = new Paint();
        bg_rect.setColor(Color.rgb(235, 101, 30));
        bg_rect.setStyle(Paint.Style.FILL);

        login_circle = new Paint();
        login_circle.setColor(Color.rgb(255, 255, 255));
        login_circle.setStyle(Paint.Style.FILL);

        div_line = new Paint();
        div_line.setColor(Color.rgb(255, 255, 255));
        div_line.setStyle(Paint.Style.FILL);

        reg_circle = new Paint();
        reg_circle.setColor(Color.rgb(255, 255, 255));
        reg_circle.setStyle(Paint.Style.FILL);

        login_round = new Paint();
        login_round.setColor(Color.rgb(71, 60, 64));
        login_round.setStrokeWidth(5);
        login_round.setStyle(Paint.Style.STROKE);

        reg_round = new Paint();
        reg_round.setColor(Color.rgb(71, 60, 64));
        reg_round.setStrokeWidth(5);
        reg_round.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), bg_rect);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 4, 300, login_circle);
        canvas.drawRect(100, getMeasuredHeight() / 2 - 15, getMeasuredWidth() - 100, getMeasuredHeight() / 2 + 15, div_line);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() * 3 / 4, 300, reg_circle);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 4, 300, login_round);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() * 3 / 4, 300, reg_round);


        login_path = new Path();
        login_path.moveTo(getMeasuredWidth() * 2 / 5, getMeasuredHeight() / 4);
        login_path.lineTo(getMeasuredWidth() * 3 / 5, getMeasuredHeight() / 4);

        login_text = new Paint();
        login_text.setTextSize(80);
        login_text.setStrokeWidth(10);
        login_text.setColor(Color.rgb(235, 101, 30));
        canvas.drawTextOnPath("LOGIN", login_path, -10, 20, login_text);

        canvas.save();
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
