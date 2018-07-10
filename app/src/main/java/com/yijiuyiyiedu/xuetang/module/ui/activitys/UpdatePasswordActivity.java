package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.UpdatePassPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.UpdatePassView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/22.
 * 更改密码
 */

public class UpdatePasswordActivity extends BaseActivity implements UpdatePassView {
    @BindView(R.id.updatePass_back)
    ImageView updatePassBack;
    @BindView(R.id.edit_oldPass)
    MyClearEditText editOldPass;
    @BindView(R.id.edit_newPass)
    MyClearEditText editNewPass;
    @BindView(R.id.edit_newPassReal)
    MyClearEditText editNewPassReal;
    @BindView(R.id.update_save)
    TextView updateSave;
    UpdatePassPresenter mPresenter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }


    private void initView() {
        mPresenter = new UpdatePassPresenter(this);
        updatePassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.update_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(ClearHistoryEntity data) {
        if (data.getStatus()==0) {
            UtilToast.showToast(this, data.getMsg());
            JumpUtil.jump(mContext,PassLoginActivity.class);
            UriConstant.clear();
            SharedPreferencesUtil.put(mContext, "token", "");
            WindowUtils.hintKeyboard((Activity) mContext);
            HttpHeaders headers = new HttpHeaders();
            headers.put("Authorization", SharedPreferencesUtil.getStringValue(getApplicationContext(),"token"));
            OkGo.getInstance()                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                    .addCommonHeaders(headers);//更新请求头
            EventBus.getDefault().postSticky(new StudyBus("1"));
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
            EventBus.getDefault().postSticky(new MyCourseBus("1"));//通知课程页面刷新数据
            finish();
        } else {
            UtilToast.showToast(this, data.getMsg());
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick(R.id.update_save)
    public void onViewClicked() {
        if (TextUtils.isEmpty(editOldPass.getText().toString())) {
            UtilToast.showToast(this, "请输入原密码");
        } else if (TextUtils.isEmpty(editNewPass.getText().toString())) {
            UtilToast.showToast(this, "请输入新密码");
        } else if (TextUtils.isEmpty(editNewPassReal.getText().toString())) {
            UtilToast.showToast(this, "请确认新密码");
        } else if (editNewPass.getText().toString().length() < 8) {
            UtilToast.showToast(this, "密码长度要大于8位");
        } else if (editNewPassReal.getText().toString().length() < 8 || editNewPassReal.getText().toString().length() > 16) {
            UtilToast.showToast(this, "密码长度要大于8位小于16位");
        } else if (!editNewPass.getText().toString().equals(editNewPassReal.getText().toString())) {
            UtilToast.showToast(this, "两次密码输入不一致");
        } else {
            mPresenter.updatePassword(UriConstant.userId, editOldPass.getText().toString(), editNewPass.getText().toString(), editNewPassReal.getText().toString());
        }
    }
}
