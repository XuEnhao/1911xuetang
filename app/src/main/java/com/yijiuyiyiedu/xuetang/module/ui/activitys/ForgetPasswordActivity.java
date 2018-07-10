package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.ForgetPresenter;
import com.yijiuyiyiedu.xuetang.module.persenter.RegisterPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.ForgetView;
import com.yijiuyiyiedu.xuetang.module.view.RegisterView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity implements RegisterView, ForgetView {

    @BindView(R.id.register_phoneNumber)
    MyClearEditText phoneNumber;//手机号
    @BindView(R.id.register_verifyNumber)
    MyClearEditText verifyNumber;//数字验证码
    @BindView(R.id.register_getVerify)
    TextView getVerify;//获取验证码
    @BindView(R.id.register_password)
    EditText password;//密码
    @BindView(R.id.register_btn)
    StateButton register;//确认按钮
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    @BindView(R.id.phone_line)
    TextView phoneLine;
    @BindView(R.id.verifyNumber_line)
    TextView verifyNumberLine;
    @BindView(R.id.register_passwordClear)
    ImageView passwordClear;
    @BindView(R.id.password_line)
    TextView passwordLine;
    private RegisterPresenter presenter;
    private ForgetPresenter forgetPresenter;
    Context mContext;
    private boolean isSee;
    int time = 30;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                getVerify.setText("获取验证码");
                getVerify.setEnabled(true);
                time = 30;
                handler.removeMessages(1);
            } else {
                getVerify.setText(time + "S");
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        unbinder = ButterKnife.bind(this);
//        StatusBarUtil.setStatusBar(this, R.id.statusBarView);
        mContext = this;
        initView();

    }


//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.statusBarView);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }


    /**
     * 初始化布局
     */
    private void initView() {
        presenter = new RegisterPresenter(this);
        forgetPresenter = new ForgetPresenter(this);//忘记密码请求接口重新
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    decideData();
                }
                return false;
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        //手机号监听
        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        //验证码
        verifyNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        passwordClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSee){
                    isSee = true;
                    //显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordClear.setImageDrawable(getResources().getDrawable(R.mipmap.password_see));
                }else{
                    isSee = false;
                    //隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordClear.setImageDrawable(getResources().getDrawable(R.mipmap.password_img));
                }
                password.setSelection(password.getText().length());
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            WindowUtils.hintKeyboard(this);
            JumpUtil.overiderAnimsition(this);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @OnClick({R.id.register_back, R.id.register_getVerify, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_back://点击返回按钮
                WindowUtils.hintKeyboard(this);
                finish();
                break;
            case R.id.register_getVerify://获取验证码
                if (getVerify.getText().toString().equals("获取验证码")) {
                    getPhoneCode();
                } else {
                    UtilToast.showToast(mContext, "请等待");
                }
                break;
            case R.id.register_btn://确定按钮
                decideData();
                break;
        }
    }

    /**
     * 点击注册要判断的信息
     */
    private void decideData() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入正确的手机号");
        } else if (TextUtils.isEmpty(verifyNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入验证码");
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            UtilToast.showToast(mContext, "请输入密码");
        } else if (password.getText().toString().length() < 8) {
            UtilToast.showToast(mContext, "密码长度要大于8位");
        } else {
            forgetPresenter.getForgetData(phoneNumber.getText().toString(), password.getText().toString(), verifyNumber.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowUtils.hintKeyboard(this);
        handler.removeMessages(1);
    }

    /**
     * 获取验证码
     */
    private void getPhoneCode() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString())||phoneNumber.getText().toString().length()!=11) {
            UtilToast.showToast(mContext, "请输入正确的手机号");
        } else {
            presenter.getCodeData(phoneNumber.getText().toString(), 2);//注册type是1 忘记密码2
            getVerify.setText(time + "S");
            getVerify.setEnabled(false);
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    @Override
    public void showData(BaseResult data) {
        if (data.getCode() == 0) {
            UtilToast.showToast(mContext, "发送成功");
        } else {
            UtilToast.showToast(mContext, "发送失败");
        }
    }

    @Override
    public void showRegisterData(LoginEntity data) {
        if (data.getStatus() == 1) {
            if (TextUtils.isEmpty(data.getData().getToken())) {
                UriConstant.token = data.getData().getToken();//填写记录用户id和手机号
                UriConstant.userPhone = phoneNumber.getText().toString();
                SharedPreferencesUtil.put(mContext, "token", data.getData().getToken());
                JumpUtil.jump(mContext, MainActivity.class);
            }
            UtilToast.showToast(mContext, data.getMsg());
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showPhoneData(ClearHistoryEntity data) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }


    @Override
    public void showForgetData(ClearHistoryEntity data) {
        if (data.getStatus()==0) {
            UriConstant.userPhone = phoneNumber.getText().toString();
            SharedPreferencesUtil.put(mContext, "userPhone", phoneNumber.getText().toString());
            JumpUtil.jump(mContext, MainActivity.class);
            EventBus.getDefault().postSticky(new StudyBus(""));
            EventBus.getDefault().postSticky(new EditMyselfInfoBus(""));
            UtilToast.showToast(mContext, data.getMsg());
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }
}
