package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.MsgManagerEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.MsgManagerPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.MsgManagerAdapter;
import com.yijiuyiyiedu.xuetang.module.view.MsgManagerView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SingleLoginUtils;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/21.
 * 我的消息
 */

public class MsgManagerActivity extends BaseActivity implements MsgManagerView {

    @BindView(R.id.msgManager_recycler)
    RecyclerView recycler;
    private List<MsgManagerEntity.DataBean.MsgListBean> list;
    private MsgManagerAdapter adapter;
    private MsgManagerPresenter mPresenter;
    Context mContext;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_manager);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }



    private void initView() {
        list = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MsgManagerAdapter(R.layout.item_msg_manager, list);
        recycler.setAdapter(adapter);
        mPresenter = new MsgManagerPresenter(this);
        mPresenter.getData(UriConstant.token, "", "");
        View view = View.inflate(mContext, R.layout.activity_course_id_empty, null);
        textView = view.findViewById(R.id.empty_companyId);
        ImageView emptyImg = view.findViewById(R.id.empty_img);
        emptyImg.setImageResource(R.mipmap.empty_msg);
        textView.setText("抱歉,暂无消息");
        adapter.setEmptyView(view);
    }



    @OnClick(R.id.settings_back)
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
    public void showData(MsgManagerEntity data) {
        if (data.getStatus()==0) {
            list.clear();
            list.addAll(data.getData().getMsgList());
            if (list.size() == 0) {
                View view = View.inflate(mContext, R.layout.activity_empty, null);
                TextView textView = view.findViewById(R.id.nothing_buy);
                textView.setText("您还没有消息通知您");
                adapter.setEmptyView(view);
            } else {
                adapter.notifyDataSetChanged();
            }
        }else if (data.getStatus()==100008){
            SingleLoginUtils.showDialog(mContext);
        }else{
            ToastUtils.showShort(data.getMsg());
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
