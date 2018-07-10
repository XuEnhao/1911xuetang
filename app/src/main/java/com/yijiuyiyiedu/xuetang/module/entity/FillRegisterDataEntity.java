package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/16.
 */

public class FillRegisterDataEntity {


    /**
     * status : 1
     * msg : u8d26u53f7u4e0du80fdu4e3au7a7a
     * data : {"file_name":"文件名","full_path":"文件路径"}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         * file_name : 文件名
         * full_path : 文件路径
         */

        private String file_name;
        private String full_path;

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFull_path() {
            return full_path;
        }

        public void setFull_path(String full_path) {
            this.full_path = full_path;
        }
    }
}
