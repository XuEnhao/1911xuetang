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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.LoginPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.LoginView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.MobileUtils;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/5/4.
 * 第三方登录后 绑定手机号页面
 */

public class BindPhoneActivity extends BaseActivity implements LoginView {
    LoginPresenter loginPresenter;
    int time = 30;
    @BindView(R.id.register_phoneNumber)
    MyClearEditText phoneNumber;
    @BindView(R.id.register_back)
    ImageView back;
    @BindView(R.id.phone_line)
    TextView phoneLine;
    @BindView(R.id.register_verifyNumber)
    MyClearEditText verifyNumber;
    @BindView(R.id.register_getVerify)
    TextView getVerify;
    @BindView(R.id.verifyNumber_line)
    TextView verifyNumberLine;
    @BindView(R.id.register_companyId)
    MyClearEditText companyId;
    @BindView(R.id.companyId_line)
    TextView companyIdLine;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_passwordClear)
    ImageView passwordClear;
    @BindView(R.id.password_line)
    TextView passwordLine;
    @BindView(R.id.register_btn)
    StateButton registerBtn;
    private Context mContext;
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
    private String openid;
    private String nickname;
    private int sex;
    private int type;
    private boolean isSee;
    private ProgressDialogUtils progressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        mContext = this;
//        StatusBarUtil.setTranslucent(this);
        openid = getIntent().getStringExtra("openid");
        nickname = getIntent().getStringExtra("nickname");
        sex = getIntent().getIntExtra("sex", 3);
        type = getIntent().getIntExtra("type", 1);
        initView();
    }

    private void initView() {
        loginPresenter = new LoginPresenter(this);
//        获取验证码
        getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVerify.getText().toString().equals("获取验证码") || getVerify.getText().toString().equals("重新发送")) {
                    getPhoneCode();
                } else {
                    UtilToast.showToast(mContext, "请等待");
                }
            }
        });

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

//        密码输入框监听
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        companyId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });
        verifyNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    companyIdLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }else{
                    verifyNumberLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decideData();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
    }


    /**
     * 点击登录要判断的信息  短信登录
     */
    private void decideData() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString()) ) {
            UtilToast.showToast(mContext, "请输入手机号");
        } else if (!MobileUtils.isMobileNO(phoneNumber.getText().toString())|| phoneNumber.getText().toString().length() != 11){
            UtilToast.showToast(mContext, "请输入正确格式的手机号");
        }else if (TextUtils.isEmpty(verifyNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入验证码");
        } else {
//            请求绑定手机号接口
            getRegisterPhoneNumber(phoneNumber.getText().toString(), verifyNumber.getText().toString(), nickname, openid, String.valueOf(sex), String.valueOf(type));
        }
    }

    /**
     * 获取验证码
     */
    private void getPhoneCode() {
        if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入手机号");
        }else if (!MobileUtils.isMobileNO(phoneNumber.getText().toString())|| phoneNumber.getText().toString().length() != 11){
            UtilToast.showToast(mContext, "请输入正确格式的手机号");
        } else {
            loginPresenter.getCheckPhoneData(phoneNumber.getText().toString());
        }
    }

    /**
     * 获取验证码
     */
    private void getCodeData() {
        OkGo.<BaseResult>post(Constant.SEND_SMS)
                .tag(this)
                .params("phone", phoneNumber.getText().toString())
                .params("type", 1)
                .execute(new JsonCallback<BaseResult>() {
                    @Override
                    public void onSuccess(Response<BaseResult> response) {
                        UtilToast.showToast(mContext, response.body().getMsg());
                    }
                });
    }

    /**
     * @param phone        手机号
     * @param phoneSmsCode 验证码
     * @param nickName     昵称
     * @param openId       openid
     * @param sex          性别
     * @param openidtype   值为 1 时 代表微信登陆 值为 2时 代表 qq登陆
     */
    private void getRegisterPhoneNumber(String phone, String phoneSmsCode, String nickName, String openId, String sex, String openidtype) {
        OkGo.<LoginEntity>post(Constant.REGISTER_THIRD_PARTY)
                .tag(this)
                .params("phone", phone)
                .params("phoneSmsCode", phoneSmsCode)
                .params("nickname", nickName)
                .params("openid", openId)
                .params("sex", sex)
                .params("openidtype", openidtype)
                .execute(new JsonCallback<LoginEntity>() {
                    @Override
                    public void onSuccess(Response<LoginEntity> response) {
                        LoginEntity body = response.body();
                        if (response.body().getStatus() == 0) {
                            UtilToast.showToast(mContext, body.getMsg());
                            UriConstant.token = body.getData().getToken();
                            SharedPreferencesUtil.put(mContext, "token", "" + body.getData().getToken());
                            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
                            EventBus.getDefault().postSticky(new StudyBus("1"));
                            JumpUtil.jump(mContext, MainActivity.class);
                        } else {
                            UtilToast.showToast(mContext, body.getMsg());
                        }
                    }
                });
    }

    @Override
    public void showData(LoginEntity data) {
        if (data.getStatus() == 0) {
            UtilToast.showToast(mContext, data.getMsg());
            UriConstant.token = data.getData().getToken();
            SharedPreferencesUtil.put(mContext, "userPhone", phoneNumber.getText().toString());
            SharedPreferencesUtil.put(mContext, "token", "" + data.getData().getToken());
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
            EventBus.getDefault().postSticky(new StudyBus("1"));
            JumpUtil.jump(mContext, MainActivity.class);
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
    public void hideLoading() {
        progressDialogUtils.dismissDialog();
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showPhoneData(ClearHistoryEntity data) {
        if (String.valueOf(data.getStatus()).equals("0")){
            getCodeData();
            getVerify.setText(time + "S");
            getVerify.setEnabled(false);
            handler.sendEmptyMessageDelayed(1, 1000);
        }else{
            UtilToast.showToast(mContext,data.getMsg());
        }
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(1);
        super.onDestroy();
    }
}
