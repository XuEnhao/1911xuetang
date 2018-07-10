package com.yijiuyiyiedu.xuetang.module.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.DownloadMultAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassContentEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"curriculumCatalogList":[{"id":"4","curriculum_id":"2","title":"365天，让学习变成一种生活状态","parent_id":"0","look_at":"1","video_time":"","free_time":"0","video_number":"0","childList":[{"id":"5","curriculum_id":"2","title":"体会不功利学英语的自在","parent_id":"4","look_at":"2","video_time":"61","free_time":"5","video_number":"1"},{"id":"16","curriculum_id":"2","title":"遨游在英语的世界","parent_id":"4","look_at":"1","video_time":"183","free_time":"0","video_number":"2"}]}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        private List<CurriculumCatalogListBean> curriculumCatalogList;

        public List<CurriculumCatalogListBean> getCurriculumCatalogList() {
            return curriculumCatalogList;
        }

        public void setCurriculumCatalogList(List<CurriculumCatalogListBean> curriculumCatalogList) {
            this.curriculumCatalogList = curriculumCatalogList;
        }

        public  class CurriculumCatalogListBean extends AbstractExpandableItem<CurriculumCatalogListBean.ChildListBean> implements MultiItemEntity {
            /**
             * id : 4
             * curriculum_id : 2
             * title : 365天，让学习变成一种生活状态
             * parent_id : 0
             * look_at : 1
             * video_time :
             * free_time : 0
             * video_number : 0
             * childList : [{"id":"5","curriculum_id":"2","title":"体会不功利学英语的自在","parent_id":"4","look_at":"2","video_time":"61","free_time":"5","video_number":"1"},{"id":"16","curriculum_id":"2","title":"遨游在英语的世界","parent_id":"4","look_at":"1","video_time":"183","free_time":"0","video_number":"2"}]
             */

            private String id;
            private String curriculum_id;
            private String title;
            private String parent_id;
            private String look_at;
            private String video_time;
            private String free_time;
            private String video_number;
            private List<ChildListBean> childList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(String curriculum_id) {
                this.curriculum_id = curriculum_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getLook_at() {
                return look_at;
            }

            public void setLook_at(String look_at) {
                this.look_at = look_at;
            }

            public String getVideo_time() {
                return video_time;
            }

            public void setVideo_time(String video_time) {
                this.video_time = video_time;
            }

            public String getFree_time() {
                return free_time;
            }

            public void setFree_time(String free_time) {
                this.free_time = free_time;
            }

            public String getVideo_number() {
                return video_number;
            }

            public void setVideo_number(String video_number) {
                this.video_number = video_number;
            }

            public List<ChildListBean> getChildList() {
                return childList;
            }

            public void setChildList(List<ChildListBean> childList) {
                this.childList = childList;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getItemType() {
                return DownloadMultAdapter.TYPE_TITLE;
            }

            public  class ChildListBean implements MultiItemEntity,Serializable{
                /**
                 * id : 5
                 * curriculum_id : 2
                 * title : 体会不功利学英语的自在
                 * parent_id : 4
                 * look_at : 2
                 * video_time : 61
                 * free_time : 5
                 * video_number : 1
                 */

                private String id;
                private String curriculum_id;
                private String title;
                private String parent_id;
                private String look_at;
                private String video_time;
                private String free_time;
                private String video_number;
                private String color;
                private String size;
                private String video_address;
                private boolean check;

                public String getVideo_address() {
                    return video_address;
                }

                public void setVideo_address(String video_address) {
                    this.video_address = video_address;
                }

                public boolean isCheck() {
                    return check;
                }

                public void setCheck(boolean check) {
                    this.check = check;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCurriculum_id() {
                    return curriculum_id;
                }

                public void setCurriculum_id(String curriculum_id) {
                    this.curriculum_id = curriculum_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                public String getLook_at() {
                    return look_at;
                }

                public void setLook_at(String look_at) {
                    this.look_at = look_at;
                }

                public String getVideo_time() {
                    return video_time;
                }

                public void setVideo_time(String video_time) {
                    this.video_time = video_time;
                }

                public String getFree_time() {
                    return free_time;
                }

                public void setFree_time(String free_time) {
                    this.free_time = free_time;
                }

                public String getVideo_number() {
                    return video_number;
                }

                public void setVideo_number(String video_number) {
                    this.video_number = video_number;
                }

                @Override
                public int getItemType() {
                    return DownloadMultAdapter.TYPE_CHILD;
                }
            }
        }
    }
}
