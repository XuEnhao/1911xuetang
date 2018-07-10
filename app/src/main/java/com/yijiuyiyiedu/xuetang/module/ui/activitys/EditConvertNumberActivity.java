package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.BankCardTextWatcher;
import com.yijiuyiyiedu.xuetang.module.ui.custom.SpaceEditText;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/19.
 * 输入兑换码
 */

public class EditConvertNumberActivity extends BaseActivity {
    @BindView(R.id.addEvaluate_statusBar)
    View addEvaluateStatusBar;
    @BindView(R.id.addEvaluate_back)
    ImageView back;
    @BindView(R.id.editConvert_edit)
    SpaceEditText editText;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_convert_number);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this, R.id.addEvaluate_statusBar);
        mContext = this;
        initView();
    }


    private void initView() {
        editText.setTextChangeListener(new SpaceEditText.TextChangeListener() {
            @Override
            public void textChange(String text) {
                if(TextUtils.isEmpty(text)){
//                    bt_submit.setEnabled(false);
                    return;
                }
                editText.setSelection(text.length());
                //判断是否满足银行卡格式，注意去空格判断
            }
        });
    }

//    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.addEvaluate_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @OnClick({R.id.addEvaluate_back, R.id.editConvert_cancel, R.id.editConvert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addEvaluate_back://回退
                //收回软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                JumpUtil.overiderAnimsition(this);
                break;
            case R.id.editConvert_cancel://取消
                //收回软键盘
                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm1 != null) {
                    imm1.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                JumpUtil.overiderAnimsition(this);
                break;
            case R.id.editConvert_confirm://确定
                JudgeData();//检查数据
                break;
        }
    }

    private void JudgeData() {
        String trim = editText.getText().toString().trim();
        String s = trim.replaceAll(" ", "");
        if (TextUtils.isEmpty(editText.getText().toString())){
            UtilToast.showToast(mContext,"兑换码不能为空");
        }else if (s.length()!=16){
            Log.d("tag", "JudgeData: "+s.length());
            UtilToast.showToast(mContext,"兑换码格式不正确");
        }else{
//            trim.substring();
//            int postion = trim.indexOf("%20");
//            int length = 3;
//            int Length = trim.length();
//            String newString = trim.substring(0,postion) + trim.substring(postion + length, Length);
//            OkGo.<ClearHistoryEntity>post(Constant.CONVERT_COURSE)
//                    .tag(this)
//                    .params("user_id", UriConstant.userId)
//                    .params("code",s.toString())
//                    .execute(new JsonCallback<ClearHistoryEntity>() {
//                        @Override
//                        public void onSuccess(Response<ClearHistoryEntity> response) {
//                            if (response.body().getStatus().equals("1")){
//                                JumpUtil.overiderAnimsition((Activity) mContext);
//                            }
//                            UtilToast.showToast(mContext,response.body().getMsg());
//                        }
//                    });
        }
    }
}
