package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/29.
 */

public class PlayVideoEntity {


    /**
     * status : 1
     * msg : 成功
     * data : {"curriculumCatalogDetail":{"id":"18","curriculum_id":"1","title":"第一小节","parent_id":"16","look_at":"2","video_address":"","video_pic":"/uploads/vedio/2018/03/26/1522070755.png","video_id":"ce0850d6bf2047f488661b1a0cefe6f2","video_time":"","voice_id":"","sort":"1","status":"1","create_time":"1522070749"},"playAuthInfo":{"RequestId":"9220EC6D-FFB6-47C1-AABB-AB6F58DC33A9","VideoMeta":{"CoverURL":"http://edu.1911thu.com//uploads/vedio/2018/03/26/1522070755.png","Status":"Normal","VideoId":"ce0850d6bf2047f488661b1a0cefe6f2","Duration":52.208999633789,"Title":"动物成长.mp4"},"PlayAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwWW43Q1lQempMdFk1cFd1TUJMUW9taythL2Q2aWJmcGhqejJJSHBOZTNocUIrMGZzUGt3bEdsVTZmZ2Nsck1xRWNFVUhoV1ZQWnNxdjg4SnFGajdKcExGc3QySjZyOEpqc1YybjVCcXhGcXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZkMzhtcE9aUXJ6eGFvWmdLZStxQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWMvRDJvZzZYTzFuK0ZQamNqUDVvajRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQxTUtpUXVranBzUWFnQUZTUk1mZjljUFp4azVhZWFUbWFjeXpvSHNkM3RwMXNQOUYzU0xRWmVnYnI1Wng5eXptNERyQnpxNitPZ3E5V3F5SVJjZzlXOUdRWC9TdkpRc3Z3UTBKK2RXZHpzSnZkRjRoT0RqcGtEQ2l2VTQ2RHZ2eXkrRlRlMUFncWFSeWUwSDloOWZFaTcrOHM1aSs2NmtvRWdTWXFBbENTZDZWUXRhTGg3QnNxTFRsV2c9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI1TkhuMDlzZUtDWXVSbXZjeDNnU1RNL3N6UG9mTWppeUc2MkdKR2xVcENzPVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxOC0wMy0yOVQxMzoyNDo1OFpcIixcIk1lZGlhSWRcIjpcImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyXCIsXCJTaWduYXR1cmVcIjpcIkx1QzViL05tUEw1cmFlcUUza1hZNXZTUy9mND1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6ImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyIiwiVGl0bGUiOiLliqjnianmiJDplb8ubXA0IiwiQ292ZXJVUkwiOiJodHRwOi8vZWR1LjE5MTF0aHUuY29tLy91cGxvYWRzL3ZlZGlvLzIwMTgvMDMvMjYvMTUyMjA3MDc1NS5wbmciLCJEdXJhdGlvbiI6NTIuMjA5fSwiQWNjZXNzS2V5SWQiOiJTVFMuREpOQjlHYWR5UVdFMjRhRmlrZ3hWZnJCZCIsIlBsYXlEb21haW4iOiJ0ZXN0dm9kLjE5MTF0aHUuY29tIiwiQWNjZXNzS2V5U2VjcmV0IjoiOEtjUXRoeEN0U05KRXJ2eWtzeE1TZ1RaUFRydHJiVXFpOHVZdHJYc2FOUGsiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjEyNjkzMTk4ODI5MzYxMzJ9"}}
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
        /**
         * curriculumCatalogDetail : {"id":"18","curriculum_id":"1","title":"第一小节","parent_id":"16","look_at":"2","video_address":"","video_pic":"/uploads/vedio/2018/03/26/1522070755.png","video_id":"ce0850d6bf2047f488661b1a0cefe6f2","video_time":"","voice_id":"","sort":"1","status":"1","create_time":"1522070749"}
         * playAuthInfo : {"RequestId":"9220EC6D-FFB6-47C1-AABB-AB6F58DC33A9","VideoMeta":{"CoverURL":"http://edu.1911thu.com//uploads/vedio/2018/03/26/1522070755.png","Status":"Normal","VideoId":"ce0850d6bf2047f488661b1a0cefe6f2","Duration":52.208999633789,"Title":"动物成长.mp4"},"PlayAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwWW43Q1lQempMdFk1cFd1TUJMUW9taythL2Q2aWJmcGhqejJJSHBOZTNocUIrMGZzUGt3bEdsVTZmZ2Nsck1xRWNFVUhoV1ZQWnNxdjg4SnFGajdKcExGc3QySjZyOEpqc1YybjVCcXhGcXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZkMzhtcE9aUXJ6eGFvWmdLZStxQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWMvRDJvZzZYTzFuK0ZQamNqUDVvajRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQxTUtpUXVranBzUWFnQUZTUk1mZjljUFp4azVhZWFUbWFjeXpvSHNkM3RwMXNQOUYzU0xRWmVnYnI1Wng5eXptNERyQnpxNitPZ3E5V3F5SVJjZzlXOUdRWC9TdkpRc3Z3UTBKK2RXZHpzSnZkRjRoT0RqcGtEQ2l2VTQ2RHZ2eXkrRlRlMUFncWFSeWUwSDloOWZFaTcrOHM1aSs2NmtvRWdTWXFBbENTZDZWUXRhTGg3QnNxTFRsV2c9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI1TkhuMDlzZUtDWXVSbXZjeDNnU1RNL3N6UG9mTWppeUc2MkdKR2xVcENzPVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxOC0wMy0yOVQxMzoyNDo1OFpcIixcIk1lZGlhSWRcIjpcImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyXCIsXCJTaWduYXR1cmVcIjpcIkx1QzViL05tUEw1cmFlcUUza1hZNXZTUy9mND1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6ImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyIiwiVGl0bGUiOiLliqjnianmiJDplb8ubXA0IiwiQ292ZXJVUkwiOiJodHRwOi8vZWR1LjE5MTF0aHUuY29tLy91cGxvYWRzL3ZlZGlvLzIwMTgvMDMvMjYvMTUyMjA3MDc1NS5wbmciLCJEdXJhdGlvbiI6NTIuMjA5fSwiQWNjZXNzS2V5SWQiOiJTVFMuREpOQjlHYWR5UVdFMjRhRmlrZ3hWZnJCZCIsIlBsYXlEb21haW4iOiJ0ZXN0dm9kLjE5MTF0aHUuY29tIiwiQWNjZXNzS2V5U2VjcmV0IjoiOEtjUXRoeEN0U05KRXJ2eWtzeE1TZ1RaUFRydHJiVXFpOHVZdHJYc2FOUGsiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjEyNjkzMTk4ODI5MzYxMzJ9"}
         */

        private CurriculumCatalogDetailBean curriculumCatalogDetail;
        private PlayAuthInfoBean playAuthInfo;

        public CurriculumCatalogDetailBean getCurriculumCatalogDetail() {
            return curriculumCatalogDetail;
        }

        public void setCurriculumCatalogDetail(CurriculumCatalogDetailBean curriculumCatalogDetail) {
            this.curriculumCatalogDetail = curriculumCatalogDetail;
        }

        public PlayAuthInfoBean getPlayAuthInfo() {
            return playAuthInfo;
        }

        public void setPlayAuthInfo(PlayAuthInfoBean playAuthInfo) {
            this.playAuthInfo = playAuthInfo;
        }

        public static class CurriculumCatalogDetailBean {
            /**
             * id : 18
             * curriculum_id : 1
             * title : 第一小节
             * parent_id : 16
             * look_at : 2
             * video_address :
             * video_pic : /uploads/vedio/2018/03/26/1522070755.png
             * video_id : ce0850d6bf2047f488661b1a0cefe6f2
             * video_time :
             * voice_id :
             * sort : 1
             * status : 1
             * create_time : 1522070749
             */

            private String id;
            private String curriculum_id;
            private String title;
            private String parent_id;
            private String look_at;
            private String video_address;
            private String video_pic;
            private String video_id;
            private String video_time;
            private String voice_id;
            private String sort;
            private String status;
            private String create_time;

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

            public String getVideo_address() {
                return video_address;
            }

            public void setVideo_address(String video_address) {
                this.video_address = video_address;
            }

            public String getVideo_pic() {
                return video_pic;
            }

            public void setVideo_pic(String video_pic) {
                this.video_pic = video_pic;
            }

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public String getVideo_time() {
                return video_time;
            }

            public void setVideo_time(String video_time) {
                this.video_time = video_time;
            }

            public String getVoice_id() {
                return voice_id;
            }

            public void setVoice_id(String voice_id) {
                this.voice_id = voice_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class PlayAuthInfoBean {
            /**
             * RequestId : 9220EC6D-FFB6-47C1-AABB-AB6F58DC33A9
             * VideoMeta : {"CoverURL":"http://edu.1911thu.com//uploads/vedio/2018/03/26/1522070755.png","Status":"Normal","VideoId":"ce0850d6bf2047f488661b1a0cefe6f2","Duration":52.208999633789,"Title":"动物成长.mp4"}
             * PlayAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklwWW43Q1lQempMdFk1cFd1TUJMUW9taythL2Q2aWJmcGhqejJJSHBOZTNocUIrMGZzUGt3bEdsVTZmZ2Nsck1xRWNFVUhoV1ZQWnNxdjg4SnFGajdKcExGc3QySjZyOEpqc1YybjVCcXhGcXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0ZkMzhtcE9aUXJ6eGFvWmdLZStxQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQWMvRDJvZzZYTzFuK0ZQamNqUDVvajRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQxTUtpUXVranBzUWFnQUZTUk1mZjljUFp4azVhZWFUbWFjeXpvSHNkM3RwMXNQOUYzU0xRWmVnYnI1Wng5eXptNERyQnpxNitPZ3E5V3F5SVJjZzlXOUdRWC9TdkpRc3Z3UTBKK2RXZHpzSnZkRjRoT0RqcGtEQ2l2VTQ2RHZ2eXkrRlRlMUFncWFSeWUwSDloOWZFaTcrOHM1aSs2NmtvRWdTWXFBbENTZDZWUXRhTGg3QnNxTFRsV2c9PSIsIkF1dGhJbmZvIjoie1wiQ2FsbGVyXCI6XCI1TkhuMDlzZUtDWXVSbXZjeDNnU1RNL3N6UG9mTWppeUc2MkdKR2xVcENzPVxcclxcblwiLFwiRXhwaXJlVGltZVwiOlwiMjAxOC0wMy0yOVQxMzoyNDo1OFpcIixcIk1lZGlhSWRcIjpcImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyXCIsXCJTaWduYXR1cmVcIjpcIkx1QzViL05tUEw1cmFlcUUza1hZNXZTUy9mND1cIn0iLCJWaWRlb01ldGEiOnsiU3RhdHVzIjoiTm9ybWFsIiwiVmlkZW9JZCI6ImNlMDg1MGQ2YmYyMDQ3ZjQ4ODY2MWIxYTBjZWZlNmYyIiwiVGl0bGUiOiLliqjnianmiJDplb8ubXA0IiwiQ292ZXJVUkwiOiJodHRwOi8vZWR1LjE5MTF0aHUuY29tLy91cGxvYWRzL3ZlZGlvLzIwMTgvMDMvMjYvMTUyMjA3MDc1NS5wbmciLCJEdXJhdGlvbiI6NTIuMjA5fSwiQWNjZXNzS2V5SWQiOiJTVFMuREpOQjlHYWR5UVdFMjRhRmlrZ3hWZnJCZCIsIlBsYXlEb21haW4iOiJ0ZXN0dm9kLjE5MTF0aHUuY29tIiwiQWNjZXNzS2V5U2VjcmV0IjoiOEtjUXRoeEN0U05KRXJ2eWtzeE1TZ1RaUFRydHJiVXFpOHVZdHJYc2FOUGsiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjEyNjkzMTk4ODI5MzYxMzJ9
             */

            private String RequestId;
            private VideoMetaBean VideoMeta;
            private String PlayAuth;

            public String getRequestId() {
                return RequestId;
            }

            public void setRequestId(String RequestId) {
                this.RequestId = RequestId;
            }

            public VideoMetaBean getVideoMeta() {
                return VideoMeta;
            }

            public void setVideoMeta(VideoMetaBean VideoMeta) {
                this.VideoMeta = VideoMeta;
            }

            public String getPlayAuth() {
                return PlayAuth;
            }

            public void setPlayAuth(String PlayAuth) {
                this.PlayAuth = PlayAuth;
            }

            public static class VideoMetaBean {
                /**
                 * CoverURL : http://edu.1911thu.com//uploads/vedio/2018/03/26/1522070755.png
                 * Status : Normal
                 * VideoId : ce0850d6bf2047f488661b1a0cefe6f2
                 * Duration : 52.208999633789
                 * Title : 动物成长.mp4
                 */

                private String CoverURL;
                private String Status;
                private String VideoId;
                private double Duration;
                private String Title;

                public String getCoverURL() {
                    return CoverURL;
                }

                public void setCoverURL(String CoverURL) {
                    this.CoverURL = CoverURL;
                }

                public String getStatus() {
                    return Status;
                }

                public void setStatus(String Status) {
                    this.Status = Status;
                }

                public String getVideoId() {
                    return VideoId;
                }

                public void setVideoId(String VideoId) {
                    this.VideoId = VideoId;
                }

                public double getDuration() {
                    return Duration;
                }

                public void setDuration(double Duration) {
                    this.Duration = Duration;
                }

                public String getTitle() {
                    return Title;
                }

                public void setTitle(String Title) {
                    this.Title = Title;
                }
            }
        }
    }
}
