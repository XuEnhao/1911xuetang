package com.yijiuyiyiedu.xuetang.db;

/**
 * Created by xuenhao on 2018/6/19.
 * 下载的bean
 */

public class DownloadEntity {

    int curriculumId;
    int chapterId;
    int catalogId;
    int sort;
    int status;
    String videoAddress;
    String title;
    String size;

    public DownloadEntity() {
    }

    public DownloadEntity(int curriculumId, int chapterId, int catalogId, int sort, int status, String videoAddress, String title, String size) {
        this.curriculumId = curriculumId;
        this.chapterId = chapterId;
        this.catalogId = catalogId;
        this.sort = sort;
        this.status = status;
        this.videoAddress = videoAddress;
        this.title = title;
        this.size = size;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
