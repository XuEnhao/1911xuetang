package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.app.Activity;
import android.content.Context;
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
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.RegisterPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.AndroidBug5497Workaround;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.RegisterView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.MobileUtils;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R.id.register_phoneNumber)
    MyClearEditText phoneNumber;//手机号
    @BindView(R.id.register_verifyNumber)
    MyClearEditText verifyNumber;//数字验证码
    @BindView(R.id.register_getVerify)
    TextView getVerify;//获取验证码
    @BindView(R.id.register_password)
    EditText password;//密码
    @BindView(R.id.register_btn)
    StateButton register;//注册按钮
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    @BindView(R.id.register_companyId)
    MyClearEditText companyId;//企业id（可选）
    @BindView(R.id.register_passwordClear)
    ImageView passwordClear;//清除密码输入框
    @BindView(R.id.register_toLogin)
    TextView toLogin;
    @BindView(R.id.phone_line)
    TextView phoneLine;
    @BindView(R.id.verifyNumber_line)
    TextView verifyNumberLine;
    @BindView(R.id.companyId_line)
    TextView companyIdLine;
    @BindView(R.id.password_line)
    TextView passwordLine;
    private RegisterPresenter presenter;
    Context mContext;
    int time = 30;
    private boolean isSee;
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
    private ProgressDialogUtils progressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
        AndroidBug5497Workaround.assistActivity(this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        StatusBarUtil.setTranslucent(this);
        mContext = this;
        initView();

    }

    /**
     * 初始化布局
     */
    private void initView() {
        presenter = new RegisterPresenter(this);
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


        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, PassLoginActivity.class);
            }
        });
//        密码输入框监听
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                } else {
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                } else {
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        companyId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                } else {
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        verifyNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                } else {
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        passwordClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSee) {
                    isSee = true;
                    //显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordClear.setImageDrawable(getResources().getDrawable(R.mipmap.password_see));
                } else {
                    isSee = false;
                    //隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordClear.setImageDrawable(getResources().getDrawable(R.mipmap.password_img));
                }
                password.setSelection(password.getText().length());
            }
        });
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, PassLoginActivity.class);
                finish();
            }
        });

        activityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowUtils.hintKeyboard((Activity) mContext);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
                if (getVerify.getText().toString().equals("获取验证码") || getVerify.getText().toString().equals("重新发送")) {
                    getPhoneCode();
                } else {
                    UtilToast.showToast(mContext, "请等待");
                }
                break;
            case R.id.register_btn://注册按钮
                decideData();
                break;
        }
    }

    /**
     * 点击注册要判断的信息
     */
    private void decideData() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入手机号");
        } else if (!MobileUtils.isMobileNO(phoneNumber.getText().toString())|| phoneNumber.getText().toString().length() != 11){
            UtilToast.showToast(mContext, "请输入正确格式的手机号");
        }else if (TextUtils.isEmpty(verifyNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入验证码");
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            UtilToast.showToast(mContext, "请输入密码");
        } else if (password.getText().toString().length() < 8) {
            UtilToast.showToast(mContext, "密码长度要大于8位");
        } else {
            //请求注册接口
            presenter.RegisterData(phoneNumber.getText().toString(), password.getText().toString(), verifyNumber.getText().toString(), companyId.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(1);
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
    }

    /**
     * 获取验证码
     */
    private void getPhoneCode() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入手机号");
        }else if (!MobileUtils.isMobileNO(phoneNumber.getText().toString())|| phoneNumber.getText().toString().length() != 11){
            UtilToast.showToast(mContext, "请输入正确格式的手机号");
        }else {
            presenter.getCheckPhoneData(phoneNumber.getText().toString());
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
        if (data.getStatus() == 0) {
            UriConstant.token = data.getData().getToken();//填写记录用户id和手机号
            UriConstant.userPhone = phoneNumber.getText().toString();
            SharedPreferencesUtil.put(mContext, "token", data.getData().getToken());
            SharedPreferencesUtil.put(mContext, "userPhone", phoneNumber.getText().toString());
            HttpHeaders headers = new HttpHeaders();
            headers.put("Authorization", SharedPreferencesUtil.getStringValue(getApplicationContext(), "token"));
            OkGo.getInstance()                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                    .addCommonHeaders(headers);//更新请求头
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
            EventBus.getDefault().postSticky(new StudyBus("1"));
            JumpUtil.jump(mContext, MainActivity.class);
            UtilToast.showToast(mContext, data.getMsg());
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    @Override
    public void showLoading() {
        progressDialogUtils = new ProgressDialogUtils(mContext);
        progressDialogUtils.showDialog();
    }

    @Override
    public void showPhoneData(ClearHistoryEntity data) {
        if (String.valueOf(data.getStatus()).equals("0")){
            presenter.getCodeData(phoneNumber.getText().toString(), 1);//注册type是1
            getVerify.setText(time + "S");
            getVerify.setEnabled(false);
            handler.sendEmptyMessageDelayed(1, 1000);
        }else{
            UtilToast.showToast(mContext,data.getMsg());
        }
    }

    @Override
    public void hideLoading() {
        progressDialogUtils.dismissDialog();
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }


}
