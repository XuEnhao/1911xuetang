package com.yijiuyiyiedu.xuetang.module.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xuenhao on 2018/4/16.
 */

public class WXPayEntity {

    /**
     * status : 1
     * msg : 调取成功
     * data : {"appId":"wx1e93e301d703d535","nonceStr":"5yg8sazsof1232pcba8yutlrzkix4nhj","package":"prepay_id=wx161431079411695243889ce34089791501","signType":"MD5","timeStamp":"1523860267","paySign":"6ADECC0C19A7BFCEC13BFF33AA17F17A"}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : wx1e93e301d703d535
         * nonceStr : 5yg8sazsof1232pcba8yutlrzkix4nhj
         * package : prepay_id=wx161431079411695243889ce34089791501
         * signType : MD5
         * timeStamp : 1523860267
         * paySign : 6ADECC0C19A7BFCEC13BFF33AA17F17A
         */

        private String appid;
        private String mch_id;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String timestamp;
        private String sign;
        private String partnerid;
        private String prepayid;

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }


        public String getPrepay_id() {
            return prepayid;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepayid = prepayid;
        }

        public String getAppId() {
            return appid;
        }

        public void setAppId(String appid) {
            this.appid = appid;
        }

        public String getNonce_str() {
            return noncestr;
        }

        public void setNonceStr(String nonceStr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }


        public String getTimeStamp() {
            return timestamp;
        }

        public void setTimeStamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String paySign) {
            this.sign = sign;
        }
    }
}
