package com.yijiuyiyiedu.xuetang.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PayActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    @BindView(R.id.pay_result_img)
    ImageView img;
    @BindView(R.id.pay_result_see)
    StateButton see;
    @BindView(R.id.pay_result)
    LinearLayout linearLayout;
    @BindView(R.id.pay_result_text)
    TextView text;

    private IWXAPI api;
    private int anInt = 3;
    private Context mContext;
    private int type = 0; //1 课程 2 项目 3充值

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        ButterKnife.bind(this);
        mContext = this;
//        f402e1a5d70090e3207ffa0b505d168d;
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
        setStatusBar();//透明导航栏
        initView();
    }

    private void initView() {
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type==1){
                    JumpUtil.jump(mContext, CourseDetailsActivity.class);
                }else if (type==2){
                    JumpUtil.jump(mContext, CourseDetailsActivity.class);
                }else{
                    JumpUtil.jump(mContext, MainActivity.class);
                }

            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            anInt--;
            text.setText(anInt + "秒后自动跳转,");
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
    private void setStatusBar() {
        // 设置透明导航栏
        StatusBarUtil.setTranslucentForImageView(this, 50, null);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
        View viewById = findViewById(R.id.pay_result_statusBar);
        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
        layoutParams.height = statusBarHeight;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        LogUtil.LogD("tag", "onReq: 成功");
        UtilToast.showToast(this, "支付成功");
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtil.LogD("tag", "onPayFinish, errCode = " + resp.errCode + resp.errStr);

//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("支付结果");
//            builder.setTitle(resp.errCode);
//            builder.setMessage("支付未完成");
//            builder.create();
//            builder.show();
//            UtilToast.showToast(this, "支付未完成");
//        }
        if (resp.errCode == 0) {
            type = PayActivity.type;
            UtilToast.showToast(this, "支付成功");
            linearLayout.setVisibility(View.VISIBLE);
            handler.postDelayed(runnable, 1000);
            EventBus.getDefault().post(new StudyBus("1"));//通知课程详情和购买页面
        } else {
            UtilToast.showToast(this, "支付失败");
//            linearLayout.setVisibility(View.VISIBLE);
            finish();
        }
    }

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