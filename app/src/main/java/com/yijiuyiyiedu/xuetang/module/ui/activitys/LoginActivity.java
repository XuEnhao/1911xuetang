package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.QQLoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_back)
    ImageView back;//返回按钮
    Context mContext;
    @BindView(R.id.qq_login)
    LinearLayout qqLogin;
    @BindView(R.id.login_wxLogin)
    LinearLayout wxLogin;
    private Tencent mTencent;
    private UserInfo mInfo = null;
    private String openId;
    private IWXAPI iwxapi;
    public static int jump = 0;

    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
            QqLogin(openId);
        } catch (Exception e) {
        }
    }


    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return //     * @throws MalformedURLException
     */
    public static Bitmap getbitmap(String imageUri) {
        LogUtil.LogD("tag", "getbitmap:" + imageUri);
        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

            LogUtil.LogD("tag", "image download finished." + imageUri);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap = null;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.LogD("tag", "getbitmap bmp fail---");
            bitmap = null;
        }
        return bitmap;
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
//                    Message msg = new Message();
//                    msg.obj = response;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            if (json.has("figureurl")) {
                                Bitmap bitmap = null;
                                try {
                                    bitmap = getbitmap(json.getString("figureurl_qq_2"));
                                } catch (JSONException e) {

                                }

                                LogUtil.LogD("tag", "bitmap" + bitmap.getConfig());
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
//            mUserInfo.setText("");
//            mUserInfo.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            LogUtil.LogD("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
//            updateLoginButton();
        }
    };

    /**
     * qq登录回调接口
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                UtilToast.showToast(mContext, "返回为空登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                UtilToast.showToast(mContext, "返回为空登录失败");
                return;
            }
//            Util.showResultDialog(MainActivity.this, response.toString(), "登录成功");
            UtilToast.showToast(mContext, "登录成功" + response.toString());
            // 有奖分享处理
//            handlePrizeShare();
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            LogUtil.LogD("tag", "onError: " + e.errorDetail);
//            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
//            Util.toastMessage(MainActivity.this, "onCancel: ");
            UtilToast.showToast(mContext, "用户取消");
//            Util.dismissDialog();
//            if (isServerSideLogin) {
//                isServerSideLogin = false;
//            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        jump = getIntent().getIntExtra("jump",0);
        initView();


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void login() {
//        mTencent = Tencent.createInstance(AppId, this.getApplicationContext());
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance("222222", this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
        }
    }


    /**
     * 初始化布局
     */
    private void initView() {
        mContext = this;
        iwxapi = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        iwxapi.registerApp(Constant.WX_APP_ID);
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();//QQ登录
            }
        });
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            EventBus.getDefault().post(new EditMyselfInfoBus("1"));
//            EventBus.getDefault().post(new StudyBus("1"));
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @OnClick({R.id.login_back, R.id.login_register, R.id.login_pass_login, R.id.login_note_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back://返回
                finish();
                break;
            case R.id.login_register://注册
                JumpUtil.jump(mContext, RegisterActivity.class);
                break;
            case R.id.login_pass_login://密码登录
                Intent it = new Intent(mContext,PassLoginActivity.class);
                it.putExtra("type",1);//1是密码登录
                startActivity(it);
                break;
            case R.id.login_note_login://短信登录
                Intent it1 = new Intent(mContext,PassLoginActivity.class);
                it1.putExtra("type",2);//1是密码登录
                startActivity(it1);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
    }


    /**
     * qq登录 判断是否绑定手机号
     *
     * @param openId
     */
    public void QqLogin(String openId) {
        OkGo.<QQLoginEntity>post(Constant.THIRD_PART_USER)
                .tag(this)
                .params("openid", openId)
                .params("type", 2)
                .execute(new JsonCallback<QQLoginEntity>() {
                    @Override
                    public void onSuccess(Response<QQLoginEntity> response) {
                        QQLoginEntity data = response.body();
                        if (data.getStatus()==0) {//如果已经绑定手机号
                            UriConstant.token = data.getData().getToken();
//                            if (isPassWordLogin) {
//                                SharedPreferencesUtil.put(mContext, "userPhone", phoneNumber.getText().toString());
//                            } else {
//                                SharedPreferencesUtil.put(mContext, "userPhone", sendPhoneNumber.getText().toString());
//                            }
                            SharedPreferencesUtil.put(mContext, "userId", "" + data.getData().getToken());
                            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
                            EventBus.getDefault().postSticky(new StudyBus("1"));
                            finish();
                        } else if (data.getStatus()==100100) {
                            UtilToast.showToast(mContext, data.getMsg());
                        } else {
//                            跳转到输入验证码绑定手机号页面
                            UtilToast.showToast(mContext,"没有绑定手机号");
                        }
                    }
                });
    }
}
