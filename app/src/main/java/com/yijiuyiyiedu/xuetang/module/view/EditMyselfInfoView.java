package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.entity.CityInfoEntity;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;

/**
 * Created by xuenhao on 2018/3/26.
 */

public interface EditMyselfInfoView {
    void showSaveData(MineEntity data);
    void showCityData(CityInfoEntity data);
}
