package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.LoginPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.AndroidBug5497Workaround;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.LoginView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
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

/**
 * Created by xuenhao on 2018/5/3.
 * 密码登录
 */

public class PassLoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.login_phoneNumber)
    MyClearEditText phoneNumber;//手机号
    @BindView(R.id.login_password)
    EditText password;//密码
    @BindView(R.id.login_login)
    StateButton loginLogin;//登录
    @BindView(R.id.note_back)
    ImageView noteBack;//back
    @BindView(R.id.login_passwordClear)
    ImageView passwordClear;
    @BindView(R.id.login_forgetPassWord)
    TextView forgetPassWord;//忘记密码
    @BindView(R.id.login_WxLogin)
    ImageView wxLogin;//微信登录
    @BindView(R.id.login_toRegister)
    TextView toRegister;//去注册
    @BindView(R.id.phone_line)
    TextView phoneLine;
    @BindView(R.id.password_line)
    TextView passwordLine;
    private boolean isSee = false;
    private Context mContext;
    private IWXAPI iwxapi;
    LoginPresenter loginPresenter;
    private int jump;
    private ProgressDialogUtils progressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_login);
        ButterKnife.bind(this);
        AndroidBug5497Workaround.assistActivity(this, true);
        mContext = this;
        jump = getIntent().getIntExtra("jump",0);
        initView();
        initWxLogin();
    }

    private void initWxLogin() {
        iwxapi = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        iwxapi.registerApp(Constant.WX_APP_ID);
        wxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxLogin();
            }
        });
    }

    private void wxLogin(){
        if (!iwxapi.isWXAppInstalled()) {
            UtilToast.showToast(mContext,"请安装微信");
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            iwxapi.sendReq(req);
        }
    }

    private void initView() {
        loginPresenter = new LoginPresenter(this);
//        forgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//添加下划线
        //添加监听软键盘回车
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
                    judgementUserAttr();
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
        //忘记密码
        forgetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, ForgetPasswordActivity.class);

            }
        });
        noteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    passwordLine.setBackgroundColor(getResources().getColor(R.color.loginButtonColor));
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
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
                }else{
                    phoneLine.setBackgroundColor(getResources().getColor(R.color.editTextLine));
                }
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext,RegisterActivity.class);
                finish();
            }
        });

    }


    /**
     * 判断是否用户有没填完的一项
     */
    private void judgementUserAttr() {

        if (TextUtils.isEmpty(phoneNumber.getText().toString()) || phoneNumber.getText().toString().length() != 11) {
            UtilToast.showToast(mContext, "请输入手机号");
        }else if (!MobileUtils.isMobileNO(phoneNumber.getText().toString())){
            UtilToast.showToast(mContext, "请输入正确格式的手机号");
        }else if (TextUtils.isEmpty(password.getText().toString())) {
            UtilToast.showToast(mContext, "请输入密码");
        }else if (password.getText().toString().length() < 8) {
            UtilToast.showToast(mContext, "密码长度要大于8位");
        } else {
            loginPresenter.getData(phoneNumber.getText().toString(), password.getText().toString());
        }

    }

    @Override
    protected void onDestroy() {
//        handler.removeMessages(1);
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
    }

    @OnClick({R.id.login_back, R.id.login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back://返回
                JumpUtil.overiderAnimsition(this);
                WindowUtils.hintKeyboard(this);
                break;
            case R.id.login_login://登陆
                judgementUserAttr();//判断用户是否输入
                break;

        }
    }


    @Override
    public void showData(LoginEntity data) {
        if (data.getStatus() == 0) {
            UriConstant.token = data.getData().getToken();
            LogUtil.LogD("token","login:"+UriConstant.token);
            SharedPreferencesUtil.put(mContext, "token", data.getData().getToken());
            WindowUtils.hintKeyboard((Activity) mContext);
            HttpHeaders headers = new HttpHeaders();
            headers.put("Authorization", SharedPreferencesUtil.getStringValue(getApplicationContext(),"token"));
            OkGo.getInstance()                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                    .addCommonHeaders(headers);//更新请求头
            EventBus.getDefault().postSticky(new StudyBus("1"));
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
            EventBus.getDefault().postSticky(new MyCourseBus("1"));//通知课程页面刷新数据
            if (jump == 0) { //1是课程详情
                JumpUtil.jump(mContext, MainActivity.class);
            } else {
                ((Activity) mContext).finish();
            }

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
        if (String.valueOf(data.getStatus()).equals("100100")){
            ToastUtils.showShort(data.getMsg());
        }else{

        }
    }

    @Override
    public void showErrorMessage() {

    }


}
