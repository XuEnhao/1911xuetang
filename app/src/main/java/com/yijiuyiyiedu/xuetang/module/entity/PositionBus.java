package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/6/14.
 */

public class PositionBus {
    String id;
    String positionName;

    public PositionBus(String id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
