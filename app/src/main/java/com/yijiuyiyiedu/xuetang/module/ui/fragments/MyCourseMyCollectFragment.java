package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.yijiuyiyiedu.xuetang.module.entity.DelCollectCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CollectPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.MyCollectAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SearchNoneAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.TestAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.CollectView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/6/4.
 * 收藏列表
 */

public class MyCourseMyCollectFragment extends BaseFragment implements CollectView {
    @BindView(R.id.fragment_myCourse_myCollect_recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    MyCollectAdapter mAdapter;
    protected CollectPresenter mPresenter;
    boolean isLoad = false;
    @BindView(R.id.fragment_myCourse_myCollect_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.fragment_myCourse_myCollect_recommendRecyclerView)
    RecyclerView recommendRecyclerView;//推荐课程
    @BindView(R.id.fragment_myCourse_myCollect_exchange)
    LinearLayout exchange;//换一批
    private List<CollectEntity.DataBean.UserCollectionBean> list;//收藏的集合
    private ArrayList<SearchNoneEntity.DataBean> searchNoneList;//推荐集合
    private int page = 1;
    private TextView emptyText;
    private SearchNoneAdapter searchNoneAdapter;//推荐课程
    private ImageView emptyImg;
    private TextView emptyLogin;
    private View view;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mycourse_mycollect;
    }

    /**
     * 删除收藏的课程
     * @param data
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void delCourse(DelCollectCourseBus data) {
        mPresenter.delCollectData(data.getCurriculumId());
    }

    /**
     * 获取收藏课程列表
     * @param item
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEditMyselfInfoBus(MyCourseBus item) {
        if (mPresenter != null) {
            page = 1;
            mPresenter.getCollectData(page, 6);
        }
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            list = new ArrayList<>();
            searchNoneList = new ArrayList<>();
            mPresenter = new CollectPresenter(this);
            mPresenter.getCollectData(page, 6);
            mAdapter = new MyCollectAdapter(R.layout.item_my_collect, list);//收藏列表
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setNestedScrollingEnabled(false);//滑动粘连
            recommendRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
            mAdapter.openLoadAnimation();
            recyclerView.setAdapter(mAdapter);
            view = View.inflate(mContext, R.layout.activity_my_course_empty, null);
            emptyText = view.findViewById(R.id.empty_myCourse_text);
            emptyImg = view.findViewById(R.id.empty_myCourse_img);
            emptyLogin = view.findViewById(R.id.empty_myCourse_login);
            emptyImg.setImageResource(R.mipmap.collect_empty);
            if (SharedPreferencesUtil.selectUserInfo(mContext)){
                emptyText.setText("抱歉,暂无收藏课程");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("重新加载");
            }else{
                emptyText.setText("登录后才能收藏哦");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("登录");
            }

            mAdapter.setEmptyView(view);
            refreshLayout.setOverScrollBottomShow(false);
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setHeaderView(new LoadView(mContext));
            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    super.onRefresh(refreshLayout);
                    mPresenter.getCollectData(page, 6);
                    getNoneData();//换一批
                }
            });
            //推荐课程
            recommendRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            searchNoneAdapter = new SearchNoneAdapter(R.layout.item_new_course_new_course, searchNoneList);
            searchNoneAdapter.openLoadAnimation();//alpha动画
            recommendRecyclerView.setAdapter(searchNoneAdapter);
            getNoneData();//获取推荐数据
            exchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getNoneData();//换一批
                }
            });
            //推荐课监听
            searchNoneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    SearchNoneEntity.DataBean item = (SearchNoneEntity.DataBean) adapter.getItem(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId",item.getId());
                    startActivity(it);
                }
            });
            //空布局 去登录
            emptyLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (emptyLogin.getText().toString().equals("登录")){
                        JumpUtil.jump(mContext, PassLoginActivity.class);
                    }else{
                        if (mPresenter != null) {
                            mPresenter.getCollectData(page, 6);
//                            getNoneData();//获取推荐数据
                        }
                    }
                }
            });
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    CollectEntity.DataBean.UserCollectionBean item = (CollectEntity.DataBean.UserCollectionBean) adapter.getItem(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId",item.getId());
                    startActivity(it);
                }
            });
            EventBus.getDefault().register(this);
            isLoad = true;
        }
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
    public void showData(ClearHistoryEntity data) {
        if (data.getStatus() == 0) {
            page = 1;
            mPresenter.getCollectData(page, 6);
        }
    }

    @Override
    public void showCollectData(CollectEntity data) {
        refreshLayout.finishRefreshing();
        if (data.getStatus()==0) {
            if (SharedPreferencesUtil.selectUserInfo(mContext)){
                emptyText.setText("抱歉,暂无收藏课程");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("重新加载");
            }else{
                emptyText.setText("登录后才能收藏哦");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("登录");
            }
            mAdapter.setEmptyView(view);
            mAdapter.setNewData(data.getData().getUserCollection());
        } else {
            if (SharedPreferencesUtil.selectUserInfo(mContext)){
                emptyText.setText("抱歉,暂无收藏课程");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("重新加载");
            }else{
                emptyText.setText("登录后才能收藏哦");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("登录");
            }
            mAdapter.setEmptyView(view);
            list.clear();
            mAdapter.setNewData(list);
        }
    }

    /**
     * 获取收藏下边的课程推荐
     */
    private void getNoneData(){
        OkGo.<SearchNoneEntity>get(Constant.SEARCH_NONE)
                .execute(new JsonCallback<SearchNoneEntity>() {
                    @Override
                    public void onSuccess(Response<SearchNoneEntity> response) {
                        SearchNoneEntity body = response.body();
                        if (body.getStatus() == 0) {
                            searchNoneAdapter.setNewData(body.getData());
                        }
                    }
                });
    }
    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
