package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateBus;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EvaluateTextEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.AddEvaluatePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.EvaluateTextAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.AndroidBug5497Workaround;
import com.yijiuyiyiedu.xuetang.module.ui.custom.RatingBar;
import com.yijiuyiyiedu.xuetang.module.view.AddEvaluateView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/8.
 * 评价页面
 */

public class AddEvaluateActivity extends Activity implements AddEvaluateView, RatingBar.OnRatingChangeListener {
    @BindView(R.id.addEvaluate_evaEdit)
    EditText evaEdit;//输入框
    @BindView(R.id.addEvaluate_ratingBar)
    RatingBar ratingBar;//星级
    @BindView(R.id.addEvaluate_evaBtn)
    TextView evaBtn;//确认评价
    AddEvaluatePresenter mPresenter;
    @BindView(R.id.addEvaluate_evaText)
    TextView evaNumber;
    @BindView(R.id.addEvaluate_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.addEvaluate_grade)
    TextView ratingTe;//评分
    private Context mContext;
    private float grade = 0;//评分
    private String courseId;
    private ArrayList<EvaluateTextEntity> list;
    private EvaluateTextAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluate);
        ButterKnife.bind(this);
        AndroidBug5497Workaround.assistActivity(this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mContext = this;
        courseId = getIntent().getStringExtra("courseId");
        initView();
    }


    /**
     * 初始化布局
     */
    private void initView() {
        //手动设置maxLength为20
        InputFilter[] filters = {new InputFilter.LengthFilter(100)};
        evaEdit.setFilters(filters);
        evaEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                evaNumber.setText(s.length() + "/100");
            }
        });
        mPresenter = new AddEvaluatePresenter(this);
        ratingBar.setClickable(true);
        ratingBar.setStar(0f);
        ratingTe.setText("0.0分");
        ratingBar.setOnRatingChangeListener(this);

        list = new ArrayList<>();
        list.add(new EvaluateTextEntity("内容精彩",true));
        list.add(new EvaluateTextEntity("内容生涩",false));
        list.add(new EvaluateTextEntity("音质不好",false));
        list.add(new EvaluateTextEntity("讲解详细",false));
        list.add(new EvaluateTextEntity("很有帮助",false));
        list.add(new EvaluateTextEntity("点赞老师",false));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        mAdapter = new EvaluateTextAdapter(R.layout.item_evaluate_check,list);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<EvaluateTextEntity> items = (ArrayList<EvaluateTextEntity>) adapter.getData();
                for (int i=0;i<items.size();i++){
                    if (i==position){
                        if (items.get(i).isCheck()){
                            items.get(i).setCheck(false);
                        }else{
                            items.get(i).setCheck(true);
                        }
                    }else{
                        items.get(i).setCheck(false);
                    }
                }
                EvaluateTextEntity item = (EvaluateTextEntity) adapter.getItem(position);
//                if (item.isCheck()){
//                    item.setCheck(false);
//                }else{
//                    item.setCheck(true);
//                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }


    @OnClick(R.id.addEvaluate_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(AddEvaluateEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void showAddEvaluateData(AddEvaluateEntity data) {
        if (data.getStatus().equals("0")) {
            UtilToast.showToast(mContext, data.getMsg());
            EventBus.getDefault().postSticky(new AddEvaluateBus("1"));
            JumpUtil.overiderAnimsition((Activity) mContext);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }

    }

    @OnClick({R.id.addEvaluate_back, R.id.addEvaluate_evaBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addEvaluate_back://返回按钮
                JumpUtil.overiderAnimsition((Activity) mContext);
                break;
            case R.id.addEvaluate_evaBtn://确认评价
                judgeContent();
                break;
        }
    }

    /**
     * 检查内容
     */
    private void judgeContent() {
        ArrayList<EvaluateTextEntity> items = (ArrayList<EvaluateTextEntity>) mAdapter.getData();
        String evaContent = null;
        for (int i=0;i<items.size();i++){
            if (items.get(i).isCheck()){
                evaContent = "#"+items.get(i).getName();
            }
        }
        if (grade == 0) {
            UtilToast.showToast(mContext, "请选择评分");
        }else if (evaContent==null&&TextUtils.isEmpty(evaEdit.getText().toString())){
            UtilToast.showToast(mContext, "你输入的内容为空，请重新输入，或选择标签");
        }else {
            mPresenter.getAddEvaluateData(courseId, evaContent+evaEdit.getText().toString(), String.valueOf(grade), UriConstant.token);
        }
    }

    @Override
    public void onRatingChange(float ratingCount) {
        grade = ratingCount;
        ratingTe.setText(ratingCount+"分");
//        ratingTe.setText(ratingCount + "分");
    }
}
