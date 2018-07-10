package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CityInfoEntity;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class EditMyselfInfoModel {

    /**
     * 保存编辑的个人信息
     *
     * @param head_img
     * @param sex
     * @param nick_name
     * @param callback
     */
    public void getSaveData(String head_img, String birthday,String sex, String nick_name, int province,int city,int area,int position ,String email,JsonCallback<MineEntity> callback) {

        OkGo.<MineEntity>post(Constant.SAVE_USER_INFO)
                .params("head_img", head_img)
                .params("sex", sex)
                .params("birthday", birthday)
                .params("nick_name", nick_name)
                .params("province", province)
                .params("city", city)
                .params("area", area)
                .params("position", position)
                .params("email", email)
                .execute(callback);
    }


    /**
     * 获取城市信息
     * @param callback
     */
    public void getCity(JsonCallback<CityInfoEntity> callback){
        OkGo.<CityInfoEntity>get(Constant.CITY_INFO)
                .execute(callback);
    }
}
