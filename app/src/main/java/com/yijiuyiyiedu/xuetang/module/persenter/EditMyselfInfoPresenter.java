package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CityInfoEntity;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.yijiuyiyiedu.xuetang.module.model.EditMyselfInfoModel;
import com.yijiuyiyiedu.xuetang.module.view.EditMyselfInfoView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class EditMyselfInfoPresenter {
    EditMyselfInfoView mView;
    EditMyselfInfoModel model;

    public EditMyselfInfoPresenter(EditMyselfInfoView mView) {
        this.mView = mView;
        model = new EditMyselfInfoModel();
    }

    public void getSaveData(String head_img, String birthday,String sex, String nick_name, int province,int city,int area,int position ,String email) {
        model.getSaveData(head_img, birthday, sex, nick_name, province,city,area,position,email, new JsonCallback<MineEntity>() {
            @Override
            public void onSuccess(Response<MineEntity> response) {
                mView.showSaveData(response.body());
            }
        });
    }

    public void getCityInfo(){
        model.getCity(new JsonCallback<CityInfoEntity>() {
            @Override
            public void onSuccess(Response<CityInfoEntity> response) {
                mView.showCityData(response.body());
            }
        });
    }
}
