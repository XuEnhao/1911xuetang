package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.moos.library.HorizontalProgressView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.ServiceActivity;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ClassContentAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.DownloadContentAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.DownloadMultAdapter;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/14.
 * 下载页面
 */

public class DownloadActivity extends BaseActivity {
    @BindView(R.id.addCourseId_back)
    ImageView back;//返回
    @BindView(R.id.download_space)
    TextView space;//剩余空间
    @BindView(R.id.download_progress)
    HorizontalProgressView progress;//剩余空间
    @BindView(R.id.download_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.download_expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.download_allCheck)
    TextView allCheck;//全选
    @BindView(R.id.download_confirm)
    TextView confirm;//确认下载
    private Context mContext;
    private List<MultiItemEntity> list;
    private DownloadMultAdapter multAdapter;
    private String courseId;
    private DownloadContentAdapter mAdapter;
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> downloadList;
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean> contentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        courseId = getIntent().getStringExtra("courseId");
        mContext = this;
        initView();
        getCurriculumCatalogList(courseId);
    }

    private void initView() {
        list = new ArrayList<>();
        contentList = new ArrayList<>();
        downloadList = new ArrayList<>();//要下载的列表
//        multAdapter = new DownloadMultAdapter(list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        recyclerView.setAdapter(multAdapter);
        String sdTotalSize = WindowUtils.getSDTotalSize(mContext);//总内存 单位GB
        String gb = sdTotalSize.substring(0, sdTotalSize.indexOf("GB"));//把GB去除
        String sdAvailableSize = WindowUtils.getSDAvailableSize(mContext);//剩余空间 单位GB
        String gb1 = sdAvailableSize.substring(0, sdAvailableSize.indexOf("GB"));//把GB去除
        float v = (float)((Double.parseDouble(gb)-Double.parseDouble(gb1))*100 / Double.parseDouble(gb));//进度
        Log.d("tag", "Double.parseDouble(gb1):"+Double.parseDouble(gb1)+"Double.parseDouble(gb):"+Double.parseDouble(gb)+"v:"+v);
        progress.setProgress(v);//进度条
        space.setText("剩余"+WindowUtils.getSDAvailableSize(mContext));
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //确认下载
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadList.clear();
                for (int i=0;i<contentList.size();i++){
                    List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> childList = contentList.get(i).getChildList();
                    for (int j = 0;j<childList.size();j++){
                        if (childList.get(j).isCheck()){
                            downloadList.add(childList.get(j));
                        }
                    }
                }
//                Intent it = new Intent(mContext,ServiceActivity.class);
                Intent it = new Intent(mContext,DownloadDetailsActivity.class);
                it.putExtra("listObj",(Serializable)downloadList);
                startActivity(it);
                LogUtil.LogD("downloadList","downloadList.size()"+downloadList.size());
            }
        });
        //全选
        allCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<contentList.size();i++){
                    List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> childList = contentList.get(i).getChildList();
                    for (int j = 0;j<childList.size();j++){
                        childList.get(j).setCheck(true);
                    }
                }
                for (int i = 0; i < contentList.size(); i++) {
                    expandableListView.collapseGroup(i);
                    expandableListView.expandGroup(i);
                }
            }
        });

    }

    /**
     * 获取课程目录列表
     * @param curriculumId
     * @param
     */
    public void getCurriculumCatalogList(String curriculumId){
        OkGo.<ClassContentEntity>get(Constant.CURRICULUM_CATALOG_LIST)
                .params("curriculum_id",curriculumId)
                .execute(new JsonCallback<ClassContentEntity>() {
                    @Override
                    public void onSuccess(Response<ClassContentEntity> response) {
                        ClassContentEntity body = response.body();
                        if (body.getStatus().equals("0")){
                            List<ClassContentEntity.DataBean.CurriculumCatalogListBean> curriculumCatalogList = response.body().getData().getCurriculumCatalogList();
                            contentList.addAll(curriculumCatalogList);
                            mAdapter = new DownloadContentAdapter(mContext,R.layout.item_contents_title,R.layout.item_download_section,contentList);
                            expandableListView.setAdapter(mAdapter);
                            for (int i = 0; i < contentList.size(); i++) {
                                expandableListView.expandGroup(i);
                            }
                            expandableListView.setGroupIndicator(null);//分割线
                            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                                @Override
                                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                                    return true;
                                }
                            });
                            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                    ToastUtils.showShort("点击了"+groupPosition+"child"+childPosition);
                                    ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean childListBean = contentList.get(groupPosition).getChildList().get(childPosition);
                                    if (childListBean.isCheck()){
                                        childListBean.setCheck(false);
                                    }else{
                                        childListBean.setCheck(true);
                                    }
                                    for (int i = 0; i < contentList.size(); i++) {
                                        expandableListView.collapseGroup(i);
                                        expandableListView.expandGroup(i);
                                    }
                                    return true;
                                }
                            });


//                            for (int i =0;i<curriculumCatalogList.size();i++){
//                                //一级数据
//                                ClassContentEntity.DataBean.CurriculumCatalogListBean lv0 = curriculumCatalogList.get(i);
//                                //二级数据
//                                List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> lv1 = lv0.getChildList();
//                                for (int j=0;j<lv1.size();j++){
//                                    lv0.addSubItem(lv1.get(j));
//                                }
//                                list.add(lv0);
//                            }
//                            multAdapter.setNewData(list);
//                            multAdapter.expandAll();//展开所有列表
                        }else{
                            ToastUtils.showShort(body.getMsg());
                        }
                    }
                });
    }
}
