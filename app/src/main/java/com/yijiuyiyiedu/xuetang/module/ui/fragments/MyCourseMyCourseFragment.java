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
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.MyCurriculumPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.MyCurriculumAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SearchNoneAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.MyCurriculumView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/6/2.
 * 我的课程界面 我的课程
 */

public class MyCourseMyCourseFragment extends BaseFragment implements MyCurriculumView {
    @BindView(R.id.fragment_myCourse_myDown_downRecyclerView)
    RecyclerView downRecyclerView;//下载列表
    MyCurriculumAdapter mAdapter;
    boolean isLoad = false;
    protected MyCurriculumPresenter mPresenter;
    Unbinder unbinder;
    @BindView(R.id.fragment_myCourse_myDown_downRefreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.fragment_myCourse_myCollect_recommendRecyclerView)
    RecyclerView recommendRecyclerView;//推荐的列表
    @BindView(R.id.fragment_myCourse_myCollect_exchange)
    LinearLayout exchange;//换一批
    private SearchNoneAdapter searchNoneAdapter;//推荐课程
    private int page = 1;
    private ArrayList<SearchNoneEntity.DataBean> searchNoneList;//推荐集合
    private ArrayList<MyCurriculumEntity.DataBean.CurriculumListBean> list;
    private TextView viewById;
    private TextView emptyLogin;
    private View view;
    private ImageView emptyImg;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mycourse_mycourse;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEditMyselfInfoBus(MyCourseBus item) {
        if (mPresenter != null) {
            page = 1;
            mPresenter.getMyCurriculumData(1, page, 6);
        }
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            mPresenter = new MyCurriculumPresenter(this);
            list = new ArrayList<>();
            searchNoneList = new ArrayList<>();
            mAdapter = new MyCurriculumAdapter(R.layout.item_my_course, list);
            mAdapter.openLoadAnimation();
            downRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            downRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
            recommendRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
            mAdapter.openLoadAnimation();
            downRecyclerView.setAdapter(mAdapter);
            view = View.inflate(mContext, R.layout.activity_my_course_empty, null);
            viewById = view.findViewById(R.id.empty_myCourse_text);
            emptyImg = view.findViewById(R.id.empty_myCourse_img);
            emptyLogin = view.findViewById(R.id.empty_myCourse_login);
            if (SharedPreferencesUtil.selectUserInfo(mContext)){
                viewById.setText("抱歉,暂无课程");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("重新加载");
            }else{
                viewById.setText("登录后才能查看呦");
                emptyLogin.setVisibility(View.VISIBLE);
                emptyLogin.setText("登录");
            }
            mAdapter.setEmptyView(view);
            LogUtil.LogD("token", getClass().getSimpleName() + SharedPreferencesUtil.getStringValue(mContext, "token"));
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MyCurriculumEntity.DataBean.CurriculumListBean item = (MyCurriculumEntity.DataBean.CurriculumListBean) adapter.getData().get(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", item.getId());
                    startActivity(it);
                }
            });
            mPresenter.getMyCurriculumData(1, page, 6);
            refreshLayout.setHeaderView(new LoadView(mContext));
            refreshLayout.setOverScrollBottomShow(false);
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    super.onRefresh(refreshLayout);
                    if (mPresenter != null) {
                        mPresenter.getMyCurriculumData(1, page, 6);
                        getNoneData();//获取推荐数据
                    }
                }
            });

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
            searchNoneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    SearchNoneEntity.DataBean item = (SearchNoneEntity.DataBean) adapter.getItem(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId",item.getId());
                    startActivity(it);
                }
            });
            isLoad = true;
            //去登录
            emptyLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (emptyLogin.getText().toString().equals("登录")){
                        JumpUtil.jump(mContext, PassLoginActivity.class);
                    }else{
                        if (mPresenter != null) {
                            mPresenter.getMyCurriculumData(1, page, 6);
//                            getNoneData();//获取推荐数据
                        }
                    }
                }
            });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCourseIdBus(CourseIdBus item) {
        if (mPresenter != null) {
            page = 1;
            mPresenter.getMyCurriculumData(1, page, 6);
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
    public void showMyCurriculumData(MyCurriculumEntity data) {
        refreshLayout.finishRefreshing();
        if (SharedPreferencesUtil.selectUserInfo(mContext)){
            viewById.setText("抱歉,暂无课程");
            emptyLogin.setVisibility(View.VISIBLE);
            emptyLogin.setText("重新加载");
        }else{
            viewById.setText("登录后才能查看呦");
            emptyLogin.setVisibility(View.VISIBLE);
            emptyLogin.setText("登录");
        }
        mAdapter.setEmptyView(view);
        if (data.getStatus().equals("0")) {
            mAdapter.setNewData(data.getData().getCurriculumList());
        } else {
            list.clear();
            mAdapter.setNewData(list);
//            ToastUtils.showShort(data.getMsg());
        }
    }
}
