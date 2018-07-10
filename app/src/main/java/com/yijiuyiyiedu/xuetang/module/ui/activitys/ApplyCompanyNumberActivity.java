package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/21.
 * 申请企业账号
 */

public class ApplyCompanyNumberActivity extends BaseActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_company_number);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.applyCompanyNumber_statusBar);
    }





    @OnClick({R.id.applyCompanyNumber_back, R.id.applyCompanyNumber_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.applyCompanyNumber_back:
                finish();
                break;
            case R.id.applyCompanyNumber_text:
//                JumpUtil.jump(mContext, FillRegisterDataActivity.class);
                break;
        }
    }
}
