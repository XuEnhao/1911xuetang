package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class EvaluateTextEntity {
    String name;
    boolean check;

    public EvaluateTextEntity(String name, boolean check) {
        this.name = name;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
