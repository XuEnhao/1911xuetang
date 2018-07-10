package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.base.NewBaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.FriendEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.FriendListAdapter;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/15.
 * 合作伙伴
 */

public class FriendActivity extends NewBaseActivity {
    @BindView(R.id.friend_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.friend_scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.addCourseId_back)
    ImageView back;
    @BindView(R.id.friend_toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.friend_image)
    ImageView img;
    @BindView(R.id.friend_statusBar)
    View statusBar;
    private Context mContext;
    private FriendListAdapter mAdapter;
    private ArrayList<FriendEntity.DataBean.CollaborationEnterpriseListBean> list;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setTranslucentForImageView(this, 100, null);
        recyclerView.setNestedScrollingEnabled(false);
        initView();
        loadData();

    }

    private void loadData() {
        OkGo.<FriendEntity>get(Constant.FRIEND_LIST)
                .execute(new JsonCallback<FriendEntity>() {
                    @Override
                    public void onSuccess(Response<FriendEntity> response) {
                        FriendEntity body = response.body();
                        if (body.getStatus() == 0) {
                            mAdapter.setNewData(body.getData().getCollaborationEnterpriseList());
                            scrollView.scrollTo(0, 0);
                        }
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(mContext);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = statusBarHeight;

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapter = new FriendListAdapter(R.layout.item_friend, list);
//        ArrayList<String> list1 = new ArrayList<>();
//        for (int i=0;i<20;i++){
//            list1.add("i"+i);
//        }
//        TestAdapter adapter = new TestAdapter(R.layout.item_mian_friend,list1);
        recyclerView.setAdapter(mAdapter);

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] ints = new int[2];
                img.getLocationOnScreen(ints);
                /**
                 * mImage距离屏幕顶部的距离(图片顶部在屏幕最上面，向上滑动为负数，所以取反)
                 * 如果不隐藏状态栏，需要加上状态栏的高度；隐藏状态栏就不用加了；
                 */
                int scrollY1 = -ints[1] + StatusBarUtil.getStatusBarHeight(mContext);

                //mImage这个view的高度
                int imageHeight = img.getHeight();

                if (img != null && imageHeight > 0) {
                    //如果“图片”没有向上滑动，设置为全透明
                    if (scrollY1 < 0) {
                        toolbar.getBackground().setAlpha(0);
                    } else {
                        //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
                        if (scrollY1 < imageHeight) {
                            int progress = (int) (new Float(scrollY1) / new Float(imageHeight) * 255);//255
                            toolbar.getBackground().setAlpha(progress);
                        } else {
                            //“图片”全部滑出屏幕的时候，设为完全不透明
                            toolbar.getBackground().setAlpha(255);
                        }
                    }
                }
            }
        });
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            //监听滑动状态的改变
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//
//            //用于监听ListView屏幕滚动
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                int[] ints = new int[2];
//                mImage.getLocationOnScreen(ints);
//                /**
//                 * mImage距离屏幕顶部的距离(图片顶部在屏幕最上面，向上滑动为负数，所以取反)
//                 * 如果不隐藏状态栏，需要加上状态栏的高度；隐藏状态栏就不用加了；
//                 */
//                int scrollY = -ints[1]+statusHeight;
//
//                //mImage这个view的高度
//                int imageHeight = mImage.getHeight();
//
//                if (mImage != null && imageHeight > 0) {
//                    //如果“图片”没有向上滑动，设置为全透明
//                    if (scrollY < 0) {
//                        llSearch.getBackground().setAlpha(0);
//                    } else {
//                        //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
//                        if (scrollY < imageHeight) {
//                            int progress = (int) (new Float(scrollY) / new Float(imageHeight) * 255);//255
//                            llSearch.getBackground().setAlpha(progress);
//                        } else {
//                            //“图片”全部滑出屏幕的时候，设为完全不透明
//                            llSearch.getBackground().setAlpha(255);
//                        }
//                    }
//                }
//
//            }
//        });
    }

    @OnClick(R.id.addCourseId_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
