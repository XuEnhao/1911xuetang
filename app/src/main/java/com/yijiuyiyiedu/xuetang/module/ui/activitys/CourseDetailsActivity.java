package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hpplay.link.HpplayLinkControl;
import com.tencent.rtmp.TXPlayerAuthBuilder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ALNAEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.PlayVideoBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.TabEntity;
import com.yijiuyiyiedu.xuetang.module.entity.playVideoItemBus;
import com.yijiuyiyiedu.xuetang.module.persenter.CourseDetailsPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.ContentNormal1PagerAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassContentsFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassDetailsFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassEvaluateFragment;
import com.yijiuyiyiedu.xuetang.module.view.CourseDetailsView;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.MediaController;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.SuperVideoPlayer;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.VodRspData;
import com.yijiuyiyiedu.xuetang.utils.EventUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.TimeUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 课程详情activity
 */
public class CourseDetailsActivity extends BaseActivity implements CourseDetailsView,SuperVideoPlayer.ProgressListener {

    @BindView(R.id.courseDetails_tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.courseDetails_viewPager)
    ViewPager viewPager;
    @BindView(R.id.courseDetails_classImg)
    ImageView classImg;//课程大图
    @BindView(R.id.courseDetails_collect)
    LinearLayout classDetailsCollect;//收藏
    @BindView(R.id.courseDetails_buyNow)
    Button buyNow;//立即购买
    @BindView(R.id.courseDetails_login)
    LinearLayout noLogin;//未登录展示
    CourseDetailsPresenter mPresenter;
    @BindView(R.id.courseDetails_courseTitle)
    TextView CourseTitle;//课程标题
    @BindView(R.id.courseDetails_bottomLinear)
    LinearLayout bottomLinear;//底部布局 当是游客身份时隐藏
    @BindView(R.id.superVideoPlayer)
    SuperVideoPlayer mSuperVideoPlayer;//播放器
    @BindView(R.id.study_statuBarView)
    View statusBar;
    @BindView(R.id.courseDetails_toolbar)
    RelativeLayout toolbar;//标题布局
    @BindView(R.id.courseDetails_tablayoutRelative)
    RelativeLayout tablayoutRelative;
    @BindView(R.id.video_viewRelative)
    RelativeLayout videoViewRelative;
    @BindView(R.id.video_view_DLNA)
    TextView videoViewDLNA;//投屏播放
    @BindView(R.id.courseDetails_back)
    ImageView back;//返回
    @BindView(R.id.courseDetails_classImg_play)
    ImageView player;//播放按钮
    @BindView(R.id.courseDetails_pauseLayout)
    RelativeLayout pauseLayout;//试看暂停的布局
    @BindView(R.id.courseDetails_lastVideo)
    TextView lastVideo;//上一集
    @BindView(R.id.courseDetails_nextVideo)
    TextView nextVideo;//下一集
    @BindView(R.id.courseDetails_finishLayout)
    RelativeLayout finishLayout;//播放完成的布局
    private Context mContext;
    private String[] inforMation = {"简介", "目录", "评价"};
    private List<Fragment> fragments = new ArrayList<>();
    private String curriculumTitle;//课程标题
    volatile String url = "http://gcqq450f71eywn6bv7u.exp.bcevod.com/mda-hbqagik5sfq1jsai/mda-hbqagik5sfq1jsai.mp4";
    private HpplayLinkControl hpplayLinkControl;
    private final String appKey = "b4f78468c281c0080c7fb5184d296336";//乐播appkey
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();//放tablayout的标题 图片集合
    private ClassContentsFragment classContentsFragment;//播放列表的fragment
    public String courseId;//课程id
    public int play = 0;// 0 没有播放权限
    public boolean pause = false;//用于播放位置的判断
    public int freeTime = 0;//试看时间
    public int is_member_see = 0;//会员是否可试看 1可以 2 不可以
    public int is_collection;
    public float score;
    private final int REQUEST_CODE_ASK_CALL_PHONE = 1;
    private String catalogId;

    @Override
    protected void onPause() {
        super.onPause();
    }

    //更换播放视频
    public void updatePlayVideo(String curriculumId, String catalogId) {
        if (mPresenter != null) {
            this.catalogId = catalogId;
            mSuperVideoPlayer.onPause();
            mPresenter.getVideoData(courseId,catalogId);
        }
        //请求地下的二级列表
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //here we can use getIntent() to get the extra data.
        courseId = intent.getStringExtra("courseId");
        mPresenter = new CourseDetailsPresenter(this);
    }

    /**
     * 获取登录页面传回来的信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StudyBusBus(StudyBus event) {
        if (mPresenter != null) {
            LogUtil.LogD("token", "details:" + UriConstant.token);
            mPresenter.getData(courseId, UriConstant.token);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void playVideoItemBus(playVideoItemBus event) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        initDLNA();//初始化投屏
        getIntentInfo();//获取intent消息
        loadData();//加载数据
        initView();//初始化布局
        initTencentView();//初始化腾讯播放器

        EventBus.getDefault().register(this);
    }

    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.onDestroy();
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }

        @Override
        public void onBack() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                finish();
            }
        }

        @Override
        public void onLoadVideoInfo(VodRspData data) {

        }
    };

    private void initTencentView() {
        mSuperVideoPlayer.setProgressListener(this);//设置监听播放进度的
        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
    }

    /**
     * 请求数据
     */
    private void loadData() {
        mPresenter = new CourseDetailsPresenter(this);
        mPresenter.getData(courseId, UriConstant.token);//请求数据
    }

    /**
     * 初始化投屏
     */
    private void initDLNA() {
//        获取单例:
        hpplayLinkControl = HpplayLinkControl.getInstance();
//        获取支持投屏码输入的单例:
//        HpplayLinkControl hpplayLinkControl = HpplayLinkControl.getInstance(ScreenCodeCallBack);
        hpplayLinkControl.setDebug(true);
        hpplayLinkControl.initHpplayLink(this, appKey);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (pause) {
            mSuperVideoPlayer.goOnPlay();
            pause = false;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!pause) {
            mSuperVideoPlayer.onPause();
            mSuperVideoPlayer.pausePlay(true);
            pause = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void getIntentInfo() {
        Intent it = getIntent();
        courseId = it.getStringExtra("courseId");
    }


    /**
     * 监听横竖屏切换
     */
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (mSuperVideoPlayer != null) {
            updatePlayerViewMode();
        }
    }

    private void updatePlayerViewMode() {
        //切换为竖屏
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == this.getResources().getConfiguration().ORIENTATION_PORTRAIT) {
            //有状态栏
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


            bottomLinear.setVisibility(View.VISIBLE);//底部布局
            statusBar.setVisibility(View.VISIBLE);//状态栏
            tablayoutRelative.setVisibility(View.VISIBLE);
//            mAliyunVodPlayerView.changeScreenMode(AliyunScreenMode.Small);
            ToastUtils.showShort( "切换为竖屏");
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            //获取当前屏幕宽高
            WindowManager wm1 = this.getWindowManager();
            int width1 = wm1.getDefaultDisplay().getWidth();
            int height1 = wm1.getDefaultDisplay().getHeight();
            ViewGroup.LayoutParams relativeParams = videoViewRelative.getLayoutParams();
            relativeParams.height = UtilDpOrPx.dip2px(mContext, 200);
            relativeParams.width = width1;
            ViewGroup.LayoutParams layoutParams = mSuperVideoPlayer.getLayoutParams();
            layoutParams.height = UtilDpOrPx.dip2px(mContext, 200);
            layoutParams.width = width1;

            ViewGroup.LayoutParams layoutParams1 = finishLayout.getLayoutParams();
            layoutParams1.height = UtilDpOrPx.dip2px(mContext, 200);
            layoutParams1.width = width1;

            ViewGroup.LayoutParams layoutParams2 = classImg.getLayoutParams();
            layoutParams2.height = UtilDpOrPx.dip2px(mContext, 200);
            layoutParams2.width = width1;
        }

//切换为横屏

        else if (orientation == this.getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //无状态栏
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            ToastUtils.showShort("切换为横屏");
            bottomLinear.setVisibility(View.GONE);//底部布局
            statusBar.setVisibility(View.GONE);//状态栏
            tablayoutRelative.setVisibility(View.GONE);
            mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            //获取当前屏幕宽高
            WindowManager wm1 = this.getWindowManager();
            int width1 = wm1.getDefaultDisplay().getWidth();
            int height1 = wm1.getDefaultDisplay().getHeight();

            ViewGroup.LayoutParams relativeParams = videoViewRelative.getLayoutParams();
            relativeParams.height = height1;
            relativeParams.width = width1;
            ViewGroup.LayoutParams layoutParams = mSuperVideoPlayer.getLayoutParams();
            layoutParams.height = height1;
            layoutParams.width = width1;
            ViewGroup.LayoutParams layoutParams1 = finishLayout.getLayoutParams();
            layoutParams1.height = height1;
            layoutParams1.width = width1;

            ViewGroup.LayoutParams layoutParams2 = classImg.getLayoutParams();
            layoutParams2.height = height1;
            layoutParams2.width = width1;


        }
    }


    /**
     * 初始化布局
     */
    private void initView() {
        for (int i = 0; i < inforMation.length; i++) {
            mTabEntities.add(new TabEntity(inforMation[i], 0, 0));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        classContentsFragment = new ClassContentsFragment();
        fragments.add(new ClassDetailsFragment());
        fragments.add(new ClassContentsFragment());
        fragments.add(new ClassEvaluateFragment());
        ContentNormal1PagerAdapter pagerAdapter = new ContentNormal1PagerAdapter(getSupportFragmentManager(), fragments, inforMation);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else if (orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT) {//竖屏
                if (mContext != null) {
                    JumpUtil.overiderAnimsition((Activity) mContext);
                }
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showData(final CourseDetailsEntity data) {
        if (data.getStatus() == 0) {
            curriculumTitle = data.getData().getCurriculumDetail().getTitle();//课程标题
            if (mSuperVideoPlayer != null) {
                mSuperVideoPlayer.updateUI(curriculumTitle);
            }
            score = data.getData().getCurriculumDetail().getScore();
            is_collection = data.getData().getCurriculumDetail().getIs_collection();
            if (mContext != null && classImg != null) {
                GlideUtils.loadImage(mContext, data.getData().getCurriculumDetail().getPicture(), classImg);
            }
            if (noLogin != null) {
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    noLogin.setVisibility(View.GONE);
                } else {
                    noLogin.setVisibility(View.VISIBLE);
                }
            }
            if (buyNow != null) {
                if (data.getData().getCurriculumDetail().getIs_study() == 1) {//是否正在学习课程  1 学习中  0 未学习
                    buyNow.setVisibility(View.GONE);
                } else {
                    buyNow.setVisibility(View.VISIBLE);
                }
                if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {//如果登录
                    buyNow.setText("立即学习");
                } else {
                    buyNow.setText("立即购买");
                }
                buyNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(buyNow.getText().toString().equals("立即学习")){
                            if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {//如果登录
                                classImg.setVisibility(View.GONE);//课程大图隐藏
                                updatePlayVideo(courseId, data.getData().getCurriculumDetail().getDefaultCurriculumCatalog().getId());//更新播放视频
                                viewPager.setCurrentItem(1);//到课程目录页面
                                if (back.getVisibility()==View.VISIBLE){
                                    back.setVisibility(View.GONE);//返回按钮
                                }
                                //通知课程目录页面展示播放的小节
                                EventBus.getDefault().postSticky(new PlayVideoBus(data.getData().getCurriculumDetail().getDefaultCurriculumCatalog().getId(), 0));
                            } else {
                                Intent it = new Intent(mContext, PassLoginActivity.class);
                                it.putExtra("jump", 1);
                                startActivity(it);
                            }
                        }else{
                            ToastUtils.showShort("请到web端购买");
                        }
                    }
                });
            }
            //判断是否购买
            if (data.getData().isCurriculumPrivilege()) {
                play = 1;
                if (classContentsFragment.adapter != null) {
                    classContentsFragment.adapter.notifyDataSetChanged();
                }
            } else {
                play = 0;
                if (classContentsFragment.adapter != null) {
                    classContentsFragment.adapter.notifyDataSetChanged();
                }
            }

            //播放按钮
            if (player != null) {
                player.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {
                            classImg.setVisibility(View.GONE);
                            updatePlayVideo(courseId, data.getData().getCurriculumDetail().getDefaultCurriculumCatalog().getId());
                            viewPager.setCurrentItem(1);
                            if (back.getVisibility()==View.VISIBLE){
                                back.setVisibility(View.GONE);//返回按钮
                            };
                            EventBus.getDefault().postSticky(new PlayVideoBus(data.getData().getCurriculumDetail().getDefaultCurriculumCatalog().getId(), 0));
                        } else {
                            Intent it = new Intent(mContext, PassLoginActivity.class);
                            it.putExtra("jump", 1);
                            startActivity(it);
                        }

                    }
                });
            }

        } else if (data.getStatus() == 100008) {
            if (mContext != null) {
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("您的账号已在另一台设备登录，您被迫下线");
                    builder.setTitle("被迫下线");
                    builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferencesUtil.put(mContext, "token", "");//清除用户数据
                            UriConstant.clear();
                            Intent it = new Intent(mContext, PassLoginActivity.class);
                            it.putExtra("jump", 0);
                            startActivity(it);
                            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
                            dialog.dismiss();
                        }
                    });
                    //不关闭写法
                    builder.setCancelable(false);
                    builder.show();
                }
            }
        } else {
            ToastUtils.showShort(data.getMsg());
        }
    }

    /**
     * 点击收藏按钮请求
     *
     * @param data
     */
    @Override
    public void showCollectData(CollectCourseEntity data) {
        if (data.getStatus().equals("0")) {
            if (mContext != null) {
                ToastUtils.showShort( "收藏成功");
            }
            if (mContext != null) {
                ToastUtils.showShort( "取消成功");
            }
        } else {
            if (mContext != null)
                ToastUtils.showShort( data.getMsg() + "");
        }
    }

    /**
     * 请求播放信息
     */
    @Override
    public void showVideoData(String data) {

        try {
            JSONObject object = new JSONObject(data);
            Log.d("tag", "showData: " + data.toString());

            if (object.getString("status").equals("0")) {
                classImg.setVisibility(View.GONE);//课程大图
                if (back.getVisibility()==View.VISIBLE){
                    back.setVisibility(View.GONE);//返回按钮
                };
//                player.setVisibility(View.GONE);
                JSONObject jsonObject = object.getJSONObject("data");
                //获取视频id
//                JSONObject catalogDetail = jsonObject.getJSONObject("curriculumCatalogDetail");
//                String video_id = catalogDetail.getString("video_id");
//                catalogTitle = catalogDetail.getString("title");
                //获取播放凭证
                JSONObject playAuthInfo = jsonObject.getJSONObject("playAuthInfo");
                String playurl = playAuthInfo.getString("video_address");
                String fileID = playAuthInfo.getString("fileID");
                String sign = playAuthInfo.getString("sign");
                String t = playAuthInfo.getString("t");
                int exper = playAuthInfo.getInt("exper");
                int appID = playAuthInfo.getInt("appID");
                Log.d("tag", "fileID: " + fileID + "  appID:" + appID + "  sign:" + sign);
                if (pauseLayout.getVisibility()==View.VISIBLE){
                    pauseLayout.setVisibility(View.GONE);//显示暂停布局
                }
//                videoViewDLNA.setVisibility(View.VISIBLE);//投屏 暂时去掉

                mSuperVideoPlayer.startPlay(appID,fileID,t,sign,exper);
                Log.d("videoAddress", playurl);
//                mSuperVideoPlayer.setPlayUrl(playurl);
                if (classImg.getVisibility()==View.VISIBLE){
                    classImg.setVisibility(View.GONE);//课程默认大图
                }
                if (player.getVisibility()==View.VISIBLE){
                    player.setVisibility(View.GONE);//play视频
                }
                if (back.getVisibility()==View.VISIBLE){
                    back.setVisibility(View.GONE);//返回按钮
                }
            } else {
                String msg = object.getString("msg");
                ToastUtils.showShort( msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 投屏信息 已废弃
     * @param data
     */
    @Override
    public void showALNAData(ALNAEntity data) {
//        if (data.getStatus().equals("1")){
//            hpplayLinkControl.showHpplayWindow(this, data.getData().getFileURL(), 0, new HpplayWindowPlayCallBack() {
//                @Override
//                public void onHpplayWindowDismiss() {
//                    //SDK界面退出
//                }
//
//                @Override
//                public void onIsConnect(boolean b) {
//                    Log.d("tag", "是否成功连接到电视 " + b);
//                }
//
//                @Override
//                public void onIsPlaySuccess(boolean b) {
//                    Log.d("tag", "是否成功推送地址到电视 " + b);
//                }
//            }, HpplayLinkControl.PUSH_VIDEO);
//        }else{
//            UtilToast.showToast(mContext,data.getMsg());
//        }
    }

    @Override
    public void showSaveSchedule(ClearHistoryEntity data) {
        LogUtil.LogD("courseDetails",data.getMsg());
    }


    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick({R.id.courseDetails_collect, R.id.courseDetails_back, R.id.video_view_DLNA, R.id.courseDetails_finishBack,R.id.courseDetails_call,R.id.courseDetails_qqChat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.courseDetails_collect:
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {//如果登录
                    if (!EventUtil.isFastDoubleClick()) {//是否重复点击
                        mPresenter.getCollectCourse(UriConstant.token, courseId);//请求接口
                    } else {
                        if (mContext != null)
                            ToastUtils.showShort( "点击太快拉，请等等");
                    }
//                    } else {
//                        UtilToast.showToast(mContext, "您已经收藏过了");
//                    }
                } else {
                    Intent intent = new Intent(mContext, PassLoginActivity.class);
                    intent.putExtra("jump", 1);
                    startActivity(intent);
                    ;//就跳转登录页面
                }
                break;
            case R.id.courseDetails_back:
                JumpUtil.overiderAnimsition((Activity) mContext);
                break;
            case R.id.video_view_DLNA://投屏
//                if (SharedPreferencesUtil.selectUserInfo(mContext)) {//如果登录
//                    if (mPresenter!=null){
//                        mPresenter.getALNAData(courseId,UriConstant.token,catalogId);
//                    }
//                } else {
//                    Intent intent = new Intent(mContext, PassLoginActivity.class);
//                    intent.putExtra("jump",1);
//                    startActivity(intent);;//就跳转登录页面
//                }

                break;
            case R.id.courseDetails_finishBack:
                break;
            case R.id.courseDetails_call: requestPermission();//打电话
                break;
            case R.id.courseDetails_qqChat:
                try {
                    //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=2844916043";//uin是发送过去的qq号码
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("请检查是否安装QQ");
                }
                break;
        }
    }

//    @OnClick(R.id.courseDetails_buyNow)
//    public void onViewClicked() {
//        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {//如果登录
//            //点击立即购买跳转显示购买信息页面
//            Intent it = new Intent(mContext, BuyCourseActivity.class);
//            it.putExtra("curriculumId", courseId);
//            startActivity(it);
//        } else {
//            Intent intent = new Intent(mContext, PassLoginActivity.class);
//            intent.putExtra("jump",1);
//            startActivity(intent);//就跳转登录页面
//        }

//    }

    @Override
    protected void onDestroy() {
        if (mSuperVideoPlayer != null) {
            int playTime = mSuperVideoPlayer.getPlayTime();
            Log.d("SuperVideoPlayer", "onDestroy: "+playTime);
            mSuperVideoPlayer.onDestroy();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    /**
     * 申请权限 打客服
     */
    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE},
//                        REQUEST_CODE_ASK_CALL_PHONE);
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
            } else {
                diallPhone();
            }
        } else {
            diallPhone();
        }
    }

    /**
     * 拨打电话
     */
    public void diallPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "010-62701911");
        intent.setData(data);
        startActivity(intent);
    }


    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    diallPhone();//打电话
                } else {
                    // Permission Denied
                    Toast.makeText(mContext, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }

    @Override
    public void showProgress(int playTime) {
        Log.d("progress", "showProgress: "+playTime);
    }

    @Override
    public void onPlayFinish() {
        Log.d("progress", "onPlayFinish: ");
    }

    @Override
    public void onVideoPause(int playTime) {
        int i = TimeUtil.convertToSecond((long) playTime);
        if (mPresenter!=null){
            if (i!=0){
                mPresenter.getSaveScheduleData(courseId,catalogId,i);

            }
        }
        Log.d("progress", "onVideoPause: "+i);

    }
}
