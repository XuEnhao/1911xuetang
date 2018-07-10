package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CoursePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.ClassicCourseActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CustomCourseActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.FriendActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.HrEnterActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.NewCourseActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.OfflineActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SchoolMsgActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SchoolMsgMoreActivty;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SearchActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.StudyProjectActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.TeacherActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.TeacherDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.AlphaScaleTransformer;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ImagePagerAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseClassicAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseFriendAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseMsgAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseTeacherAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.ui.custom.ViewPagerScroller;
import com.yijiuyiyiedu.xuetang.module.view.CourseView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.ImageHelper;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.NetWorkUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by xuenhao on 2018/3/19.
 * 新版首页课程fragment
 */

public class NewCourseFragment extends BaseFragment implements CourseView {
    @BindView(R.id.home_viewpager)
    ViewPager viewPager;//viewpager
    CoursePresenter mPresenter;
    @BindView(R.id.home_search)
    ImageView searchLinear;//搜索框
    @BindView(R.id.course_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.main_newCourseRecycler)
    RecyclerView newCourseRecyclerView;//最新好课
    @BindView(R.id.main_classicRecycler)
    RecyclerView classicRecycler;//经典好课
    @BindView(R.id.newCourse_msgRecyclerView)
    RecyclerView msgRecyclerView;//学堂资讯
    @BindView(R.id.newCourse_teacherRecyclerView)
    RecyclerView teacherRecyclerView;//名师大咖
    @BindView(R.id.newCourse_friendRecyclerView)
    RecyclerView friendRecyclerView;//合作伙伴
    @BindView(R.id.newCourse_newMore)
    TextView newMore;//新选好课 更多
    @BindView(R.id.newCourse_classicMore)
    TextView classicMore;//经典好课 更多
    @BindView(R.id.newCourse_teacherMore)
    TextView teacherMore;//名师大咖 更多
    @BindView(R.id.newCourse_friendMore)
    TextView friendMore;//合作伙伴 更多
    @BindView(R.id.newCourse_msgMore)
    TextView msgMore;//新闻资讯 更多
    @BindView(R.id.course_scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.course_otherBanner)
    BGABanner otherBanner;//最下边的banner
    @BindView(R.id.home_customCourse)
    LinearLayout customCourse;//定制课程
    @BindView(R.id.home_offline)
    LinearLayout offline;//线下活动
    @BindView(R.id.home_studyProject)
    LinearLayout studyProject;//学位项目
    @BindView(R.id.home_selectCourse)
    ImageView selectCourse;//hr入口
    private GridLayoutManager projectManager;//项目布局管理
    private GridLayoutManager newCourseManager;//最新课程布局管理
    private NewCourseAdapter newCourseAdapter;//最新课程适配器
    private NewCourseClassicAdapter classicAdapter;//惊课程适配器
    private NewCourseMsgAdapter msgAdapter;//学堂资讯
    private ImagePagerAdapter viewpagerAdapter;
    private boolean isLoad = false;
    private Gson gson;
    private NewCourseTeacherAdapter teacherAdapter;
    private NewCourseFriendAdapter friendAdapter;
    private List<CourseEntity.DataBean.NewsCurriculumListBean> newsCurriculumList;
    private List<CourseEntity.DataBean.RecommendCurriculumListBean> recommendCurriculumList;
    private List<CourseEntity.DataBean.NewsListBean> newsList;
    private List<CourseEntity.DataBean.CollaborationEnterpriseListBean> collList;
    private List<CourseEntity.DataBean.TeacherListBean> teacherList;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //如果已经有消息了，先移除消息
            if (handler.hasMessages(1)) {
                handler.removeMessages(1);
            }
            if (msg.what == 0) {

            } else if (msg.what == 1) {
                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                sendEmptyMessageDelayed(1, 4000);
            }
        }
    };
    private List<CourseEntity.DataBean.BannerListBean> bannerList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_new_course;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        newCourseRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
//        projectRecycler.setNestedScrollingEnabled(false);//滑动粘连
        classicRecycler.setNestedScrollingEnabled(false);//滑动粘连
        msgRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
        teacherRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
        friendRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0) {//如果不是在0位置就不可以下拉刷新
                    refreshLayout.setEnableRefresh(false);
                } else {
                    refreshLayout.setEnableRefresh(true);
                }
            }
        });
        if (!NetWorkUtil.judgeNetWork(mContext)) {
            NotNetWork();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 无网络
     */
    private void NotNetWork() {

        Log.d("tag", "NotNetWork: 无网络 从数据库里那东西");
        String homeCache = SharedPreferencesUtil.getStringValue(mContext, "homeCache");
        Log.d("tag", "homeCache:" + homeCache);
        gson = new Gson();
        CourseEntity dataBean = gson.fromJson(homeCache, CourseEntity.class);
        initAdapter(dataBean);
    }


    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (isLoad == false) {
            mPresenter = new CoursePresenter(this);
            mPresenter.getData();//获取数据 获取新闻资讯
//            mPresenter.getNewsData("", "");//
            refreshLayout.setEnableLoadmore(false);
            refreshLayout.setHeaderView(new LoadView(mContext));
            refreshLayout.setOverScrollBottomShow(false);//越界回弹不显示loadmore布局
            //最新课程adapter
            newCourseManager = new GridLayoutManager(mContext, 2);
            newCourseRecyclerView.setLayoutManager(newCourseManager);
            newCourseRecyclerView.setItemAnimator(new DefaultItemAnimator());
            newCourseAdapter = new NewCourseAdapter(R.layout.item_new_course_new_course, newsCurriculumList);
            // 一行代码搞定（默认为渐显效果）
            newCourseAdapter.openLoadAnimation();
            newCourseRecyclerView.setAdapter(newCourseAdapter);
            //经典好课adapter
            classicAdapter = new NewCourseClassicAdapter(R.layout.item_new_course_new_course, recommendCurriculumList);
            // 一行代码搞定（默认为渐显效果）
            classicAdapter.openLoadAnimation();
            classicRecycler.setLayoutManager(new GridLayoutManager(mContext, 2));
            classicRecycler.setAdapter(classicAdapter);
            //学堂资讯
            msgAdapter = new NewCourseMsgAdapter(R.layout.item_new_course_msg, newsList);
            msgRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            msgRecyclerView.setAdapter(msgAdapter);
            // 一行代码搞定（默认为渐显效果）
            msgAdapter.openLoadAnimation();
            //合作伙伴
            friendRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            friendAdapter = new NewCourseFriendAdapter(R.layout.item_mian_friend, collList);
            // 一行代码搞定（默认为渐显效果）
            friendAdapter.openLoadAnimation();
            friendRecyclerView.setAdapter(friendAdapter);
            //名师大咖
            teacherRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            teacherAdapter = new NewCourseTeacherAdapter(R.layout.item_mian_teacher, teacherList);
            // 一行代码搞定（默认为渐显效果）
            teacherAdapter.openLoadAnimation();
            teacherRecyclerView.setAdapter(teacherAdapter);

            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    super.onRefresh(refreshLayout);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.getData();//获取数据
                            mPresenter.getNewsData("", "");
                        }
                    },1000);

                }

                @Override
                public void onFinishRefresh() {
                    super.onFinishRefresh();
                }
            });
            isLoad = true;
            //添加搜索框监听
            searchLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, SearchActivity.class);
                }
            });

            //最新好课
            newCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", newsCurriculumList.get(position).getId());
                    startActivity(it);
                }
            });
            //经典好课
            classicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", recommendCurriculumList.get(position).getId());
                    startActivity(it);
                }
            });

            //学堂资讯
            msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, SchoolMsgActivity.class);
                    CourseEntity.DataBean.NewsListBean item = (CourseEntity.DataBean.NewsListBean) adapter.getItem(position);
                    it.putExtra("id", item.getId());//新闻id
                    startActivity(it);
                }
            });
            //合作伙伴
            friendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position == adapter.getData().size() - 1) {
                        JumpUtil.jump(mContext, FriendActivity.class);
                    }
                }
            });
            //名师大咖
            teacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    CourseEntity.DataBean.TeacherListBean item = (CourseEntity.DataBean.TeacherListBean) adapter.getItem(position);
                    Intent it = new Intent(mContext, TeacherDetailsActivity.class);
                    it.putExtra("teacherId", item.getId());
                    startActivity(it);
                }
            });

            //最新好课 更多
            newMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, NewCourseActivity.class);
                }
            });
            //经典好课 更多
            classicMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, ClassicCourseActivity.class);
                }
            });

            //合作伙伴 更多
            friendMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, FriendActivity.class);
                }
            });
            //名师大咖 更多
            teacherMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, TeacherActivity.class);
                }
            });
            //学堂咨询 更多
            msgMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(mContext, SchoolMsgMoreActivty.class);
//                it.putExtra("id", data.getData().getNewsList().get(position).getId());//新闻id
                    startActivity(it);
                }
            });
            //定制课程
            customCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, CustomCourseActivity.class);
                }
            });
            //线下活动
            offline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, OfflineActivity.class);
                }
            });
            //学位项目
            studyProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, StudyProjectActivity.class);
                }
            });
            //选课
            selectCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, HrEnterActivity.class);
                }
            });
        }

//        refreshLayout.view
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 请求回来后
     *
     * @param data
     */
    @Override
    public void showData(final CourseEntity data) {
        if (refreshLayout != null) {
            refreshLayout.finishRefreshing();
        }
        if (data.getStatus() == 0) {
            //缓存 以便于 无网络时拿数据
            gson = new Gson();
            String s = gson.toJson(data);
            SharedPreferencesUtil.put(mContext, "homeCache", s);
            initAdapter(data);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }


    }

    /**
     * 初始化adapter(不包含学堂资讯)
     *
     * @param data
     */
    private void initAdapter(final CourseEntity data) {
        bannerList = data.getData().getBannerList();
        viewpagerAdapter = new ImagePagerAdapter(mContext, bannerList);
        viewPager.setAdapter(viewpagerAdapter);
        viewpagerAdapter.setOnClickListener(new ImagePagerAdapter.OnPagerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        // 缓存的页面数
        viewPager.setOffscreenPageLimit(3);
        //间距
        viewPager.setPageMargin(UtilDpOrPx.dip2px(mContext, 12));
        if (handler.hasMessages(1)) {
            handler.removeMessages(1);
            handler.sendEmptyMessageDelayed(1, 4000);
        }
        viewPager.setCurrentItem(999);
        //设置viewpager的切换速度
        ViewPagerScroller pagerScroller = new ViewPagerScroller(mContext);
        pagerScroller.setScrollDuration(1000);//设置时间，时间越长，速度越慢
        pagerScroller.initViewPagerScroll(viewPager);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                int paddingLeft = viewPager.getPaddingLeft();
                int imgWidth = viewPager.getWidth() - paddingLeft * 2;
                float excursion = -(float) paddingLeft / (float) imgWidth;
                viewPager.setPageTransformer(true, new AlphaScaleTransformer());
                viewPager.setCurrentItem(1000);
                viewpagerAdapter.notifyDataSetChanged();
            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //用户正在滑动，暂停轮播
                        handler.sendEmptyMessage(0);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //滑动结束，继续轮播
                        handler.sendEmptyMessageDelayed(1, 4000);
                        break;
                }

            }
        });

        otherBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                GlideUtils.loadRoundImage(mContext, (String) model, (ImageView) view);
            }
        });
        List<String> otherBannerList = new ArrayList<>();
        List<String> otherBannerTitle = new ArrayList<>();
        for (int i = 0; i < data.getData().getOtherNewsList().size(); i++) {
            otherBannerList.add(data.getData().getOtherNewsList().get(i).getPicture());
//                bannerTitle.add(data.getData().getBannerList().get(i).getTitle());
            otherBannerTitle.add("");
        }
        otherBanner.setData(otherBannerList, otherBannerTitle);//设置banner轮播图
        otherBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                Intent it = new Intent(mContext, SchoolMsgActivity.class);
                it.putExtra("id", data.getData().getOtherNewsList().get(position).getId());
                startActivity(it);

            }
        });
        //合作企业
        collList = data.getData().getCollaborationEnterpriseList();
        collList.add(new CourseEntity.DataBean.CollaborationEnterpriseListBean("", "更多", ImageHelper.getResourcesUri(mContext, R.mipmap.item_friend_more)));
        newsList = data.getData().getNewsList();//学堂咨询
        newsCurriculumList = data.getData().getNewsCurriculumList();//新上好课
        recommendCurriculumList = data.getData().getRecommendCurriculumList();//经典好课
        if (data.getData().getTeacherList().size() > 4) {
            teacherList = data.getData().getTeacherList().subList(0, 3);
        } else {
            teacherList = data.getData().getTeacherList();
        }


        //新上好课
        if (newCourseAdapter != null)
            newCourseAdapter.setNewData(newsCurriculumList);
        //推荐好课
        classicAdapter.setNewData(recommendCurriculumList);
        //teacher
        teacherAdapter.setNewData(teacherList);
        //合作伙伴
        friendAdapter.setNewData(collList);
        //学堂资讯
        msgAdapter.setNewData(newsList);


//
//        ToastUtils.setBgColor(getResources().getColor(R.color.dark_green));


    }

    /**
     * 显示学堂资讯信息  已弃用
     *
     * @param data
     */
    @Override
    public void showNewsData(final NewsEntity data) {
        if (data.getStatus().equals("1")) {
            //学堂资讯
//            R.layout.item_new_course_msg//学堂资讯
            //缓存 以便于 无网络时拿数据
//            gson = new Gson();
//            String s = gson.toJson(data);
//            SharedPreferencesUtil.put(mContext, "homeCache_news", s);
//            initNewsData(data);
        }

    }

    /**
     * 初始化学堂资讯信息
     *
     * @param data
     */
    private void initNewsData(final NewsEntity data) {
        List<NewsEntity.DataBean.NewsListBean> newsList = new ArrayList<>();
        if (data.getData().getNewsList().size() > 4) {
            newsList.addAll(data.getData().getNewsList().subList(0, 4));
        } else {
            newsList = data.getData().getNewsList();
        }


    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
        unbinder.unbind();
    }

//    }
}
