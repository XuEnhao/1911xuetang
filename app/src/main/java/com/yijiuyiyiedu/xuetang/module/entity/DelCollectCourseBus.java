package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/6/13.
 */

public class DelCollectCourseBus {

    String curriculumId;

    public DelCollectCourseBus(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }
}
