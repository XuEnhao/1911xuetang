package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/29.
 */

public class StudyBus {
    String id;

    public StudyBus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudyBus{" +
                "id='" + id + '\'' +
                '}';
    }
}
