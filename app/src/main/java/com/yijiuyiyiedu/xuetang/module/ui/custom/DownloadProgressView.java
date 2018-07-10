package com.yijiuyiyiedu.xuetang.module.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

/**
 * Created by xuenhao on 2018/6/12.
 * 下载的扇形
 */
@Keep
public class DownloadProgressView extends View {
    private int degree = 0;//角度
    private Paint paint;//画笔
    private int width;
    private int height;
    private int strokeWidth = 4;
    private int strokeColor = Color.parseColor("#6317A5");

    public DownloadProgressView(Context context) {
        this(context, null);
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DownloadProgressView);
        degree = a.getInt(R.styleable.DownloadProgressView_degree, 0);
        strokeColor = a.getColor(R.styleable.DownloadProgressView_color, strokeColor);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
    }


    /**
     * 设置边框颜色
     * @param color
     */
    public void setStrokeColor(int color){
        strokeColor = color;
        invalidate();
    }

    /**
     * 设置角度
     * @param degree
     */
    @Keep
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        paint.setStyle(Paint.Style.STROKE);//描边模式
        paint.setStrokeWidth(strokeWidth);//设置线的宽度
        paint.setColor(strokeColor);//设置颜色
        canvas.drawCircle(width / 2, height / 2, height / 2 - 2, paint);
        paint.setStyle(Paint.Style.FILL);//填充模式
        float sweepAngle = 1.0f * 360 * degree / 100;
        // 绘制弧线/扇形时的坐标, 或者外围矩形的坐标是相对于该控件自身左上角的点的距离, 不是相对于其父控件左上角的点的距离.
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawArc(rectF, 270, sweepAngle, true, paint);
        LogUtil.LogD("DownloadProgressView", degree+"");

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://手指按下
                break;
            case MotionEvent.ACTION_MOVE://移动
            case MotionEvent.ACTION_UP://手指抬起
                break;
        }
        return false;
    }
}
