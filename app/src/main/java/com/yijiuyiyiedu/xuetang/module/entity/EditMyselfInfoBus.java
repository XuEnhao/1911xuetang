package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class EditMyselfInfoBus {

    String load;

    public EditMyselfInfoBus(String load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "EditMyselfInfoBus{" +
                "load='" + load + '\'' +
                '}';
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }
}
