package com.yijiuyiyiedu.xuetang.wxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.QQLoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.BindPhoneActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    @BindView(R.id.pay_result_statusBar)
    View payResultStatusBar;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String access_token;
    private String openid;
    private Context mContext;
    private ProgressDialogUtils progressDialogUtils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.pay_result_statusBar);
//        setStatusBar();
        mContext = this;
        initView();
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        api.registerApp(Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }


    private void initView() {
        progressDialogUtils = new ProgressDialogUtils(mContext);
        progressDialogUtils.showDialog();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
//        Toast.makeText(this, "openid = " + req.openId, Toast.LENGTH_SHORT).show();

        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            case ConstantsAPI.COMMAND_LAUNCH_BY_WX:
//			Toast.makeText(this, R.string.launch_from_wx, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
//        Toast.makeText(this, "openid = " + resp.openId, Toast.LENGTH_SHORT).show();

        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
//            Toast.makeText(this, "code = " + ((SendAuth.Resp) resp).code, Toast.LENGTH_SHORT).show();
        }

        initOpenId(((SendAuth.Resp) resp).code);

        int result = 0;
        String re = "";

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
//			result = R.string.errcode_success;
                re = "登录成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//			result = R.string.errcode_cancel;
                re = "取消登录";
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                re = "异常";
//			result = R.string.errcode_deny;
                finish();
                break;
            default:
//			result = R.string.errcode_unknown;
                break;
        }

//        Toast.makeText(this, re, Toast.LENGTH_LONG).show();
    }

    private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
    }

    private void goToShowMsg(ShowMessageFromWX.Req showReq) {
        WXMediaMessage wxMsg = showReq.message;
        WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;

        StringBuffer msg = new StringBuffer(); // 组织一个待显示的消息内容
        msg.append("description: ");
        msg.append(wxMsg.description);
        msg.append("\n");
        msg.append("extInfo: ");
        msg.append(obj.extInfo);
        msg.append("\n");
        msg.append("filePath: ");
        msg.append(obj.filePath);

//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
    }

    private void initOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        OkGo.<String>get(url)
                .tag(this)
                .params("appid", Constant.WX_APP_ID)
                .params("secret", Constant.WX_APP_SCERET)
                .params("code", code)
                .params("grant_type", "authorization_code")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body() != null) {
                            try {
                                JSONObject object = new JSONObject(response.body());
                                access_token = object.getString("access_token");
                                openid = object.getString("openid");
                                LogUtil.LogD("tag", "openid: " + openid);
                                WXLogin(openid);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });
    }

    /**
     * 微信登录 判断是否绑定手机号
     *
     * @param openId
     */
    public void WXLogin(final String openId) {
        OkGo.<QQLoginEntity>post(Constant.THIRD_PART_USER)
                .tag(this)
                .params("openid", openId)
                .params("type", 1)
                .execute(new JsonCallback<QQLoginEntity>() {
                    @Override
                    public void onSuccess(Response<QQLoginEntity> response) {
                        QQLoginEntity data = response.body();
                        if (data.getStatus()==0) {//如果已经绑定手机号
                            UriConstant.token = data.getData().getToken();
                            SharedPreferencesUtil.put(mContext, "token", "" + data.getData().getToken());
                            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
                            EventBus.getDefault().postSticky(new StudyBus("1"));
                            JumpUtil.jump(mContext, MainActivity.class);
                        } else if (data.getStatus()==100100) {
                            UtilToast.showToast(mContext, data.getMsg());
                        } else {
//                           获取用户信息 然后跳转绑定手机号
                            getUserInfo(access_token, openId);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mContext!=null){
            progressDialogUtils.dismissDialog();
        }
    }

    public void getUserInfo(String access_token, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        OkGo.<String>get(url)
                .tag(this)
                .params("access_token", access_token)
                .params("openid", openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject object = new JSONObject(response.body());
                            String nickname = object.getString("nickname");
                            int sex = object.getInt("sex");
                            String openid = object.getString("openid");
                            Intent it = new Intent(mContext, BindPhoneActivity.class);
                            it.putExtra("openid", openid);
                            it.putExtra("nickname", nickname);
                            it.putExtra("sex", sex);
                            it.putExtra("type", 1);//1微信 2qq
                            startActivity(it);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}