package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/23.
 * 热门搜索adapter
 */

public class SearchHotAdapter extends BaseQuickAdapter<SearchEntity.DataBean.HotSearchRecordBean, BaseViewHolder> {
    public SearchHotAdapter(int layoutResId, @Nullable List<SearchEntity.DataBean.HotSearchRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchEntity.DataBean.HotSearchRecordBean item) {
        helper.setText(R.id.item_search_history_text, item.getSearch_word());
//                item.getData().getUserSearchRecord()
    }
}
