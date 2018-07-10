package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.LoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 课程目录标题下的子条目
 */

public class ClassContentSectionAdapter extends BaseQuickAdapter<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean, BaseViewHolder> {

    public ClassContentSectionAdapter(int layoutResId, @Nullable List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean item) {
        TextView title = helper.getView(R.id.item_contentsSectionTitle);
        title.setText(item.getTitle());
        if (((CourseDetailsActivity)mContext).play == 1) {//有播放权限
            CircleImageView play = helper.getView(R.id.item_contents_section_play);//播放按钮
            play.setVisibility(View.VISIBLE);
            helper.getView(R.id.item_contents_section_see).setVisibility(View.GONE);
        } else if (item.getLook_at().equals("2")) {//如果可以观看 就显示可观看 点击跳转
            TextView see = helper.getView(R.id.item_contents_section_see);
            see.setVisibility(View.VISIBLE);//是否可观看
            helper.getView(R.id.item_contents_section_play).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.item_contents_section_lock).setVisibility(View.VISIBLE);//是否有小锁
            helper.getView(R.id.item_contents_section_see).setVisibility(View.GONE);//是否可观看
            helper.getView(R.id.item_contents_section_play).setVisibility(View.GONE);
        }

        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    //如果可以观看就跳转播放页面
                    if (item.getLook_at().equals("2") || ((CourseDetailsActivity)mContext).play == 1) {
//                        Intent it = new Intent(mContext, PlayVideoActivity.class);
//                        it.putExtra("userId", UriConstant.userId);
//                        it.putExtra("catalogId", mData.get(position).getId());
//                        it.putExtra("curriculumId", mData.get(position).getCurriculum_id());
//                        it.putExtra("title", mData.get(position).getTitle());
//                        mContext.startActivity(it);
                    } else {
                        UtilToast.showToast(mContext, "您未购买");
                    }
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }

            }
        });
    }
}