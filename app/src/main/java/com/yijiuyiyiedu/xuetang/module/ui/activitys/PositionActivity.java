package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.PositionBus;
import com.yijiuyiyiedu.xuetang.module.entity.PositionEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.PositionPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.PositionAdapter;
import com.yijiuyiyiedu.xuetang.module.view.PositionView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/8.
 * 职业列表
 */

public class PositionActivity extends BaseActivity implements PositionView{
    @BindView(R.id.position_back)
    ImageView back;
    @BindView(R.id.position_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.position_save)
    TextView save;
    PositionPresenter mPresenter;
    private ArrayList<PositionEntity.DataBean> list;
    private PositionAdapter mAdapter;
    private Context mContext;
    private String userJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        ButterKnife.bind(this);
        userJob = getIntent().getStringExtra("job");
        mContext = this;
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        mAdapter = new PositionAdapter(R.layout.item_position,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        mPresenter = new PositionPresenter(this);
        mPresenter.getPositionData();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PositionEntity.DataBean item = (PositionEntity.DataBean) adapter.getItem(position);
                ArrayList<PositionEntity.DataBean> dataBeans = (ArrayList<PositionEntity.DataBean>) adapter.getData();
                for (int i = 0;i<dataBeans.size();i++){
                    dataBeans.get(i).setCheck(false);
                }
                item.setCheck(true);
                mAdapter.notifyDataSetChanged();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PositionEntity.DataBean> dataBeans = (ArrayList<PositionEntity.DataBean>) mAdapter.getData();
                int position = 0;
                for (int i = 0;i<dataBeans.size();i++){
                    if (dataBeans.get(i).isCheck()){
                        position = i;
                        EventBus.getDefault().post(new PositionBus(dataBeans.get(i).getId(),dataBeans.get(i).getPosition_name()));
                        finish();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
    }

    @Override
    public void showPositionData(PositionEntity data) {
        if (data.getStatus()==0){
            mAdapter.setNewData(data.getData());
            List<PositionEntity.DataBean> items = mAdapter.getData();
            for (int i=0;i<items.size();i++){
                if (items.get(i).getPosition_name().equals(userJob)){
                    items.get(i).setCheck(true);
                    mAdapter.notifyItemChanged(i);
                }
            }

        }
    }
}
