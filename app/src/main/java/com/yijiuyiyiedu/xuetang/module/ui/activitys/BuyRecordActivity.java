package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.BuyRecordPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.BuyRecordAdapter;
import com.yijiuyiyiedu.xuetang.module.view.BuyRecordView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 订单管理页面
 * xeh 2018/03/23
 */

public class BuyRecordActivity extends BaseActivity implements BuyRecordView{

//    @BindView(R.id.buyRecord_tabLayout)
//    CommonTabLayout tabLayout;//tablayout
//    @BindView(R.id.buyRecord_viewPager)
//    ViewPager viewPager;//viewpager
//    String[] titles = {"课程订单", "项目订单", "会员订单"};
//    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
//    private List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.buyRecord_recyclerView)
    RecyclerView recycler;
    @BindView(R.id.buyRecord_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private List<BuyRecordEntity.DataBean.OrderListBean> list;
    BuyRecordPresenter mPresenter;
    private BuyRecordAdapter adapter;
    protected Unbinder unbinder;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_record);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.buyRecord_statuBar);
        initView();
    }


//    private void initView() {
//        /**
//         * 初始化布局
//         */
//        for (int i = 0; i < titles.length; i++) {
//            mTabEntities.add(new TabEntity(titles[i], 0, 0));
//        }
//        fragments.add(BuyRecordFragment.getInstance("1"));
//        fragments.add(BuyRecordFragment.getInstance("2"));
//        fragments.add(BuyRecordFragment.getInstance("3"));
//        ContentNormalPagerAdapter pagerAdapter = new ContentNormalPagerAdapter(getSupportFragmentManager(), fragments, titles);
//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setTabData(mTabEntities);
//        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                viewPager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//                if (position == 0) {
////                        mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
////                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
//                }
//            }
//        });
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.setCurrentTab(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//
//        viewPager.setCurrentItem(0);
//    }

    private void initView() {
        list = new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuyRecordAdapter(list);
        recycler.setAdapter(adapter);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                if (mPresenter!=null){
                    mPresenter.getBuyRecordData(UriConstant.userId, "1", "", "");
                }
            }
        });
        mPresenter = new BuyRecordPresenter(this);
        mPresenter.getBuyRecordData(UriConstant.userId, "1", "", "");
    }
//
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.buyRecord_statuBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }

    @OnClick(R.id.buyRecord_back)
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
    public void showData(BuyRecordEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            list.clear();
            list.addAll(data.getData().getOrderList());
            if (list.size() == 0) {
                View view = View.inflate(mContext, R.layout.activity_empty, null);
                TextView textView = view.findViewById(R.id.nothing_buy);
                textView.setText("您还没有购买记录信息");
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
