package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/22.
 */

public class PlayVideoBus {

    String numberId;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PlayVideoBus(String numberId,int type) {
        this.numberId = numberId;
        this.type = type;
    }
    public PlayVideoBus(String numberId) {
        this.numberId = numberId;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }
}
