package com.example.dllo.testdemo.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 */
public class NewsSerachBean {

    /**
     * code : 0
     * data : {"page":1,"totalCount":2,"data":[{"feedId":"206998","title":"［已结束］#幸运时氪#36氪微博粉丝破50万之际，氪星人只想道一句：感谢一路陪伴我们的你们","publishTime":1420524638000,"columnName":"其他","columnId":"72","featureImg":"http://a.36krcnd.com/photo/855aea08caea988a378ed2bede6657b3.jpg","user":{"avatar":"http://a.36krcnd.com/user/normal_avatar/53904.jpg","name":"痘痘姐","ssoId":1627305801}},{"feedId":"17072","title":"新浪微博的重大安全漏洞，可能导致微博帐号被轻易盗用","publishTime":1299543282000,"columnName":"其他","columnId":"72","featureImg":"http://static.36kr.com/wp-content/uploads/2011/03/IMG_16761-120x100.png","user":{"avatar":"http://9429127371.a.uxengine.net/avatar/04e36b8d5ce2c1a18ed97531e5498efb.png?s=48","name":"朱思维(NoTor)","ssoId":1780363967}}],"pageSize":30,"totalPages":1}
     * msg : 操作成功！
     */

    private int code;
    /**
     * page : 1
     * totalCount : 2
     * data : [{"feedId":"206998","title":"［已结束］#幸运时氪#36氪微博粉丝破50万之际，氪星人只想道一句：感谢一路陪伴我们的你们","publishTime":1420524638000,"columnName":"其他","columnId":"72","featureImg":"http://a.36krcnd.com/photo/855aea08caea988a378ed2bede6657b3.jpg","user":{"avatar":"http://a.36krcnd.com/user/normal_avatar/53904.jpg","name":"痘痘姐","ssoId":1627305801}},{"feedId":"17072","title":"新浪微博的重大安全漏洞，可能导致微博帐号被轻易盗用","publishTime":1299543282000,"columnName":"其他","columnId":"72","featureImg":"http://static.36kr.com/wp-content/uploads/2011/03/IMG_16761-120x100.png","user":{"avatar":"http://9429127371.a.uxengine.net/avatar/04e36b8d5ce2c1a18ed97531e5498efb.png?s=48","name":"朱思维(NoTor)","ssoId":1780363967}}]
     * pageSize : 30
     * totalPages : 1
     */

    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private int page;
        private int totalCount;
        private int pageSize;
        private int totalPages;
        /**
         * feedId : 206998
         * title : ［已结束］#幸运时氪#36氪微博粉丝破50万之际，氪星人只想道一句：感谢一路陪伴我们的你们
         * publishTime : 1420524638000
         * columnName : 其他
         * columnId : 72
         * featureImg : http://a.36krcnd.com/photo/855aea08caea988a378ed2bede6657b3.jpg
         * user : {"avatar":"http://a.36krcnd.com/user/normal_avatar/53904.jpg","name":"痘痘姐","ssoId":1627305801}
         */

        private List<DataBean1> data;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<DataBean1> getData() {
            return data;
        }

        public void setData(List<DataBean1> data) {
            this.data = data;
        }

        public static class DataBean1 {
            private String feedId;
            private String title;
            private long publishTime;
            private String columnName;
            private String columnId;
            private String featureImg;
            /**
             * avatar : http://a.36krcnd.com/user/normal_avatar/53904.jpg
             * name : 痘痘姐
             * ssoId : 1627305801
             */

            private UserBean user;

            public String getFeedId() {
                return feedId;
            }

            public void setFeedId(String feedId) {
                this.feedId = feedId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getColumnName() {
                return columnName;
            }

            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }

            public String getColumnId() {
                return columnId;
            }

            public void setColumnId(String columnId) {
                this.columnId = columnId;
            }

            public String getFeatureImg() {
                return featureImg;
            }

            public void setFeatureImg(String featureImg) {
                this.featureImg = featureImg;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                private String avatar;
                private String name;
                private int ssoId;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getSsoId() {
                    return ssoId;
                }

                public void setSsoId(int ssoId) {
                    this.ssoId = ssoId;
                }
            }
        }
    }
}
