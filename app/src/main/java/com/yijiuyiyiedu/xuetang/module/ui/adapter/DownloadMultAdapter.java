package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/14.
 * 下载二级列表
 */

public class DownloadMultAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CHILD = 1;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DownloadMultAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_TITLE, R.layout.item_contents_title);
        addItemType(TYPE_CHILD, R.layout.item_download_section);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_TITLE:
                final ClassContentEntity.DataBean.CurriculumCatalogListBean parent = (ClassContentEntity.DataBean.CurriculumCatalogListBean) item;
                helper.setText(R.id.item_contentsTitle, parent.getTitle());//父布局标题
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        int pos = helper.getAdapterPosition();
//                        if (parent.isExpanded()) {
//                            collapse(pos);
//                        } else {
//                            expand(pos);
//                        }
                    }});
                break;
            case TYPE_CHILD:
                final ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean person = (ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean) item;
                // 获取当前父级位置
                int cp = getParentPosition(person);
                // 通过父级位置找到当前list，删除指定下级
                int subItemPosition = ((ClassContentEntity.DataBean.CurriculumCatalogListBean) getData().get(cp)).getSubItemPosition(person);
                helper.setText(R.id.item_download_section_number,subItemPosition+"");
                helper.setText(R.id.item_download_section_title,person.getTitle());
                helper.setText(R.id.item_download_section_size,person.getSize());
                final CheckBox checkBox = helper.getView(R.id.item_download_section_check);
                checkBox.setOnCheckedChangeListener(null);
                checkBox.setChecked(person.isCheck());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()){
                            person.setCheck(false);
                            checkBox.setChecked(false);
                        }else{
                            person.setCheck(true);
                            checkBox.setChecked(true);
                        }
                    }
                });
                break;
        }
        LogUtil.LogD("MultiItem",item.getItemType()+"");
    }


}
