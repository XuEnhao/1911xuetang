package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Intent;
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
 * Created by xuenhao on 2018/3/23.
 * 子账户编辑
 */

public class SonAccountInfoActivity extends BaseActivity implements EditSonAccountView {

    @BindView(R.id.editSon_back)
    TextView back;
    @BindView(R.id.sonInfo_companyName)
    TextView companyNameTe;//公司名字
    @BindView(R.id.sonInfo_fillAccount)
    EditText accountTe;//账号
    @BindView(R.id.sonInfo_initPass)
    EditText passwordTe;//密码
    @BindView(R.id.sonInfo_fillStaffName)
    EditText staffNameTe;//姓名
    @BindView(R.id.sonInfo_fillStaffJob)
    EditText staffJobTe;//职位
    @BindView(R.id.sonInfo_positiveBtn)
    StateButton positiveBtn;//确认
    @BindView(R.id.sonInfo_allClearBtn)
    StateButton allClearBtn;//解除关联
    private String childId;
    EditSonAccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_account_info);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.sonInfo_statusBar);
        getIntentData();
    }


    private void getIntentData() {
//        it.putExtra("companyName",companyName);//公司名字
//        it.putExtra("childPhone",list.get(position).getUser_name());//用户名（手机号）
//        it.putExtra("childName",list.get(position).getReal_name());//员工姓名
//        it.putExtra("childPass",list.get(position).getPassword());//密码
//        it.putExtra("childJob"
        Intent it = getIntent();
        String companyName = it.getStringExtra("companyName");
        String childPhone = it.getStringExtra("childPhone");
        String childName = it.getStringExtra("childName");
        String childPass = it.getStringExtra("childPass");
        String childJob = it.getStringExtra("childJob");
        childId = it.getStringExtra("childId");
        companyNameTe.setText(companyName);
        accountTe.setText(childPhone);
        passwordTe.setText(childPass);
        staffNameTe.setText(childName);
        staffJobTe.setText(childJob);
        mPresenter = new EditSonAccountPresenter(this);
//        EventBus.getDefault().register(this);
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.sonInfo_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @OnClick({R.id.editSon_back, R.id.sonInfo_positiveBtn, R.id.sonInfo_allClearBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editSon_back:
                finish();
                break;
            case R.id.sonInfo_positiveBtn:
                //保存员工信息
                judgeInfo();
                break;
            case R.id.sonInfo_allClearBtn:
                //解除关联
//                mPresenter.setDelSonAccountData(childId);
                break;
        }
    }

    private void judgeInfo() {
        if (TextUtils.isEmpty(accountTe.getText().toString())) {
            UtilToast.showToast(this, "手机号不能为空");
        } else if (TextUtils.isEmpty(staffNameTe.getText().toString())) {
            UtilToast.showToast(this, "员工姓名不能为空");
        } else if (passwordTe.getText().toString().length() > 14 || passwordTe.getText().toString().length() < 6 && passwordTe.getText().toString().length() != 0) {
            UtilToast.showToast(this, "密码必须是6到14位");
        } else {
            if (TextUtils.isEmpty(passwordTe.getText().toString())) {//如果密码等于空  传入8888
//                mPresenter.getAddSonAccountData(UriConstant.userId, accountTe.getText().toString(), "888888", staffNameTe.getText().toString(), staffJobTe.getText().toString(), childId);
            } else {
//                mPresenter.getAddSonAccountData(UriConstant.userId, accountTe.getText().toString(), passwordTe.getText().toString(), staffNameTe.getText().toString(), staffJobTe.getText().toString(), childId);
            }
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
            UtilToast.showToast(this, data.getMsg());
            EventBus.getDefault().post(new AddSonAccountBus("1"));
//            JumpUtil.jump(this, ManagerCompanySonAccountActivity.class);
            finish();
        } else {
            UtilToast.showToast(this, data.getMsg());
        }
    }

    @Override
    public void showDelData(ClearHistoryEntity data) {
        if (data.getStatus()==0) {
            UtilToast.showToast(this, data.getMsg());
            EventBus.getDefault().post(new AddSonAccountBus("1"));
//            JumpUtil.jump(this, ManagerCompanySonAccountActivity.class);
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
}
