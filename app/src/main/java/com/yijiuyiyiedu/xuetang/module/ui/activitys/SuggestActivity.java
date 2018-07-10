package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.AndroidBug5497Workaround;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/13.
 * 反馈建议
 */

public class SuggestActivity extends BaseActivity {
    @BindView(R.id.suggest_commit)
    TextView commit;
    @BindView(R.id.suggest_edittext)
    EditText edittext;
    @BindView(R.id.suggest_textNum)
    TextView textNum;
    private Context mContext;
    private final String url = Constant.NEW_BASE_URL+"/Problem/addProblem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        mContext = this;
        AndroidBug5497Workaround.assistActivity(this, true);
        initView();
    }

    private void initView() {
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textNum.setText(s.length() + "/500");
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postQuestion();
            }
        });
    }

    @Override
    protected void onDestroy() {
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
    }

    @OnClick(R.id.suggest_back)
    public void onViewClicked() {
        WindowUtils.hintKeyboard(this);
        JumpUtil.overiderAnimsition(this);
    }

    private void postQuestion(){
        OkGo.<ClearHistoryEntity>post(url)
                .params("problem_content",edittext.getText().toString())
                .execute(new JsonCallback<ClearHistoryEntity>() {
                    @Override
                    public void onSuccess(Response<ClearHistoryEntity> response) {
                        if (response.body().getStatus()==0){
                            ToastUtils.showShort(response.body().getMsg());
                            WindowUtils.hintKeyboard((Activity) mContext);
                            finish();
                        }else{
                            ToastUtils.showShort(response.body().getMsg());
                        }
                    }
                });
    }
}
