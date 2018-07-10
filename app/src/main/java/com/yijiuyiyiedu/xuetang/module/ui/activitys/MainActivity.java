package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tencent.rtmp.TXLiveBase;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseApplication;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.LateStudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.TabEntity;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.MineFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.MyCourseFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.NewCategoryFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.NewCourseFragment;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_frameLayout)
    FrameLayout viewPager;//frameLayout
    @BindView(R.id.main_tabLayout)
    CommonTabLayout tabLayout;//tabLayout
    String[] tabTitle = {"首页", "分类","我的课程", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.home_normal,R.mipmap.home_category_normal, R.mipmap.home_course_normal,
            R.mipmap.home_mine_normal};
    private int[] mIconSelectIds = {
            R.mipmap.home_press,R.mipmap.home_category_press, R.mipmap.home_course_press,
            R.mipmap.home_mine_press,};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
//    int[] tabIcon = {R.drawable.selector_tab_course, R.drawable.selector_tab_study, R.drawable.selector_tab_mine};
    private List<Fragment> fragments = new ArrayList<>();
    private Context mContext;
    protected Unbinder unbinder;
    protected BGASwipeBackHelper mSwipeBackHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.main_statusBar);
        mContext =this;
        initView();
        String sdkver = TXLiveBase.getSDKVersionStr();
        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLateStudyBus(LateStudyBus item){
        switchFragment(2);//展示我的课程
    }


    /**
     * 初始化布局
     */
    private void initView() {
        mSwipeBackHelper = new BGASwipeBackHelper(this,this);
        mSwipeBackHelper.setSwipeBackEnable(false);//设置不可侧滑
        for (int i = 0; i < tabTitle.length; i++) {
            mTabEntities.add(new TabEntity(tabTitle[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        initTabLayout();//初始化tablayout
        fragments.add(new NewCourseFragment());
        fragments.add(new NewCategoryFragment());
        fragments.add(new MyCourseFragment());
        fragments.add(new MineFragment());
        switchFragment(0);//默认展示第一个
    }
    //选择展示的fragment
    private void switchFragment(int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(tabTitle[position]);
        if (fragmentByTag==null){
            ft.add(R.id.main_frameLayout,fragments.get(position),tabTitle[position]);
        }else{
            ft.show(fragmentByTag);
        }
        ft.commitAllowingStateLoss();

    }

    private void hideFragment(FragmentTransaction ft) {
        for (int i=0;i<tabTitle.length;i++){
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tabTitle[i]);
            if (fragment!=null){
                ft.hide(fragment);
            }
        }
    }

    private void initTabLayout() {
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);

            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

    }



    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//                finish();
                BaseApplication.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
