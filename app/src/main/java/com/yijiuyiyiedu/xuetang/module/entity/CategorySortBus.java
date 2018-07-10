package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/6/12.
 */

public class CategorySortBus {
    String position;

    public CategorySortBus(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
