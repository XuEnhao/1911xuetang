package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
import com.yijiuyiyiedu.xuetang.module.view.LoginView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/5/3.
 * 短信登录
 */

public class NoteLoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.login_send_phoneNumber)
    EditText sendPhoneNumber;//发送布局的手机号
    @BindView(R.id.login_send_verifyNumber)
    EditText verifyNumber;//发送验证码的布局
    @BindView(R.id.login_send_getVerify)
    TextView getVerify;//发送验证码
    @BindView(R.id.login_send_login)
    StateButton sendLogin;//登录
    LoginPresenter loginPresenter;
    int time = 60;
    @BindView(R.id.pass_login)
    TextView passLogin;//密码登录
    private Context mContext;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                getVerify.setText("重新发送");
                getVerify.setEnabled(true);
                time = 60;
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
        setContentView(R.layout.activity_note_login);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setTranslucent(this);
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
        passLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext,PassLoginActivity.class);
                finish();
            }
        });
        //短信登录 登录
        sendLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decideData();
            }
        });
    }


    /**
     * 点击登录要判断的信息  短信登录
     */
    private void decideData() {
        if (TextUtils.isEmpty(sendPhoneNumber.getText().toString()) || sendPhoneNumber.getText().toString().length() != 11) {
            UtilToast.showToast(mContext, "请输入正确的手机号");
        } else if (TextUtils.isEmpty(verifyNumber.getText().toString())) {
            UtilToast.showToast(mContext, "请输入验证码");
        } else {
//            请求登录接口
//            presenter.RegisterData(phoneNumber.getText().toString(), password.getText().toString(), verifyNumber.getText().toString(), 1);
//            loginPresenter.getData(sendPhoneNumber.getText().toString(), "", "2", verifyNumber.getText().toString());
        }
    }

    /**
     * 获取验证码
     */
    private void getPhoneCode() {
        if (TextUtils.isEmpty(sendPhoneNumber.getText().toString()) || sendPhoneNumber.getText().toString().length() != 11) {
            UtilToast.showToast(mContext, "请输入正确的手机号");
        } else {
            getCodeData();
            getVerify.setText(time + "S");
            getVerify.setEnabled(false);
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    /**
     * 获取验证码
     */
    private void getCodeData() {
        OkGo.<BaseResult>post(Constant.SEND_SMS)
                .tag(this)
                .params("phone", sendPhoneNumber.getText().toString())
                .params("type", 3)
                .execute(new JsonCallback<BaseResult>() {
                    @Override
                    public void onSuccess(Response<BaseResult> response) {
                        UtilToast.showToast(mContext, response.body().getMsg());
                    }
                });
    }

    @Override
    public void showData(LoginEntity data) {
        if (data.getStatus() == 1) {
//            UtilToast.showToast(mContext, data.getMsg());
            UriConstant.token = data.getData().getToken();
            SharedPreferencesUtil.put(mContext, "userPhone", sendPhoneNumber.getText().toString());
            SharedPreferencesUtil.put(mContext, "userId", "" + data.getData().getToken());
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
            EventBus.getDefault().postSticky(new StudyBus("1"));
            JumpUtil.jump(mContext, MainActivity.class);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showPhoneData(ClearHistoryEntity data) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(1);
        super.onDestroy();
    }

    @OnClick(R.id.note_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }


}
