package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryBus;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CategorySortBus;
import com.yijiuyiyiedu.xuetang.module.entity.CategorySortEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CategoryPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CategoryFragmentAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CategorySortAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.CategoryView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SingleLoginUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/3/21.
 * 分类详情里
 */

public class CategoryFragment extends BaseFragment implements CategoryView {
    @BindView(R.id.fragment_categoryRecycler)
    RecyclerView recyclerView;
    protected Unbinder unbinder;
    CategoryPresenter mPresenter;
    @BindView(R.id.category_refreshLayout)
    TwinklingRefreshLayout swipeLayout;
    private String categoryId;//二级id
    private List<CategoryEntity.DataBean.CurriculumListBean> list;
    ArrayList<CategorySortEntity> popList = new ArrayList<>();
    private CategoryFragmentAdapter adapter;
    private boolean isLoad = false;//是否加载过数据
    private int page = 1;//页数
    private boolean isLoadMore = false;//是否加载更多
    private int isHide = 0;//是否隐藏
    private int sortBy = 1;//排序方式
    private TextView emptyText;
    private View footer;
    private Handler handler = new Handler();

    public static CategoryFragment getInstance(String category_id) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category_id", category_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCategryBus(CategoryBus event) {
        LogUtil.LogD("tag", "收到的hide:" + event.getIshide());
        isHide = event.getIshide();
        mPresenter.getData(String.valueOf(page), "6", "", "", "", String.valueOf(sortBy), "", "", categoryId, isHide);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCategorySortBus(CategorySortBus event) {
        sortBy = Integer.parseInt(event.getPosition());
        mPresenter.getData(String.valueOf(page), "6", "", "", "", String.valueOf(sortBy), "", "", categoryId, isHide);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            list = new ArrayList();
            categoryId = getArguments().getString("category_id");//分类id
            mPresenter = new CategoryPresenter(this);
            mPresenter.getData(String.valueOf(page), "6", "", "", "", String.valueOf(sortBy), "", "", categoryId, isHide);
            adapter = new CategoryFragmentAdapter(R.layout.item_new_course_new_course, list);
            final GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            footer = View.inflate(mContext, R.layout.evaluate_footer, null);
//            View headView = View.inflate(mContext, R.layout.item_category_header, null);
//            hide = headView.findViewById(R.id.category_hide);
//            adapter.addHeaderView(headView);
            View view = View.inflate(mContext, R.layout.activity_category_details_empty, null);
            emptyText = view.findViewById(R.id.empty_companyId);
            ImageView emptyImg = view.findViewById(R.id.empty_img);
            emptyText.setText("抱歉,暂无课程");
            adapter.setEmptyView(view);
            swipeLayout.setEnableRefresh(true);
            swipeLayout.setEnableLoadmore(true);
            swipeLayout.setOverScrollBottomShow(false);
            swipeLayout.setHeaderView(new LoadView(mContext));

            swipeLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page = 1;
                            mPresenter.getData(String.valueOf(page), "6", "", "", "", String.valueOf(sortBy), "", "", categoryId, isHide);
                        }
                    },1000);


                }

                @Override
                public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                    super.onLoadMore(refreshLayout);
                    isLoadMore = true;
                    mPresenter.getData(String.valueOf(++page), "6", "", "", "", String.valueOf(sortBy), "", "", categoryId, isHide);

                }
            });
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    CategoryEntity.DataBean.CurriculumListBean item =
                            (CategoryEntity.DataBean.CurriculumListBean) adapter.getItem(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", item.getId());
                    startActivity(it);
                }
            });
            isLoad = true;

        }
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
    public void showData(final CategoryEntity data) {
        swipeLayout.finishRefreshing();
        swipeLayout.finishLoadmore();
        if (data.getStatus().equals("0")) {
            if (isLoadMore) {
                isLoadMore = false;
                if (data.getData().getCurriculumList().size() < 10) {
                    adapter.setFooterView(footer);
                    swipeLayout.setEnableLoadmore(false);
                }
                adapter.addData(data.getData().getCurriculumList());
            } else {
                swipeLayout.setEnableLoadmore(true);
                adapter.setNewData(data.getData().getCurriculumList());
            }
        } else if (data.getStatus().equals("100008")) {
            SingleLoginUtils.showDialog(mContext);
        }

    }

    @Override
    public void showChildData(NewChildCategoryEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
