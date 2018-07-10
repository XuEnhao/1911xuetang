package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.utils.DataCleanManager;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 下载目录
 */

public class DownloadContentAdapter extends BaseExpandableListAdapter {
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean> groupList;
    private int groupLayout;
    private int childLayout;
    private Context context;

    public DownloadContentAdapter(Context mContext, int groupId, int childId,
                                  List<ClassContentEntity.DataBean.CurriculumCatalogListBean> groupList) {
        this.groupList = groupList;
        groupLayout = groupId;
        childLayout = childId;
        context = mContext;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    /**
     * 读取父级数据条目数
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 读取子级条目数
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return groupList.get(groupPosition).getChildList().size();
    }

    /**
     * 读取gourp数据
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(groupLayout, parent, false);
        } else {
            view = convertView;
        }
        ClassContentEntity.DataBean.CurriculumCatalogListBean groupBean = groupList.get(groupPosition);
        TextView title = view.findViewById(R.id.item_contentsTitle);
        title.setText(groupList.get(groupPosition).getTitle());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(childLayout, parent, false);
        } else {
            view = convertView;
        }
        ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean childListBean = groupList.get(groupPosition).getChildList().get(childPosition);
        ImageView imageView = view.findViewById(R.id.item_download_section_check);
        TextView number = view.findViewById(R.id.item_download_section_number);//1-1
        TextView title = view.findViewById(R.id.item_download_section_title);//标题
        TextView size = view.findViewById(R.id.item_download_section_size);//大小
        number.setText(groupPosition+"-"+childPosition);
        title.setText(childListBean.getTitle());
        LogUtil.LogD("downloadContent", "childListBean.getSize(): "+childListBean.getSize());
        if (TextUtils.isEmpty(childListBean.getSize())){
            size.setText("0M");
        }else{
            String fileSize = Formatter.formatFileSize(context, Long.parseLong(childListBean.getSize()));
            size.setText(fileSize+"");
        }

        if (childListBean.isCheck()){
            imageView.setImageResource(R.drawable.download_check);
//            childListBean.setCheck(true);
        }else{
            imageView.setImageResource(R.drawable.download_uncheck);
//            childListBean.setCheck(false);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}