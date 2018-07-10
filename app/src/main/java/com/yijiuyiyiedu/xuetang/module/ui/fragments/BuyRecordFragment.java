package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordBus;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.BuyRecordPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.BuyRecordAdapter;
import com.yijiuyiyiedu.xuetang.module.view.BuyRecordView;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/4/23.
 * 购买记录
 */

public class BuyRecordFragment extends BaseFragment implements BuyRecordView {
    @BindView(R.id.buyRecord_recyclerView)
    RecyclerView recycler;
    @BindView(R.id.buyRecord_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private List<BuyRecordEntity.DataBean.OrderListBean> list;
    BuyRecordPresenter mPresenter;
    private BuyRecordAdapter adapter;
    Unbinder unbinder;
    private boolean isLoad = false;
    int type = 0;//1,课程2，项目，3会员)

    public static BuyRecordFragment getInstance(String type){
        BuyRecordFragment fragment = new BuyRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_buy_record;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBuyRecordBus(BuyRecordBus bus){
        if (mPresenter!=null){
            mPresenter.getBuyRecordData(UriConstant.userId, getArguments().getString("type"), "", "");
        }
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad){
            list = new ArrayList<>();
            recycler.setLayoutManager(new LinearLayoutManager(mContext));
            adapter = new BuyRecordAdapter(list);
            recycler.setAdapter(adapter);
            refreshLayout.setOverScrollBottomShow(false);
            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    super.onRefresh(refreshLayout);
                    mPresenter.getBuyRecordData(UriConstant.userId, getArguments().getString("type"), "", "");
                }
            });
            mPresenter = new BuyRecordPresenter(this);
            type = Integer.parseInt(getArguments().getString("type"));
            mPresenter.getBuyRecordData(UriConstant.userId, getArguments().getString("type"), "", "");
            isLoad=true;
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(BuyRecordEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            list.clear();
            list.addAll(data.getData().getOrderList());
            if (list.size() == 0) {
                View view = View.inflate(mContext, R.layout.activity_empty, null);
                TextView textView = view.findViewById(R.id.nothing_buy);
                if (type==1){
                    textView.setText("您还没有购买课程");
                }else if (type==2){
                    textView.setText("您还没有购买项目");
                }else{
                    textView.setText("您还没有购买会员");
                }
                adapter.setEmptyView(view);
            } else {
                adapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
