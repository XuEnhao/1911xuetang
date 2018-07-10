package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/23.
 * 历史搜索adapter
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<SearchEntity.DataBean.UserSearchRecordBean, BaseViewHolder> {
    public SearchHistoryAdapter(int layoutResId, @Nullable List<SearchEntity.DataBean.UserSearchRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchEntity.DataBean.UserSearchRecordBean item) {
        helper.setText(R.id.item_search_history_text, item.getSearch_word());
//                item.getData().getUserSearchRecord()
    }
}
