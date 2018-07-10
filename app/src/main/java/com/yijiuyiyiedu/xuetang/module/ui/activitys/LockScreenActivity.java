package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/3/31.
 * 锁屏界面
 */

public class LockScreenActivity extends AppCompatActivity {
    Context mContext;
    @BindView(R.id.lock_screen_image)
    ImageView imageView;
    @BindView(R.id.underView)
    RelativeLayout underView;
    @BindView(R.id.moveview)
    LinearLayout mMoveView;
    @BindView(R.id.lock_screen_time)
    TextView time;
    @BindView(R.id.lock_screen_date)
    TextView date;
    @BindView(R.id.lock_screen_title)
    TextView title;
    @BindView(R.id.lock_screen_pause)
    TextView pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_lock_screen);
        ButterKnife.bind(this);
        mContext = this;
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        ImageView imageView = findViewById(R.id.lock_screen_image);
        // 获取壁纸管理器

        WallpaperManager wallpaperManager = WallpaperManager

                .getInstance(mContext);

        // 获取当前壁纸

        Drawable wallpaperDrawable = wallpaperManager.getDrawable();

        // 将Drawable,转成Bitmap

        Bitmap bm = ((BitmapDrawable) wallpaperDrawable).getBitmap();
        imageView.setImageBitmap(bm);


        time.setText(TimeUtil.formatNowData());
        date.setText(TimeUtil.getCurrentDay3());
        title.setText(getIntent().getStringExtra("title") + "");
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_BACK: {
                return true;
            }
            case KeyEvent.KEYCODE_MENU: {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private float mStartX;
    private int mWidth;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float nx = event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = nx;
//                finish();
                break;

            case MotionEvent.ACTION_MOVE:
                handleMoveView(nx);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                doTriggerEvent(nx);
                break;
        }
        return true;
    }

    private void doTriggerEvent(float x) {
        float movex = x - mStartX;
        if (movex > (mWidth * 0.4)) {
            moveMoveView(mWidth - mMoveView.getLeft(), true);//自动移动右边屏幕之外销毁

        } else {
            moveMoveView(-mMoveView.getLeft(), false);
        }

    }

    private void moveMoveView(float to, boolean exit) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMoveView,
                "translationX", to);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (underView.getBackground() != null) {
                    underView.getBackground().setAlpha((int) (((float) mWidth - mMoveView.
                            getTranslationX() / (float) mWidth * 200)));
                }
            }
        });
        animator.setDuration(250).start();
        if (exit) {
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    finish();
                    super.onAnimationEnd(animation);
                }
            });

        }
    }

    private void handleMoveView(float x) {
        float movex = x - mStartX;
        if (movex < 0)
            movex = 0;
        mMoveView.setTranslationX(movex);

        float mWidthFloat = (float) mWidth;
        if (underView.getBackground() != null) {
            underView.getBackground().setAlpha((int) ((mWidthFloat - mMoveView.getTranslationX() / mWidthFloat * 200)));//初始透明200
        }


    }


}
