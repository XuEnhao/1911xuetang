package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BusinessEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.MobileUtils;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yijiuyiyiedu.xuetang.api.constant.Constant.ADD_CONTACT_BUSINESS;

public class CallBusinessActivity extends BaseActivity {

    @BindView(R.id.callBus_name)
    MyClearEditText name;//名字
    @BindView(R.id.callBus_phone)
    MyClearEditText phone;//电话
    @BindView(R.id.callBus_verifyNumber)
    MyClearEditText verifyNumber;//验证码
    @BindView(R.id.callBus_getVerify)
    TextView getVerify;//获取验证码
    @BindView(R.id.callBus_company)
    MyClearEditText company;//公司名称
    @BindView(R.id.callBus_companyAddress)
    MyClearEditText companyAddress;//公司地址
    @BindView(R.id.call_business)
    LinearLayout callBusiness;
    @BindView(R.id.call_business_linear)
    LinearLayout callBusinessLinear;
    private Context mContext;
    private boolean isShow = false;
    int time = 30;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                getVerify.setText("重新发送");
                getVerify.setEnabled(true);
                time = 30;
                handler.removeMessages(1);
            } else {
                getVerify.setText(time + "S");
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };
    private View popup;

    /**
     * 这个函数在Activity创建完成之后会调用。购物车悬浮窗需要依附在Activity上，如果Activity还没有完全建好就去
     * 调用showCartFloatView()，则会抛出异常
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus&&isShow) {
////            isInitFloatViewLocation = true;     // 购物车有数据了，肯定要初始化购物车悬浮窗的初始位置，准备显示
//            UtilsShowPopup.showCenterPopup(mContext, ((Activity) mContext).getWindow(), popup, callBusiness);
//            UtilsShowPopup.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    isShow = false;
//                }
//            });
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_business);
        callBusiness = findViewById(R.id.call_business);
        ButterKnife.bind(this);
//        AndroidBug5497Workaround.assistActivity(this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mContext = this;
        popup = View.inflate(mContext, R.layout.popup_call_business, null);

        callBusinessLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowUtils.hintKeyboard((Activity) mContext);
            }
        });
//        UtilsShowPopup.showBottomPopup(mContext,((Activity) mContext).getWindow(), popup,callBusiness);
    }

    @OnClick({R.id.callBus_back, R.id.callBus_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.callBus_back:
                JumpUtil.overiderAnimsition(this);
                break;
            case R.id.callBus_commit:
//                检查信息
//                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    judgeData();
//                } else {
//                    Intent it = new Intent(mContext, PassLoginActivity.class);
//                    it.putExtra("jump", 3);
//                    startActivity(it);
//                }
                break;
        }
    }

    /**
     * 检查信息
     */
    private void judgeData() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            ToastUtils.showShort("请输入联系人姓名");
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            ToastUtils.showShort("请输入手机号");
        } else if (!MobileUtils.isMobileNO(phone.getText().toString()) || phone.getText().toString().length() != 11) {
            ToastUtils.showShort("请输入正确格式的手机号");
        } else if (TextUtils.isEmpty(verifyNumber.getText().toString())) {
            ToastUtils.showShort("请输入验证码");
        } else if (TextUtils.isEmpty(company.getText().toString())) {
            ToastUtils.showShort("请输入公司名称");
        } else if (TextUtils.isEmpty(companyAddress.getText().toString())) {
            ToastUtils.showShort("请输入公司地址");
        } else {
            loadData();
        }
    }

    /**
     *
     */
    private void loadData() {
        OkGo.<BusinessEntity>post(ADD_CONTACT_BUSINESS)
                .tag(this)
                .params("company_name", company.getText().toString())
                .params("company_address", companyAddress.getText().toString())
                .params("contact_person", name.getText().toString())
                .params("phone", phone.getText().toString())
                .params("sms_code", verifyNumber.getText().toString())
                .execute(new JsonCallback<BusinessEntity>() {
                    @Override
                    public void onSuccess(Response<BusinessEntity> response) {
                        BusinessEntity body = response.body();
                        if (body.getStatus() == 0) {
                            ToastUtils.showShort(body.getMsg());
                            isShow = true;
                            UtilsShowPopup popupview =new UtilsShowPopup();
                            popupview.showCenterPopup(mContext, ((Activity) mContext).getWindow(), popup, callBusiness);
                            popupview.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    WindowUtils.setWindowBackground(((Activity) mContext).getWindow(), 1f);
                                    finish();
                                }
                            });
                            LogUtil.LogD("tag", getClass().getSimpleName() + body.getData().getId());
                        } else {
                            ToastUtils.showShort(body.getMsg());
                        }
                    }
                });

    }


    /**
     * 获取验证码
     */
    private void getVerifyCode() {
        OkGo.<BaseResult>post(Constant.SEND_SMS)
                .tag(this)
                .params("phone", phone.getText().toString())
                .params("type", 6)
                .execute(new JsonCallback<BaseResult>() {
                    @Override
                    public void onSuccess(Response<BaseResult> response) {
                        BaseResult body = response.body();
                        if (body.getCode() == 0) {
                            ToastUtils.showShort("发送成功");
                            getVerify.setText(time + "S");
                            getVerify.setEnabled(false);
                            handler.sendEmptyMessageDelayed(1, 1000);
                        } else {
                            ToastUtils.showShort(body.getMsg());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @OnClick(R.id.callBus_getVerify)
    public void onViewClicked() {
        if (getVerify.getText().toString().equals("获取验证码") || getVerify.getText().toString().equals("重新发送")) {
            if (TextUtils.isEmpty(phone.getText().toString())) {
                UtilToast.showToast(mContext, "请输入手机号");
            } else if (!MobileUtils.isMobileNO(phone.getText().toString()) || phone.getText().toString().length() != 11) {
                UtilToast.showToast(mContext, "请输入正确格式的手机号");
            } else {
                getVerifyCode();
            }
        } else {
            UtilToast.showToast(mContext, "请等待");
        }
    }


}
