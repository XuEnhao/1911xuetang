package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateEntity;

/**
 * Created by xuenhao on 2018/4/8.
 * 添加评价接口
 */

public interface AddEvaluateView extends BaseView<AddEvaluateEntity> {
    /**
     * 显示
     *
     * @param data
     */
    void showAddEvaluateData(AddEvaluateEntity data);
}
