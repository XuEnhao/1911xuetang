package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.alipay.AlipayResultActivity;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.AliPayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PayResult;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.WXPayEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.PayPresenter;
import com.yijiuyiyiedu.xuetang.module.view.PayView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.beecloud.BCPay;
import cn.beecloud.async.BCCallback;
import cn.beecloud.async.BCResult;
import cn.beecloud.entity.BCPayResult;
import cn.beecloud.entity.BCReqParams;

/**
 * Created by xuenhao on 2018/3/23.
 * 支付页面
 */

public class PayActivity extends BaseActivity implements PayView {
    @BindView(R.id.sonAccount_back)
    ImageView accountBack;
    @BindView(R.id.pay_courseName)
    TextView courseName;
    @BindView(R.id.pay_orderPhone)
    TextView orderPhone;//订单号
    @BindView(R.id.pay_price)
    TextView payPrice;
    @BindView(R.id.pay_aliPay)
    CheckBox aliPayCheck;
    @BindView(R.id.pay_weChatPay)
    CheckBox weChatCheck;
    Context mContext;
    @BindView(R.id.pay_aliPayLinear)
    LinearLayout AliPayLinear;
    @BindView(R.id.pay_weChatLinear)
    LinearLayout WeChatLinear;
    @BindView(R.id.pay_buyCourse)
    TextView buyCourse;
    @BindView(R.id.pay_positive)
    StateButton payPositive;
    private IWXAPI api;
    private int orderId;
    PayPresenter mPresenter;
    private String toastMsg;
    public static int type = 0;// 1 课程 2 项目 3充值
    /**
     * 支付宝
     */
    private String orderInfo = "";// 获取到的充值信息，金额。订单信息等。
    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult(
                            (Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //需要查询一下支付结果
                        UtilToast.showToast(mContext, "支付成功");
                        EventBus.getDefault().post(new StudyBus("1"));
                        Intent it = new Intent(mContext, AlipayResultActivity.class);
                        it.putExtra("pay", type);
                        startActivity(it);
                    } else {
                        UtilToast.showToast(mContext, "支付失败");
                        LogUtil.LogD("tag", "handleMessage: " + payResult.getMemo());
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 聚合支付 回调
     */
    //支付结果返回入口
    BCCallback bcCallback = new BCCallback() {
        @Override
        public void done(final BCResult bcResult) {
            final BCPayResult bcPayResult = (BCPayResult) bcResult;
            //此处关闭loading界面
//            loadingDialog.dismiss();

            //根据你自己的需求处理支付结果
            String result = bcPayResult.getResult();

            /*
              注意！
              所有支付渠道建议以服务端的状态金额为准，此处返回的RESULT_SUCCESS仅仅代表手机端支付成功
            */
            Message msg = mHandler.obtainMessage();
            //单纯的显示支付结果
            msg.what = 2;
            if (result.equals(BCPayResult.RESULT_SUCCESS)) {
                toastMsg = "用户支付成功";
            } else if (result.equals(BCPayResult.RESULT_CANCEL)) {
                toastMsg = "用户取消支付";
            } else if (result.equals(BCPayResult.RESULT_FAIL)) {
                toastMsg = "支付失败, 原因: " + bcPayResult.getErrCode() +
                        " # " + bcPayResult.getErrMsg() +
                        " # " + bcPayResult.getDetailInfo();

                /*
                 * 你发布的项目中不应该出现如下错误，此处由于支付宝政策原因，
                 * 不再提供支付宝支付的测试功能，所以给出提示说明
                 */
                if (bcPayResult.getErrMsg().equals("PAY_FACTOR_NOT_SET") &&
                        bcPayResult.getDetailInfo().startsWith("支付宝参数")) {
                    toastMsg = "支付失败：由于支付宝政策原因，故不再提供支付宝支付的测试功能，给您带来的不便，敬请谅解";
                }

                /*
                 * 以下是正常流程，请按需处理失败信息
                 */
                LogUtil.LogD("PayActivity", toastMsg);

            } else if (result.equals(BCPayResult.RESULT_UNKNOWN)) {
                //可能出现在支付宝8000返回状态
                toastMsg = "订单状态未知";
            } else {
                toastMsg = "invalid return";
            }

            mHandler.sendMessage(msg);

            /*
            if (bcPayResult.getId() != null) {
                //你可以把这个id存到你的订单中，下次直接通过这个id查询订单
                Log.w(TAG, "bill id retrieved : " + bcPayResult.getId());

                //根据ID查询，此处只是演示如何通过id查询订单，并非支付必要部分
                getBillInfoByID(bcPayResult.getId());
            }
            */
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//支付宝沙箱环境
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.sonAccount_statusBar);
        mContext = this;
        initPayData();
        initView();
    }

    private void initPayData() {
        // 如果用到微信支付，在用到微信支付的Activity的onCreate函数里调用以下函数.
        // 第二个参数需要换成你自己的微信AppID.
        String initInfo = BCPay.initWechatPay(this, Constant.WX_APP_ID);
    }

    private void initView() {
        orderId = getIntent().getIntExtra("orderId", 0);
        type = getIntent().getIntExtra("type", 0);//  1 课程 2 项目 3充值
        if (type==1){
            buyCourse.setText("购买课程:");
        }else if (type==2){
            buyCourse.setText("购买项目:");
        }else{
            buyCourse.setText("充值:");
        }
        mPresenter = new PayPresenter(this);
        mPresenter.getOrderData(String.valueOf(orderId));

        // 支付宝 checkbox
        aliPayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weChatCheck.setChecked(false);
                }
            }
        });
        //微信 checkbox
        weChatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aliPayCheck.setChecked(false);
                }
            }
        });
        accountBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });

        AliPayLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPayCheck.setChecked(true);
                weChatCheck.setChecked(false);
            }
        });
        WeChatLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPayCheck.setChecked(false);
                weChatCheck.setChecked(true);
            }
        });
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.sonAccount_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @OnClick(R.id.pay_positive)
    public void onViewClicked() {
        if (aliPayCheck.isChecked()) {
//            UtilToast.showToast(this, "选中支付宝");
            mPresenter.getAliPayData(String.valueOf(orderId));
        } else {
//            UtilToast.showToast(this, "选中微信");
            mPresenter.getWxPayData(String.valueOf(orderId));
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 吊起微信
     *
     * @param data 数据源
     */
    @Override
    public void showData(WXPayEntity data) {
        if (data.getStatus().equals("1")) {
            api = WXAPIFactory.createWXAPI(this, "wx1e93e301d703d535");
            api.registerApp("wx1e93e301d703d535");
            PayReq req = new PayReq();
            WXPayEntity.DataBean dataBean = data.getData();
            req.appId = dataBean.getAppId();//微信appid
            req.partnerId = "1501374291";//商户id
            req.prepayId = dataBean.getPrepay_id().toString();//预支付交易会话ID
            req.packageValue = dataBean.getPackageX().toString();//扩展字段
            req.nonceStr = dataBean.getNonce_str().toString();//随机字符串
            req.timeStamp = dataBean.getTimeStamp().toString();//时间戳
            req.sign = dataBean.getSign().toString();//签名
            req.signType = "MD5";//类型
            Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req);
//            wxPay();

        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }

    }

    /**
     * 聚合支付 微信
     */
    private void wxPay() {
        //对于微信支付, 手机内存太小会有OutOfResourcesException造成的卡顿, 以致无法完成支付
        //这个是微信自身存在的问题
        Map<String, String> mapOptional = new HashMap<String, String>();

        mapOptional.put("testkey1", "测试value值1");

        if (BCPay.isWXAppInstalledAndSupported() &&
                BCPay.isWXPaySupported()) {

            BCPay.PayParams payParams = new BCPay.PayParams();
            payParams.channelType = BCReqParams.BCChannelTypes.WX_APP;
            payParams.billTitle = "安卓微信支付测试";   //订单标题
            payParams.billTotalFee = 11;    //订单金额(分)
            payParams.billNum = "111111";  //订单流水号
            payParams.couponId = "bbbf835d-f6b0-484f-bb6e-8e6082d4a35f";    // 优惠券ID
            payParams.optional = mapOptional;            //扩展参数(可以null)

            BCPay.getInstance(this).reqPaymentAsync(
                    payParams,
                    bcCallback);            //支付完成后回调入口

        } else {
            Toast.makeText(this,
                    "您尚未安装微信或者安装的微信版本不支持", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 吊起支付宝
     *
     * @param data
     */
    @Override
    public void showAliPayData(AliPayEntity data) {
        if (data.getStatus().equals("1")) {
            UtilToast.showToast(mContext, data.getMsg());
            final String orderInfo = data.getData().get(0);   // 订单信息

            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(PayActivity.this);
                    String substring = orderInfo;
                    Map<String, String> result = alipay.payV2(substring, true);

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();


//            aliPay();
        }
    }

    /**
     * 聚合支付 支付宝
     */
    private void aliPay() {
        Map<String, String> mapOptional = new HashMap<>();
//                        mapOptional.put("disable_pay_channels", "balance,creditCard");

        BCPay.PayParams aliParam = new BCPay.PayParams();
        aliParam.channelType = BCReqParams.BCChannelTypes.ALI_APP;
        aliParam.billTitle = "测试";
        aliParam.billTotalFee = 1;
        aliParam.billNum = "111111";
        aliParam.optional = mapOptional;

        BCPay.getInstance(this).reqPaymentAsync(
                aliParam, bcCallback);
    }

    @Override
    public void showWxqueryData() {

    }

    @Override
    public void showAliPayqueryData() {

    }

    @Override
    public void showOrderData(PayEntity data) {
        if (data.getStatus().equals("1")) {
            orderPhone.setText(data.getData().getOrder_sn());
            payPrice.setText(data.getData().getOrder_amount());
            courseName.setText(data.getData().getTitle());
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
