package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 播放页面课程目录
 */

public class CoursePlayAdapter extends BaseQuickAdapter<ClassContentEntity.DataBean.CurriculumCatalogListBean, BaseViewHolder> {

    public CoursePlayAdapter(int layoutResId, @Nullable List<ClassContentEntity.DataBean.CurriculumCatalogListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassContentEntity.DataBean.CurriculumCatalogListBean item) {
        helper.setText(R.id.item_playTitle, item.getTitle());//标题
        RecyclerView recyclerView = helper.getView(R.id.item_playRecycler);//reyclerview
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        CoursePlaySectionAdapter adapter = new CoursePlaySectionAdapter(R.layout.item_play_section, item.getChildList());
        recyclerView.setAdapter(adapter);

    }
}