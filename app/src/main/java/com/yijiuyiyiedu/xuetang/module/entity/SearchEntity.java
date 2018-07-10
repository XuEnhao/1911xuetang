package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class SearchEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"userSearchRecord":[{"id":"799","search_word":"抓住2"},{"id":"798","search_word":"抓住1"}],"hotSearchRecord":[{"id":"801","search_word":"抓住4"},{"id":"800","search_word":"抓住3"}]}
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
        private List<UserSearchRecordBean> userSearchRecord;
        private List<HotSearchRecordBean> hotSearchRecord;

        public List<UserSearchRecordBean> getUserSearchRecord() {
            return userSearchRecord;
        }

        public void setUserSearchRecord(List<UserSearchRecordBean> userSearchRecord) {
            this.userSearchRecord = userSearchRecord;
        }

        public List<HotSearchRecordBean> getHotSearchRecord() {
            return hotSearchRecord;
        }

        public void setHotSearchRecord(List<HotSearchRecordBean> hotSearchRecord) {
            this.hotSearchRecord = hotSearchRecord;
        }

        public static class UserSearchRecordBean {
            /**
             * id : 799
             * search_word : 抓住2
             */

            private String id;
            private String search_word;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSearch_word() {
                return search_word;
            }

            public void setSearch_word(String search_word) {
                this.search_word = search_word;
            }
        }

        public static class HotSearchRecordBean {
            /**
             * id : 801
             * search_word : 抓住4
             */

            private String id;
            private String search_word;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSearch_word() {
                return search_word;
            }

            public void setSearch_word(String search_word) {
                this.search_word = search_word;
            }
        }
    }
}
