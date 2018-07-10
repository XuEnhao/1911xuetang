package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.NewBaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CustomProjectContentPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.AndroidBug5497Workaround;
import com.yijiuyiyiedu.xuetang.module.view.CustomProjectContentView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/9.
 * 定制项目页面
 */

public class CustomProjectContentActivity extends Activity implements CustomProjectContentView {

    @BindView(R.id.customProjectContent_statusBar)
    View statusBar;
    @BindView(R.id.customProjectContent_back)
    ImageView back;//返回
    @BindView(R.id.customProjectContent_submit)
    TextView submit;//提交
    @BindView(R.id.customProjectContent_name)
    EditText name;//名字
    @BindView(R.id.customProjectContent_phone)
    EditText phone;//联系方式
    @BindView(R.id.customProjectContent_need)
    EditText need;//需求
    private Context mContext;
    CustomProjectContentPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_project_content);
        ButterKnife.bind(this);
//        AndroidBug5497Workaround.assistActivity(this,true);
        mContext = this;
        setStatusBar();
        initView();
        //获取屏幕高度

    }

    private void initView() {
        mPresenter = new CustomProjectContentPresenter(this);
    }

    //导航栏
    private void setStatusBar() {
        // 设置透明导航栏
        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.customProjectContent_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
    }

    @OnClick({R.id.customProjectContent_back, R.id.customProjectContent_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.customProjectContent_back:
                JumpUtil.overiderAnimsition((Activity) mContext);
                break;
            case R.id.customProjectContent_submit://点击提交
                judgeInfo();//检查信息

        }

    }

    /**
     * 检查信息
     */
    private void judgeInfo() {
        if (TextUtils.isEmpty(name.getText().toString())) {//名字为空
            UtilToast.showToast(mContext, "名字不能为空");
        } else if (TextUtils.isEmpty(phone.getText().toString())) {//电话为空
            UtilToast.showToast(mContext, "联系电话不能为空");
        } else if (need.getText().length() == 0) {//需求为空
            UtilToast.showToast(mContext, "需求不能为空");
        } else {//请求接口
//            mPresenter.getSubmitProjectContentData(name.getText().toString(), phone.getText().toString(), need.getText().toString());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(ClearHistoryEntity data) {
        if (data.getStatus()==0) {
            UtilToast.showToast(mContext, data.getMsg());
            JumpUtil.overiderAnimsition((Activity) mContext);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
