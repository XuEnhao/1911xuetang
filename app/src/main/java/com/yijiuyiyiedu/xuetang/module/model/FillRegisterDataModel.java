package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.lzy.okgo.OkGo;

import java.io.File;

/**
 * Created by xuenhao on 2018/3/16.
 */

public class FillRegisterDataModel {
    public FillRegisterDataModel() {

    }
//
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
//     * @param JsonCallback     回调
//     */
//    public void addCompanyCertMsg(String userId, String companyName, String companyTelephone, String companyAddress,
//                                  String contactName, String contactPhone, String numberEmployees, String pictureLogo, String pictureLicense, JsonCallback<LoginEntity> JsonCallback) {
//        OkGo.<LoginEntity>post(Constant.COMPANY_CERT)
//                .tag(this)
//                .params("user_id", userId)
//                .params("company_name", companyName)
//                .params("company_telephone", companyTelephone)
//                .params("company_address", companyAddress)
//                .params("contact_name", contactName)
//                .params("company_phone", contactPhone)
//                .params("number_employees", numberEmployees)
//                .params("picture_logo", pictureLogo)
//                .params("picture_license", pictureLicense)
//                .execute(JsonCallback);
//    }

    public void uploadImg(String fileName, String imagePath, JsonCallback<FillRegisterDataEntity> JsonCallback) {
        OkGo.<FillRegisterDataEntity>post(Constant.BUSINESS_UPLOAD)
                .tag(this)
                .isMultipart(true)
                .params("folderName", fileName)
                .params("FILES", new File(imagePath))
                .execute(JsonCallback);
    }
}
