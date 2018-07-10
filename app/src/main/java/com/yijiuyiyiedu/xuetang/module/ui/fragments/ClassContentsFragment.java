package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PlayVideoBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.playVideoItemBus;
import com.yijiuyiyiedu.xuetang.module.persenter.ClassContentPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.DownloadActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.LoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ClassContentAdapter;
import com.yijiuyiyiedu.xuetang.module.view.ClassContentView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
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
 * Created by xuenhao on 2018/3/14.
 * 课程目录fragment
 */

public class ClassContentsFragment extends BaseFragment implements ClassContentView {
    Unbinder unbinder;
    ClassContentPresenter mPresenter;
    @BindView(R.id.fragment_classContents_expand)
    ExpandableListView expandListView;
    @BindView(R.id.classContent_download)
    ImageView download;
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean> list;
    public ClassContentAdapter adapter;
    private boolean isLoad = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_contents;
    }

    //    初始化数据
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mPresenter = new ClassContentPresenter(this);
        list = new ArrayList<>();
        adapter = new ClassContentAdapter(mContext, R.layout.item_contents_title, R.layout.item_play_section, list);
        expandListView.setAdapter(adapter);
        mPresenter.getCatalogData(((CourseDetailsActivity) mContext).courseId, UriConstant.token);
        isLoad = true;
//        download.setVisibility(View.VISIBLE);//下载
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, DownloadActivity.class);
                it.putExtra("courseId", ((CourseDetailsActivity) mContext).courseId);
                startActivity(it);
            }
        });
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StudyBusBus(StudyBus event) {
        if (mPresenter != null) {
            mPresenter.getCatalogData(((CourseDetailsActivity) mContext).courseId, UriConstant.token);
        } else {
//            UtilToast.showToast(mContext, "presenter为空");
//            mPresenter.getCatalogData(((CourseDetailsActivity) mContext).courseId, UriConstant.token);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void playVideoBus(PlayVideoBus event) {
        for (int i = 0; i < list.size(); i++) {
            List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> childList = list.get(i).getChildList();
            for (int n = 0; n < childList.size(); n++) {
                if (childList.get(n).getId().equals(event.getNumberId())) {//如果id相同的项就是默认播放的视频 要变色
                    childList.get(n).setColor("灰");
//                    ((CourseDetailsActivity) mContext).updatePlayVideo(childList.get(n).getVideo_address(), childList.get(n).getTitle());
                    for (int j = 0; j < list.size(); j++) {
                        expandListView.collapseGroup(j);
                        expandListView.expandGroup(j);
                    }
                    return;
                }
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //懒加载
    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

        //请求数据课程目录
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
    }


    @Override
    public void showCatalogData(ClassContentEntity data) {
        if (data.getStatus().equals("0")) {
            list.clear();
            list.addAll(data.getData().getCurriculumCatalogList());
            if (expandListView != null) {

                for (int i = 0; i < list.size(); i++) {
                    List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> childList = list.get(i).getChildList();
                    for (int n = 0; n < childList.size(); n++) {
                        childList.get(n).setColor("白");
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    expandListView.expandGroup(i);
                }
                expandListView.setGroupIndicator(null);
//                        点击不收缩
                expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
                        // TODO Auto-generated method stub
                        return true;
                    }
                });
                expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                    Toast.makeText(mContext, "获取到的curriculumId:"+list.get(groupPosition).getCurriculum_id()
//                            +"   获取到的catagoId:"+list.get(groupPosition).getChildList().get(childPosition).getId(), Toast.LENGTH_SHORT).show();
                        ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean childListBean = list.get(groupPosition).getChildList().get(childPosition);
                        if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {
                            if (childListBean.getLook_at().equals("2") || ((CourseDetailsActivity) mContext).play == 1) {
                                for (int i = 0; i < list.size(); i++) {
                                    List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> childList = list.get(i).getChildList();
                                    for (int n = 0; n < childList.size(); n++) {
                                        childList.get(n).setColor("白");
                                    }
                                }
                                childListBean.setColor("灰");
                                for (int i = 0; i < list.size(); i++) {
                                    expandListView.collapseGroup(i);
                                    expandListView.expandGroup(i);
                                }
                                EventBus.getDefault().postSticky(new playVideoItemBus(childListBean.getVideo_number()));
                                ((CourseDetailsActivity) mContext).freeTime = Integer.valueOf(childListBean.getFree_time());
                                ((CourseDetailsActivity) mContext).updatePlayVideo(((CourseDetailsActivity) mContext).courseId, childListBean.getId());

                            } else if (((CourseDetailsActivity) mContext).is_member_see == 1) {//会员可看

                            } else {
                                UtilToast.showToast(mContext, "您未购买");
                            }
                        } else {
                            Intent it = new Intent(mContext,PassLoginActivity.class);
                            it.putExtra("jump",1);
                            startActivity(it);
                        }
                        return false;
                    }
                });
            }
        }
    }
}
