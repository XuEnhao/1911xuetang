package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;

/**
 * Created by xuenhao on 2018/3/26.
 */

public interface CollectView extends BaseView<ClearHistoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ClearHistoryEntity data);

    void showCollectData(CollectEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
