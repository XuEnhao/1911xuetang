package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/6/12.
 */

public class CategorySortEntity {
    String title;
    boolean check;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public CategorySortEntity(String title, boolean check) {
        this.title = title;
        this.check = check;
    }
}
