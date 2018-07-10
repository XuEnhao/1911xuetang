package com.yijiuyiyiedu.xuetang.alipay;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PayActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/4/18.
 * 阿里支付结果页面
 */

public class AlipayResultActivity extends BaseActivity {
    @BindView(R.id.pay_result_statusBar)
    View payResultStatusBar;
    @BindView(R.id.pay_result_img)
    ImageView payResultImg;
    @BindView(R.id.pay_result_text)
    TextView payResultText;
    @BindView(R.id.pay_result_see)
    StateButton payResultSee;
    @BindView(R.id.pay_result)
    LinearLayout payResult;
    private Context mContext;
    private int anInt = 2;
    private int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.pay_result_statusBar);
        initView();
        type = PayActivity.type;
        UtilToast.showToast(this, "支付成功");
        handler.postDelayed(runnable, 1000);
        EventBus.getDefault().post(new StudyBus("1"));//通知课程详情和购买页面
    }


    private void initView() {
        payResultSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, CourseDetailsActivity.class);
            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            anInt--;
            payResultText.setText(anInt + "秒后自动跳转,");
            if (anInt == 0) {
//                1 课程 2 项目 3充值
                if (type==1){
                    JumpUtil.jump(mContext, CourseDetailsActivity.class);
                }else if (type==2){
                    JumpUtil.jump(mContext, CourseDetailsActivity.class);
                }else{
                    JumpUtil.jump(mContext, MainActivity.class);
                }
            } else {
                handler.postDelayed(this, 1000);
            }
        }
    };
    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.pay_result_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            JumpUtil.jump(mContext, CourseDetailsActivity.class);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
