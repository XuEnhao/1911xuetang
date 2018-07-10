package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 课程目录下标题
 */

public class ClassContentAdapter extends BaseExpandableListAdapter {
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean> groupList;
    private int groupLayout;
    private int childLayout;
    private Context context;

    public ClassContentAdapter(Context mContext, int groupId, int childId,
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
        TextView title = view.findViewById(R.id.item_play_sectionTitle);
        TextView number = view.findViewById(R.id.item_play_sectionNumber);
        ImageView imageView = view.findViewById(R.id.item_play_sectionPlay);
        title.setText(childListBean.getTitle() + "");
        number.setText(childListBean.getVideo_number());
        LinearLayout linearLayout = view.findViewById(R.id.item_play_section_linear);
        if (childListBean.getColor().equals("灰")) {//选中状态
            number.setTextColor(Color.parseColor("#6317A5"));
            title.setTextColor(Color.parseColor("#6317A5"));
            imageView.setImageResource(R.mipmap.course_details_play);
//            linearLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
        } else {
            imageView.setImageResource(R.mipmap.course_details_unplay);
            number.setTextColor(Color.parseColor("#787993"));
            title.setTextColor(Color.parseColor("#787993"));
//            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        if (((CourseDetailsActivity)context).play == 1) {//有播放权限
            TextView see = view.findViewById(R.id.item_play_section_see);//可试看
            see.setVisibility(View.GONE);
        } else if (childListBean.getLook_at().equals("2")) {//如果可以观看 就显示可观看 点击跳转
            TextView see = view.findViewById(R.id.item_play_section_see);//可试看
            see.setVisibility(View.VISIBLE);
        } else {
            TextView see = view.findViewById(R.id.item_play_section_see);//可试看
            see.setVisibility(View.GONE);
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