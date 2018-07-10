package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.model.FillRegisterDataModel;
import com.yijiuyiyiedu.xuetang.module.view.FillRegisterDataView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/16.
 */

public class FillRegisterDataPresenter {

    FillRegisterDataModel model;
    FillRegisterDataView mview;

    public FillRegisterDataPresenter(FillRegisterDataView view) {
        mview = view;
        model = new FillRegisterDataModel();
    }

//    /**
//     * @param userId           用户id 必填
//     * @param companyName      公司名字 必填
//     * @param companyTelephone 公司电话
//     * @param companyAddress   公司地址
//     * @param contactName      联系人姓名
//     * @param contactPhone     联系人电话
//     * @param numberEmployees  公司员工人数
//     * @param pictureLogo      公司logo
//     * @param pictureLicense   营业执照  必填
//     */
//    public void addCompanyCertMessage(String userId, String companyName, String companyTelephone, String companyAddress, String contactName, String contactPhone, String numberEmployees, String pictureLogo, String pictureLicense) {
//        mview.showLoading();
//        model.addCompanyCertMsg(userId, companyName, companyTelephone, companyAddress, contactName, contactPhone, numberEmployees, pictureLogo, pictureLicense, new JsonCallback<LoginEntity>() {
//            @Override
//            public void onSuccess(Response<LoginEntity> response) {
//                mview.hideLoading();
//                mview.showData(response.body());
//            }
//        });
//    }

    public void uploadImage(String folderName, String path) {
        mview.showLoading();
        model.uploadImg(folderName, path, new JsonCallback<FillRegisterDataEntity>() {
            @Override
            public void onSuccess(Response<FillRegisterDataEntity> response) {
                mview.hideLoading();
                mview.uploadData(response.body());
            }
        });
    }

}
