package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;

/**
 * Created by xuenhao on 2018/3/23.
 */

public interface EditSonAccountView extends BaseView<ClearHistoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ClearHistoryEntity data);

    void showDelData(ClearHistoryEntity data);
}
