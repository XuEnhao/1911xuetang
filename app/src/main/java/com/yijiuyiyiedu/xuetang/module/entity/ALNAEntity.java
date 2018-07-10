package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/26.
 */

public class ALNAEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"FileURL":"https://in-20180322100648300-4frnd8qym4.oss-cn-shanghai.aliyuncs.com/video/328c7356-162b7e2de2f-0004-8270-90b-a8f44.mov?Expires=1524749064&OSSAccessKeyId=LTAIXduujLCSSEHS&Signature=z8pQHE/CzLtDxgktq/50J2aMmUQ="}
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
         * FileURL : https://in-20180322100648300-4frnd8qym4.oss-cn-shanghai.aliyuncs.com/video/328c7356-162b7e2de2f-0004-8270-90b-a8f44.mov?Expires=1524749064&OSSAccessKeyId=LTAIXduujLCSSEHS&Signature=z8pQHE/CzLtDxgktq/50J2aMmUQ=
         */

        private String FileURL;

        public String getFileURL() {
            return FileURL;
        }

        public void setFileURL(String FileURL) {
            this.FileURL = FileURL;
        }
    }
}
