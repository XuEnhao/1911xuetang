package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 课程目录标题下的子条目
 */

public class CoursePlaySectionAdapter extends BaseQuickAdapter<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean, BaseViewHolder> {


    public CoursePlaySectionAdapter(int layoutResId, @Nullable List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean item) {
        TextView title = helper.getView(R.id.item_play_sectionTitle);
        title.setText(item.getTitle() + "");
        if (((CourseDetailsActivity)mContext).play == 1) {//有播放权限
//            ImageView play = helper.getView(R.id.item_play_section_video);//播放按钮
//            play.setVisibility(View.VISIBLE);
            helper.getView(R.id.item_play_section_see).setVisibility(View.GONE);
//            helper.getView(R.id.item_play_section_lock).setVisibility(View.GONE);
        } else if (item.getLook_at().equals("2")) {//如果可以观看 就显示可观看 点击跳转
            TextView see = helper.getView(R.id.item_play_section_see);
            see.setVisibility(View.VISIBLE);//是否可观看
//            helper.getView(R.id.item_play_section_video).setVisibility(View.GONE);
//            helper.getView(R.id.item_play_section_lock).setVisibility(View.GONE);
        } else {
//            helper.getView(R.id.item_play_section_lock).setVisibility(View.VISIBLE);//是否有小锁
            helper.getView(R.id.item_play_section_see).setVisibility(View.GONE);
//            helper.getView(R.id.item_play_section_video).setVisibility(View.GONE);
        }

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                如果可以观看就跳转播放页面
                if (item.getLook_at().equals("2") || ((CourseDetailsActivity)mContext).play == 1) {
//                    ((CourseDetailsActivity)mContext).updatePlayVideo(UriConstant.userId, item.getCurriculum_id(), item.getId());

                    helper.getLayoutPosition();
//                    adapter.bindToRecyclerView();
//                    for (int i = 0;i<mData.size();i++){
//                        LinearLayout viewByPosition = (LinearLayout) adapter.getViewByPosition(position, R.id.item_play_section_linear);
//                        viewByPosition.setBackgroundColor(Color.parseColor("#ffffff"));
//                    }
//                    LinearLayout linearLayout = helper.getView(R.id.item_play_section_linear);
//                    linearLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
//                    notifyDataSetChanged();

                } else {
                    UtilToast.showToast(mContext, "您未购买");
                }
            }
        });
//        if (!TextUtils.isEmpty(PlayVideoActivity.catalogId)){
//            if (PlayVideoActivity.catalogId.equals(item.getId())){//如果正在播放就加个背景
//                LinearLayout view = helper.getView(R.id.item_play_section_linear);
//
//            }
//        }
    }
}