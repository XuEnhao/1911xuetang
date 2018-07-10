package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.AddSonAccountBus;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.EditSonAccountPresenter;
import com.yijiuyiyiedu.xuetang.module.view.EditSonAccountView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/22.
 * 添加子账号
 */

public class EditSonAccountActivity extends BaseActivity implements EditSonAccountView {

    @BindView(R.id.editSon_back)
    TextView back;//返回
    @BindView(R.id.son_companyName)
    TextView companyName;//公司名字
    @BindView(R.id.son_fillAccount)
    EditText account;//账号
    @BindView(R.id.son_initPass)
    EditText initPass;//密码
    @BindView(R.id.son_fillStaffName)
    EditText staffName;//员工姓名
    @BindView(R.id.son_fillStaffJob)
    EditText staffJob;//职位
    @BindView(R.id.son_positiveBtn)
    StateButton positiveBtn;//确认
    @BindView(R.id.son_negativeBtn)
    StateButton delBtn;//删除
    EditSonAccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_son_account);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.son_statusBar);
        getIntentData();
        initView();
    }
    private void initView() {
        mPresenter = new EditSonAccountPresenter(this);
//        EventBus.getDefault().register(this);
    }

    /**
     * 获取传guo l
     */
    private void getIntentData() {
        //获取传过来的公司名字
        String companyNameStr = getIntent().getStringExtra("companyName");
        companyName.setText(companyNameStr);
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.son_statusBar);
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
            EventBus.getDefault().post(new AddSonAccountBus("1"));
//            JumpUtil.jump(this, ManagerCompanySonAccountActivity.class);
        } else {
            UtilToast.showToast(this, data.getMsg());
        }
    }

    @Override
    public void showDelData(ClearHistoryEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick({R.id.son_positiveBtn, R.id.son_negativeBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.son_positiveBtn://确认
                judgeInfo();
                break;
            case R.id.son_negativeBtn:
                finish();
                break;
        }
    }

    private void judgeInfo() {
        if (TextUtils.isEmpty(account.getText().toString())) {
            UtilToast.showToast(this, "手机号不能为空");
        } else if (TextUtils.isEmpty(staffName.getText().toString())) {
            UtilToast.showToast(this, "员工姓名不能为空");
        } else if (initPass.getText().toString().length() > 14 || initPass.getText().toString().length() < 6 && initPass.getText().toString().length() != 0) {
            UtilToast.showToast(this, "密码必须是6到14位");
        } else {
            if (TextUtils.isEmpty(initPass.getText().toString())) {//如果密码等于空  传入8888
//                mPresenter.getAddSonAccountData(UriConstant.userId, account.getText().toString(), "888888", staffName.getText().toString(), staffJob.getText().toString(), "");
            } else {
//                mPresenter.getAddSonAccountData(UriConstant.userId, account.getText().toString(), initPass.getText().toString(), staffName.getText().toString(), staffJob.getText().toString(), "");
            }
        }
    }
}
