package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.rtmp.downloader.ITXVodDownloadListener;
import com.tencent.rtmp.downloader.TXVodDownloadManager;
import com.tencent.rtmp.downloader.TXVodDownloadMediaInfo;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.NewSwipeBackActivity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.utils.DataCleanManager;
import com.yijiuyiyiedu.xuetang.utils.GoMarketUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowWindow;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/21.
 * 设置页面
 */

public class SettingsActivity extends NewSwipeBackActivity {

    @BindView(R.id.settings_back)
    ImageView Back;
    @BindView(R.id.settings_updatePassword)
    LinearLayout updatePassword;
    @BindView(R.id.settings_editUserInfo)
    LinearLayout editUserInfo;
    @BindView(R.id.settings_quit)
    LinearLayout quit;
    Context mContext;
    @BindView(R.id.settings_downloadText)
    TextView downloadText;//下载清晰度 默认高清
    @BindView(R.id.settings_download)
    LinearLayout download;//下载清晰度
    @BindView(R.id.settings_mine)
    LinearLayout settingsMine;//关于我们
    @BindView(R.id.settings_app)
    LinearLayout settingsApp;//关于产品
    @BindView(R.id.settings_cacheText)
    TextView cacheText;//缓存
    @BindView(R.id.settings_cache)
    LinearLayout cache;//缓存
    @BindView(R.id.settings_goStar)
    LinearLayout goStar;//去评分
    private TXVodDownloadManager manager;//下载管理类
    /**
     * 判断当前存储卡是否可用
     **/
    public boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        //清晰度
        final UtilsShowPopup popup = new UtilsShowPopup();
        final View view = View.inflate(mContext, R.layout.popupwindow_clarity, null);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showBottomPopup(mContext, getWindow(), view, findViewById(R.id.settings_scrollView));
            }
        });
        view.findViewById(R.id.man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadText.setText("高清");
                popup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadText.setText("流畅");
                popup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
            }
        });
        try {
            //获取缓存大小
            String totalCacheSize = DataCleanManager.getTotalCacheSize(mContext);
            cacheText.setText(""+totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //清除缓存
        final View view1 = View.inflate(mContext, R.layout.popupwindow_clear_cache, null);
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showBottomPopup(mContext, getWindow(), view1, findViewById(R.id.settings_scrollView));
            }
        });
        view1.findViewById(R.id.man).setOnClickListener(new View.OnClickListener() {
            //做操作
            @Override
            public void onClick(View v) {
                DataCleanManager.clearAllCache(mContext);
                cacheText.setText("0M");
                popup.popupWindow.dismiss();
            }
        });

        view1.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
            }
        });

        settingsMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext,AboutUsActivity.class);
            }
        });
        //去评分
        goStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoMarketUtils.goToMarket(mContext,"111");
            }
        });



    }


    @OnClick({R.id.settings_back, R.id.settings_editUserInfo, R.id.settings_updatePassword, R.id.settings_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.settings_back:
                JumpUtil.overiderAnimsition(this);
                break;
            case R.id.settings_editUserInfo://编辑用户信息
                JumpUtil.jump(this, UserInfoActivity.class);
                break;
            case R.id.settings_updatePassword://修改密码
                JumpUtil.jump(this, UpdatePasswordActivity.class);
                break;
            case R.id.settings_quit://点击退出
                UtilsShowWindow.showDialog(this, "退出？", "确定退出吗?", "确定", "取消",
                        new DialogInterface.OnClickListener() {//确定
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferencesUtil.put(mContext, "token", null);
                                UriConstant.clear();
                                HttpHeaders headers = new HttpHeaders();
                                headers.put("Authorization", SharedPreferencesUtil.getStringValue(getApplicationContext(),"token"));
                                OkGo.getInstance()                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                                        .addCommonHeaders(headers);//更新请求头
//                                UtilToast.showToast(mContext, "退出成功");
                                EventBus.getDefault().post(new EditMyselfInfoBus("1"));
                                EventBus.getDefault().post(new StudyBus("1"));
                                EventBus.getDefault().post(new MyCourseBus("1"));
//                                JumpUtil.jump(mContext, MainActivity.class);
                                finish();
                                dialog.dismiss();
                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//取消
                                dialog.dismiss();
                            }
                        });
                break;
        }
    }

}
